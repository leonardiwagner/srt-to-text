(ns srt-to-text.parser
  (:require [srt-to-text.text-cleaner :as text-cleaner]))

(defn parse-new-item [coll line]
  (if (empty? coll)
    (conj coll {:index line})
    (conj (pop coll) {:index line})))

(defn cleanup-text [text options]
  (if (= true (:has-to-cleanup-text options))
    (text-cleaner/clean text)
    text))

(defn parse-item-text [coll line item options]
  (if (not (contains? item :text))
    (conj (pop coll) (assoc item :text (cleanup-text line options)))
    (if (= (count line) 0)
      (conj coll {})
      (conj
        (pop coll)
        (assoc item :text (str (:text item) " " (cleanup-text line options)))))))


(defn parse-item [coll line item options]
  (if (not (contains? item :time))
    (conj (pop coll) (assoc item :time line))
    (parse-item-text coll line item options)))

(defn parse [lines options]
  (reduce
    (fn [coll x]
      (let [item (last coll)]
        (if (not (contains? item :index))
          (parse-new-item coll x)
          (parse-item coll x item options))))
    [] lines))

