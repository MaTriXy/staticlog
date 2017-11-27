//@file:JvmName("Log")

package de.jupf.staticlog

import de.jupf.staticlog.core.Logger
import de.jupf.staticlog.core.LogLevel
import de.jupf.staticlog.format.Builder
import de.jupf.staticlog.format.Indent
import de.jupf.staticlog.format.Line
import de.jupf.staticlog.format.LogFormat
import de.jupf.staticlog.printer.AndroidPrinter
import de.jupf.staticlog.printer.DesktopPrinter
import de.jupf.staticlog.printer.Printer
import java.util.*

/**
 * The StaticLog main logging interface object
 *
 * @author J.Pfeifer
 * @created on 03.02.2016.
 */
object Log {
    internal val logInstance = Logger(4)
    @JvmStatic var logLevel: LogLevel
        get() = logInstance.logLevel
        set(value) {
            logInstance.logLevel = value
        }
    var filterTag: String
        get() = logInstance.filterTag
        set(value) { logInstance.filterTag = value }

    @JvmStatic val slf4jLogInstancesMap: Map<String,Logger> = LinkedHashMap()

    @JvmStatic
    var slf4jLogLevel: LogLevel
        get() = SLF4JBindingFacade.logLevel
        set(value) {
            SLF4JBindingFacade.logLevel = value
        }

    /**
     * Returns a logger instance for Java
     */
    @JvmStatic
    fun javaInstance(): Logger {
        return Logger(4)
    }

    /**
     * Returns a logger instance for kotlin
     */
    @JvmStatic
    fun kotlinInstance(): Logger {
        return Logger(3)
    }

    /**
     * Adding the given [printer] to the printers used by this [Logger]
     */
    @JvmStatic
    fun addPrinter(printer: Printer) {
        logInstance.addPrinter(printer)
    }

    /**
     * Resets the [Printer] of this [Logger] to the default.
     */
    @JvmStatic
    fun setDefaultPrinter() {
        logInstance.setDefaultPrinter()
    }

    /**
     * Sets the given [printer] as the new and only printer for this [Logger].
     */
    @JvmStatic
    fun setPrinter(printer: Printer) {
        logInstance.setPrinter(printer)
    }

    /**
     * Logs a debug message.
     *
     * @param message   The log message
     * @param tag       The tag the message is logged under
     * @param throwable The log-related throwable
     */
    @JvmStatic
    fun debug(message: String, tag: String, throwable: Throwable) {
        logInstance.debug(message, tag, throwable)
    }

    /**
     * Logs a debug message.
     * The tag will default to the class name the log is created from.
     *
     * @param message   The log message
     * @param throwable The log-related throwable
     */
    @JvmStatic
    fun debug(message: String, throwable: Throwable) {
        logInstance.debug(message, throwable)
    }

    /**
     * Logs a debug message.
     *
     * @param message The log message
     * @param tag     The tag the message is logged under
     */
    @JvmStatic
    fun debug(message: String, tag: String) {
        logInstance.debug(message, tag)
    }

    /**
     * Logs a debug message.
     * The tag will default to the class name the log is created from.
     *
     * @param message The log message
     */
    @JvmStatic
    fun debug(message: String) {
        logInstance.debug(message)
    }

    /**
     * Logs an info message.
     *
     * @param message   The log message
     * @param tag       The tag the message is logged under
     * @param throwable The log-related throwable
     */
    @JvmStatic
    fun info(message: String, tag: String, throwable: Throwable) {
        logInstance.info(message, tag, throwable)
    }

    /**
     * Logs an info message.
     * The tag will default to the class name the log is created from.
     *
     * @param message   The log message
     * @param throwable The log-related throwable
     */
    @JvmStatic
    fun info(message: String, throwable: Throwable) {
        logInstance.info(message, throwable)
    }

    /**
     * Logs an info message.
     *
     * @param message The log message
     * @param tag     The tag the message is logged under
     */
    @JvmStatic
    fun info(message: String, tag: String) {
        logInstance.info(message, tag)
    }

    /**
     * Logs an info message.
     * The tag will default to the class name the log is created from.
     *
     * @param message The log message
     */
    @JvmStatic
    fun info(message: String) {
        logInstance.info(message)
    }

    /**
     * Logs a warning message.
     *
     * @param message   The log message
     * @param tag       The tag the message is logged under
     * @param throwable The log-related throwable
     */
    @JvmStatic
    fun warn(message: String, tag: String, throwable: Throwable) {
        logInstance.warn(message, tag, throwable)
    }

    /**
     * Logs a warning message.
     * The tag will default to the class name the log is created from.
     *
     * @param message   The log message
     * @param throwable The log-related throwable
     */
    @JvmStatic
    fun warn(message: String, throwable: Throwable) {
        logInstance.warn(message, throwable)
    }

    /**
     * Logs a warning message.
     *
     * @param message The log message
     * @param tag     The tag the message is logged under
     */
    @JvmStatic
    fun warn(message: String, tag: String) {
        logInstance.warn(message, tag)
    }

    /**
     * Logs a warning message.
     * The tag will default to the class name the log is created from.
     *
     * @param message The log message
     */
    @JvmStatic
    fun warn(message: String) {
        logInstance.warn(message)
    }


    /**
     * Logs an error message.
     *
     * @param message   The log message
     * @param tag       The tag the message is logged under
     * @param throwable The log-related throwable
     */
    @JvmStatic
    fun error(message: String, tag: String, throwable: Throwable) {
        logInstance.error(message, tag, throwable)
    }

    /**
     * Logs an error message.
     * The tag will default to the class name the log is created from.
     *
     * @param message   The log message
     * @param throwable The log-related throwable
     */
    @JvmStatic
    fun error(message: String, throwable: Throwable) {
        logInstance.error(message, throwable)
    }

    /**
     * Logs an error message.
     *
     * @param message The log message
     * @param tag     The tag the message is logged under
     */
    @JvmStatic
    fun error(message: String, tag: String) {
        logInstance.error(message, tag)
    }

    /**
     * Logs an error message.
     * The tag will default to the class name the log is created from.
     *
     * @param message The log message
     */
    @JvmStatic
    fun error(message: String) {
        logInstance.error(message)
    }

    /**
     * This method deletes the old [LogFormat] and
     * returns a handle to create the new format with.
     *
     * @return log format handle
     */
    @JvmStatic
    fun newFormat(): LogFormat {
        return logInstance.newFormat()
    }

    /**
     * Sets a tag filter for this Logger.
     * Only messages with this tag will be printed.
     *
     * @param filterTag
     */
    @JvmStatic
    fun setTagFilter(filterTag: String) {
        logInstance.filterTag = filterTag
    }

    /**
     * Deletes a previously set tag filter.
     */
    @JvmStatic
    fun deleteTagFilter() {
        logInstance.deleteTagFilter()
    }

    fun newFormat(buildFun: LogFormat.() -> Unit) {
        logInstance.newFormat(buildFun)
    }

    object FormatOperations {

        /**
         * Creates a space [Builder] which prints [times] spaces.
         *
         * @param times number of spaces to print
         *
         * @return [Builder] for spaces
         */
        @JvmStatic
        fun space(times: Int): Builder {
            return Log.logInstance.logFormat.space(times)
        }

        /**
         * Creates a tab [Builder] which prints [times] tabs.

         * @param times number of tabs to print
         *
         * @return [Builder] for tabs
         */
        @JvmStatic
        fun tab(times: Int): Builder {
            return Log.logInstance.logFormat.tab(times)
        }

        /**
         * Creates a date [Builder] which prints the actual date/time in the given [format].

         * @param format String in a [SimpleDateFormat](http://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html)
         *
         * @return [Builder] for spaces
         */
        @JvmStatic
        fun date(format: String): Builder {
            return Log.logInstance.logFormat.date(format)
        }

        /**
         * Creates a [Builder] which prints the current time in milliseconds.
         *
         * @return [Builder] for time since epoch
         */
        @JvmStatic
        fun epoch(): Builder {
            return Log.logInstance.logFormat.epoch
        }

        /**
         * Creates a [Builder] which prints the [LogLevel] of the log.
         *
         * @return [Builder] for current [LogLevel]
         */
        @JvmStatic
        fun level(): Builder {
            return Log.logInstance.logFormat.level
        }

        /**
         * Creates a [Builder] which prints the log message itself.
         *
         * @return [Builder] for the message
         */
        @JvmStatic
        fun message(): Builder {
            return Log.logInstance.logFormat.message
        }

        /**
         * Creates a [Builder] which prints the tag of the log.
         *
         * @return [Builder] for the tag
         */
        @JvmStatic
        fun tag(): Builder {
            return Log.logInstance.logFormat.tag
        }

        /**
         * Creates a [Builder] which prints the fully qualified name of the occurrence of the log.
         *
         * @return [Builder] for the occurrence
         */
        @JvmStatic
        fun occurrence(): Builder {
            return Log.logInstance.logFormat.occurrence
        }

        /**
         * Creates a Builder which prints the given [text]
         *
         * @param text the text to print
         *
         * @return [Builder] for [text]
         */
        @JvmStatic
        fun text(text: String): Builder {
            return Log.logInstance.logFormat.text(text)
        }

        /**
         * Creates a [Builder] which prints all added Builders.
         *
         * @return [Builder] for current [LogLevel]
         */
        @JvmStatic
        fun line(vararg builders: Builder): Builder {
            val line = Line()
            for (i in builders.indices) {
                line.children.add(builders[i])
            }
            return line
        }

        @JvmStatic
        fun indent(vararg builders: Builder): Builder {
            val indent = Indent()
            for (i in builders.indices) {
                indent.children.add(builders[i])
            }
            return indent
        }
    }
}



