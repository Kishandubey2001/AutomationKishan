package importExport;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFReaderUtil {
	
	/**
     * Reads a PDF and extracts key-value pairs assuming format "Key: Value".
     *
     * @param filePath Path to the PDF file
     * @return Map of key-value pairs from the PDF
     */
    public static Map<String, String> extractKeyValuePairs(String filePath) {
        Map<String, String> keyValueMap = new LinkedHashMap<>();

        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFTextStripper stripper = new PDFTextStripper();
            String pdfText = stripper.getText(document);

            String[] lines = pdfText.split("\r\n|\r|\n"); // Split by line
            for (String line : lines) {
                if (line.contains(":")) {
                    String[] parts = line.split(":", 2); // Split into 2 parts
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    keyValueMap.put(key, value);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return keyValueMap;
    }

    /**
     * Gets value for a specific key from a PDF file.
     *
     * @param filePath Path to the PDF file
     * @param key      Key to look for
     * @return Corresponding value, or null if not found
     */
    public static String getValueByKey(String filePath, String key) {
        Map<String, String> map = extractKeyValuePairs(filePath);
        return map.get(key);
    }

    // Example usage
    public static void main(String[] args) {
        String path = "C:\\Users\\KishanKumarDubey\\OneDrive - GEDU Global\\Desktop\\pdfread.pdf";
        String key = "LoginWrongPassTost";

        String value = getValueByKey(path, key);
        System.out.println( key + "': " + value);



    }
}
