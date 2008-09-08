package org.obi.attr

import org.obi.util.io.FilePath

/**
 * The destination directory of an action.
 */
sealed trait DestDir

private final case class DestDir_(destdir: FilePath) extends DestDir

/**
 * The destination directory of an action.
 */
object DestDir {
    def destdir(destdir: FilePath): DestDir = DestDir_(destdir)

    /**
     * Converts a DestDir to a String.
     */
    implicit def toString(d: DestDir): String = d match {
        case DestDir_(d) => d
    }

}
