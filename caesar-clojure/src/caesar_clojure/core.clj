(ns caesar-clojure.core
  (:use [clojure.string :only [upper-case join]]))

(def alphabet "abcdefghijklmnopqrstuvwxyz")
(def caesar-offset 3)

(defn get-alphabet []
  (str (upper-case alphabet)
        alphabet))

(defn get-cipher
  ([alphabet offset] (let [len (count alphabet)
                           value (if (> offset 0) offset (+ len offset))]
                       (->> (cycle alphabet)
                         (drop value)
                         (take len)
                         (zipmap alphabet))))
  ([alphabet] (get-cipher alphabet 3)))

(defn abc-shift [alphabet offset text]
  (join (replace (get-cipher alphabet offset) text)))

(defn encode [text]
  (abc-shift (get-alphabet) caesar-offset text))

(defn decode [text]
  (abc-shift (get-alphabet) (- caesar-offset) text))

(defn -main [& args]
  (if (= (first args) "-e")
    (println (encode (second args)))
    (println (decode (first args)))))
