import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    private Message message1;
    private Message message2;

    @Before
    public void setUp() {
        // Test Data for Message 1
        message1 = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight", 0);

        // Test Data for Message 2
        message2 = new Message("08575975889", "Hi Keegan, did you receive the payment?", 1);
    }

    // ===== MESSAGE LENGTH TESTS =====
    @Test
    public void testMessageLength_Success() {
        Message shortMessage = new Message("+27718693002", "Short message", 0);
        assertEquals("Message ready to send.", shortMessage.validateMessageLength());
    }

    @Test
    public void testMessageLength_Failure() {
        String longText = "A".repeat(251); // 251 characters
        Message longMessage = new Message("+27718693002", longText, 0);
        String expected = "Message exceeds 250 characters by 1, please reduce size.";
        assertEquals(expected, longMessage.validateMessageLength());
    }

    @Test
    public void testMessageLength_Exact250() {
        String exact250 = "A".repeat(250);
        Message exactMessage = new Message("+27718693002", exact250, 0);
        assertEquals("Message ready to send.", exactMessage.validateMessageLength());
    }

    // ===== RECIPIENT NUMBER TESTS =====
    @Test
    public void testRecipientNumber_Success() {
        assertEquals("Cell phone number successfully captured.", message1.validateRecipientNumber());
    }

    @Test
    public void testRecipientNumber_Failure() {
        String expected = "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        assertEquals(expected, message2.validateRecipientNumber());
    }

    // ===== MESSAGE HASH TESTS =====
    @Test
    public void testMessageHash_Format() {
        String hash = message1.generateMessageHash();
        // Should be in format: 00:0:HITONIGHT
        assertTrue("Hash should match format NN:N:FIRSTLAST", hash.matches("\\d{2}:\\d:[A-Z]+[A-Z]+"));
    }

    @Test
    public void testMessageHash_Content() {
        String hash = message1.generateMessageHash();
        // For "Hi Mike, can you join us for dinner tonight"
        // First word: "HI", Last word: "TONIGHT"
        assertTrue("Hash should contain HITONIGHT", hash.endsWith("HITONIGHT"));
    }

    // ===== MESSAGE ID TESTS =====
    @Test
    public void testMessageId_Creation() {
        String messageId = message1.generateMessageId();
        assertTrue("Message ID should be generated", messageId != null && !messageId.isEmpty());
        assertTrue("Message ID should start with MSG", messageId.startsWith("MSG"));
    }

    // ===== MESSAGE STATUS TESTS =====
    @Test
    public void testMessageStatus_Send() {
        message1.setStatus(1); // Send
        assertEquals("sent", message1.getStatus());
    }

    @Test
    public void testMessageStatus_Discard() {
        message2.setStatus(2); // Discard
        assertEquals("discarded", message2.getStatus());
    }

    @Test
    public void testMessageStatus_Store() {
        Message storeMessage = new Message("+27718693002", "Test store", 2);
        storeMessage.setStatus(3); // Store
        assertEquals("stored", storeMessage.getStatus());
    }
}