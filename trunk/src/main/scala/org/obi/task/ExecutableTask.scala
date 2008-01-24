package org.obi.task

/**
 * A task that can be executed.
 */
trait ExecutableTask {
    def execute: Unit
    def unary_! = execute
}
