package service.telegrambot;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.pagemanager.model.ResultsVote;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TelegramBot extends TelegramLongPollingBot {
    public static final String COMMAND_START = "/start";
    public static final String COMMAND_GET_RESULTS_VOTE = "/resultsvote";

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

            String[] parts = text.split(" ");
            String command = parts[0];
            switch (command) {
                case COMMAND_START:
                    commandStart(message, userId);
                    break;
                case COMMAND_GET_RESULTS_VOTE:
                    commandGetResultsVote(message, userId, parts[1]);
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

    private void commandGetResultsVote(SendMessage message, Long userId, String text) {
        StringBuilder stringBuilder = new StringBuilder();
        List<ResultsVote> resultsVotes = fileToArrayObjectWithGson();
        for (ResultsVote resultsVote : resultsVotes) {
            if (resultsVote.getTitle().contains(text)) {
                String title = resultsVote.getTitle();
                String count = resultsVote.getCount();
                stringBuilder.append(title).append(" = ").append(count);
            }
        }

        message.setText(stringBuilder.toString());
        message.setChatId(String.valueOf(userId));
    }

    public List<ResultsVote> fileToArrayObjectWithGson() {
        List<ResultsVote> vote = new ArrayList<>();
        try (Reader reader = new FileReader("src/resources/results_votes.json")) {
            Type type = new TypeToken<List<ResultsVote>>(){}.getType();
            return new Gson().fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vote;
    }

    private void commandStart(SendMessage message, Long userId) {
        String startText = "/resultsvote Получить результат голосования\n" +
                "/status Статус сервера\n";

        message.setChatId(String.valueOf(userId));
        message.setText(startText);
    }
}
