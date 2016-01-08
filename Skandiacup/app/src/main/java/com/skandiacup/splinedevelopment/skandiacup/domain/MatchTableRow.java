/**
 * Copyright 2016 Bjørn Hoxmark, Borgar Lie, Eirik Sandberg, Jørgen Wilhelmsen
 */

package com.skandiacup.splinedevelopment.skandiacup.domain;

public class MatchTableRow {
    String id;
    String MatchClassId;
    String MatchGroupId;
    String DisplayOrder;
    String Position;
    String PlayoffId;
    String PlayoffLevel;
    String a;
    String b;
    String c;
    String d;
    String e;
    String f;
    String g;
    String h;
    String i;
    String j;
    String k;
    String l;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatchClassId() {
        return MatchClassId;
    }

    public void setMatchClassId(String matchClassId) {
        MatchClassId = matchClassId;
    }

    public String getMatchGroupId() {
        return MatchGroupId;
    }

    public void setMatchGroupId(String matchGroupId) {
        MatchGroupId = matchGroupId;
    }

    public String getDisplayOrder() {
        return DisplayOrder;
    }

    public void setDisplayOrder(String displayOrder) {
        DisplayOrder = displayOrder;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getPlayoffId() {
        return PlayoffId;
    }

    public void setPlayoffId(String playoffId) {
        PlayoffId = playoffId;
    }

    public String getPlayoffLevel() {
        return PlayoffLevel;
    }

    public void setPlayoffLevel(String playoffLevel) {
        PlayoffLevel = playoffLevel;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getJ() {
        return j;
    }

    public void setJ(String j) {
        this.j = j;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    @Override
    public String toString() {
        return "MatchTableRow{" +
                "id='" + id + '\'' +
                ", MatchClassId='" + MatchClassId + '\'' +
                ", MatchGroupId='" + MatchGroupId + '\'' +
                ", DisplayOrder='" + DisplayOrder + '\'' +
                ", Position='" + Position + '\'' +
                ", PlayoffId='" + PlayoffId + '\'' +
                ", PlayoffLevel='" + PlayoffLevel + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                ", e='" + e + '\'' +
                ", f='" + f + '\'' +
                ", g='" + g + '\'' +
                ", h='" + h + '\'' +
                ", i='" + i + '\'' +
                ", j='" + j + '\'' +
                ", k='" + k + '\'' +
                ", l='" + l + '\'' +
                '}';
    }
}
