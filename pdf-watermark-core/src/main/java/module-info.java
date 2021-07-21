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

    requires pdfbox;
    requires org.apache.pdfbox.tools;
    requires fontbox;
    requires commons.logging;
    requires org.apache.commons.io;
    requires info.picocli;
    requires org.apache.pdfbox.debugger;

    exports io.github.yakirchen.watermark.core;

}