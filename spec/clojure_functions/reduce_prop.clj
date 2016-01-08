(ns clojure-functions.reduce-prop
  (:require [clojure-functions.coll-generators :refer :all]
            [clojure-functions.reduce :refer :all]
            [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]))

(defn red-fn 
    ([] true)
    ([a b] b))

(defspec my-reduce-property-test 20
    (prop/for-all [c colls v gen/any]
        (and 
            (= (reduce red-fn c) (my-reduce red-fn c))
            (= (reduce red-fn v c) (my-reduce red-fn v c)))))