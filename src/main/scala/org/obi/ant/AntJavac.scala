package org.obi.ant

import org.obi.task.{Javac, ExecutableTask}

object AntJavac {
    implicit def javacToAntJavac(j: Javac) = new AntJavac(j)
}

final class AntJavac(j: Javac) {
    // Tom: Why is this lazy? Where is it used? Should this be referenced instead of j?
    lazy val javac = j

    def srcfiles(srcFiles: List[String]) = new ExecutableTask {
        override def execute {
            // val j = new JavacTask(...)
            // foreach is needed to get pull the srcdir out of the option
            j.srcdir.foreach(srcdir => {
                // j.update(srcdir)
                error("execute javac via ant")
            })
            // j.execute()
        }
    }
}
