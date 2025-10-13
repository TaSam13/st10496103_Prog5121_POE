// JSON storage implementation assisted by ChatGPT
// Prompt: "I need help creating a Java method to store Message objects in a JSON file for my school assignment."
// ChatGPT provided the core MessageStorage class structure and JSON handling logic.
// Author: Samuel Damas
// Date: 13 October 2025

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessageStorage {
    private static final String DIRECTORY_PATH = "messages";
    private static final String FILE_PATH = DIRECTORY_PATH + File.separator + "messages.json";
    private static final ObjectMapper mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    public static void storeMessage(Message message) {
        try {
            File directory = new File(DIRECTORY_PATH);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File file = new File(FILE_PATH);
            List<Message> messages = new ArrayList<>();

            // If file exists, read existing messages WITH ERROR HANDLING
            if (file.exists() && file.length() > 0) {
                try {
                    messages = mapper.readValue(file, new TypeReference<List<Message>>() {});
                } catch (Exception e) {
                    // If reading fails, start fresh
                    System.out.println("Starting fresh message storage due to corruption...");
                    messages = new ArrayList<>();
                }
            }

            messages.add(message);
            mapper.writeValue(file, messages);

            // REMOVED the success message to reduce console clutter

        } catch (IOException e) {
            System.err.println("Error saving message: " + e.getMessage());
        }
    }
}