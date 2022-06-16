package service.telegrambot.commands;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import service.pagemanager.model.ParticipantVote;
import utils.jackson.JsonMapper;

import java.util.List;

public class CommandParticipants extends CommandsImpl {
    private static final Logger log = Logger.getLogger(CommandStatus.class);
    private final String fileName = "participants.json";

    @Override
    public SendMessage execute(Long userId, String text) {
        List<ParticipantVote> participantVotes = JsonMapper.fileToListObject(fileName, ParticipantVote.class);

        getParticipants(participantVotes);

        log.debug(getClass().getSimpleName() + ": " + stringMessage);

        return sendMessageBuild(userId);
    }

    private void getParticipants(List<ParticipantVote> participantVotes) {
        participantVotes.forEach(this::stringMessage);
    }

    public void stringMessage(ParticipantVote participant) {
        stringMessage.append(participant.getId()).append("-").append(participant.getTitle()).append("\n");
    }
}
