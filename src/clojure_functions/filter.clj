(ns clojure-functions.filter)

(defn my-filter [function coll]
	(loop [input coll filtered []]
		(if (empty? input)
			filtered
			(recur (rest input) (if (function (first input))
									(conj filtered (first input)) 
									filtered)))))