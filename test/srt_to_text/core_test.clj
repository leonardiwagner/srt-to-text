(ns srt-to-text.core-test
  (:require [clojure.test :refer :all]
            [srt-to-text.core :as srt-to-text]))

(def srt-file-path "./resources/1.srt")
(def srt-file-2-path "./resources/2.srt")

(deftest should-parse-file-items
  (let [result (srt-to-text/parse-file srt-file-path)]
    (is (= 3 (count result)))))

(deftest should-parse-file-item
  (let [result (srt-to-text/parse-file srt-file-path)
        item (first result)]
    (do
      (is (= "1" (:index item)))
      (is (= "00:00:53,900 --> 00:00:58,900" (:time item)))
      (is (= "Marocci ���~~ Visca el Bar�a ~~���" (:text item))))))

(deftest should-get-parsed-text
  (let [parsed-text (srt-to-text/parse-file srt-file-path)
        result (srt-to-text/get-parsed-text parsed-text)]
    (is (= result "Marocci ���~~ Visca el Bar�a ~~��� Toen de jongen werd geboren... werd hij ge�nspecteerd, zoals oude Spartanen."))))

(deftest should-cleanup-text
  (let [parsed-text (srt-to-text/parse-file srt-file-2-path {:has-to-cleanup-text true})
        result (srt-to-text/get-parsed-text parsed-text)]
    (is (= result "Precies drie jaar geleden beloofde ik jullie een president die jullie pijn voelt en jullie dromen deelt Ik wil het met jullie hebben over een fundamentele bedreiging"))))
