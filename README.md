# Duct Shadow CLJS Compiler [![Build Status](https://github.com/duct-framework/compiler.cljs.shadow/actions/workflows/test.yml/badge.svg)](https://github.com/duct-framework/compiler.cljs.shadow/actions/workflows/test.yml)

[Integrant][] methods for running [Shadow CLJS][]. Designed for
the [Duct][] framework, but can be used in any Integrant configuration.

This library is still very new and API may change.

[duct]: https://github.com/duct-framework/duct
[integrant]: https://github.com/weavejester/integrant
[shadow cljs]: https://github.com/thheller/shadow-cljs

## Installation

Add the following dependency to your deps.edn file:

    org.duct-framework/compiler.cljs.shadow {:mvn/version "0.1.3"}

Or to your Leiningen project file:

    [org.duct-framework/compiler.cljs.shadow "0.1.3"]

## Usage

This library provides Integrant methods for two keys:

- `:duct.compiler.cljs.shadow/release`
- `:duct.compiler.cljs.shadow/server`

Both take the same configuration, but have different applications. The
'release' key generates production-ready ClojureScript, while the
'server' key starts a Shadow CLJS development server.

The options for both keys are the same as a `shadow-cljs.edn` file, with
one notable exception; instead of a map of build configurations under
the `:builds` option, there is a single build configuration under the
`:build` key.

For more information, see the documentation on [Shadow CLJS
configuration][config].

[config]: https://shadow-cljs.github.io/docs/UsersGuide.html#config

## License

Copyright © 2025 James Reeves

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
