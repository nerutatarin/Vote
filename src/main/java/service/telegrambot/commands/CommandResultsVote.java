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
        ResultsVote resultsVote = JsonMapper.fileToObject(fileName, ResultsVote.class);

        List<ResultVote> resultVoteList = null;
        if (resultsVote != null) {
            timestamp = resultsVote.getTimeStamp();
            resultVoteList = resultsVote.getResultVotes();
        }

        resultVote = getResultVote(text, resultVoteList);
        String stringMessage = stringMessage();

        return sendMessageBuild(userId, stringMessage);
    }

    private ResultVote getResultVote(String text, List<ResultVote> resultVoteList) {
        if (resultVoteList == null) return null;

        for (ResultVote result : resultVoteList) {
            String title = result.getTitle();
            int id = result.getId();
            String s = Utils.substringAfterSpaceByRegex(text);

            if (title.contains(text)){
                return result;
            }

            int parseInt = Integer.parseInt(s);

            if (id == parseInt) {
                return result;
            }
        }

        return null;
    }

    private String stringMessage() {
        if (resultVote == null) {
            return "Искомый участник не найден!";
        } else {
            return timestamp + " " + resultVote.toString();
        }
    }
}
