package json;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private final HashMap<String, Json> jsonPairs = new HashMap<>();

    public JsonObject(JsonPair... jsonPairs) {
        for (JsonPair pair : jsonPairs) {
            this.jsonPairs.put(pair.key, pair.value);
        }
    }

    @Override
    public String toJson() {
        if (jsonPairs.isEmpty()) {
            return "{}";
        }
        StringBuilder string = new StringBuilder();
        for (Map.Entry<String, Json> pair : jsonPairs.entrySet()) {
            string.append(pair.getKey());
            string.append(": ");
            string.append(pair.getValue().toJson());
            string.append(", ");
        }
        return "{" + string.substring(0, string.length() - 2) + "}";
    }

    public void add(JsonPair jsonPair) {
        jsonPairs.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        return jsonPairs.get(name);
    }

    public JsonObject projection(String... names) {
        JsonObject newObject = new JsonObject();
        for (String name : names) {
            Json value = find(name);
            if (value != null){
                newObject.add(new JsonPair(name, value));
            }
        }
        return newObject;
    }
}
