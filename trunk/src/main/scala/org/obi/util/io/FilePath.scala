/*
 * Copyright 2006-2008 Workingmouse
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.obi.util.io

sealed trait FilePath

private final case class FilePath_(path: String) extends FilePath {
  override def toString = path
}

object FilePath {
  import java.io.File

  implicit def filepath(filePath: String): FilePath = filePath match {
    case null => null
    case _ => FilePath_(filePath)
  }

  implicit def filepath(file: File): FilePath = file match {
    case null => null
    case _ => file.getCanonicalPath
  }

  implicit def filePathToString(filePath: FilePath): String = filePath match {
    case null => null
    case FilePath_(p) => p
  }

  implicit def stringToFile(filePath: String): File = filePath match {
    case null => null
    case f => filePathToFile(filepath(f))
  }

  implicit def filePathToFile(filePath: FilePath): File = filePath match {
    case null => null
    case FilePath_(filePath) => new File(filePath)
  }

  implicit def filePathToScalazFile(filePath: FilePath): scalaz.io.File = filePath match {
    case null => null
    case _ => scalaz.io.File.file(filePath)
  }
}
