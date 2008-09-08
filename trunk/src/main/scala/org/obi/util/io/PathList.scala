package org.obi.util.io

import FilePath._
import java.io.File

/**
 * A list of paths delimited with the system path seperator, used for setting PATH, CLASSPATH, etc.
 * Usage:
 * <pre>
 * val cp0 = pathlist << pathlist
 * val cp1 = pathlist("/foo/bar.jar", "/foo/baz.jar")
 * val cp2 = pathlist(List("/foo/bar.jar", "/foo/baz.jar"))
 * val cp3 = pathlist << "/foo/bar.jar" << "/foo/baz.jar"
 * val cp4 = pathlist << List("/foo/bar.jar", "/foo/baz.jar")
 * val cp5 = "/foo/bar.jar" << pathlist("/foo/baz.jar")
 * val cp6 = List("/foo/bar.jar", "/foo/baz.jar") <<: pathlist
 * val cp7 = "/foo/bar.jar" << "/foo/baz.jar"
 * val cp8 = pathlist("/foo/bar.jar") << pathlist("/foo/baz.jar")
 * val cp9 = pathlist("/foo/bar.jar") ::: pathlist("/foo/baz.jar")
 * val cp10 = pathlist("/foo/bar.jar") + pathlist("/foo/baz.jar")
 * val cp11 = pathlist("/foo/bar.jar") + "/foo/baz.jar"
 * val cp12 = "/foo/bar.jar" :: pathlist("/foo/baz.jar")
 * val cp13: PathList = "/foo/bar.jar:/foo/baz.jar"
 * val cp14: PathList = "/foo/bar.jar;/foo/baz.jar"
 * val cp15: PathList = "/foo/bar.jar:/foo/baz.jar;/foo/quux.jar"
 * val cps: String = "/foo/bar.jar" << "/foo/baz.jar"
 * </pre>
 */
sealed trait PathList {

  /**
   * Prepends the given path element to the start of the pathlist.
   */
  def ::(prefix: FilePath): PathList = this match {
    case PathList_(Nil) => PathList_(List(prefix))
    case PathList_(elements) => PathList_(List(prefix) ::: elements)
  }

  /**
   * Prepends the given path element to the start of the pathlist.
   */
  def <<:(prefix: FilePath): PathList = prefix :: this

  /**
   * Prepends the given list of path elements to the start of the pathlist.
   */
  def <<:(prefix: List[String]): PathList = this match {
    case PathList_(Nil) => PathList_(prefix.map(filepath(_)))
    case PathList_(elements) => PathList_(elements ::: prefix.map(filepath(_)))
  }

  /**
   * Prepends the given pathlist to the start of this pathlist.
   */
  def :::(prefix: PathList): PathList = prefix match {
    case PathList_(Nil) => this
    case PathList_(prefixElements) => this match {
      case PathList_(Nil) => prefix
      case PathList_(suffixElements) => PathList_(prefixElements ::: suffixElements)
    }
  }

  /**
   * Appends the given path list to the end of this pathlist.
   */
  def +(suffix: PathList): PathList = this ::: suffix

  /**
   * Appends the given path list to the end of this pathlist.
   */
  def <<(suffix: PathList): PathList = this ::: suffix

  /**
   * Appends the given path element (e.g. a Jar file) to the end of the pathlist.
   */
  def <<(suffix: FilePath): PathList = this match {
    case PathList_(Nil) => PathList_(List(suffix))
    case PathList_(elements) => PathList_(elements ::: List(suffix))
  }

  /**
   * Appends the given list of path elements to the end of the pathlist.
   */
  def <<(suffix: List[String]): PathList = this match {
    case PathList_(Nil) => PathList_(suffix.map(filepath(_)))
    case PathList_(elements) => PathList_(elements ::: suffix.map(filepath(_)))
  }
}

private final case class PathList_(elements: List[FilePath]) extends PathList

object PathList {
  import java.util.regex.Pattern.compile

  lazy val pathSeparatorChar = File.pathSeparatorChar.toString
  lazy val pathSeparatorMatchPattern = compile(".*[:|;].*")
  lazy val pathSeparatorSplitPattern = compile(":|;")

  implicit def toSystemSpecificPath(pathList: PathList): String = pathList match {
    case PathList_(Nil) => ""
    case PathList_(elements) => {
      elements.map(filePathToString(_)).mkString(pathSeparatorChar: String)
    }
  }

  implicit def stringToPathList(s: String): PathList = {
    if (pathSeparatorMatchPattern.matcher(s).matches) {
      PathList_(pathSeparatorSplitPattern.split(s).map(filepath(_)).toList)
    } else {
      PathList_(List(filepath(s)))
    }
  }

  implicit def filePathToPathList(filePath: FilePath): PathList = PathList_(List(filePath))

  def pathlist: PathList = PathList_(Nil)

  def pathlist(pathElements: String*): PathList = PathList_(pathElements.map(filepath(_)).toList)

  def pathlist(pathElements: List[String]): PathList = PathList_(pathElements.map(filepath(_)))
}
