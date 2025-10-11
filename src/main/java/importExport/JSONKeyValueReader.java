package importExport;
import java.io.File;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONKeyValueReader {
	
	 /**
     * Reads a JSON file and loads it into a Map.
     *
     * @param filePath Path to the JSON file
     * @return Map with key-value pairs
     */
    public static Map<String, String> loadJsonAsMap(String filePath) {
        Map<String, String> keyValueMap = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            keyValueMap = mapper.readValue(new File(filePath), Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyValueMap;
    }

    /**
     * Gets value for a specific key from a JSON file.
     *
     * @param filePath Path to the JSON file
     * @param key      Key to look for
     * @return Corresponding value, or null if not found
     */
    public static String getValueByKey(String filePath, String key) {
        Map<String, String> map = loadJsonAsMap(filePath);
        return map != null ? map.get(key) : null;
    }

    // Example usage
    public static void main(String[] args) {
        String path = "C:\\Users\\KishanKumarDubey\\OneDrive - GEDU Global\\Desktop\\data.json";
        String key = "LoginWrongPassTost";

        String value = getValueByKey(path, key);
        System.out.println("Value for key '" + key + "': " + value);
    }

}
