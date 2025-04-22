package com.example.project;

public class Player extends Sprite {
    private int treasureCount;
    private int numLives;
    private boolean win;

    //
    public Player(int x, int y) {
        super(x, y);
        this.treasureCount = 0;
        this.numLives = 2;
        this.win = false;
    }

    // Getters for player attributes
    public int getTreasureCount() { return treasureCount; }
    public int getLives() { return numLives; }
    public boolean getWin() { return win; }

    @Override
    public String getCoords() {
        return "Player:" + super.getCoords();
    }

    @Override
    public String getRowCol(int size) {
        return "Player:" + super.getRowCol(size);
    }

    //Moves the player based on direction
    @Override
    public void move(String direction) {
        if (direction.toLowerCase().equals("w")) setY(getY() + 1); // Move up
        if (direction.toLowerCase().equals("a")) setX(getX() - 1); // Move left
        if (direction.toLowerCase().equals("s")) setY(getY() - 1); // Move down
        if (direction.toLowerCase().equals("d")) setX(getX() + 1); // Move right
    }

    //Handles interactions with other objects
    public void interact(int size, String direction, int numTreasures, Object obj) {
        // Move the player first to make sure the interaction is based on the new position
        if (obj instanceof Treasure) {
            if (!(obj instanceof Trophy)) {
            treasureCount++;
            } else {
                if (treasureCount == numTreasures) {
                    win = true;
                }
            }
        } else if (obj instanceof Enemy) {
            numLives--; // Lose a life if interacting with an enemy
        }
    }

    //Checks if a move is valid (within grid boundaries)
    public boolean isValid(int size, String direction) {
        int newX = getX();
        int newY = getY();
        
        if (direction.toLowerCase().equals("w")) {
            newY++;
        }
        if (direction.toLowerCase().equals("a")) {
            newX--;
        }
        if (direction.toLowerCase().equals("s")) {
            newY--;
        }
        if (direction.toLowerCase().equals("d")) {
            newX++;
        }
        
        // Check if the new coordinates are within the grid
        return newX >= 0 && newX < size && newY >= 0 && newY < size;
    }
}