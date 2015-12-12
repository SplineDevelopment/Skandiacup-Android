package com.skandiacup.splinedevelopment.skandiacup.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jorgen on 28/10/15.
 */
public class MatchClass implements Serializable{
    private String id;
    private String code;
    private String name;
    private String gender;
    private String periodLengthInMinutes;
    private String numberOfPeriodsInMatch;
    private ArrayList<MatchGroup> matchGroups;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPeriodLengthInMinutes() {
        return periodLengthInMinutes;
    }

    public void setPeriodLengthInMinutes(String periodLengthInMinutes) {
        this.periodLengthInMinutes = periodLengthInMinutes;
    }

    public String getNumberOfPeriodsInMatch() {
        return numberOfPeriodsInMatch;
    }

    public void setNumberOfPeriodsInMatch(String numberOfPeriodsInMatch) {
        this.numberOfPeriodsInMatch = numberOfPeriodsInMatch;
    }

    public ArrayList<MatchGroup> getMatchGroups() {
        return matchGroups;
    }

    public void setMatchGroups(ArrayList<MatchGroup> matchGroups) {
        this.matchGroups = matchGroups;
    }

    @Override
    public String toString() {
        return "MatchClass{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", periodLengthInMinutes='" + periodLengthInMinutes + '\'' +
                ", numberOfPeriodsInMatch='" + numberOfPeriodsInMatch + '\'' +
                ", matchGroups=" + matchGroups +
                '}';
    }
}