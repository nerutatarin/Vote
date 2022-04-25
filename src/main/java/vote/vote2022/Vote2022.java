package vote.vote2022;

import org.apache.log4j.Logger;
import vote.vote2022.browsers.Browsers;
import vote.vote2022.browsers.FirefoxBrowser;

import java.util.ArrayList;
import java.util.List;

public class Vote2022 extends Vote {
    private static final Logger log = Logger.getLogger(Vote2022.class);
    private static final int voteCount = 1;

    public void init() {
        for (int i = 0; i < voteCount; i++) {
            log.info("Начало работы: " + i);
            List<Browsers> browsers = new ArrayList<>();
            browsers.add(new FirefoxBrowser());
            //browsers.add(new EdgeBrowser());
            //browsers.add(new ChromeBrowser());
            //browsers.add(new OperaBrowser());
            //browsers.parallelStream().forEach(this::vote);
            browsers.forEach(this::vote);
        }
    }

    @Override
    protected String getMyIpUrl() {
        return "https://myip.ru/";
    }

    @Override
    protected String getCssSelector() {
        return "#ipcontent > table > tbody > tr:nth-child(2) > td";
    }

    @Override
    protected String getBaseUrl() {
        return "https://www.ufa.kp.ru/best/msk/oprosy/ufa_klinikagoda2022";
    }

    @Override
    protected ArrayList<String> inputs() {
        ArrayList<String> inputs = new ArrayList<>();

        String G1_KUVATOVO = "inp4";
        String G2_RKIB = "inp19";
        String G3_DRKB = "inp23";
        String G4_GB1 = "inp24";
        String G5_RD3 = "inp29";
        String G9_BUBNOVSKY = "inp49";
        String G13_DP4 = "inp59";
        String G14_KUVATOVO = "inp63";
        String G15_BUBNOVSKY = "inp67";
        String G16_KUVATOVO = "inp69";

        inputs.add(G1_KUVATOVO);
        inputs.add(G2_RKIB);
        inputs.add(G3_DRKB);
        //inputs.add(G4_GB1);
        //inputs.add(G5_RD3);
        inputs.add(G9_BUBNOVSKY);
        //inputs.add(G13_DP4);
        inputs.add(G14_KUVATOVO);
        inputs.add(G15_BUBNOVSKY);
        inputs.add(G16_KUVATOVO);

        return inputs;
    }
}
