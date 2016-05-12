(ns clojure-functions.reduce)

(defn my-reduce 
		([f coll]
			(if (seq coll)
		 		(my-reduce f (first coll) (rest coll))
		 		(f)))
		([f val coll]
			(if (seq coll)
				(recur f (f val (first coll)) (rest coll))
				val)))