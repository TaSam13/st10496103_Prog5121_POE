import java.util.regex.Pattern;
import java.util.regex.Matcher;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
    private String messageId;
    private String recipient;
    private String content;
    private String messageHash;
    private String status;
    private int messageNumber;

    // Default constructor (needed for Jackson)
    public Message() {}

    // Main constructor for creating messages in code
    @JsonCreator
    public Message(
            @JsonProperty("recipient") String recipient,
            @JsonProperty("content") String content,
            @JsonProperty("messageNumber") int messageNumber) {
        this.recipient = recipient;
        this.content = content;
        this.messageNumber = messageNumber;
        this.messageId = generateMessageId();
        this.messageHash = generateMessageHash();
        this.status = "created";
    }

    // Setters (Jackson needs these to fill fields)
    public void setMessageId(String messageId) { this.messageId = messageId; }
    public void setRecipient(String recipient) { this.recipient = recipient; }
    public void setContent(String content) { this.content = content; }
    public void setMessageHash(String messageHash) { this.messageHash = messageHash; }
    public void setStatus(String status) { this.status = status; }
    public void setMessageNumber(int messageNumber) { this.messageNumber = messageNumber; }

    // Getters
    public String getMessageId() { return messageId; }
    public String getRecipient() { return recipient; }
    public String getContent() { return content; }
    public String getMessageHash() { return messageHash; }
    public String getStatus() { return status; }
    public int getMessageNumber() { return messageNumber; }

    // Generate message ID
    public String generateMessageId() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String id = "MSG" + timestamp.substring(timestamp.length() - 6) + messageNumber;
        return id.length() > 10 ? id.substring(0, 10) : id;
    }

    // Generate message hash
    public String generateMessageHash() {
        String numericPart = messageId.replaceAll("[^0-9]", "");
        String firstTwoNumbers = numericPart.length() >= 2 ? numericPart.substring(0, 2)
                : numericPart.length() == 1 ? "0" + numericPart : "00";

        String cleanContent = content.replaceAll("[^a-zA-Z0-9 ]", "");
        String[] words = cleanContent.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0].toUpperCase() : "";
        String lastWord = words.length > 1 ? words[words.length - 1].toUpperCase() : firstWord;

        return firstTwoNumbers + ":" + messageNumber + ":" + firstWord + lastWord;
    }

    // Validate recipient - UPDATED TO RETURN STRING
    public String validateRecipientNumber() {
        String regex = "^\\+27\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(recipient);
        if (matcher.matches()) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Validate message length - UPDATED TO RETURN STRING AND 250 CHARS
    public String validateMessageLength() {
        if (content.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = content.length() - 250;
            return "Message exceeds 250 characters by " + excess + ", please reduce size.";
        }
    }

    // Set message status
    public void setStatus(int action) {
        switch (action) {
            case 1: this.status = "sent"; break;
            case 2: this.status = "discarded"; break;
            case 3: this.status = "stored"; break;
            default: this.status = "pending";
        }
    }

    // Display message details
    public String getMessageDetails() {
        return "Message ID: " + messageId +
                "\nRecipient: " + recipient +
                "\nMessage: " + (content.length() > 50 ? content.substring(0, 50) + "..." : content) +
                "\nMessage Hash: " + messageHash +
                "\nStatus: " + status;
    }
}