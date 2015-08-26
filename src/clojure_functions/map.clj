(ns clojure-functions.map)

(defn my-map 
	([f coll]
		(lazy-seq (when (seq coll)
				(cons (f (first coll)) (my-map f (rest coll))))))
	([f c1 c2]
		(lazy-seq (when (and (seq c1) (seq c2))
				(cons (f (first c1) (first c2)) (my-map f (rest c1) (rest c2))))))
	([f c1 c2 & more]
		(loop [c1 c1 c2 c2 r more]
			(if (empty? r)
				(my-map f c1 c2)
				(recur (my-map f c1 c2) (first r) (rest r))))))