package com.example.project;

public class Treasure extends Sprite {
    public Treasure(int x, int y) {
        super(x, y);
    }

    @Override
    public String getCoords() {
        return "Treasure:" + super.getCoords();
    }

    @Override
    public String getRowCol(int size) {
        return "Treasure:" + super.getRowCol(size);
    }
}