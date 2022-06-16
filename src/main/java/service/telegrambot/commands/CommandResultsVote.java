package service.telegrambot.commands;

import org.apache.log4j.Logger;
import service.pagemanager.model.ResultVote;
import service.pagemanager.model.ResultsVote;
import utils.Utils;
import utils.jackson.JsonMapper;

import java.util.Date;
import java.util.List;

public class CommandResultsVote extends CommandsImpl {
    private static final Logger log = Logger.getLogger(CommandStatus.class);
    private Date timestamp;

    @Override
    protected StringBuilder replyMessageMake() {
        return getStringBuilder();
    }

    private StringBuilder getStringBuilder() {
        ResultVote result = getResult();
        if (result == null) {
            return new StringBuilder().append("Искомый участник не найден!");
        } else {
            return new StringBuilder()
                    .append(timestamp)
                    .append("\n")
                    .append(result.getTitle())
                    .append("\n")
                    .append(result.getCount())
                    .append("\n");
        }
    }

    private ResultVote getResult() {
        String newData = Utils.firstSubstringAfterSpace(data);
        if (newData.isEmpty())
            if (newData.equals("")) {
                return null;
            }

        return getResultVote(newData);
    }

    private ResultVote getResultVote(String newData) {
        String fileName = "results_votes.json";
        ResultsVote resultsVote = JsonMapper.fileToObject(fileName, ResultsVote.class);
        if (resultsVote == null) return null;

        timestamp = resultsVote.getTimeStamp();

        List<ResultVote> resultVoteList = resultsVote.getResultVotes();
        if (resultVoteList == null) return null;

        return resultVoteList.stream()
                .filter(resultVote -> resultVote.getId() == Utils.parseInt(newData))
                .findFirst()
                .orElse(null);
    }
}
