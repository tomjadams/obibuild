package org.obi.attr

import java.io.File
import org.obi.io.file.FilePath
import org.obi.io.file.FilePath._

/**
 * Where to find user class files.<br/>
 * Usage:
 * <pre>
 * val cp0 = classpath << classpath
 * val cp1 = classpath("/foo/bar.jar", "/foo/baz.jar")
 * val cp2 = classpath(List("/foo/bar.jar", "/foo/baz.jar"))
 * val cp3 = classpath << "/foo/bar.jar" << "/foo/baz.jar"
 * val cp4 = classpath << List("/foo/bar.jar", "/foo/baz.jar")
 * val cp5 = "/foo/bar.jar" << classpath("/foo/baz.jar")
 * val cp6 = List("/foo/bar.jar", "/foo/baz.jar") <<: classpath
 * val cp7 = "/foo/bar.jar" << "/foo/baz.jar"
 * val cp8 = classpath("/foo/bar.jar") << classpath("/foo/baz.jar")
 * val cp9 = classpath("/foo/bar.jar") ::: classpath("/foo/baz.jar")
 * val cp10 = classpath("/foo/bar.jar") + classpath("/foo/baz.jar")
 * val cp11 = classpath("/foo/bar.jar") + "/foo/baz.jar"
 * val cp12 = "/foo/bar.jar" :: classpath("/foo/baz.jar")
 * val cp13: ClassPath = "/foo/bar.jar:/foo/baz.jar"
 * val cp14: ClassPath = "/foo/bar.jar;/foo/baz.jar"
 * val cp15: ClassPath = "/foo/bar.jar:/foo/baz.jar;/foo/quux.jar"
 * val cps: String = "/foo/bar.jar" << "/foo/baz.jar"
 * </pre>
 */
sealed trait ClassPath {
    // TODO This class probably isn't a classpath, make it generic and add an alias to it called classpath.

    /**
     * Prepends the given path element to the start of the classpath.
     */
    def ::(prefix: FilePath): ClassPath = this match {
        case ClassPath_(Nil) => ClassPath_(List(prefix))
        case ClassPath_(elements) => ClassPath_(List(prefix) ::: elements)
    }

    /**
     * Prepends the given path element to the start of the classpath.
     */
    def <<:(prefix: FilePath): ClassPath = prefix :: this

    /**
     * Prepends the given list of path elements to the start of the classpath.
     */
    def <<:(prefix: List[String]): ClassPath = this match {
        case ClassPath_(Nil) => ClassPath_(prefix.map(path(_)))
        case ClassPath_(elements) => ClassPath_(elements ::: prefix.map(path(_)))
    }

    /**
     * Prepends the given classpath to the start of this classpath.
     */
    def :::(prefix: ClassPath): ClassPath = prefix match {
        case ClassPath_(Nil) => this
        case ClassPath_(prefixElements) => this match {
            case ClassPath_(Nil) => prefix
            case ClassPath_(suffixElements) => ClassPath_(prefixElements ::: suffixElements)
        }
    }

    /**
     * Appends the given class path to the end of this classpath.
     */
    def +(suffix: ClassPath): ClassPath = this ::: suffix

    /**
     * Appends the given class path to the end of this classpath.
     */
    def <<(suffix: ClassPath): ClassPath = this ::: suffix

    /**
     * Appends the given path element (e.g. a Jar file) to the end of the classpath.
     */
    def <<(suffix: FilePath): ClassPath = this match {
        case ClassPath_(Nil) => ClassPath_(List(suffix))
        case ClassPath_(elements) => ClassPath_(elements ::: List(suffix))
    }

    /**
     * Appends the given list of path elements to the end of the classpath.
     */
    def <<(suffix: List[String]): ClassPath = this match {
        case ClassPath_(Nil) => ClassPath_(suffix.map(path(_)))
        case ClassPath_(elements) => ClassPath_(elements ::: suffix.map(path(_)))
    }
}

// TODO This should use a Set, not a list.
private final case class ClassPath_(elements: List[FilePath]) extends ClassPath

object ClassPath {
    import scalaz.control.Foldable._
    import java.util.regex.Pattern.compile

    lazy val pathSeparatorChar = File.pathSeparatorChar.toString
    lazy val pathSeparatorMatchPattern = compile(".*[:|;].*")
    lazy val pathSeparatorSplitPattern = compile(":|;")

    implicit def toSystemSpecificPath(classPath: ClassPath): String = classPath match {
        case ClassPath_(Nil) => ""
        case ClassPath_(elements) => {
            intersperse[List, List, String](List(pathSeparatorChar), elements.map(FilePath.toString(_)).toList).foldLeft("")(_ + _)
        }
    }

    implicit def stringToClassPath(s: String): ClassPath = {
        if (pathSeparatorMatchPattern.matcher(s).matches) {
            ClassPath_(pathSeparatorSplitPattern.split(s).map(path(_)).toList)
        } else {
            ClassPath_(List(path(s)))
        }
    }

    implicit def filePathToClassPath(filePath: FilePath): ClassPath = ClassPath_(List(filePath))

    def classpath: ClassPath = ClassPath_(Nil)

    def classpath(pathElements: String*): ClassPath = ClassPath_(pathElements.map(path(_)).toList)

    def classpath(pathElements: List[String]): ClassPath = ClassPath_(pathElements.map(path(_)))
}
