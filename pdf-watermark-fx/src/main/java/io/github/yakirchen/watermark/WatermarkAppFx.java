package io.github.yakirchen.watermark;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * WatermarkAppFx JavaFX版
 * <p>
 * 应用名称设置 -Xdock:name=PDF水印
 *
 * @author yakir on 2021/07/09 11:36.
 */
public class WatermarkAppFx extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        var url = this.getClass().getClassLoader().getResource("pdf_watermark.fxml");
        if (null == url) {
            throw new RuntimeException("不能加载fxml");
        }

        var fxmlLoader = new FXMLLoader(url);
        var root       = fxmlLoader.<VBox>load();
        var rootScene  = new Scene(root);

        var controller = fxmlLoader.<WatermarkController>getController();
        controller.setStage(primaryStage);

        primaryStage.initStyle(StageStyle.UNIFIED);
        primaryStage.setResizable(false);
        primaryStage.setScene(rootScene);
        primaryStage.show();

    }
}
