# Kross2D

Kross2D is a cross-platform, *multi-platform* 2D game engine built in Kotlin.

The goal of this library is to be simple to use, providing a minimal and
intuitive core API as well as some optional, useful extras. You should be able
to get a game loop up and running with minimal code.

The library is 100% Kotlin, designed for Kotlin codebases.

It is built using
[Kotlin multiplatform support](https://kotlinlang.org/docs/reference/multiplatform.html),
which means that the same core API is not only cross-platform on desktop
(thanks to the ubiquitous JVM target), but it can potentially target other
platforms as well (web, mobile, and *native* desktop). JVM and web backends
will be supplied out of the box.

## Layout

This project contains three main areas:

### kross2d-core

The main library. Users should be able to make a 2D game using only the code
found here.

### kross2d-extras

Tasty extras. Users should expect to find support classes that solve common
game programming issues here.

### examples

A list of example projects, in growing complexity, that showcase the various
features of Kross2D.

## Notes

Kross2D has an API inspired by Rust's [ggez](https://github.com/ggez/ggez) library,
with some slightly different design choices and a few tricks unique to Kotlin.

