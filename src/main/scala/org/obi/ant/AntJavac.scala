package org.obi.ant

import org.obi.task.Javac

/**
 * Implementation of the javac command using Apache Ant.
 */
object AntJavac {
    // TODO Returning of the executable happens when the src dir is supplied
    implicit def javacToAntJavac(j: Javac) = new AntJavac(j)
}

/**
 * Implementation of the javac command using Apache Ant.
 */
final class AntJavac(javac: Javac) {
    // TODO This class gets called ExecutableJavac, put into Javac.scala.
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
