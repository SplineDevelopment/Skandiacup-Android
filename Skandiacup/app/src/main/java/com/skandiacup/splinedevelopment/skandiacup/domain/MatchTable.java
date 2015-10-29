package com.skandiacup.splinedevelopment.skandiacup.domain;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by eiriksandberg on 29.10.2015.
 */
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
}
