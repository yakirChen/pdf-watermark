package io.github.yakirchen.watermark.swing;

import javax.swing.Box;
import javax.swing.JComponent;
import java.awt.Dimension;

/**
 * BoxBuilder
 *
 * @author yakir on 2021/07/27 17:46.
 */
public class BoxBuilder {

    private final Box box;

    private BoxBuilder(Box box) {
        this.box = box;
    }

    public static BoxBuilder verticalBox(Dimension size) {
        var box = Box.createVerticalBox();
        box.setPreferredSize(size);
        box.setMaximumSize(size);
        box.setMinimumSize(size);
        return new BoxBuilder(box);
    }

    public static BoxBuilder horizontalBox(Dimension size) {
        var box = Box.createHorizontalBox();
        box.setPreferredSize(size);
        box.setMaximumSize(size);
        box.setMinimumSize(size);
        return new BoxBuilder(box);
    }

    public BoxBuilder add(JComponent box) {
        this.box.add(box);
        return this;
    }

    public BoxBuilder addVerticalGlue() {
        this.box.add(Box.createVerticalGlue());
        return this;
    }

    public BoxBuilder addHorizontalBox() {
        this.box.add(Box.createHorizontalGlue());
        return this;
    }

    public BoxBuilder addVerticalStrut(int height) {
        this.box.add(Box.createVerticalStrut(height));
        return this;
    }

    public BoxBuilder addHorizontalStrut(int width) {
        this.box.add(Box.createHorizontalStrut(width));
        return this;
    }

    public Box get() {
        return this.box;
    }

}
