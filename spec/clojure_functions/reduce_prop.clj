(ns clojure-functions.reduce-prop
  (:require [clojure-functions.coll-generators :refer :all]
            [clojure-functions.reduce :refer :all]
            [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]))

(defn red-fn 
    ([] true)
    ([a b] b))

(def my-reduce-property
    (prop/for-all [c colls v gen/any]
        (is (= (reduce red-fn c) (my-reduce red-fn c)))
        (is (= (reduce red-fn v c) (my-reduce red-fn v c)))))

(defspec my-reduce-property-test 50 my-reduce-property)