package org.obi.attr

import org.obi.io.file.FilePath

/**
 * The destination directory of an action.
 */
sealed trait DestDir

private final case class DestDir_(destdir: FilePath) extends DestDir

object DestDir {
    def destdir(destdir: FilePath): DestDir = DestDir_(destdir)
}
