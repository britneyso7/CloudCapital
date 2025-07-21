package model;

import model.Account;
import model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import persistence.JsonReader;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @BeforeEach
        void setup() {
        User.resetIdCounter();
}
        @Test
        void testReaderNonExistentFile() {
            JsonReader reader = new JsonReader("./data/noSuchFile.json");
            try {
                User user = reader.read();
                fail("IOException expected");
            } catch (IOException e) {
                // pass
            }
        }
    
        @Test
        void testReaderEmptyUser() {
            JsonReader reader = new JsonReader("./data/testWriterEmptyUser.json");
            try {
                User user = reader.read();
                assertEquals("bso7", user.getUserName());
                assertEquals(1, user.getUserID());
                assertEquals(2, user.getUserAccounts().size());
            } catch (IOException e) {
                fail("Couldn't read from file");
            }
        }
    
        @Test
        void testReaderGeneralUser() {
            JsonReader reader = new JsonReader("./data/testWriterGeneralUser.json");
            try {
                User user = reader.read();
                assertEquals("bso7", user.getUserName());
                assertEquals(2, user.getUserID());
    
                List<Account> accounts = user.getUserAccounts();
                assertEquals(4, accounts.size());
                checkAccount(0, "Chequing", 0, accounts.get(0));
                checkAccount(0, "Chequing", 0, accounts.get(1));
            } catch (IOException e) {
                fail("Couldn't read from file");
            }
        }
    }


 