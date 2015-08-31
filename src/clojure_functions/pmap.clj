(ns clojure-functions.pmap
	(:require [clojure-functions.map :refer :all]))

(defn my-pmap
	([f coll]
		(let [results
			(loop [rets coll acc '()]
				(if rets
					(recur (next rets) (cons (future (f (first rets))) acc))
					(reverse acc)))]
			(my-map deref results)))
	([f c1 c2]
		(let [results
			(loop [r1 c1 r2 c2 acc '()]
				(if (and r1 r2)
					(recur (next r1) (next r2) (cons (future (f (first r1) (first r2))) acc))
					(reverse acc)))]
			(my-map deref results)))
	([f c1 c2 & more]
				(my-pmap f (my-pmap f c1 c2) (first more)))) 