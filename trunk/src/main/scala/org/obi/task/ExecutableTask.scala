package org.obi.task

/**
 * A task (unit of work) that can be executed.
 */
trait ExecutableTask {
    def execute: Unit
    final def unary_! = execute
}
