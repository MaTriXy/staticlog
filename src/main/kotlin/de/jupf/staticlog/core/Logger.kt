package de.jupf.staticlog.core

import de.jupf.staticlog.Log
import de.jupf.staticlog.format.LogFormat
import de.jupf.staticlog.printer.AndroidPrinter
import de.jupf.staticlog.printer.DesktopPrinter
import de.jupf.staticlog.printer.Printer
import java.util.*

/**
 * @author J.Pfeifer
 * @created 19.01.2016
 */
class Logger() {
    internal val logFormat = LogFormat() // The defined log format for this logger
    var logLevel = LogLevel.DEBUG // Defines the minimum log level to print
    var filterTag: String
        get() = logFormat.filterTag
        set(value) {
            logFormat.filterTag = value
        }

    constructor(needLongTrace: Int) : this() {
        logFormat.traceSteps = needLongTrace
    }

    /**
     * Logs a debug message.
     *
     * @param message The log message
     * @param tag The tag the message is logged under
     * @param throwable The log-related throwable
     */
    fun debug(message: String, tag: String, throwable: Throwable) {
        if (!checkLogLevel(LogLevel.DEBUG)) return
        print(LogLevel.DEBUG, message, tag, throwable)
    }

    /**
     * Logs a debug message.
     * The tag will default to the class name the log is created from.
     *
     * @param message The log message
     * @param throwable The log-related throwable
     */
    fun debug(message: String, throwable: Throwable) {
        if (!checkLogLevel(LogLevel.DEBUG)) return
        print(LogLevel.DEBUG, message, "", throwable)
    }

    /**
     * Logs a debug message.
     *
     * @param message The log message
     * @param tag The tag the message is logged under
     */
    fun debug(message: String, tag: String) {
        if (!checkLogLevel(LogLevel.DEBUG)) return
        print(LogLevel.DEBUG, message, tag, null)
    }

    /**
     * Logs a debug message.
     * The tag will default to the class name the log is created from.
     *
     * @param message The log message
     */
    fun debug(message: String) {
        if (!checkLogLevel(LogLevel.DEBUG)) return
        print(LogLevel.DEBUG, message, "", null)
    }

    /**
     * Logs an info message.
     *
     * @param message The log message
     * @param tag The tag the message is logged under
     * @param throwable The log-related throwable
     */
    fun info(message: String, tag: String, throwable: Throwable) {
        if (!checkLogLevel(LogLevel.INFO)) return;
        print(LogLevel.INFO, message, tag, throwable)
    }

    /**
     * Logs an info message.
     * The tag will default to the class name the log is created from.
     *
     * @param message The log message
     * @param throwable The log-related throwable
     */
    fun info(message: String, throwable: Throwable) {
        if (!checkLogLevel(LogLevel.INFO)) return;
        print(LogLevel.INFO, message, "", throwable)
    }

    /**
     * Logs an info message.
     *
     * @param message The log message
     * @param tag The tag the message is logged under
     */
    fun info(message: String, tag: String) {
        if (!checkLogLevel(LogLevel.INFO)) return;
        print(LogLevel.INFO, message, tag, null)
    }

    /**
     * Logs an info message.
     * The tag will default to the class name the log is created from.
     *
     * @param message The log message
     */
    fun info(message: String) {
        if (!checkLogLevel(LogLevel.INFO)) return;
        print(LogLevel.INFO, message, "", null)
    }

    /**
     * Logs a warning message.
     *
     * @param message The log message
     * @param tag The tag the message is logged under
     * @param throwable The log-related throwable
     */
    fun warn(message: String, tag: String, throwable: Throwable) {
        if (!checkLogLevel(LogLevel.WARN)) return
        print(LogLevel.WARN, message, tag, throwable)
    }

    /**
     * Logs a warning message.
     * The tag will default to the class name the log is created from.
     *
     * @param message The log message
     * @param throwable The log-related throwable
     */
    fun warn(message: String, throwable: Throwable) {
        if (!checkLogLevel(LogLevel.WARN)) return
        print(LogLevel.WARN, message, "", throwable)
    }

    /**
     * Logs a warning message.
     *
     * @param message The log message
     * @param tag The tag the message is logged under
     */
    fun warn(message: String, tag: String) {
        if (!checkLogLevel(LogLevel.WARN)) return
        print(LogLevel.WARN, message, tag, null)
    }

    /**
     * Logs a warning message.
     * The tag will default to the class name the log is created from.
     *
     * @param message The log message
     */
    fun warn(message: String) {
        if (!checkLogLevel(LogLevel.WARN)) return
        print(LogLevel.WARN, message, "", null)
    }

    /**
     * Logs an error message.
     *
     * @param message The log message
     * @param tag The tag the message is logged under
     * @param throwable The log-related throwable
     */
    fun error(message: String, tag: String, throwable: Throwable) {
        print(LogLevel.ERROR, message, tag, throwable)
    }

    /**
     * Logs an error message.
     * The tag will default to the class name the log is created from.
     *
     * @param message The log message
     * @param throwable The log-related throwable
     */
    fun error(message: String, throwable: Throwable) {
        print(LogLevel.ERROR, message, "", throwable)
    }

    /**
     * Logs an error message.
     *
     * @param message The log message
     * @param tag The tag the message is logged under
     */
    fun error(message: String, tag: String) {
        print(LogLevel.ERROR, message, tag, null)
    }

    /**
     * Logs an error message.
     * The tag will default to the class name the log is created from.
     *
     * @param message The log message
     */
    fun error(message: String) {
        print(LogLevel.ERROR, message, "", null)
    }

    /**
     * Sets a tag filter for this Logger.
     * Only messages with this tag will be printed.
     *
     * @param filterTag
     */
    fun setTagFilter(filterTag: String) {
        logFormat.filterTag = filterTag
        logFormat.tagFilterUsed = true
    }

    /**
     * Deletes a previously set tag filter.
     */
    fun deleteTagFilter() {
        logFormat.tagFilterUsed = false
    }

    /**
     * This method deletes the old [LogFormat] and
     * returns a handle to create the new format with.
     *
     * @return log format handle
     */
    fun newFormat(): LogFormat {
        logFormat.children.clear()
        logFormat.occurrenceUsed = false
        logFormat.tagUsed = false
        return logFormat
    }

    /**
     * This function deletes the old LogFormat.
     * Then executes the given [build] function to create a new log format.
     */
    fun newFormat(build: LogFormat.() -> Unit) {
        logFormat.build(build)
    }

    /**
     * Adding the given [printer] to the printers used by this [Logger]
     */
    fun addPrinter(printer: Printer) {
        logFormat.printer.add(printer)
    }

    /**
     * Resets the [Printer] of this [Logger] to the default.
     */
    fun setDefaultPrinter() {
        logFormat.setDefaultPrinter()
    }

    /**
     * Sets the given [printer] as the new and only printer for this [Logger].
     */
    fun setPrinter(printer: Printer) {
        logFormat.printer.clear()
        logFormat.printer.add(printer)
    }

    /**
     * Checks the given [level] against the [LogLevel] set in this [Logger].
     */
    private fun checkLogLevel(level: LogLevel) = logLevel <= level

    private fun print(level: LogLevel, message: String, tag: String = "", throwable: Throwable? = null) {
        if (logFormat.occurrenceUsed || logFormat.tagFilterUsed || (logFormat.tagUsed && tag == "")) {
            val trace = getTrace()
            var newTag = tag
            if (tag == "")
                newTag = getTraceTag(trace)

            if (tagIsFiltered(tag))
                return

            logFormat.print(level, System.currentTimeMillis(), message, newTag, throwable, getTrace())
        } else
            logFormat.print(level, System.currentTimeMillis(), message, tag, throwable, null)
    }

    private fun getTraceTag(trace: StackTraceElement): String {
        val className = trace.className.split(".")
        return className[className.size - 1]
    }

    private fun getTrace(): StackTraceElement {
        return Exception().stackTrace[logFormat.traceSteps]
    }

    private fun tagIsFiltered(tag: String): Boolean {
        return logFormat.tagFilterUsed && logFormat.filterTag != tag
    }
}