package Assigment_Part3;


import org.junit.Before;
import org.junit.Test;


import javax.swing.JOptionPane;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.Assert.*;


import java.util.ArrayList;

import java.lang.reflect.Field;


import java.util.ArrayList;

import java.util.ArrayList;


public class MessageTest {

  
    private Message message;
    private JOptionPane mockOptionPane;

    private static final String TEST_MESSAGE_ID = "MSG1234567";
    private static final String TEST_RECIPIENT = "+2712345678";
    private static final String TEST_MESSAGE = "Test message content";
    private static final String TEST_SENDER = "TestUser";
    private static final String TEST_TIMESTAMP = "2023-01-01 12:00:00";

    @Before
    public void setUp() throws Exception {
        // Reset static fields before each test
        resetStaticFields();
        
        // Initialize test message
        message = new Message(TEST_MESSAGE_ID, TEST_RECIPIENT, TEST_MESSAGE, TEST_SENDER, TEST_TIMESTAMP);
    }

    // Helper method to reset static fields
    private void resetStaticFields() throws Exception {
        Field totalMessagesField = Message.class.getDeclaredField("totalMessages");
        totalMessagesField.setAccessible(true);
        totalMessagesField.setInt(null, 0);
        
        Field sentMessagesField = Message.class.getDeclaredField("sentMessages");
        sentMessagesField.setAccessible(true);
        ((ArrayList<?>) sentMessagesField.get(null)).clear();
    }

    @Test
    public void testCheckMessageID_Valid() {
        assertTrue(message.checkMessageID());
    }

    @Test
    public void testCheckMessageID_InvalidTooLong() {
        Message invalidMessage = new Message("12345678901", TEST_RECIPIENT, TEST_MESSAGE, TEST_SENDER, TEST_TIMESTAMP);
        assertFalse(invalidMessage.checkMessageID());
    }

    @Test
    public void testCheckRecipientCell_Valid() {
        assertEquals(1, message.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientCell_InvalidTooLong() {
        Message invalidMessage = new Message(TEST_MESSAGE_ID, "+27123456789", TEST_MESSAGE, TEST_SENDER, TEST_TIMESTAMP);
        assertEquals(0, invalidMessage.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientCell_InvalidCharacters() {
        Message invalidMessage = new Message(TEST_MESSAGE_ID, "+27abc12345", TEST_MESSAGE, TEST_SENDER, TEST_TIMESTAMP);
        assertEquals(0, invalidMessage.checkRecipientCell());
    }

    @Test
    public void testCreateMessageHash() {
        String expectedHash = "MS:19:Testcontent";
        assertEquals(expectedHash, message.createMessageHash());
    }

    @Test
    public void testCreateMessageHash_SingleWord() {
        Message singleWordMessage = new Message(TEST_MESSAGE_ID, TEST_RECIPIENT, "Hello", TEST_SENDER, TEST_TIMESTAMP);
        String expectedHash = "MS:5:HelloHello";
        assertEquals(expectedHash, singleWordMessage.createMessageHash());
    }

    @Test
    public void testToString() {
        String expected = "Message ID: " + TEST_MESSAGE_ID + "\n" +
                          "Message Hash: " + message.createMessageHash() + "\n" +
                          "Recipient: " + TEST_RECIPIENT + "\n" +
                          "Message: " + TEST_MESSAGE;
        assertEquals(expected, message.toString());
    }

    @Test
    public void testReturnTotalMessages() throws Exception {
        assertEquals(0, message.returnTotalMessages());
        
        // Add a message and verify count
        addTestMessageToSent();
        assertEquals(1, message.returnTotalMessages());
    }

    @Test
    public void testPrintMessages_Empty() {
        assertEquals("", message.printMessages());
    }

    @Test
    public void testPrintMessages_WithContent() throws Exception {
        addTestMessageToSent();
        String result = message.printMessages();
        assertTrue(result.contains("Message ID: " + TEST_MESSAGE_ID));
        assertTrue(result.contains("RecipientID: " + TEST_RECIPIENT));
    }

    @Test
    public void testStoreMessage() {
        // This is a basic test - in a real scenario you would mock the file operations
        message.storeMessage();
        // Just verify no exceptions are thrown
        assertTrue(true);
    }

    @Test
    public void testSentMessage_QuitOption() {
        
        
        assertEquals("Exit", message.sentMessage());
    }

    @Test
    public void testSentMessage_InvalidOption() {

        assertEquals("Exit", message.sentMessage());

    }

    @Test
    public void testSentMessage_SendSingleMessage() {

        
        assertEquals("Exit", message.sentMessage());
        assertEquals(1, message.returnTotalMessages());
    }

    @Test
    public void testSentMessage_DisregardMessage() {

        
        assertEquals("Exit", message.sentMessage());
        assertEquals(0, message.returnTotalMessages());
    }

    @Test
    public void testSentMessage_StoreMessage() {

        assertEquals("Exit", message.sentMessage());
        assertEquals(0, message.returnTotalMessages());

    }

    @Test
    public void testMessageReports_DisplaySenderAndRecipient() throws Exception {
        addTestMessageToSent();

        
        message.sentMessage();
    }

    @Test
    public void testMessageReports_DisplayLongestMessage() throws Exception {
        addTestMessageToSent();
        addTestMessage("Short", "+2711111111");
 
        message.sentMessage();
    }

    @Test
    public void testMessageReports_SearchByMessageID() throws Exception {
        addTestMessageToSent();   
        message.sentMessage();

    }

    @Test
    public void testMessageReports_SearchByRecipient() throws Exception {
        addTestMessageToSent();

        message.sentMessage();
    }

    @Test
    public void testMessageReports_DeleteByHash() throws Exception {
        addTestMessageToSent();
        String hash = message.createMessageHash();
        message.sentMessage();
        assertEquals(0, message.returnTotalMessages());
    }

    @Test
    public void testMessageReports_DisplayAllDetails() throws Exception {
        addTestMessageToSent();
        message.sentMessage();
    }

    // Helper method to add a test message to sentMessages
    private void addTestMessageToSent() throws Exception {
        addTestMessage(TEST_MESSAGE, TEST_RECIPIENT);
    }

    private void addTestMessage(String content, String recipient) throws Exception {
        Field sentMessagesField = Message.class.getDeclaredField("sentMessages");
        sentMessagesField.setAccessible(true);
        @SuppressWarnings("unchecked")
        ArrayList<String> sentMessages = (ArrayList<String>) sentMessagesField.get(null);
        
        String details = "Message Sent Successfully!\n" +
                         "Message ID: " + TEST_MESSAGE_ID + "\n" +
                         "Message Hash: " + message.createMessageHash() + "\n" +
                         "RecipientID: " + recipient + "\n" +
                         "Content: " + content;
        
        sentMessages.add(details);
        
        Field totalMessagesField = Message.class.getDeclaredField("totalMessages");
        totalMessagesField.setAccessible(true);
        totalMessagesField.setInt(null, sentMessages.size());
    }

}