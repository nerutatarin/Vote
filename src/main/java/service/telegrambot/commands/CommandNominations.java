package service.telegrambot.commands;

import org.apache.log4j.Logger;
import service.pagemanager.model.VotingPage;

import static utils.Thesaurus.FilesNameJson.PAGE_BEFORE_VOTING_JSON;
import static utils.jackson.JsonMapper.fileToObject;

public class CommandNominations extends CommandsImpl {
    private static final Logger log = Logger.getLogger(CommandNominations.class);

    @Override
    protected StringBuilder replyMessageMake() {
        return getStringBuilder();
    }

    private StringBuilder getStringBuilder() {
        VotingPage votingPage = fileToObject(PAGE_BEFORE_VOTING_JSON, VotingPage.class);
        if (votingPage == null) return null;

        StringBuilder stringBuilder = new StringBuilder();
        votingPage.getNominations().forEach(nomination -> stringBuilder.append(nomination).append("\n"));

        return stringBuilder;
    }
}