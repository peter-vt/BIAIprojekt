package com.biai.projekt.parser;

import com.biai.projekt.model.Match;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Peter on 2016-09-19.
 */
public class StatsParser {

    private Document page;

    // Basic info
    private String homeName;
    private String guestName;
    private String homePoints;
    private String guestPoints;

    private String audience;

    // Home team
    private String homeAttackPerformance;
    private String homeRecievePerformance;
    private String homeBlocks;


    // Guest team
    private String guestAttackPerformance;
    private String guestRecievePerformance;
    private String guestBlocks;


    public Match parse(String URL) throws IOException {

        page = Jsoup.connect(URL).get();

        homeName = page.select("span[id=Content_Main_LBL_HomeTeam]").text();
        homePoints = page.select("span[id=Content_Main_LBL_WonSetHome]").text();
        guestName = page.select("span[id=Content_Main_LBL_GuestTeam]").text();
        guestPoints = page.select("span[id=Content_Main_LBL_WonSetGuest]").text();
        audience = page.select("span[id=L_Spettatori]").text();


        Elements homeTeam = page.select("div[id=RG_HomeTeam]");
        homeTeam = homeTeam.select("tr[class=Tabellino_Row_Totals]");
        homeAttackPerformance = homeTeam.select("span[id=SpikePos]").text();
        homeRecievePerformance = homeTeam.select("span[id=RecPerf]").text();
        homeBlocks = homeTeam.select("span[id=BlockWin]").text();

        Elements guestTeam = page.select("div[id=RG_GuestTeam]");
        guestTeam = guestTeam.select("tr[class=Tabellino_Row_Totals]");
        guestAttackPerformance = guestTeam.select("span[id=SpikePos]").text();
        guestRecievePerformance = guestTeam.select("span[id=RecPerf]").text();
        guestBlocks = guestTeam.select("span[id=BlockWin]").text();

        return new Match(homeName, guestName, homePoints, guestPoints, audience, homeAttackPerformance, homeRecievePerformance, homeBlocks, guestAttackPerformance, guestRecievePerformance, guestBlocks);
    }
}
