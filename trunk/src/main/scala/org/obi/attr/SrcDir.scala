package org.obi.attr

/**
 * A directory containing source code.
 */
sealed trait SrcDir

// TODO Make this take a filepath
private final case class SrcDir_(srcdir: String) extends SrcDir

object SrcDir {
    // TODO Make this take a filepath
    def srcdir(srcdir: String): SrcDir = SrcDir_(srcdir)
}
