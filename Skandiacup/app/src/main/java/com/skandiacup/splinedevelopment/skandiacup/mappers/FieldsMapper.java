package com.skandiacup.splinedevelopment.skandiacup.mappers;

import com.skandiacup.splinedevelopment.skandiacup.domain.Field;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentTeam;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;

/**
 * Created by eiriksandberg on 28.10.2015.
 */
public class FieldsMapper {
    public static ArrayList<Field> mapFields(SoapObject soapObject) {
        ArrayList<Field> fields = new ArrayList<>();
        for (int i = 0; i < soapObject.getPropertyCount(); i++) {
            SoapObject obj2 = (SoapObject) soapObject.getProperty(i);
            Field field = new Field();
            field.setFieldID(obj2.getPrimitiveProperty("fieldID").toString());
            field.setArenaID(obj2.getPrimitiveProperty("arenaID").toString());
            field.setFieldName(obj2.getPrimitiveProperty("fieldName").toString());
            field.setFieldDescription(obj2.getPrimitiveProperty("fieldDescription").toString());
            field.setUpdate_timestamp(obj2.getPrimitiveProperty("update_timestamp").toString());
            fields.add(field);
        }
        return fields;
    }
}
