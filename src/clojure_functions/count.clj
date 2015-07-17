(ns clojure-functions.count)


(defn my-count [coll]
	(reduce (fn [result element] (inc result)) 0 coll))
