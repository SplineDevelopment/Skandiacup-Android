package com.skandiacup.splinedevelopment.skandiacup.domain;

/**
 * Created by Jorgen on 28/10/15.
 */
public class MatchGroup {
    private String id;
    private String name;
    private String matchClassId;
    private String isPlayOff;
    private String playOffId;
    private String endGameLevel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatchClassId() {
        return matchClassId;
    }

    public void setMatchClassId(String matchClassId) {
        this.matchClassId = matchClassId;
    }

    public String getIsPlayOff() {
        return isPlayOff;
    }

    public void setIsPlayOff(String isPlayOff) {
        this.isPlayOff = isPlayOff;
    }

    public String getPlayOffId() {
        return playOffId;
    }

    public void setPlayOffId(String playOffId) {
        this.playOffId = playOffId;
    }

    public String getEndGameLevel() {
        return endGameLevel;
    }

    public void setEndGameLevel(String endGameLevel) {
        this.endGameLevel = endGameLevel;
    }

    @Override
    public String toString() {
        return "MatchGroup{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", matchClassId='" + matchClassId + '\'' +
                ", isPlayOff='" + isPlayOff + '\'' +
                ", playOffId='" + playOffId + '\'' +
                ", endGameLevel='" + endGameLevel + '\'' +
                '}';
    }
}