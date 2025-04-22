package com.example.project;

public class Border extends Sprite {
    public Border(int x, int y) {
        super(x, y);
    }

    @Override
    public String getCoords() {
        return "Border:" + super.getCoords();
    }

    @Override
    public String getRowCol(int size) {
        return "Border:" + super.getRowCol(size);
    }
}