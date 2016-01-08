/**
 * Copyright 2016 Bjørn Hoxmark, Borgar Lie, Eirik Sandberg, Jørgen Wilhelmsen
 */

package com.skandiacup.splinedevelopment.skandiacup.domain;

import java.util.ArrayList;

public class MatchTable {
    MatchTableRow header;
    ArrayList<MatchTableRow> rows;

    public MatchTableRow getHeader() {
        return header;
    }

    public void setHeader(MatchTableRow header) {
        this.header = header;
    }

    public ArrayList<MatchTableRow> getRows() {
        return rows;
    }

    public void setRows(ArrayList<MatchTableRow> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "MatchTable{" +
                "header=" + header +
                ", rows=" + rows +
                '}';
    }
}