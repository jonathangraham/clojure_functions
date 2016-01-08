(ns clojure-functions.count-prop
  (:require [clojure-functions.coll-generators :refer :all]
            [clojure-functions.count :refer :all]
            [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.test.check.properties :as prop]))

(defspec my-count-property-test 20
  (prop/for-all [c colls]
    (= (count c) (my-count c))))