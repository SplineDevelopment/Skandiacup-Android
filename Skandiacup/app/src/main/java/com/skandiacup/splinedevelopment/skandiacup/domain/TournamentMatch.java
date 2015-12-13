package com.skandiacup.splinedevelopment.skandiacup.domain;

import java.io.Serializable;

/**
 * Created by Jorgen on 28/10/15.
 */
public class TournamentMatch implements Serializable{
    private String id;
    private String matchTXID;
    private String matchno;
    private String matchname;
    private String classid;
    private String hometeamid;
    private String hometeamname;
    private String awayteamid;
    private String awayteamname;
    private String homeclubid;
    private String homeclubname;
    private String awayclubid;
    private String awayclubname;
    private String matchdate;
    private String matchcomment;
    private String hometeamtext;
    private String awayteamtext;
    private String homegoal;
    private String awaygoal;
    private String fieldid;
    private String endgamelevel;
    private String sortorder;
    private String reason;
    private String winner;
    private String periodLengthInMinutes;
    private String numberOfPeriodsInMatch;
    private String updateTimeStamp;
    private String matchGroupId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatchTXID() {
        return matchTXID;
    }

    public void setMatchTXID(String matchTXID) {
        this.matchTXID = matchTXID;
    }

    public String getMatchno() {
        return matchno;
    }

    public void setMatchno(String matchno) {
        this.matchno = matchno;
    }

    public String getMatchname() {
        return matchname;
    }

    public void setMatchname(String matchname) {
        this.matchname = matchname;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getHometeamid() {
        return hometeamid;
    }

    public String getHomeclubname() {
        return homeclubname;
    }

    public void setHomeclubname(String homeclubname) {
        this.homeclubname = homeclubname;
    }

    public String getAwayclubid() {
        return awayclubid;
    }

    public void setAwayclubid(String awayclubid) {
        this.awayclubid = awayclubid;
    }

    public void setHometeamid(String hometeamid) {
        this.hometeamid = hometeamid;
    }

    public String getHometeamname() {
        return hometeamname;
    }

    public void setHometeamname(String hometeamname) {
        this.hometeamname = hometeamname;
    }

    public String getAwayteamid() {
        return awayteamid;
    }

    public void setAwayteamid(String awayteamid) {
        this.awayteamid = awayteamid;
    }

    public String getAwayteamname() {
        return awayteamname;
    }

    public void setAwayteamname(String awayteamname) {
        this.awayteamname = awayteamname;
    }

    public String getHomeclubid() {
        return homeclubid;
    }

    public void setHomeclubid(String homeclubid) {
        this.homeclubid = homeclubid;
    }

    public String getAwayclubname() {
        return awayclubname;
    }

    public void setAwayclubname(String awayclubname) {
        this.awayclubname = awayclubname;
    }

    public String getMatchdate() {
        return matchdate;
    }

    public void setMatchdate(String matchdate) {
        this.matchdate = matchdate;
    }

    public String getMatchcomment() {
        return matchcomment;
    }

    public void setMatchcomment(String matchcomment) {
        this.matchcomment = matchcomment;
    }

    public String getHometeamtext() {
        return hometeamtext;
    }

    public void setHometeamtext(String hometeamtext) {
        this.hometeamtext = hometeamtext;
    }

    public String getAwayteamtext() {
        return awayteamtext;
    }

    public void setAwayteamtext(String awayteamtext) {
        this.awayteamtext = awayteamtext;
    }

    public String getHomegoal() {
        return homegoal;
    }

    public void setHomegoal(String homegoal) {
        this.homegoal = homegoal;
    }

    public String getAwaygoal() {
        return awaygoal;
    }

    public void setAwaygoal(String awaygoal) {
        this.awaygoal = awaygoal;
    }

    public String getFieldid() {
        return fieldid;
    }

    public void setFieldid(String fieldid) {
        this.fieldid = fieldid;
    }

    public String getEndgamelevel() {
        return endgamelevel;
    }

    public void setEndgamelevel(String endgameleve) {
        this.endgamelevel = endgameleve;
    }

    public String getSortorder() {
        return sortorder;
    }

    public void setSortorder(String sortorder) {
        this.sortorder = sortorder;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
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

    public String getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public void setUpdateTimeStamp(String updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }

    public String getMatchGroupId() {
        return matchGroupId;
    }

    public void setMatchGroupId(String matchGroupId) {
        this.matchGroupId = matchGroupId;
    }

    @Override
    public String toString() {
        return "TournamentMatch{" +
                "id='" + id + '\'' +
                ", matchTXID='" + matchTXID + '\'' +
                ", matchno='" + matchno + '\'' +
                ", matchname='" + matchname + '\'' +
                ", classid='" + classid + '\'' +
                ", hometeamid='" + hometeamid + '\'' +
                ", hometeamname='" + hometeamname + '\'' +
                ", awayteamid='" + awayteamid + '\'' +
                ", awayteamname='" + awayteamname + '\'' +
                ", homeclubid='" + homeclubid + '\'' +
                ", homeclubname='" + homeclubname + '\'' +
                ", awayclubid='" + awayclubid + '\'' +
                ", awayclubname='" + awayclubname + '\'' +
                ", matchdate='" + matchdate + '\'' +
                ", matchcomment='" + matchcomment + '\'' +
                ", hometeamtext='" + hometeamtext + '\'' +
                ", awayteamtext='" + awayteamtext + '\'' +
                ", homegoal='" + homegoal + '\'' +
                ", awaygoal='" + awaygoal + '\'' +
                ", fieldid='" + fieldid + '\'' +
                ", endgamelevel='" + endgamelevel + '\'' +
                ", sortorder='" + sortorder + '\'' +
                ", reason='" + reason + '\'' +
                ", winner='" + winner + '\'' +
                ", periodLengthInMinutes='" + periodLengthInMinutes + '\'' +
                ", numberOfPeriodsInMatch='" + numberOfPeriodsInMatch + '\'' +
                ", updateTimeStamp='" + updateTimeStamp + '\'' +
                ", matchGroupId='" + matchGroupId + '\'' +
                '}';
    }
}