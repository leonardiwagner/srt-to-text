(ns srt-to-text.text-cleaner
  (:require [clojure.string :as string]))

(defn clean [text]
  (let [text-without-spaces (string/trim text)
        text-without-hyphen (string/replace text-without-spaces #"^-" "")
        text-without-tags (string/replace text-without-hyphen #"<[^>]*>" "")
        cleaned-text (string/replace text-without-tags  #"[!|.|,|\"|?|:]" "")
        text-without-extra-spaces (string/replace cleaned-text #"\s\s+" " ")]
    (string/trim text-without-extra-spaces)))