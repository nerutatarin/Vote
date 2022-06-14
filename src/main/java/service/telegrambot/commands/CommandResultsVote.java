package service.telegrambot.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import service.pagemanager.model.ResultsVote;
import utils.jackson.JsonMapper;

import java.util.List;

public class CommandResultsVote extends CommandsImpl {

    private final String fileName = "src/resources/results_votes.json";

    private ResultsVote resultsVote;

    @Override
    public void execute(SendMessage message, Update update) {
        Long userId = update.getMessage().getChatId();
        String text = update.getMessage().getText();

        //singleThreadVoteInit();

        List<ResultsVote> resultsVotes = JsonMapper.fileToObject(fileName, ResultsVote.class);

        getResultsVote(text, resultsVotes);

        messageBuild(message, userId);
    }

    private void getResultsVote(String text, List<ResultsVote> resultsVotes) {
        resultsVotes.forEach(resultsVote -> validMoTitle(text, resultsVote));
    }

    private void validMoTitle(String text, ResultsVote resultsVote) {
        if (resultsVote.getTitle().contains(text)) stringBuild();
    }

    @Override
    protected void stringBuild() {
        builder.append(resultsVote.getTitle())
                .append(" = ")
                .append(resultsVote.getCount());
    }
}
