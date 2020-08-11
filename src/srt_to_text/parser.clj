(ns srt-to-text.parser)

(defn parse-new-item [coll line]
  (if (empty? coll)
    (conj coll {:index line})
    (conj (pop coll) {:index line})))

(defn parse-item-text [coll x item]
  (if (not (contains? item :text))
    (conj (pop coll) (assoc item :text x))
    (if (= (count x) 0)
      (conj coll {})
      (conj (pop coll) (assoc item :text (str (:text item) " " x))))))

(defn parse-item [coll x item]
  (if (not (contains? item :time))
    (conj (pop coll) (assoc item :time x))
    (parse-item-text coll x item)))

(defn parse [lines]
  (reduce
    (fn [coll x]
      (let [item (last coll)]
        (if (not (contains? item :index))
          (parse-new-item coll x)
          (parse-item coll x item))))
    [] lines))

