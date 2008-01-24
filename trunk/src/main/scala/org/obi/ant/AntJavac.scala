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
    import org.apache.tools.ant.Project
    import org.apache.tools.ant.taskdefs.Javac
    import org.apache.tools.ant.types.Path
    import org.obi.attr.SrcDir._

    lazy val project = new Project()
    lazy val antJavac = new Javac()

    def srcfiles(srcFiles: List[String]) = new ExecutableTask {
        override def execute {
            // TODO Set the project basedir, expose it somewhere.
            project.init
            // TODO Expose the project name somewhere.
            project.setName(project.getBaseDir.getName)

            println("Basedir: " + project.getBaseDir)
            println("Project name: " + project.getName)

            j.srcdir.foreach(srcdir => {
                println("Setting srcdir: " + srcdir)
                antJavac.setSrcdir(new Path(project, srcdir))
            })
            j.destdir.foreach(destdir => {
                println("Setting destdir: " + destdir)
                antJavac.setDestdir(new File(destdir))
            })
           antJavac.execute
        }
    }
}
