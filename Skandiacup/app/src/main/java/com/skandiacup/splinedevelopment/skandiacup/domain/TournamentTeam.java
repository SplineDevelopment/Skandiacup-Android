/**
 * Copyright 2016 Bjørn Hoxmark, Borgar Lie, Eirik Sandberg, Jørgen Wilhelmsen
 */

package com.skandiacup.splinedevelopment.skandiacup.domain;

import java.io.Serializable;

public class TournamentTeam implements Serializable{
    String id;
    String name;
    String clubId;
    String matchGroupId;
    String matchClassId;
    String tournamentTeamRegistrationId;
    String updateTimestamp;
    String shirtColor;
    String countryCode;

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

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getMatchGroupId() {
        return matchGroupId;
    }

    public void setMatchGroupId(String matchGroupId) {
        this.matchGroupId = matchGroupId;
    }

    public String getMatchClassId() {
        return matchClassId;
    }

    public void setMatchClassId(String matchClassId) {
        this.matchClassId = matchClassId;
    }

    public String getTournamentTeamRegistrationId() {
        return tournamentTeamRegistrationId;
    }

    public void setTournamentTeamRegistrationId(String tournamentTeamRegistrationId) {
        this.tournamentTeamRegistrationId = tournamentTeamRegistrationId;
    }

    public String getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(String updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public String getShirtColor() {
        return shirtColor;
    }

    public void setShirtColor(String shirtColor) {
        this.shirtColor = shirtColor;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "TournamentTeam{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", clubId='" + clubId + '\'' +
                ", matchGroupId='" + matchGroupId + '\'' +
                ", matchClassId='" + matchClassId + '\'' +
                ", tournamentTeamRegistrationId='" + tournamentTeamRegistrationId + '\'' +
                ", updateTimestamp='" + updateTimestamp + '\'' +
                ", shirtColor='" + shirtColor + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
