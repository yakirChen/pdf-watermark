/**
 * module-info
 *
 * @author yakir on 2021/07/19 17:09.
 */
module pdf.watermark.core {

    requires java.desktop;

    requires pdfbox;
    requires org.apache.pdfbox.tools;
    requires fontbox;

    exports io.github.yakirchen.watermark.core;

}