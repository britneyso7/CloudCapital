package model;

import model.Account;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @BeforeEach
    void setup() {
        User.resetIdCounter();
    }

    @Test
    void testWriterInvalidFile() {
        try {
            User user = new User("bso7", 1);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyUser() {
        try {
            User user = new User("bso7", 1);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyUser.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyUser.json");
            User readUser = reader.read();
            assertEquals("bso7", readUser.getUserName());
            assertEquals(1, readUser.getUserID());
            assertEquals(2, readUser.getUserAccounts().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralUser() {
        try {
            User user = new User("bso7", 2);
            user.addUserAccount(new Account(10, "chequing", 150.0));
            user.addUserAccount(new Account(11, "savings", 300.0));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralUser.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralUser.json");
            User readUser = reader.read();

            assertEquals("bso7", readUser.getUserName());
            assertEquals(2, readUser.getUserID());

            List<Account> accounts = readUser.getUserAccounts();
            assertEquals(4, accounts.size());

            assertEquals(4, accounts.size());
            checkAccount(1, "Chequing", 0, accounts.get(0));
            checkAccount(0, "Chequing", 0, accounts.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
