package org.obi.attr

import org.obi.io.file.FilePath

/**
 * A directory containing source code.
 */
sealed trait SrcDir

private final case class SrcDir_(srcdir: FilePath) extends SrcDir

/**
 * A directory containing source code.
 */
object SrcDir {
    // TODO Can this be a unary function so that you don't need the braces?
    def srcdir(srcdir: FilePath): SrcDir = SrcDir_(srcdir)

    /**
     * Converts a SrcDir to a String.
     */
    implicit def toString(s: SrcDir): String = s match {
        case SrcDir_(s) => s
    }
}
