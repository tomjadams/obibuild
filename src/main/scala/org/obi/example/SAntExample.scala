package org.obi.example

import sant.Predef._
import sant.Ant._

project.name = "sant"

// include file build-local.scala if it exists
if (new File("build-local.scala").exists)
    load("build-local.scala")

target("clean") {
    delete("dir" -> "target/classes")
}

target("prepare", "description" -> "Prepares project for compiling") {
    log("yo!")
    mkdir("dir" -> "target/classes")
}

target("compile", "description" -> "Compiles all SAnt's classes", "depends" -> "prepare") {

    scalac.srcdir("src/main/scala").destdir("target/classes").classpath(dir("lib"), glob("*.jar"))

    scalac(
        "srcdir" -> "src/main/scala",
        "destdir" -> "target/classes",
        classpath(
            fileset("dir" -> "lib", "includes" -> "*.jar")
        )
    )
}

target("jar", "description" -> "Creates SAnt's main JAR file", "depends" -> "compile") {
    jar(
        fileset("dir" -> "target/classes"),
        fileset("dir" -> "src/main/scala", "includes" -> "**/*.xml"),
        "destfile" -> "target/sjar"
    )
}

target("dist", "description" -> "Builds SAnt's distribution as zip-file", "depends" -> "jar", "default" -> true) {
    mkdir("dir" -> "target/sant")
    mkdir("dir" -> "target/sant/bin")
    mkdir("dir" -> "target/sant/lib")

    copy("todir" -> "target/sant/lib",
        FileSet.dir("lib").include("*.jar"),
        FileSet.file("target/sjar")
    )

    copy("toDir" -> "target/sant/bin",
        FileSet.dir("src/main/bin"))
    chmod("perm" -> "755",
        FileSet.dir("target/sant/bin").excludes("*.bat"))
        // fileset("dir" -> "target/sant/bin", "excludes" -> "*.bat")

    zip("destfile" -> "target/szip", "basedir" -> "target", "includes" -> "sant/**/*")

    // this does not work yet
    //val tarfileset = dataType("tarfileset")
    val tarfileset = nested("tarfileset")
    tar("destfile" -> "target/stgz", "compression" -> "gzip",
        tarfileset(
            "dir" -> "target", "includes" -> "sant/bin/*.sh", "filemode" -> "755"),
        tarfileset(
            "dir" -> "target", include("name" -> "sant/**/*"), exclude("name" -> "sant/bin/*")))
}

target("bootstrap", "description" -> "Rebuilds SAnt's bootstrap JAR", "depends" -> "clean,jar") {
    //copy("todir" -> "src/bootstrap/lib", "file" -> "target/sjar")
    Copy.toDir("src/bootstrap/lib").file("target/sjar")
}

target("all", "description" -> "Build all", "depends" -> "clean,dist,bootstrap") {
}

target("test-hello") {
    sant("dir" -> "src/tests/simple")
}

// vim: set ts=4 sw=4 et: