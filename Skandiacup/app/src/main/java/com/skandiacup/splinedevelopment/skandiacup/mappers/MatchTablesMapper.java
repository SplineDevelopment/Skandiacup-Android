/**
 * Copyright 2016 Bjørn Hoxmark, Borgar Lie, Eirik Sandberg, Jørgen Wilhelmsen
 */

package com.skandiacup.splinedevelopment.skandiacup.mappers;

import com.skandiacup.splinedevelopment.skandiacup.domain.MatchTable;

import org.ksoap2.serialization.SoapObject;
import java.util.ArrayList;

public class MatchTablesMapper {
    public static ArrayList<MatchTable> mapMatchTables(SoapObject soapObject) {
        ArrayList<MatchTable> tables = new ArrayList<>();
        for (int i = 0; i < soapObject.getPropertyCount(); i++) {
            SoapObject obj2 = (SoapObject) soapObject.getProperty(i);
            MatchTable table = new MatchTable();
            table.setHeader(MatchTableRowMapper.mapMatchTableRow((SoapObject) obj2.getProperty("Header")));
            table.setRows(MatchTableRowMapper.mapMatchTableRows((SoapObject) obj2.getProperty("Rows")));
            tables.add(table);
        }
        return tables;
    }
}
