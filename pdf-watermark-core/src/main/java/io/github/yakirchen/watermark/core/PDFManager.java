package io.github.yakirchen.watermark.core;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * PDFManager
 *
 * @author yakir on 2021/07/30 20:25.
 */
public class PDFManager {

    public static byte[] createEmptyPdf() {

        try (var outStream = new ByteArrayOutputStream()) {
            var pdDoc  = new PDDocument();
            var pdPage = new PDPage();
            pdDoc.addPage(pdPage);
            pdDoc.save(outStream);
            return outStream.toByteArray();
        } catch (IOException exp) {
            exp.printStackTrace();
            return null;
        }
    }

}
