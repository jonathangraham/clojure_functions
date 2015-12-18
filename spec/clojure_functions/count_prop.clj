(ns clojure-functions.count-prop
  (:require [clojure-functions.coll-generators :refer :all]
            [clojure-functions.count :refer :all]
            [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]))


(def my-count-prop
  (prop/for-all [c colls]
    (is (= (count c) (my-count c)))))

(defspec my-count-property-test 50 my-count-prop)