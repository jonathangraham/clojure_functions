(ns clojure-functions.pmap-prop
  (:require [clojure-functions.coll-generators :refer :all]
            [clojure-functions.pmap :refer :all]
            [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]))


(defn long-running-job 
    ([& args]
        (Thread/sleep 50)
        (apply list args)))

(defn realize-lazy-seq 
    ([map-type f & args]
        (loop [res (apply map-type f args)]
            (when res
                (recur (next res))))))

(defn test-time 
    ([map-type f & coll]
        (let [st (System/nanoTime)]
            (apply realize-lazy-seq map-type f coll)
            (/ (- (System/nanoTime) st) 1e9))))


(def my-pmap-property
    (prop/for-all [c colls-more-one-element]
        (is (= (pmap list c) (my-pmap list c)))
        (is (= (pmap list c c) (my-pmap list c c)))
        (is (= (pmap list c c c) (my-pmap list c c c)))
        (is (= (pmap list c c c c c) (my-pmap list c c c c c)))))

(def my-pmap-time-property
    (prop/for-all [c colls-more-one-element]
        (is (> (test-time map long-running-job c) (test-time my-pmap long-running-job c)))
        (is (> (test-time map long-running-job c c) (test-time my-pmap long-running-job c c)))
        (is (> (test-time map long-running-job c c c) (test-time my-pmap long-running-job c c c)))
        (is (> (test-time map long-running-job c c c c c) (test-time my-pmap long-running-job c c c c c)))))

(defspec my-pmap-property-test 50 my-pmap-property)
(defspec my-pmap-time-property-test 50 my-pmap-time-property)