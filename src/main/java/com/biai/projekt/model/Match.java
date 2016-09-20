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

    @Override
    public String toString() {
        return homeName + " " + homePoints + " - " + guestPoints + " " + guestName + " " + " " + homeAttackPerformance + " " + homeRecievePerformance + " " + homeBlocks
                + " " + guestAttackPerformance + " " + guestRecievePerformance + " " + guestBlocks;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getHomePoints() {
        return homePoints;
    }

    public void setHomePoints(String homePoints) {
        this.homePoints = homePoints;
    }

    public String getGuestPoints() {
        return guestPoints;
    }

    public void setGuestPoints(String guestPoints) {
        this.guestPoints = guestPoints;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getHomeAttackPerformance() {
        return homeAttackPerformance;
    }

    public void setHomeAttackPerformance(String homeAttackPerformance) {
        this.homeAttackPerformance = homeAttackPerformance;
    }

    public String getHomeRecievePerformance() {
        return homeRecievePerformance;
    }

    public void setHomeRecievePerformance(String homeRecievePerformance) {
        this.homeRecievePerformance = homeRecievePerformance;
    }

    public String getHomeBlocks() {
        return homeBlocks;
    }

    public void setHomeBlocks(String homeBlocks) {
        this.homeBlocks = homeBlocks;
    }

    public String getGuestAttackPerformance() {
        return guestAttackPerformance;
    }

    public void setGuestAttackPerformance(String guestAttackPerformance) {
        this.guestAttackPerformance = guestAttackPerformance;
    }

    public String getGuestRecievePerformance() {
        return guestRecievePerformance;
    }

    public void setGuestRecievePerformance(String guestRecievePerformance) {
        this.guestRecievePerformance = guestRecievePerformance;
    }

    public String getGuestBlocks() {
        return guestBlocks;
    }

    public void setGuestBlocks(String guestBlocks) {
        this.guestBlocks = guestBlocks;
    }
}
