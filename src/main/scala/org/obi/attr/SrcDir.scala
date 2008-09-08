package org.obi.attr

import org.obi.util.io.FilePath

/**
 * A directory containing source code.
 */
sealed trait SrcDir {
  override def toString = this match {
      case SrcDir_(f) => f
  }
}

private final case class SrcDir_(srcdir: FilePath) extends SrcDir

/**
 * A directory containing source code.
 */
object SrcDir {
    def srcdir(srcdir: FilePath): SrcDir = SrcDir_(srcdir)

    /**
     * Converts a SrcDir to a String.
     */
    implicit def srcDirToString(s: SrcDir): String = s.toString
}
