(ns clojure-functions.pmap
	(:require [clojure-functions.map :refer :all]))

(defn my-pmap
	([f coll]
		(let [results
			(loop [remaining coll acc []]
				(if (seq remaining)
					(recur (rest remaining) (conj acc (future (f (first remaining)))))
					acc))]
			(my-map deref results)))
	([f c1 & colls]
     	(my-pmap #(apply f %) (reorder (cons c1 colls)))))