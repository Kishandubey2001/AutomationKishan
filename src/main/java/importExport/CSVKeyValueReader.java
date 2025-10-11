package importExport;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class CSVKeyValueReader {

	
	/**
     * Loads key-value pairs from CSV file into a Map.
     *
     * @param filePath Path to the CSV file
     * @return Map of key-value pairs
     */
    public static Map<String, String> loadCsvAsMap(String filePath) {
        Map<String, String> map = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Split by first comma
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    map.put(key, value);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     * Get value by key from CSV.
     *
     * @param filePath Path to CSV
     * @param key      Key to look up
     * @return Corresponding value
     */
    public static String getValueByKey(String filePath, String key) {
        Map<String, String> dataMap = loadCsvAsMap(filePath);
        return dataMap.get(key);
    }

    // Example usage
    public static void main(String[] args) {
        String path = "C:\\Users\\KishanKumarDubey\\OneDrive - GEDU Global\\Desktop\\datac.csv";
        String key = "LoginWrongPassTost";

        String value = getValueByKey(path, key);
        System.out.println("Value for key '" + key + "': " + value);
    }

	
}
