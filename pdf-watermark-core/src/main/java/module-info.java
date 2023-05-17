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
    requires jdk.unsupported;

    requires org.apache.pdfbox;
    requires org.apache.fontbox;
//    requires info.picocli;
//    requires org.apache.pdfbox.tools;
//    requires org.apache.pdfbox.debugger;
//    requires org.apache.commons.io;

//    requires pdf.watermark.api;
    requires pdf.watermark.log;

//    uses io.github.yakirchen.watermark.api.PDFEntity;
    uses io.github.yakirchen.watermark.log.Log;

    exports io.github.yakirchen.watermark.core;

}