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

import FilePath._
import com.googlecode.instinct.expect.Expect._
import com.googlecode.instinct.marker.annotate.Specification
import java.io.File

final class TwoEqualFilePaths {
  @Specification
  def areEqualToEachOther {
    expect.that(filepath("/foo/bar.jar")).isEqualTo(filepath("/foo/bar.jar"))
  }
}

final class AStringFilePath {
  @Specification
  def getsImplicitlyConvertedToAFile {
    expect.that("/foo/bar.jar": File).isEqualTo(new File("/foo/bar.jar"))
  }

  @Specification
  def getsImplicitlyConvertedToAFilePath {
    expect.that("/foo/bar.jar": FilePath).isEqualTo(filepath("/foo/bar.jar"))
  }
}

final class AFile {
  @Specification
  def getsImplicitlyConvertedToAFilePath {
    expect.that(new File("/foo/bar.jar")).isEqualTo(filepath("/foo/bar.jar"))
  }
}

final class AFilePath {
  @Specification
  def getsImplicitlyConvertedToAString {
    expect.that(filepath("/foo/bar.jar"): String).isEqualTo("/foo/bar.jar")
  }

  @Specification
  def getsImplicitlyConvertedToAFile {
    expect.that(filepath("/foo/bar.jar"): File).isEqualTo(new File("/foo/bar.jar"))
  }

  @Specification
  def getsImplicitlyConvertedToAScalazFile {
    expect.that(filepath("/foo/bar.jar"): File).isEqualTo(scalaz.io.File.file("/foo/bar.jar"))
  }
}


