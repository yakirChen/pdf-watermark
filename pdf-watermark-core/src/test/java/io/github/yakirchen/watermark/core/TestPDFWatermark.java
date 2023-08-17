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

        String origin = "/Volumes/sm/work/shushi/Oinone7天从入门到精通-PDF.pdf";

        var text = "Oinone-西安华越";

        var watermark = new Watermark()
                .setOrigin(origin)
                .setAlpha(0.35f)
                .setColorRGB(225, 0, 0)
                .setFontSize(60)
                .setText(text);

        PDFWatermark.builder(watermark)
                .mark();
    }

}