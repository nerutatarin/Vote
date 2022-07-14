package service.telegrambot.commands;

public enum CommandsEnum {
    COMMAND_START("/start"),
    COMMAND_STATUS("/status"),
    COMMAND_MEMBERS("/members"),
    COMMAND_RESULT("/result"),
    COMMAND_VOTE("/vote"),
    COMMAND_NOMINATIONS("/nominations"),
    COMMAND_BLOCKED_USERS("/blockedusers"),
    COMMAND_DEFAULT("");
    private final String value;

    CommandsEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
