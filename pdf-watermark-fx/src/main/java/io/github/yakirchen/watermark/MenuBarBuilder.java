package io.github.yakirchen.watermark;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * MenuBarBuilder
 *
 * @author yakir on 2021/08/25 12:57.
 */
public class MenuBarBuilder {

    private final MenuBar menuBar;

    public MenuBarBuilder() {
        this.menuBar = new MenuBar();
        final String os = System.getProperty("os.name");
        if (null != os && os.contains("Mac OS")) {
            menuBar.useSystemMenuBarProperty().set(true);
        }
    }

    public MenuBar build() {

        var fileMenu = new Menu("文件");
        var fileAdd  = new MenuItem("添加PDF");
        var fileDel  = new MenuItem("移除PDF");

        fileMenu.getItems()
                .addAll(fileAdd, fileDel);

        this.menuBar.getMenus()
                .add(fileMenu);

        return this.menuBar;
    }

}
