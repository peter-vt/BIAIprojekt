package com.biai.projekt.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by Peter on 2016-09-19.
 */
public class StatsParser {

    private Document page;
    private String homeName;
    private String guestName;
    private String homePoints;
    private String guestPoints;

    public void parse(String URL) throws IOException {
        page = Jsoup.connect(URL).get();

        homeName = page.select("span[id=Content_Main_LBL_HomeTeam]").text();
        homePoints = page.select("span[id=Content_Main_LBL_WonSetHome]").text();
        guestName = page.select("span[id=Content_Main_LBL_GuestTeam]").text();
        guestPoints = page.select("span[id=Content_Main_LBL_WonSetGuest]").text();
    }
}
