package org.obi.ant

import org.obi.task.Javac

/**
 * Implementation of the javac command using Apache Ant.
 */
object ExecutableJavac {
    // TODO Returning of the executable happens when the src dir is supplied
    implicit def javacToExecutableJavac(javac: Javac) = new ExecutableJavac(javac)
}

/**
 * Implementation of the javac command using Apache Ant.
 */
final class ExecutableJavac(javac: Javac) {
    // TODO This class gets called ExecutableJavac, put into Javac.scala.
    // TODO Expose the project basedir somewhere.
    // TODO Expose the project name somewhere.
    import org.apache.tools.ant.Project
    import org.obi.attr.SrcDir._

    lazy val project = new Project()

    // TODO Use these source files.
    def srcfiles(srcFilePattern: String) = {
        project.init
        project.setName(project.getBaseDir.getName)
        new AntJavacTask(project, javac)
    }
}
