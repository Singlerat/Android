package com.bytedance.camp.chapter4.widget;

class Time {
    private int hours;
    private int minutes;
    private int seconds;
    private int Am;

    Time(int Am,int hours, int minutes, int seconds) {
        this.Am = Am;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getAmpm(){return Am;}
}
