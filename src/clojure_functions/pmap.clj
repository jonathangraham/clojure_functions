(ns clojure-functions.pmap
	(:require [clojure-functions.map :refer :all]
		[clojure-functions.reduce :refer :all]))

(defn my-pmap
	([f coll]
		(let [results (my-reduce #(conj %1 (future (f %2))) [] coll)]
			(my-map deref results)))
	([f c1 & colls]
     	(my-pmap #(apply f %) (reorder (cons c1 colls)))))