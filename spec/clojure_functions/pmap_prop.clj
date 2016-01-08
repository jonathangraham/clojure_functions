(ns clojure-functions.pmap-prop
  (:require [clojure-functions.coll-generators :refer :all]
            [clojure-functions.pmap :refer :all]
            [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]))

(defn long-running-job 
    ([& args]
        (Thread/sleep 250)
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

(defspec my-pmap-property-test 20
    (prop/for-all [c (gen/not-empty (gen/vector colls))]
        (= (apply pmap list (concat c)) (apply my-pmap list (concat c)))))

(defspec my-pmap-time-property-test 20
    (prop/for-all [c (gen/not-empty (gen/vector colls-more-one-element))]
        (> (apply test-time map long-running-job (concat c)) (apply test-time my-pmap long-running-job (concat c)))))