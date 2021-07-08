package io.github.yakirchen.watermark.core;

import org.junit.Test;

/**
 * TestPDFWatermark
 *
 * @author yakir on 2021/07/08 15:32.
 */
public class TestPDFWatermark {

    @Test
    public void test0() {

        String origin = "/Users/yakir/Library/Mobile Documents/com~apple~CloudDocs/docs/shushi/泰州石化/Pamirs后端基本概念.pdf";

        var text = "你好🙂!";

        var watermark = new Watermark()
                .setOrigin(origin)
                .setAlpha(0.2f)
                .setColorRGB(225, 0, 0)
                .setFontSize(130)
                .setText(text);

        PDFWatermark.builder(watermark)
                .mark();
    }

}