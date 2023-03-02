package net.azalealibrary.command;

public class AzaleaException extends RuntimeException {

    private final String[] messages;

    public AzaleaException() {
        messages = new String[0];
    }

    public AzaleaException(String message) {
        this(message, new String[0]);
    }

    public AzaleaException(String message, String... messages) {
        super(message);
        this.messages = messages;
    }

    public AzaleaException(String message, Throwable throwable) {
        super(message, throwable);
        messages = new String[0];
    }

    public String[] getMessages() {
        return messages;
    }
}
