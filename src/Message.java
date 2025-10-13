public class Message {
    private String content;
    private String sender;

    // Add constructor
    public Message(String content, String sender) {
        this.content = content;
        this.sender = sender;
    }

    // Basic methods - FIXED SYNTAX
    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }
}