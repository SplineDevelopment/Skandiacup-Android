package com.skandiacup.splinedevelopment.skandiacup.domain;

/**
 *
 */
public class Arena {
    private int arenaID;
    private String arenaName;
    private String arenaDescription;
    private String update_timestamp;

    public int getArenaID() {
        return arenaID;
    }

    public void setArenaID(int arenaID) {
        this.arenaID = arenaID;
    }

    public String getArenaName() {
        return arenaName;
    }

    public void setArenaName(String arenaName) {
        this.arenaName = arenaName;
    }

    public String getArenaDescription() {
        return arenaDescription;
    }

    public void setArenaDescription(String arenaDescription) {
        this.arenaDescription = arenaDescription;
    }

    public String getUpdate_timestamp() {
        return update_timestamp;
    }

    public void setUpdate_timestamp(String update_timestamp) {
        this.update_timestamp = update_timestamp;
    }
}
