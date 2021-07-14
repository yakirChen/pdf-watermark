package io.github.yakirchen.watermark.swing.entity;

/**
 * PDFEntity
 *
 * @author yakir on 2021/07/12 11:52.
 */
public class PDFEntity {

    private String name;
    private String path;

    public String getName() {
        return name;
    }

    public PDFEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getPath() {
        return path;
    }

    public PDFEntity setPath(String path) {
        this.path = path;
        return this;
    }

    @Override
    public String toString() {
        return " " + name + " ";
    }
}
