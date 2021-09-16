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

    requires commons.logging;
    requires info.picocli;
    requires org.apache.pdfbox;
    requires org.apache.pdfbox.tools;
    requires org.apache.pdfbox.debugger;
    requires org.apache.fontbox;
    requires org.apache.commons.io;

    exports io.github.yakirchen.watermark.core;

}