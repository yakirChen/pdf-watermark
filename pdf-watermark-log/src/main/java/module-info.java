/**
 * module-info
 *
 * @author yakir on 2021/09/17 12:35.
 */
module pdf.watermark.log {

    requires java.logging;
    requires java.scripting;

    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires org.apache.logging.log4j.jul;
    requires org.apache.logging.log4j.iostreams;
    requires org.apache.logging.log4j.jcl;
    requires org.apache.logging.log4j.jpl;

    exports io.github.yakirchen.watermark.log;
    opens io.github.yakirchen.watermark.log;

}