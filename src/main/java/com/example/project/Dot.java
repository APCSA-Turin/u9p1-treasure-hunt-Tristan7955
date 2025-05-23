package com.example.project;

public class Dot extends Sprite {
    public Dot(int x, int y) {
        super(x, y);
    }

    @Override
    public String getCoords() {
        return "Dot:" + super.getCoords();
    }

    @Override
    public String getRowCol(int size) {
        return "Dot:" + super.getRowCol(size);
    }
}