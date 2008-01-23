package org.obi.task

trait ExecutableTask {
    def execute: Unit
    def unary_! = execute
}
