(defproject blocky-hail "0.0.1"
  :java-source-path "src/jvm"
  :javac-options {:debug "true" :fork "true"}
  :aot :all
  :jvm-opts ["-Djava.library.path=/usr/local/lib:/opt/local/lib:/usr/lib"]

  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [com.googlecode.json-simple/json-simple "1.1"]
                 ]

  :dev-dependencies [[storm "0.5.1"]
                     ])

