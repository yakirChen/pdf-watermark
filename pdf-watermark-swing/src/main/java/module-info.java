/**
 * module-info
 *
 * @author yakir on 2021/07/19 17:11.
 */
module pdf.watermark.swing {

    requires java.base;
    requires java.desktop;
    requires java.xml;
    requires java.logging;
    requires jdk.unsupported;

    requires pdf.watermark.core;

}