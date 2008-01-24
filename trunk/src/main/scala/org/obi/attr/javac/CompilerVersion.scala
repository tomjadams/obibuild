package org.obi.attr.javac

/**
  * The version of the Java compiler.<br/>
  * More information can be found on the following links:
  * <ul>
  * <li><a href="http://java.sun.com/javase/6/docs/technotes/tools/solaris/javac.html">Java programming language compiler</a>
  * <li><a href="http://ant.apache.org/manual/CoreTasks/javac.html">Javac Ant Task</a>
  * </ul>
  */
sealed trait CompilerVersion {
    def version: String
}

/**
  * The standard compiler of JDK 1.1/1.2.
  */
final case object Javac11 extends CompilerVersion {
    def version = "javac1.1"
}

/**
  * The standard compiler of JDK 1.1/1.2.
  */
final case object Javac12 extends CompilerVersion {
    def version = "javac1.2"
}

/**
 * The standard compiler of JDK 1.3/1.4/1.5/1.6.
 */
final case object Javac13 extends CompilerVersion {
    def version = "javac1.3"
}

/**
 * The standard compiler of JDK 1.3/1.4/1.5/1.6.
 */
final case object Javac14 extends CompilerVersion {
    def version = "javac1.4"
}

/**
 * The standard compiler of JDK 1.3/1.4/1.5/1.6.
 */
final case object Javac15 extends CompilerVersion {
    def version = "javac1.5"
}

/**
 * The standard compiler of JDK 1.3/1.4/1.5/1.6.
 */
final case object Javac16 extends CompilerVersion {
    def version = "javac1.6"
}

/**
 * The standard compiler of JDK 1.1/1.2. Javac11 and Javac12 can be used as aliases.
 */
final case object Classic extends CompilerVersion {
    def version = "classic"
}

/**
 * The standard compiler of JDK 1.3/1.4/1.5/1.6. Javac13, Javac14, Javac15 oand Javac16 can be used as aliases.
 */
final case object Modern extends CompilerVersion {
    def version = "modern"
}

/**
 * The Jikes compiler.
 */
final case object Jikes extends CompilerVersion {
    def version = "jikes"
}

/**
 * The Command-Line Compiler from Microsoft's SDK for Java / Visual J++. Microsoft can be used as an alias.
 */
final case object Jvc extends CompilerVersion {
    def version = "jvc"
}

/**
 * The Command-Line Compiler from Microsoft's SDK for Java / Visual J++. Jvc can be used as an alias.
 */
final case object Microsoft extends CompilerVersion {
    def version = "microsoft"
}

/**
 * The kopi compiler.
 */
final case object Kjc extends CompilerVersion {
    def version = "kjc"
}

/**
 * The gcj compiler from gcc.
 */
final case object Gcj extends CompilerVersion {
    def version = "gcj"
}

/**
 * The Symantec java compiler. Symantec can be used as an alias.
 */
final case object Sj extends CompilerVersion {
    def version = "sj"
}

/**
 * The Symantec java compiler.
 */
final case object Symantec extends CompilerVersion {
    def version = "symantec"
}
