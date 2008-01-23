package org.obi.attr

/**
 * A directory containing source code.
 */
sealed trait SrcDir

private final case class SrcDir_(srcdir: String) extends SrcDir

object SrcDir {
    def srcdir(srcdir: String): SrcDir = SrcDir_(srcdir)
}
