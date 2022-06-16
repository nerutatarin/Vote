package service.telegrambot.commands;

import org.apache.log4j.Logger;
import service.pagemanager.model.ParticipantVote;
import utils.jackson.JsonMapper;

import java.util.List;

public class CommandParticipants extends CommandsImpl {
    private static final Logger log = Logger.getLogger(CommandStatus.class);
    
    @Override
    protected StringBuilder replyMessageMake() {
        return getStringBuilder();
    }

    private StringBuilder getStringBuilder() {
        String fileName = "participants.json";
        List<ParticipantVote> participantVotes = JsonMapper.fileToListObject(fileName, ParticipantVote.class);
        
        StringBuilder stringBuilder = new StringBuilder();
        for (ParticipantVote participantVote : participantVotes) {
            stringBuilder.append(participantVote.getId()).append("-").append(participantVote.getTitle()).append("\n");
        }
        return stringBuilder;
    }
}
