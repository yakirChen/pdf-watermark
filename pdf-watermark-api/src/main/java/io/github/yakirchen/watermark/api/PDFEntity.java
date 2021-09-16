package io.github.yakirchen.watermark.api;

import java.io.Serial;
import java.io.Serializable;

/**
 * PDFEntity
 *
 * @author yakir on 2021/07/12 11:52.
 */
public class PDFEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -3946611633016736682L;

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
