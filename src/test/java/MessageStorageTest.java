import org.junit.Test;
import java.io.File;
import static org.junit.Assert.*;

public class MessageStorageTest {

    @Test
    public void testMessageStorageFileCreation() {
        Message testMessage = new Message("+27718693002", "Test message for storage", 1);
        MessageStorage.storeMessage(testMessage);

        File file = new File("messages/messages.json");
        assertTrue("Message storage file should be created", file.exists());
    }

    @Test
    public void testMessageStorageWithTestData() {
        Message testMessage = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight", 1);
        testMessage.setStatus(1); // Set to sent

        // This should not throw an exception
        MessageStorage.storeMessage(testMessage);

        // Verify the message has correct hash format
        String hash = testMessage.generateMessageHash();
        assertTrue("Hash should be in correct format", hash.matches("\\d{2}:1:HITONIGHT"));
    }
}