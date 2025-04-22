package com.example.project;
import java.util.Scanner;

public class Game {
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    /**
     * Constructor to initialize the game with a grid of given size
     * @param size the size of the grid
     */
    public Game(int size) {
        this.size = size;
        initialize();
        play();
    }

    public static void clearScreen() {
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;

        while(gameRunning) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen();
            
            System.out.println("Lives: " + player.getLives() + " | Treasures: " + 
                              player.getTreasureCount() + "/" + treasures.length);
            grid.display();
            
            // Check if player has lost all lives
            if (player.getLives() <= 0) {
                grid.gameover();
                gameRunning = false;
                break;
            }
            
            // Check if player has won
            if (player.getWin()) {
                grid.win();
                gameRunning = false;
                break;
            }
            
            System.out.print("Enter move (w/a/s/d): ");
            String direction = scanner.nextLine().toLowerCase();
            
            if (!direction.matches("[wasd]")) {
                continue;
            }
            
            if (player.isValid(size, direction)) {
                int oldX = player.getX();
                int oldY = player.getY();
                
                // Store old position
                int oldRow = size - 1 - oldY;
                int oldCol = oldX;
                
                // Move player
                player.move(direction);
                
                int newX = player.getX();
                int newY = player.getY();
                int newRow = size - 1 - newY;
                int newCol = newX;
                
                Sprite target = grid.getGrid()[newRow][newCol];
                
                // Handle interaction
                if (target instanceof Enemy) {
                    player.interact(size, direction, treasures.length, target);
                    grid.placeSprite(new Dot(oldX, oldY));
                    grid.placeSprite(player);
                } 
                // Handle interaction with treasure
                else if (target instanceof Treasure && !(target instanceof Trophy)) {
                    player.interact(size, direction, treasures.length, target);
                    grid.placeSprite(new Dot(oldX, oldY));
                    grid.placeSprite(player);
                }
                // Handle interaction with trophy
                else if (target instanceof Trophy) {
                    if (player.getTreasureCount() == treasures.length) {
                        player.interact(size, direction, treasures.length, target);
                        grid.placeSprite(new Dot(oldX, oldY));
                        grid.placeSprite(player);
                    } else {
                        player.setX(oldX);
                        player.setY(oldY);
                

                        System.out.println("You need to collect all treasures first!");
                    }
                }
                else {
                    // Regular movement to empty space
                    grid.placeSprite(new Dot(oldX, oldY));
                    grid.placeSprite(player);
                }
            }
        }
        scanner.close();
    }


    public void initialize() {
        grid = new Grid(size);
        
        player = new Player(0, 0);
        grid.placeSprite(player);
        
        treasures = new Treasure[2];
        treasures[0] = new Treasure(1, 7);  // [2][1] in grid
        treasures[1] = new Treasure(2, 2);  // [7][2] in grid
        for (Treasure t : treasures) {
            grid.placeSprite(t);
        }
        
        enemies = new Enemy[2];
        enemies[0] = new Enemy(7, 2);  // [7][7] in grid
        enemies[1] = new Enemy(5, 5);  // [4][5] in grid
        for (Enemy e : enemies) {
            grid.placeSprite(e);
        }
        
        trophy = new Trophy(9, 9);  // [0][9] in grid
        grid.placeSprite(trophy);
    }

    public static void main(String[] args) {
        new Game(10);
    }
}