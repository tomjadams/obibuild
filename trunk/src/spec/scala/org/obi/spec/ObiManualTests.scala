package org.obi.example

import util.io.{FilePattern, PathList, FilePath}
import util.io.FilePath._
import util.io.FilePattern._
import util.io.PathList._
import org.obi.ant.ExecutableJavac
import org.obi.ant.ExecutableJavac._
import org.obi.attr.SrcDir
import org.obi.attr.SrcDir._
import org.obi.task.Javac
import org.obi.task.Javac._

// TODO Move these to Instinct tests.
object ObiManualTests {
    // TODO Use pathlist
    // TODO Use version
    // TODO Use target
    def antJavacTests {
        val src = "Foo.java" << "Bar.java"
        !javac.srcdir("src/spec/data/java").destdir("build/spec-classes").srcfiles("**/*.java")
    }

    def main(args: Array[String]): Unit = {
        antJavacTests
    }
}