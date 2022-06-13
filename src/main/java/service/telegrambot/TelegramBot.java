package service.telegrambot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {
    public static final String COMMAND_START = "/start";

    @Override
    public String getBotUsername() {
        return "Avalanche_Voting_Bot";
    }

    @Override
    public String getBotToken() {
        return "5575622725:AAEHzNIXpknign9N9M_33nOZHlEks-mApHs";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage();
            Long userId = update.getMessage().getChatId();
            String text = update.getMessage().getText();
            Integer messageId = update.getMessage().getMessageId();
            System.out.println("userId=" + userId + " text=" + text + " messageId=" + messageId);

            message.setChatId(userId.toString());
            message.setText(text);
            message.setReplyToMessageId(messageId);

            switch (text) {
                case COMMAND_START:

                    String startText = "/add добавить заметку (шаблон: /add title text)\n" +
                            "/del удалить заметку (шаблон: /del id) где id номер заметки\n" +
                            "/list список заметок\n";

                    message.setChatId(String.valueOf(userId));
                    message.setText(startText);
                    break;
                default:
                    message.setChatId(String.valueOf(userId));
            }

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            System.out.println("message.getChatId()=" + message.getChatId() + " message.getText()=" + message.getText() + " message.getReplyToMessageId()=" + message.getReplyToMessageId());
        }
    }
}
