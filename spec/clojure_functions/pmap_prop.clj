(ns clojure-functions.pmap-prop
  (:require [clojure-functions.pmap :refer :all]
            [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]))

(def colls
     ; (gen/such-that #(< 1 (count %)) (gen/vector gen/any)))
    ; (gen/such-that #(< 1 (count %))
    (gen/one-of
    [(gen/such-that #(< 1 (count %))(gen/vector gen/any)) 
    (gen/such-that #(< 1 (count %))(gen/list gen/any)) 
    (gen/such-that #(< 1 (count %))(gen/set gen/any)) 
    (gen/such-that #(< 1 (count %))(gen/map gen/keyword gen/any)) 
    (gen/such-that #(< 1 (count %))gen/bytes) 
    (gen/such-that #(< 1 (count %))gen/string)
    (gen/such-that #(< 1 (count %))(gen/tuple gen/any gen/any))
    (gen/such-that #(< 1 (count %))(gen/tuple gen/any gen/any gen/any))
    ]))

(defn long-running-job 
    ([& args]
        (Thread/sleep 10)
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
    (prop/for-all [c colls c1 colls c2 colls c3 colls c4 colls]
        (is (= (pmap list c) (my-pmap list c)))
        (is (= (pmap list c c1) (my-pmap list c c1)))
        (is (= (pmap list c c1 c2) (my-pmap list c c1 c2)))
        (is (= (pmap list c c1 c2 c3 c4) (my-pmap list c c1 c2 c3 c4)))))

(def my-pmap-time-property
    (prop/for-all [c colls c1 colls c2 colls c3 colls c4 colls]
        (is (> (test-time map long-running-job c) (test-time my-pmap long-running-job c)))
        (is (> (test-time map long-running-job c c1) (test-time my-pmap long-running-job c c1)))
        (is (> (test-time map long-running-job c c1 c2) (test-time my-pmap long-running-job c c1 c2)))
        (is (> (test-time map long-running-job c c1 c2 c3 c4) (test-time my-pmap long-running-job c c1 c2 c3 c4)))))

(defspec my-pmap-property-test 50 my-pmap-property)
(defspec my-pmap-time-property-test 50 my-pmap-time-property)