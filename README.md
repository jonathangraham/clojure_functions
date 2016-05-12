# clojure_functions

Writing implementations of some of the core functions of a language can be a great way to really get to understand the language.

Included here are implementations of `reduce`, `count`, `filter`, `map`, and `pmap`. The implementations are only models of the core functions, and are not intended to provide the same efficiency benefits that the core functions offer.

There are both unit and property-based tests to demonstrate that the functions behave as desired. The unit tests can be run with `lein spec`, and the property-based tests with `lein test`.

For further details, see <a href="http://jonathangraham.github.io/2015/09/01/Clojure%20functions/">Understanding Core Clojure Functions</a> and <a href="http://jonathangraham.github.io/2016/01/07/property_based_testing_clojure_functions/">Property-Based Testing in Clojure</a>.
