package com.example.project;

public class Grid {
    private Sprite[][] grid;
    private int size;

    public Grid(int size) {
        this.size = size;
        grid = new Sprite[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = new Dot(col, size - 1 - row);
            }
        }
    }

    public Sprite[][] getGrid() {
        return grid;
    }


    
    public void placeSprite(Sprite s) {
        if (s == null) return;
        int row = size - 1 - s.getY();
        int col = s.getX();
        if (row >= 0 && row < size && col >= 0 && col < size) {
            grid[row][col] = s;
        }
    }

    public void placeSprite(Sprite s, String direction) {
        if (direction.equals("w")) {
            grid[size - s.getY() - 1][s.getX()] = s;
            grid[size - s.getY() - 1 + 1][s.getX()] = new Dot(s.getX(), s.getY());
        }
        if (direction.equals("a")) {
            grid[size - s.getY() - 1][s.getX()] = s;
            grid[size - s.getY() - 1][s.getX() + 1] = new Dot(s.getX(), s.getY());
        }        
        if (direction.equals("s")) {
            grid[size - s.getY() - 1][s.getX()] = s;
            grid[size - s.getY() - 1 - 1][s.getX()] = new Dot(s.getX(), s.getY());
        }        
        if (direction.equals("d")) {
            grid[size - s.getY() - 1][s.getX()] = s;
            grid[size - s.getY() - 1][s.getX() - 1] = new Dot(s.getX(), s.getY());
        }
    }

    //Displays the current state of the grid
    public void display() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Sprite s = grid[i][j];
                if (s instanceof Player) {
                    System.out.print("🦄");
                } else if (s instanceof Enemy) {
                    System.out.print("🦂");
                } else if (s instanceof Trophy) {
                        System.out.print("🏆");
                } else if (s instanceof Treasure) {
                    System.out.print("🌈");
                } else {
                    System.out.print("⬜");
                }
            }
            System.out.println();
        }
    }
    
    //Displays game over message
    public void gameover() {
        System.out.println("Game Over! You have lost all your lives.");
    }

    //Displays win message
    public void win() {
        System.out.println("Congratulations! You found all treasures and reached the trophy!");
    }
}