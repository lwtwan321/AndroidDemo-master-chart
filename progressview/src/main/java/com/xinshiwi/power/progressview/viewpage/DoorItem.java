package com.xinshiwi.power.progressview.viewpage;


public class DoorItem {

    private String name;
    private String buildName;
    private int position = -1;

    public DoorItem(String name, String buildName) {
        this.name = name;
        this.buildName = buildName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
