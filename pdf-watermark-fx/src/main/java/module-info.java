/**
 * module-info
 *
 * @author yakir on 2021/07/19 17:11.
 */
module pdf.watermark.fx {

    requires java.base;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.web;

    requires pdf.watermark.api;
    requires pdf.watermark.core;
    requires pdf.watermark.logging;

    uses javafx.application.Application;

    exports io.github.yakirchen.watermark;
    opens io.github.yakirchen.watermark
            to
            javafx.controls,
            javafx.web,
            javafx.fxml,
            javafx.media,
            javafx.graphics;

}