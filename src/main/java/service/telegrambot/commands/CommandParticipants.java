package service.telegrambot.commands;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import service.pagemanager.model.PageVote;
import service.pagemanager.model.ParticipantVote;
import utils.jackson.JsonMapper;

import java.util.List;

public class CommandParticipants extends CommandsImpl {
    private static final Logger log = Logger.getLogger(CommandStatus.class);
    private final StringBuilder stringMessage = new StringBuilder();
    private final String fileName = "src/resources/page_vote.json";

    @Override
    public SendMessage execute(Long userId, String text) {
        List<PageVote> pageVotes = JsonMapper.fileToListObject(fileName, PageVote.class);

        getParticipants(pageVotes);

        log.info(stringMessage);

        return sendMessageBuild(userId, stringMessage.toString());
    }

    private void getParticipants(List<PageVote> pageVotes) {
        pageVotes.forEach(this::getParticipant);
    }

    private void getParticipant(PageVote pageVote) {
        for (ParticipantVote participantVote : pageVote.getParticipant()) {
            stringMessage(participantVote);
        }
    }

    public void stringMessage(ParticipantVote participant) {
        stringMessage.append(participant.getId()).append("-").append(participant.getTitleMO()).append("\n");
    }
}
