(ns clojure-functions.map)

(declare my-map)

(defn reorder [c]
        (when (every? seq c)
           	(cons (my-map first c) (reorder (my-map rest c)))))

(defn my-map 
	([f coll]
		(lazy-seq (when (seq coll)
				(cons (f (first coll)) (my-map f (rest coll))))))
	([f c1 & colls]
     	(my-map #(apply f %) (reorder (cons c1 colls)))))