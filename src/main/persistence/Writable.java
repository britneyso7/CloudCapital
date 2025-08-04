package persistence;

import org.json.JSONObject;

// Modelled after Writable in JsonSerializationDemo project

public interface Writable {
    // EFFECTS: returns this as JSON Object
    JSONObject toJson();
}
