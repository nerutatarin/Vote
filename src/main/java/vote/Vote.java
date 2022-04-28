package vote;

import vote.browsers.BrowsersImpl;

public interface Vote {
    void init();

    void vote(BrowsersImpl browsersImpl);
}
