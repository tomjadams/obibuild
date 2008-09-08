package org.obi.util.io

/**
 * The path or glob to file(s) on a filesystem (using Ant glob syntax, see
 * <a href="http://ant.apache.org/manual/CoreTypes/selectors.html#filenameselect">Filename Selector</a> for more information).<br/>
 * Usage:
 * <pre>
 * import org.obi.util.io.FilePattern, FilePattern._
 *
 * val p1 = pattern("*.scala")
 * val p2: FilePattern = "*.scala"
 * val p3: String = pattern("*.scala")
 * </pre>
 */
sealed trait FilePattern

private final case class FilePattern_(pattern: String) extends FilePattern

object FilePattern {
    /**
     * Creates a file pattern from a String.
     */
    implicit def pattern(pattern: String): FilePattern = FilePattern_(pattern)

    /**
     * Converts a FilePattern to a String.
     */
    implicit def toString(pattern: FilePattern): String = pattern match {
        case FilePattern_(p) => p
    }
}
