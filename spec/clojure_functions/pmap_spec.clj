(ns clojure-functions.pmap-spec
  (:require [speclj.core :refer :all]
            [clojure-functions.pmap :refer :all]
            [clojure-functions.map :refer :all]))

(describe "test my-pmap function"

	(it "result as map"
		(should= (my-map inc [1 2]) (my-pmap inc [1 2]))
		(should= '(2 3) (my-pmap inc [1 2])))

	(it "test long-running-job function"
		(should= '(11 12) (my-pmap long-running-job [1 2])))

	(it "test time for pmap"
		(should (> 1.5 (time (run-myfunc pmap [1 2 3 4])))))

	(it "test time for my-pmap"
		(should (> 1.5 (time (run-myfunc my-pmap [1 2 3 4]))))
		(should= '(11 12 13 14) (my-pmap long-running-job [1 2 3 4])))

	(it "test time for my-pmap"
		(should (> 1.5 (time (run-myfunc my-pmap (into [] (range 1000)))))))

	(it "maps two vectors"
		(should= '(4 6) (my-pmap + [1 2] [3 4])))

	(it "maps three vectors"
		(should= '(4 6) (my-pmap + [1 2] [3 4] [0 0])))

	(it "time maps three vectors"
		(should (> 4 (time (run-myfunc my-pmap [1 2 3 4] [5 6 7 8] [9 10 11 12]))))))
