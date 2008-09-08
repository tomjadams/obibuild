package org.obi.util.io

import FilePath._

sealed trait Dir {
    def apply(path: String): Dir = Dir_(path)
    // TODO Define filter, map, foldL, foldR, find, etc. All should retrieve the list of files in the dir
    //      and delegate the function call to the list.
}

private final case class Dir_(path: FilePath) extends Dir