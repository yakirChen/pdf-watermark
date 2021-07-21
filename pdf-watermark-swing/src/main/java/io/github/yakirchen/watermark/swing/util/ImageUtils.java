package io.github.yakirchen.watermark.swing.util;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.URL;

/**
 * ImageUtils
 *
 * @author yakir on 2021/07/19 14:11.
 */
public class ImageUtils {

    public static Image createImage(String path, String description) {

        URL imageURL = ImageUtils.class.getClassLoader().getResource(path);

        if (null != imageURL) {
            return new ImageIcon(imageURL, description).getImage();
        } else {
            System.err.println("资源未找到 " + path);
            return null;
        }
    }
}
