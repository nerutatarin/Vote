package service.telegrambot.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import service.pagemanager.model.PageVote;
import service.pagemanager.model.ParticipantVote;
import utils.jackson.JsonMapper;

import java.util.List;

public class CommandParticipants extends CommandsImpl {
    private final String fileName = "src/resources/page_vote.json";

    private ParticipantVote participant;

    @Override
    public void execute(SendMessage message, Update update) {
        Long userId = update.getMessage().getChatId();

        List<PageVote> pageVotes = JsonMapper.fileToObject(fileName, PageVote.class);

        getParticipants(pageVotes);

        log.info(builder.toString());

        messageBuild(message, userId);
    }

    private void getParticipants(List<PageVote> pageVotes) {
        pageVotes.forEach(this::getParticipant);
    }

    private void getParticipant(PageVote pageVote) {
        for (ParticipantVote participantVote : pageVote.getParticipant()) {
            stringBuild(participantVote);
        }
    }


    protected void stringBuild(ParticipantVote participantVote) {
        builder.append(participant.getId())
                .append("-")
                .append(participant.getTitleMO())
                .append("\n");
    }

    protected void stringBuild() {
        builder.append(participant.getId())
                .append("-")
                .append(participant.getTitleMO())
                .append("\n");
    }
}
