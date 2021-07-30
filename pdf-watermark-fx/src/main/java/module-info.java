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
    requires javafx.swing;
    requires javafx.graphics;

    uses javafx.application.Application;

    requires pdf.watermark.core;

    opens io.github.yakirchen.watermark.fx
            to javafx.controls, javafx.web, javafx.fxml, javafx.media, javafx.swing, javafx.graphics;

    exports io.github.yakirchen.watermark.fx;

}