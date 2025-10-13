import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Message {
    private final String messageId;
    private final String recipient;
    private final String content;
    private final String messageHash;
    private String status;
    private final int messageNumber;

    // Constructor
    public Message(String recipient, String content, int messageNumber) {
        this.recipient = recipient;
        this.content = content;
        this.messageNumber = messageNumber;
        this.messageId = generateMessageId();
        this.messageHash = generateMessageHash();
        this.status = "created";
    }

    // Generate unique message ID (string manipulation with substring)
    public String generateMessageId() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        // Take last 6 characters of timestamp + message number
        String id = "MSG" + timestamp.substring(timestamp.length() - 6) + messageNumber;
        return id.length() > 10 ? id.substring(0, 10) : id; // Ensure ≤10 characters
    }

    // Generate message hash (string manipulation)
    public String generateMessageHash() {
        // Format: (first 2 chars of message)(message number)(first word)(last word)
        String firstTwoChars = content.length() >= 2 ? content.substring(0, 2) : content;
        String[] words = content.split(" ");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;

        return "(" + firstTwoChars + ")(" + messageNumber + ")(" + firstWord + ")(" + lastWord + ")";
    }

    // Validate recipient number (reuse Login validation)
    public boolean validateRecipientNumber() {
        // Reuse the same regex from Login class
        String regex = "^\\+27\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(recipient);
        return matcher.matches();
    }

    // Validate message content length
    public boolean validateMessageLength() {
        return content.length() <= 300;
    }

    // Set message status based on user action
    public void setStatus(int action) {
        switch (action) {
            case 1: this.status = "sent"; break;
            case 2: this.status = "discarded"; break;
            case 3: this.status = "stored"; break;
            default: this.status = "pending";
        }
    }

    // Get message details for display
    public String getMessageDetails() {
        return "Message ID: " + messageId +
                "\nRecipient: " + recipient +
                "\nMessage: " + (content.length() > 50 ? content.substring(0, 50) + "..." : content) +
                "\nMessage Hash: " + messageHash +
                "\nStatus: " + status;
    }

    // Getters
    public String getMessageId() { return messageId; }
    public String getRecipient() { return recipient; }
    public String getContent() { return content; }
    public String getMessageHash() { return messageHash; }
    public String getStatus() { return status; }
}