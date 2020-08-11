(ns srt-to-text.core
  (:require [srt-to-text.parser :as parser]
            [srt-to-text.charset-detector :as charset-detector]))


(defn parse-lines [lines options]
  (parser/parse lines options))

(defn parse-file
  ([path]
   (parse-file path {}))
  ([path options]
   (let [charset (charset-detector/detect path)]
     (with-open [reader (clojure.java.io/reader path :encoding charset)]
       (parser/parse (line-seq reader) options)))))

(defn get-parsed-text [parsed-lines]
  (reduce
    (fn [text-result item]
      (str
        text-result
        (if (empty? text-result) "" " ")
        (:text item)))
    "" parsed-lines))