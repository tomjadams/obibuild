package org.obi.example

import org.obi.ant.AntJavac._
import org.obi.attr.SrcDir._
import org.obi.task.{ExecutableTask, Javac}
import org.obi.task.Javac._

object ObiExample {
    def run {
        // Define two compile targets, one for Java code, one for Scala code
        val jc = javac(srcdir("src/main/java")).
                destdir("build/main-classes").
                srcfiles(List("Foo.java", "Bar.java"))
    //    val sc = scalac.srcdir("src/main/scala").srcfiles(List("Foo.scala", "Bar.scala"))

        // This is the equivalent of a "target", Java compile is performed before Scala
        // compile, both tasks are executed when called.
        def compile {
            !jc
    //        !sc
        }

        // Compile the files Foo.java & Bar.java in the directory src/main/java
        !javac(srcdir("src/main/java")).srcfiles(List("Foo.java", "Bar.java"))

        // Create a compile task (but don't execute it) for the files Foo.java & Bar.java in the
        // directory src/main.java
//        List("Foo.java", "Bar.java") >>: javac(srcdir("src/main/java"))

        // Create a compile task (but don't execute it) for all files in the directory src/main.java
//        Dir("src/main/scala")  >>: scala(srcdir("src/main/scala"))

        // compile all Scala source files in an "obi" directory
//        val scalaCompile = Dir("src/main/scala").filter(_.matches("""obi.*\.scala$""")) >>: scalac
//        !scalaCompile

        // compile all Scala source files in an "obi" directory
//        !scalac.srcdir("src/main/scala").include(".scala")
    }
}

object ObiExampleApplication {
   def main(args: Array[String]) {
        ObiExample.run
    }
}
