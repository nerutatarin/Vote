package service.telegrambot.commands;

public enum CommandsEnum {
    COMMAND_START("/start"),
    COMMAND_STATUS("/status"),
    COMMAND_PARTICIPANTS("/participants"),
    COMMAND_RESULTS_VOTE("/resultsvote"),
    COMMAND_DEFAULT("");
    private final String value;

    CommandsEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
