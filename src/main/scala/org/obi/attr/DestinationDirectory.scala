package org.obi.attr

import org.obi.io.file.FilePath

/**
 * The destination directory of an action.
 */
sealed trait DestinationDirectory

private final case class DestinationDirectory_(destdir: FilePath) extends DestinationDirectory

object DestinationDirectory {
    // TODO Make this take a filepath
    def destdir(destdir: FilePath): DestinationDirectory = DestinationDirectory_(destdir)
}
