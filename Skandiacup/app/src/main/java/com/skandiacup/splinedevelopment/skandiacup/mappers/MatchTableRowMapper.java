package com.skandiacup.splinedevelopment.skandiacup.mappers;

import com.skandiacup.splinedevelopment.skandiacup.domain.MatchTable;
import com.skandiacup.splinedevelopment.skandiacup.domain.MatchTableRow;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;

/**
 * Created by eiriksandberg on 29.10.2015.
 */
public class MatchTableRowMapper {

    public static ArrayList<MatchTableRow> mapMatchTableRows(SoapObject soapObject) {
        ArrayList<MatchTableRow> rows = new ArrayList<>();
        for (int i = 0; i < soapObject.getPropertyCount(); i++) {
            SoapObject obj2 = (SoapObject) soapObject.getProperty(i);
            MatchTableRow row = mapMatchTableRow(obj2);
            rows.add(row);
        }
        return rows;
    }

    public static MatchTableRow mapMatchTableRow(SoapObject soapObject) {
            SoapObject obj2 = soapObject;
            MatchTableRow tableRow = new MatchTableRow();
            tableRow.setId(obj2.getPrimitiveProperty("id").toString());
            tableRow.setMatchClassId(obj2.getPrimitiveProperty("MatchClassId").toString());
            tableRow.setMatchGroupId(obj2.getPrimitiveProperty("MatchGroupId").toString());
            tableRow.setDisplayOrder(obj2.getPrimitiveProperty("DisplayOrder").toString());
            tableRow.setPosition(obj2.getPrimitiveProperty("Position").toString());
            tableRow.setPlayoffId(obj2.getPrimitiveProperty("PlayoffId").toString());
            tableRow.setPlayoffLevel(obj2.getPrimitiveProperty("PlayoffLevel").toString());
            tableRow.setA(obj2.getPrimitiveProperty("a").toString());
            tableRow.setB(obj2.getPrimitiveProperty("b").toString());
            tableRow.setC(obj2.getPrimitiveProperty("c").toString());
            tableRow.setD(obj2.getPrimitiveProperty("d").toString());
            tableRow.setE(obj2.getPrimitiveProperty("e").toString());
            tableRow.setF(obj2.getPrimitiveProperty("f").toString());
            tableRow.setG(obj2.getPrimitiveProperty("g").toString());
            tableRow.setH(obj2.getPrimitiveProperty("h").toString());
            tableRow.setI(obj2.getPrimitiveProperty("i").toString());
            tableRow.setJ(obj2.getPrimitiveProperty("j").toString());
            tableRow.setK(obj2.getPrimitiveProperty("k").toString());
            tableRow.setL(obj2.getPrimitiveProperty("l").toString());
            return tableRow;
    }
}
