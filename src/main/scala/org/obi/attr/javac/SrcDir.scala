package org.obi.attr

import org.obi.io.file.FilePath

/**
 * A directory containing source code.
 */
sealed trait SrcDir

private final case class SrcDir_(srcdir: FilePath) extends SrcDir

object SrcDir {
    def srcdir(srcdir: FilePath): SrcDir = SrcDir_(srcdir)
}
