package org.obi.ant

import org.obi.task.{Javac, ExecutableTask}

/**
 * Implementation of the javac command using Apache Ant.
 */
object AntJavac {
    implicit def javacToAntJavac(j: Javac) = new AntJavac(j)
}

/**
 * Implementation of the javac command using Apache Ant.
 */
final class AntJavac(j: Javac) {
    import java.io.File
    import org.apache.tools.ant.taskdefs.Javac
    import org.apache.tools.ant.types.Path

    lazy val project = new Project()
    lazy val antJavac = new Javac()

    def srcfiles(srcFiles: List[String]) = new ExecutableTask {
        override def execute {
            // foreach is needed to get pull the srcdir out of the option
            j.srcdir.foreach(srcdir => {
                println("Setting srcdir: " + srcdir)
                antJavac.setSrcdir(new Path(srcdir)
            })
            j.destdir.foreach(destdir => {
                println("Setting destdir: " + destdir)
                antJavac.setDestdir(new File())
            })
           javac.execute()
        }
    }
}
