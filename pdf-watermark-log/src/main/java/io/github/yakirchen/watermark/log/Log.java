package io.github.yakirchen.watermark.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Log
 *
 * @author yakir on 2021/09/17 12:06.
 */
public class Log {

    private static final Logger log = LogManager.getLogger("Watermark");

    public static void info(final String msg, final Object... objs) {
        log.info(msg, objs);
    }

    public static void error(final String msg, final Throwable throwable) {
        log.error(msg, throwable);
    }

}
