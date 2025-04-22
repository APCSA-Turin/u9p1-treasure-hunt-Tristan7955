package com.example.project;

public class Sprite {
    private int x, y;

    //Constructor to init sprite position
    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Getters for x and y coordinates
    public int getX(){return x;}
    public int getY(){return y;}

    //Setters for x and y coordinates
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}

    //Returns the coordinates in (x,y) format
    public String getCoords(){
        return "(" + x + "," + y + ")";
    }

    //Returns the row and column in [row][col] format
    public String getRowCol(int size){
        int row = size - 1 - y;
        int col = x;
        return "[" + row + "][" + col + "]";
    }
    
    //Default move method to be overridden by subclasses
    public void move(String direction) {
        // Default behavior (can be overridden by subclasses)
    }

    //Default interact method to be overridden by subclasses
    public void interact() {
        // Default behavior (can be overridden by subclasses)
    }
}