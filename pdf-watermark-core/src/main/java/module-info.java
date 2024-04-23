import io.github.yakirchen.watermark.core.log.Log;

/**
 * module-info
 *
 * @author yakir on 2021/07/19 17:09.
 */
module pdf.watermark.core {

    requires java.base;
    requires java.desktop;
    requires java.xml;
    requires java.logging;
    requires java.scripting;
    requires jdk.unsupported;

    requires org.apache.pdfbox;
    requires org.apache.fontbox;
//    requires info.picocli;
//    requires org.apache.pdfbox.tools;
//    requires org.apache.pdfbox.debugger;
//    requires org.apache.commons.io;

//    requires pdf.watermark.api;

//    uses io.github.yakirchen.watermark.api.PDFEntity;
    uses Log;


    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires org.apache.logging.log4j.jul;
    requires org.apache.logging.log4j.jpl;

    exports io.github.yakirchen.watermark.core.log;
    exports io.github.yakirchen.watermark.core;

    opens io.github.yakirchen.watermark.core.log;
}