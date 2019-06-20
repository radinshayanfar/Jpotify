package helper;

import java.util.HashMap;
import java.util.Map;

final public class StringHelper {
    private StringHelper() {
    }

    public static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] pair = param.split("=");
            if (pair.length > 1) {
                result.put(pair[0], pair[1]);
            } else {
                result.put(pair[0], "");
            }
        }
        return result;
    }


    public static String randomString(int count) {
        final String ALPHABET_STRING = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHABET_STRING.length());
            builder.append(ALPHABET_STRING.charAt(character));
        }
        return builder.toString();
    }
}
