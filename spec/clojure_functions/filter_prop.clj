(ns clojure-functions.filter-prop
  (:require [clojure-functions.filter :refer :all]
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

(defn bool_fn [b _] b)

(def my-filter-property
    (prop/for-all [c colls b gen/boolean]
        (is (= (filter #(bool_fn b %) c) (my-filter #(bool_fn b %) c)))))

(defspec my-filter-property-test 50 my-filter-property)