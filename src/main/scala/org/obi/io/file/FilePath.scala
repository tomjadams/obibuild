package org.obi.io.file

/**
 * The path to a file on a filesystem.
 * Usage:
 * <pre>
 * import org.obi.io.file.FilePath
 * import org.obi.io.file.FilePath._
 *
 * // Create a FilePath
 * val p1 = filepath("/foo/bar.jar")
 * val p2 = path("/foo/bar.jar")
 *
 * // Implicit conversion from String to FilePath
 * val p3: FilePath = "/foo/bar.jar"
 *
 * // Implicit conversion from FilePath to String
 * val p4: String = path("/foo/bar.jar")
 * </pre>
 */
sealed trait FilePath {
    // TODO Make this a cons list of FilePath elements? Then use an intersperse with '/' etc. to return a system specific path?
    // TODO Check the format of the path, for :, :, invalid chars, etc.
    // TODO Strip trailing / and \
}

private final case class FilePath_(path: String) extends FilePath

object FilePath {
    /**
     * Creates a file path from a String.
     */
    def filepath(filePath: String): FilePath = FilePath_(filePath)

    /**
     * Creates a file path from a String.
     */
    implicit def path(filePath: String): FilePath = FilePath_(filePath)

    /**
     * Converts a FilePath to a String.
     */
    implicit def toString(filePath: FilePath): String = filePath match {
        case FilePath_(p) => p
    }
}
