(ns clojure-functions.reduce-prop
  (:require [clojure-functions.reduce :refer :all]
            [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]))

(def colls
    (gen/one-of 
    [(gen/vector gen/any) 
    (gen/list gen/any) 
    (gen/set gen/any) 
    (gen/map gen/keyword gen/any) 
    gen/bytes 
    gen/string
    (gen/tuple gen/any gen/any)
    (gen/tuple gen/any gen/any gen/any)
    (gen/hash-map gen/keyword gen/any gen/keyword gen/any)]))

(defn red_fn 
    ([] true)
    ([a b] b))

(def my-reduce-property
    (prop/for-all [c colls v gen/any]
        (is (= (reduce red_fn c) (my-reduce red_fn c)))
        (is (= (reduce red_fn v c) (my-reduce red_fn v c)))))

(defspec my-reduce-property-test 50 my-reduce-property)