package io.github.yakirchen.watermark.core;

import org.junit.jupiter.api.Test;

/**
 * TestPDFWatermark
 *
 * @author yakir on 2021/07/08 15:32.
 */
public class TestPDFWatermark {

    @Test
    public void test0() {

        String origin = "/Users/yakir/Developer/think/pdf-watermark/docs/test.pdf";

        var text = "水印";

        var watermark = new Watermark()
                .setOrigin(origin)
                .setAlpha(0.35f)
                .setColorRGB(0, 0, 0)
                .setFontSize(50)
                .setText(text);

        PDFWatermark.builder(watermark)
                .mark();
    }

}