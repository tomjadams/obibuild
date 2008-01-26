package org.obi.ant

import java.io.File
import org.apache.tools.ant.Project
import org.apache.tools.ant.types.Path
import org.obi.attr.SrcDir._
import org.obi.task.{Javac, ExecutableTask}

final class AntJavacTask(project: Project, javac: Javac) extends ExecutableTask {
    // TODO Dos the project needs to go somewhere to be shared by other tasks??
    // TODO Find a way to remove this logic. Put it somewhere else.
    // TODO Expose the project basedir somewhere.
    // TODO Expose the project name somewhere.

    lazy val antJavac = new org.apache.tools.ant.taskdefs.Javac()

    override def execute {
        antJavac.setProject(project)
        javac.srcdir.foreach(srcdir => {
            val srcDirPath = new Path(project)
            srcDirPath.setPath(srcdir)
            antJavac.setSrcdir(srcDirPath)
        })
        antJavac.setIncludes("**/*.java")
        javac.destdir.foreach(destdir => antJavac.setDestdir(new File(destdir)))
        antJavac.execute
    }
}
