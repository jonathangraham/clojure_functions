(ns clojure-functions.filter-prop
  (:require [clojure-functions.coll-generators :refer :all]
            [clojure-functions.filter :refer :all]
            [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]))

(defn bool-fn [b _] b)

(defspec my-filter-property-test 20
    (prop/for-all [c colls b gen/boolean]
        (= (filter #(bool-fn b %) c) (my-filter #(bool-fn b %) c))))