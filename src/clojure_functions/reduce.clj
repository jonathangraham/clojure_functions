(ns clojure-functions.reduce)

(defn my-reduce 
	([f coll]
		(if (empty? coll)
			(f)
			(my-reduce f (first coll) (rest coll))))
	([f result coll]
		(if (empty? coll)
			result
			(my-reduce f (f result (first coll)) (rest coll)))))