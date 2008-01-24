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
        // TODO Find a way to remove this logic. Put it somewhere else.
        // TODO Expose the project basedir somewhere.
        // TODO Expose the project name somewhere.
        override def execute {
            project.init
            project.setName(project.getBaseDir.getName)
            antJavac.setProject(project)
            j.srcdir.foreach(srcdir => antJavac.setSrcdir(new Path(project, srcdir)))
            j.destdir.foreach(destdir => antJavac.setDestdir(new File(destdir)))
            antJavac.execute
        }
    }
}
