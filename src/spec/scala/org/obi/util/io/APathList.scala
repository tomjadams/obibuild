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

import com.googlecode.instinct.expect.Expect._
import com.googlecode.instinct.marker.annotate.Specification
import PathList._

// TODO Check: varargs call, list call

final class AnEmptyPathList {
  @Specification
  def isEmpty {
    expect.that(pathlist).isEqualTo(pathlist)
  }

  @Specification
  def givesTheEmptyString {
    expect.that(pathlist: String).isEqualTo("")
  }

  @Specification
  def canBeAppendedWithAnotherEmptyPathList {
    expect.that(pathlist << pathlist).isEqualTo(pathlist)
  }
}

final class APathList {
  lazy val barBaz = "/foo/bar.jar" << "/foo/baz.jar"

  @Specification
  def canBeCreatedANumberOfWays {
    expect.that(pathlist("/foo/bar.jar", "/foo/baz.jar")).isEqualTo(barBaz)
    expect.that(pathlist(List("/foo/bar.jar", "/foo/baz.jar"))).isEqualTo(barBaz)
    expect.that(pathlist << "/foo/bar.jar" << "/foo/baz.jar").isEqualTo(barBaz)
    expect.that(pathlist << List("/foo/bar.jar", "/foo/baz.jar")).isEqualTo(barBaz)
    expect.that("/foo/bar.jar" << pathlist("/foo/baz.jar")).isEqualTo(barBaz)
    expect.that(List("/foo/bar.jar", "/foo/baz.jar") <<: pathlist).isEqualTo(barBaz)
    expect.that("/foo/bar.jar" << "/foo/baz.jar").isEqualTo(barBaz)
    expect.that(pathlist("/foo/bar.jar") << pathlist("/foo/baz.jar")).isEqualTo(barBaz)
    expect.that(pathlist("/foo/bar.jar") ::: pathlist("/foo/baz.jar")).isEqualTo(barBaz)
    expect.that(pathlist("/foo/bar.jar") + pathlist("/foo/baz.jar")).isEqualTo(barBaz)
    expect.that(pathlist("/foo/bar.jar") + "/foo/baz.jar").isEqualTo(barBaz)
    expect.that("/foo/bar.jar" :: pathlist("/foo/baz.jar")).isEqualTo(barBaz)
//    expect.that().isEqualTo(barBaz)
//    expect.that().isEqualTo(barBaz)
//    expect.that().isEqualTo(barBaz)
  }
}

