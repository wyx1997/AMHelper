package com.am.example.amtest.entity;

public class ViewPagerEventMsg implements MyEventBusMsg{

    private int position;

    public ViewPagerEventMsg(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
