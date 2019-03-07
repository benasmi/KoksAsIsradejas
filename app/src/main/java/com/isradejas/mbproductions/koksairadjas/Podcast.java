package com.isradejas.mbproductions.koksairadjas;

public class Podcast {

    public boolean isRunning = false;
    public int fileName;
    public String description;
    public int resourceId = R.drawable.ic_play;

    public Podcast(boolean isRunning, int fileName, String description) {
        this.isRunning = isRunning;
        this.fileName = fileName;
        this.description = description;
    }
}
