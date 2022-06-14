package service.pagemanager.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResultsVote {

    private Date timeStamp;

    private List<ResultVote> resultVotes = new ArrayList<>();

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<ResultVote> getResultVotes() {
        return resultVotes;
    }

    public void setResultVotes(List<ResultVote> resultVotes) {
        this.resultVotes = resultVotes;
    }

    @Override
    public String toString() {
        return "ResultsVote{" +
                "timeStamp=" + timeStamp +
                ", resultVotes=" + resultVotes +
                '}';
    }
}
