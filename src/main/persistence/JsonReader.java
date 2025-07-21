package persistence;

// Modelled after JsonRead.java from JsonSerializationDemo project

import model.Account;
import model.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public User read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUser(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private User parseUser(JSONObject jsonObject) {

        
        String userName = jsonObject.getString("userName");
        int userID = jsonObject.getInt("userID");
        User user = new User(userName, userID);
        addAccounts(user, jsonObject);
        return user;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addAccounts(User user, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("thingies");
        for (Object json : jsonArray) {
            JSONObject nextAccount= (JSONObject) json;
            addAccount(user, nextAccount);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addAccount(User user, JSONObject jsonObject) {
        int accountNum = jsonObject.getInt("accountNum");
        String accountType = jsonObject.getString("accountType");
        double funds = jsonObject.getDouble("funds");
        
        Account account = new Account(accountNum, accountType, funds);

        user.addUserAccount(account);
    }
}
