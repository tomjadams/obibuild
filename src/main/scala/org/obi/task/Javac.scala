package org.obi.task

import org.obi.attr.DestDir
import org.obi.attr.SrcDir
import org.obi.io.file.FilePath

/**
 * The javac tool reads class and interface definitions, written in the Java programming language, and compiles them into bytecode class files.<br/>
 * More information can be found on the following links:
 * <ul>
 * <li><a href="http://java.sun.com/javase/6/docs/technotes/tools/solaris/javac.html">Java programming language compiler</a>
 * <li><a href="http://ant.apache.org/manual/CoreTasks/javac.html">Javac Ant Task</a>
 * </ul>
 */
sealed trait Javac {
    // TODO Write more apply's here, to handle different attributes, files, etc.

    def apply(srcdir: SrcDir): Javac = this match {
        case Javac_(_, d) => Javac_(Some(srcdir), d)
    }

    def apply(destdir: DestDir): Javac = this match {
        case Javac_(s, _) => Javac_(s, Some(destdir))
    }

    def srcdir(s: FilePath) = apply(SrcDir.srcdir(s))

    def srcdir = this match {
        case Javac_(s, _) => s
    }

    def destdir(d: FilePath) = apply(DestDir.destdir(d))

    def destdir = this match {
        case Javac_(_, d) => d
    }
}

private final case class Javac_(s: Option[SrcDir], d: Option[DestDir]) extends Javac

object Javac {
    /**
     * Creates a new Java compiler.
     */
    def javac: Javac = Javac_(None, None)
}
