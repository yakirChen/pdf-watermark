package io.github.yakirchen.watermark.core;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.blend.BlendMode;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.util.Matrix;

import java.io.File;
import java.io.IOException;

/**
 * PDFWatermark
 *
 * @author yakir on 2021/07/08 14:53.
 */
public class PDFWatermark {

    public static PDFWatermarkBuilder builder(Watermark watermark) {
        return new PDFWatermarkBuilder(watermark);
    }

    public static class PDFWatermarkBuilder {

        private Watermark watermark;

        public PDFWatermarkBuilder(Watermark watermark) {
            this.watermark = watermark;
        }

        public void mark() {

            var origin     = watermark.getOrigin();
            var override   = watermark.getOverride();
            var suffix     = watermark.getSuffix();
            var alpha      = watermark.getAlpha();
            var color      = watermark.getColorRGB();
            var fontSize   = watermark.getFontSize();
            var fontFamily = watermark.getFontFamily();
            var text       = watermark.getText();

            var originFile = new File(origin);

            try (var pdDoc = Loader.loadPDF(originFile)) {

                pdDoc.setAllSecurityToBeRemoved(true);

                var fontHanaMinA = PDType0Font.load(pdDoc, PDFFont.load(PDFFont.FONT_A));
//                var fontHanaMinB = PDType0Font.load(pdDoc, PDFFont.load(PDFFont.FONT_B));

                for (var page : pdDoc.getPages()) {
                    try (var cs = new PDPageContentStream(pdDoc, page, PDPageContentStream.AppendMode.APPEND, true, true)) {

                        var resources = page.getResources();
                        resources.add(fontHanaMinA);
//                        resources.add(fontHanaMinB);
                        page.setResources(resources);

                        var width    = page.getMediaBox().getWidth();
                        var height   = page.getMediaBox().getHeight();
                        var rotation = page.getRotation();

                        switch (rotation) {
                            case 90:
                                width = page.getMediaBox().getHeight();
                                height = page.getMediaBox().getWidth();
                                cs.transform(Matrix.getRotateInstance(Math.toRadians(90), height, 0));
                                break;
                            case 180:
                                cs.transform(Matrix.getRotateInstance(Math.toRadians(180), width, height));
                                break;
                            case 270:
                                width = page.getMediaBox().getHeight();
                                height = page.getMediaBox().getWidth();
                                cs.transform(Matrix.getRotateInstance(Math.toRadians(270), 0, width));
                                break;
                            default:
                                break;
                        }

                        var stringWidth    = fontHanaMinA.getStringWidth(text) / 1000 * fontSize;
                        var diagonalLength = (float) Math.sqrt(width * width + height * height);
                        var angle          = (float) Math.atan2(height, width);
                        var x              = (diagonalLength - stringWidth) / 2; // "horizontal" position in rotated world
                        var y              = -fontSize / 4; // 4 is a trial-and-error thing, this lowers the text a bit
                        cs.transform(Matrix.getRotateInstance(angle, 0, 0));
                        cs.setFont(fontHanaMinA, fontSize);
                        // cs.setRenderingMode(RenderingMode.STROKE) // for "hollow" effect

                        var gs = new PDExtendedGraphicsState();
                        gs.setNonStrokingAlphaConstant(alpha);
                        gs.setStrokingAlphaConstant(alpha);
                        gs.setBlendMode(BlendMode.MULTIPLY);
                        gs.setLineWidth(3f);
                        cs.setGraphicsStateParameters(gs);

                        cs.setNonStrokingColor(color);
                        cs.setStrokingColor(color);

                        cs.beginText();
                        cs.newLineAtOffset(x, y);
                        cs.showText(text);
                        cs.endText();
                    }
                }
                var targetFile = origin;
                if (!override) {
                    targetFile = targetFile.trim();
                    targetFile = targetFile.substring(0, targetFile.length() - 4).concat(Watermark.DEFAULT_SUFFIX).concat(".pdf");
                }

                pdDoc.save(new File(targetFile));
                System.out.println(targetFile);
            } catch (IOException exp) {
                exp.printStackTrace();
            }
        }

    }

}
