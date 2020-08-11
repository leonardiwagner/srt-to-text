(defproject srt-to-text "0.1.0-SNAPSHOT"
  :description "Parse subtitle files"
  :url "https://github.com/leonardiwagner/srt-to-text"
  :license {:name "Apache License"
            :url  "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [
                 [org.clojure/clojure "1.10.1"]
                 [org.apache.tika/tika-parsers "1.24.1"]]
  :repl-options {:init-ns srt-to-text.core})
