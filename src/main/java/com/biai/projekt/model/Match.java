package com.biai.projekt.model;

/**
 * Created by Peter on 2016-09-20.
 */
public class Match {
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

    public Match(String homeName, String guestName, String homePoints, String guestPoints, String audience, String homeAttackPerformance, String homeRecievePerformance, String homeBlocks, String guestAttackPerformance, String guestRecievePerformance, String guestBlocks) {
        this.homeName = homeName;
        this.guestName = guestName;
        this.homePoints = homePoints;
        this.guestPoints = guestPoints;
        this.audience = audience;
        this.homeAttackPerformance = homeAttackPerformance;
        this.homeRecievePerformance = homeRecievePerformance;
        this.homeBlocks = homeBlocks;
        this.guestAttackPerformance = guestAttackPerformance;
        this.guestRecievePerformance = guestRecievePerformance;
        this.guestBlocks = guestBlocks;
    }
}
