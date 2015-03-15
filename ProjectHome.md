# About #


**Obi has been superseded by the excellent [sbt](http://code.google.com/p/simple-build-tool/).**

Obi is a build tool for Scala and Java projects. Obi uses a flexible DSL style syntax to specify a build configuration.

Consider the following examples of a Java compilation task:

```
// Compile Foo and Bar in src/main/java into build/main-classes.
javac(srcdir("src/main/java")).
        destdir("build/main-classes").
        srcfiles(List("Foo.java", "Bar.java"))

// Create a compile task for all files in src/main/java.
val jc = Dir("src/main/java") << javac

// Execute the task
!jc
```

Obi encourages builds that are simple, sufficient and efficient. Obi exposes tasks that exploit Scala's type system to reduce the amount of mistakes you make with a build. For example invalid files will not compile and tasks that do not have their preconditions fulfilled (e.g. a Javac task without a source dir) will not run. As Obi's tasks are immutable (side effect free) you can combine them in flexible ways to implement common features in other build tools.

# Download #

There are currently no downloads. Please consult the [source](http://code.google.com/p/obibuild/source).

# Sponsors #

Obi development is sponsored by [Workingmouse](http://workingmouse.com/).

[![](http://obibuild.googlecode.com/svn/wiki/images/workingmouse_logo_full_transparent.png)](http://workingmouse.com/)