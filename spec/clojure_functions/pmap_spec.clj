(ns clojure-functions.pmap-spec
  (:require [speclj.core :refer :all]
            [clojure-functions.pmap :refer :all]
            [clojure-functions.map :refer :all]))

(defn long-running-job 
	([& args]
    	(Thread/sleep 1000)
    	(apply + 10 args)))

(defn realize-lazy-seq 
	([map-type f & args]
		(loop [res (apply map-type f args)]
    		(when res
       			(recur (next res))))))

(defn test-time 
	([map-type f & coll]
		(let [st (System/nanoTime)]
			(apply realize-lazy-seq map-type f coll)
			(/ (- (System/nanoTime) st) 1e9))))


(describe "test my-pmap function"

	(it "result as map"
		(should= (map inc [1 2]) (my-pmap inc [1 2])))

	(it "test long-running-job function"
		(should= '(11 12) (my-pmap long-running-job [1 2])))

	(it "test long-running-job time"
		(should (< 1.0 (let [st (System/nanoTime)]
							(long-running-job 1)
							(/ (- (System/nanoTime) st) 1e9)))))

	(it "test time long-running job with map"
		(should (< 4.0 (test-time map long-running-job [1 2 3 4]))))

	(it "test time long-running job with my-pmap"
		(should (> 1.1 (test-time my-pmap long-running-job [1 2 3 4]))))

	(it "maps two vectors"
		(should= '(4 6) (my-pmap + [1 2] [3 4])))

	(it "maps two vectors"
		(should= '(14 16) (my-pmap long-running-job [1 2] [3 4])))

	(it "test time long-running job with my-pmap and two collections"
		(should (> 1.1 (time (test-time my-pmap long-running-job [1 2 3 4][1 2 3 4])))))

	(it "stops when one list completed"
		(should= '(6 16) (my-pmap #(* 2 %1 %2) [1 2] [3 4 5 6])))

	(it "maps three lists"
		(should= '(9 12) (my-pmap + '(1 2) '(3 4) '(5 6))))

	(it "maps four vectors"
		(should= '(16 20) (my-map + [1 2] [3 4] [5 6] [7 8])))

	(it "test time long-running job with my-pmap and four collections"
		(should (> 2.1 (time (test-time my-pmap long-running-job [1 2][3 4][5 6][7 8]))))))
