import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Message {
    private final String content;
    private final String sender;
    private final String recipient;

    public Message(String content, String sender, String recipient) {
        this.content = content;
        this.sender = sender;
        this.recipient = recipient;
    }

    // Getters for message properties
    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

}

class MessagePrinter { // Xử lí riêng

    public static void printSummary(Message message) {
        System.out.println("Content: " + message.getContent());
        System.out.println("Sender: " + message.getSender());
        System.out.println("Recipient: " + message.getRecipient());
    }

    public static void printDetails(Message message) { // độ dài nội dung và biến đổi tên người gửi và người nhận.

        printSummary(message);
        System.out.println("Content Length: " + message.getContent().length());
        System.out.println("Sender Uppercase: " + message.getSender().toUpperCase());
        System.out.println("Recipient Lowercase: " + message.getRecipient().toLowerCase());
    } // --> Vi phạm nguyên lý "Single Responsibility Principle (SRP)"
}

class MessagingService {
    private final Map<String, List<Message>> inbox; // Lưu trữ tin nhắn

    public MessagingService() {
        this.inbox = new HashMap<>();
    }

    public void sendMessage(String content, String sender, String recipient) {
        Message message = new Message(content, sender, recipient);
        inbox.computeIfAbsent(message.getRecipient(), k -> new ArrayList<>()).add(message);
    }

    public List<Message> getMessagesForRecipient(String recipient) {
        return inbox.getOrDefault(recipient, new ArrayList<>());
    }

    public void printAllMessages() {
        for (String recipient : inbox.keySet()) {

            List<Message> messages = inbox.get(recipient);

            if (messages != null) {
                for (Message message : messages) {
                    MessagePrinter.printSummary(message); // Sử dụng MessagePrinter để in thông tin.
                }
            }
        }
    }
}

public class CommunicationManager_V1 {
    public static void main(String[] args) {
        MessagingService messagingService = new MessagingService();

        // sending messages
        messagingService.sendMessage("Hello, tenant!", "Property Manager", "Tenant A");
        messagingService.sendMessage("Important notice: Rent due next week.", "Property Owner", "Tenant A");
        messagingService.sendMessage("Maintenance request received.", "Tenant A", "Property Manager");

        // retrieving messages for a recipient
        List<Message> tenantAMessages = messagingService.getMessagesForRecipient("Tenant A");
        for (Message message : tenantAMessages) {
            System.out.println("From: " + message.getSender() + ", Content: " + message.getContent());
        }

        // Calling the new method
        Message exampleMessage = new Message("Test", "Sender", "Recipient");
        MessagePrinter.printDetails(exampleMessage);

        messagingService.printAllMessages();
    }
}

/*
 * Code smell
 * Mã lặp lại (duplicate code).
 * Các hàm hoặc lớp quá dài hoặc phức tạp.
 * Các tên biến hoặc phương thức không rõ ràng.
 * Phương thức làm quá nhiều việc (vi phạm nguyên lý
 * "Single Responsibility Principle").
 * Thiếu tính linh hoạt trong thiết kế hệ thống.
 * 
 */
