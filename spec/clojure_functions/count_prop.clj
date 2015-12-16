(ns clojure-functions.count-prop
  (:require [clojure-functions.count :refer :all]
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

(def my-count-prop
  (prop/for-all [c colls]
    (is (= (count c) (my-count c)))))

(defspec my-count-property-test 50 my-count-prop)