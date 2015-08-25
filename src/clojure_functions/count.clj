(ns clojure-functions.count
	(:require [clojure-functions.reduce :refer :all]))


(defn my-count [coll]
	(my-reduce (fn [result _] (inc result)) 0 coll))