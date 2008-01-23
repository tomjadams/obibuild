package org.obi.attr

sealed trait JavaVersion {
    def version: String
}

final case object Java11 extends JavaVersion {
    override def version = "1.1"
}

final case object Java12 extends JavaVersion {
    override def version = "1.2"
}

final case object Java13 extends JavaVersion {
    override def version = "1.3"
}

final case object Java14 extends JavaVersion {
    override def version = "1.4"
}

final case object Java15 extends JavaVersion {
    override def version = "1.5"
}

final case object Java16 extends JavaVersion {
    override def version = "1.6"
}