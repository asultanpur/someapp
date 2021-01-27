(ns someapp.web-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [someapp.web :refer :all]))

(deftest test-app
  (testing "main route"
    (let [response (application (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (.contains (:body response) "SAY SOMETHING"))))

  (testing "not-found route"
    (let [response (application (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))
