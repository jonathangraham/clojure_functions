(ns clojure-functions.filter
	(:require [clojure-functions.reduce :refer :all]))

(defn my-filter [pred coll]
		(lazy-seq (when (seq coll)
			(if (pred (first coll))
				(cons (first coll) (my-filter pred (rest coll))) 
				(my-filter pred (rest coll))))))