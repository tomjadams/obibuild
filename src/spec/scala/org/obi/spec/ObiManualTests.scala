package org.obi.example

import util.io.{FilePath, PathList}
import util.io.FilePath._
import util.io.PathList._
import org.obi.ant.ExecutableJavac
import org.obi.ant.ExecutableJavac._
import org.obi.attr.{FilePattern, SrcDir}
import org.obi.attr.FilePattern._
import org.obi.attr.SrcDir._
import org.obi.task.Javac
import org.obi.task.Javac._

// TODO Move these to Instinct tests.
object ObiManualTests {

    def pathlistTest {
        val log4j = filepath("log4j.jar")
        val cglib = filepath("cglib.jar")
        val commons_logging = filepath("commons-logging.jar") <<: log4j
        val ehcache = commons_logging << "ehcache.jar"
        val hibernate = ehcache + commons_logging + cglib
        println(hibernate)
    }

    def filepatternTests {
        val p1 = pattern("**/*")
        val p2: FilePattern = "**/*"
        val p3: String = pattern("**/*")
        println(p1)
        println(p2)
        println(p3)
    }

    // TODO Use pathlist
    // TODO Use version
    // TODO Use target
    def antJavacTests {
        val src = "Foo.java" << "Bar.java"
        !javac.srcdir("src/spec/data/java").
            destdir("build/spec-classes").
            srcfiles("**/*.java")
    }

    def main(args: Array[String]): Unit = {
        pathlistTest
//        filepatternTests
        antJavacTests
    }
}