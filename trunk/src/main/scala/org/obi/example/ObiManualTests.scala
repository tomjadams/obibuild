package org.obi.example

import org.obi.ant.AntJavac
import org.obi.ant.AntJavac._
import org.obi.attr.ClassPath
import org.obi.attr.ClassPath._
import org.obi.attr.FilePattern
import org.obi.attr.FilePattern._
import org.obi.attr.SrcDir
import org.obi.attr.SrcDir._
import org.obi.io.file.FilePath
import org.obi.io.file.FilePath._
import org.obi.task.Javac
import org.obi.task.Javac._

object ObiManualTests {

    def filepathTest {
        val p1 = filepath("/foo/bar.jar")
        val p2 = path("/foo/bar.jar")
        val p3: FilePath = "/foo/bar.jar"
        val p4: String = path("/foo/bar.jar")
        println(p1)
        println(p2)
        println(p3)
        println(p4)
    }

    def classpathTest {
        val cp0 = classpath << classpath
        val cp1 = classpath("/foo/bar.jar", "/foo/baz.jar")
        val cp2 = classpath(List("/foo/bar.jar", "/foo/baz.jar"))
        val cp3 = classpath << "/foo/bar.jar" << "/foo/baz.jar"
        val cp4 = classpath << List("/foo/bar.jar", "/foo/baz.jar")
        val cp5 = "/foo/bar.jar" << classpath("/foo/baz.jar")
        val cp6 = List("/foo/bar.jar", "/foo/baz.jar") <<: classpath
        val cp7 = "/foo/bar.jar" << "/foo/baz.jar"
        val cp8 = classpath("/foo/bar.jar") << classpath("/foo/baz.jar")
        val cp9 = classpath("/foo/bar.jar") ::: classpath("/foo/baz.jar")
        val cp10 = classpath("/foo/bar.jar") + classpath("/foo/baz.jar")
        val cp11 = classpath("/foo/bar.jar") + "/foo/baz.jar"
        val cp12 = "/foo/bar.jar" :: classpath("/foo/baz.jar")
        val cp13: ClassPath = "/foo/bar.jar:/foo/baz.jar"
        val cp14: ClassPath = "/foo/bar.jar;/foo/baz.jar"
        val cp15: ClassPath = "/foo/bar.jar:/foo/baz.jar;/foo/quux.jar"
        val cps: String = "/foo/bar.jar" << "/foo/baz.jar"

        println(" 0: " + cp0)
        println(" 1: " + cp1)
        println(" 2: " + cp2)
        println(" 3: " + cp3)
        println(" 4: " + cp4)
        println(" 5: " + cp5)
        println(" 6: " + cp6)
        println(" 7: " + cp7)
        println(" 8: " + cp8)
        println(" 9: " + cp9)
        println("10: " + cp10)
        println("11: " + cp11)
        println("12: " + cp12)
        println("13: " + cp13)
        println("14: " + cp14)
        println("15: " + cp15)
        println("cps: " + cps)

        val log4j = path("log4j.jar")
        val cglib = path("cglib.jar")
        val commons_logging = path("commons-logging.jar") <<: log4j
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

    def antJavacTests {
        !javac(
            srcdir("src/spec/data/java")).
            destdir("build/spec-classes").
            srcfiles(List("Foo.java", "Bar.java"))
    }

    def main(args: Array[String]) {
//        filepathTest
//        classpathTest
//        filepatternTests
        antJavacTests
    }
}