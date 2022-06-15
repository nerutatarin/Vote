package service.telegrambot.commands;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import service.pagemanager.model.ResultVote;
import service.pagemanager.model.ResultsVote;
import utils.Utils;
import utils.jackson.JsonMapper;

import java.util.Date;
import java.util.List;

public class CommandResultsVote extends CommandsImpl {
    private static final Logger log = Logger.getLogger(CommandStatus.class);

    private final String fileName = "src/resources/results_votes.json";
    private ResultVote resultVote;
    private Date timestamp;

    @Override
    public SendMessage execute(Long userId, String text) {
        getMessage(text);

        if (!message.equals("") && !message.isEmpty()) {
            ResultsVote resultsVote = JsonMapper.fileToObject(fileName, ResultsVote.class);

            List<ResultVote> resultVoteList = null;
            if (resultsVote != null) {
                timestamp = resultsVote.getTimeStamp();
                resultVoteList = resultsVote.getResultVotes();
            }

            resultVote = getResultVote(resultVoteList);
        }

        stringMessage();
        log.debug(getClass().getSimpleName() + ": " + stringMessage);

        return sendMessageBuild(userId);
    }

    private ResultVote getResultVote(List<ResultVote> resultVoteList) {
        if (resultVoteList == null) return null;

        for (ResultVote result : resultVoteList) {
            if (result.getId() == Utils.parseInt(message)) return result;
        }

        return null;
    }

    private void stringMessage() {
        if (resultVote == null) {
            stringMessage.append("Искомый участник не найден!");
        } else {
            stringMessage
                    .append(timestamp)
                    .append("\n")
                    .append(resultVote.getTitle())
                    .append("\n")
                    .append(resultVote.getCount())
                    .append("\n");
        }
    }
}
