/**
 * Copyright 2016 Bjørn Hoxmark, Borgar Lie, Eirik Sandberg, Jørgen Wilhelmsen
 */

package com.skandiacup.splinedevelopment.skandiacup.domain;

public class Field {
    String fieldID;
    String arenaID;
    String fieldName;
    String fieldDescription;
    String update_timestamp;

    public String getFieldID() {
        return fieldID;
    }

    public void setFieldID(String fieldID) {
        this.fieldID = fieldID;
    }

    public String getArenaID() {
        return arenaID;
    }

    public void setArenaID(String arenaID) {
        this.arenaID = arenaID;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldDescription() {
        return fieldDescription;
    }

    public void setFieldDescription(String fieldDescription) {
        this.fieldDescription = fieldDescription;
    }

    public String getUpdate_timestamp() {
        return update_timestamp;
    }

    public void setUpdate_timestamp(String update_timestamp) {
        this.update_timestamp = update_timestamp;
    }

    @Override
    public String toString() {
        return "Field{" +
                "fieldID='" + fieldID + '\'' +
                ", arenaID='" + arenaID + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", fieldDescription='" + fieldDescription + '\'' +
                ", update_timestamp='" + update_timestamp + '\'' +
                '}';
    }
}
