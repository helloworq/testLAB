import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.tools.PDFBox;

import java.io.*;

public class test {
    public static void main(String[] args) {
        convertText("C:\\Users\\12733\\Desktop\\666.pdf");
    }
    public static void convertText(String pdfPath) {
        PDDocument doc = null;
        OutputStream fos = null;
        Writer writer = null;
        PDFTextStripper stripper = null;
        try {
            doc = PDDocument.load(new File(pdfPath));
            fos = new FileOutputStream(pdfPath.substring(0, pdfPath.indexOf(".")) + ".doc");
            writer = new OutputStreamWriter(fos, "UTF-8");
            stripper = new PDFTextStripper();
            int pageNumber = doc.getNumberOfPages();
            stripper.setSortByPosition(true);
            stripper.setStartPage(1);
            stripper.setEndPage(pageNumber);
            stripper.writeText(doc, writer);
            writer.close();
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("end..");
    }
}
