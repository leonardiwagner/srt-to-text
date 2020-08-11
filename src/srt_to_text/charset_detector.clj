(ns srt-to-text.charset-detector
  (:import [org.apache.tika.parser.txt CharsetDetector]))

(defn detect [path]
  (let [input (clojure.java.io/input-stream path)
        detector (CharsetDetector.)]
    (do
      (.setText detector input)
      (.getName (.detect detector)))))