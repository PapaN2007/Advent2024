import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdventDay6Part1 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/input");
        int rows = fileData.size();
        int columns = fileData.get(0).length();
        String[][] grid = new String[rows][columns];
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                grid[r][c] = fileData.get(r).substring(c, c + 1);
            }
        }
        String[][] newGrid = new String[rows][columns];
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                newGrid[r][c] = grid[r][c];
            }
        }
        ArrayList<String> originalPath = traverseMap(newGrid);
        int count = 0;
        for (int r = 0; r < newGrid.length; r++) {
            for (int c = 0; c < newGrid.length; c++) {
                if (newGrid[r][c].equals("X")) count++;
            }
        }
        System.out.println(count);
    }

    public static ArrayList<String> traverseMap(String[][] grid) {
        int guardRow = -1;
        int guardColumn = -1;
        String direction = "north";
        ArrayList<String> positionsVisited = new ArrayList<String>();
        String foundLoop = "no";
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c].equals("^")) {
                    guardRow = r;
                    guardColumn = c;
                    grid[r][c] = "X";
                }
            }
        }

        boolean canMove = true;
        while (canMove) {
            int nextRow = guardRow;
            int nextColumn = guardColumn;
            if (direction.equals("north")){
                nextRow = guardRow - 1;
            }
            if (direction.equals("east")){
                nextColumn = guardColumn + 1;
            }
            if (direction.equals("south")){
                nextRow = guardRow + 1;
            }
            if (direction.equals("west")){
                nextColumn = guardColumn - 1;
            }

            try {
                if (grid[nextRow][nextColumn].equals("#")) {
                    direction = turn(direction);
                } else {
                    grid[nextRow][nextColumn] = "X";
                    guardRow = nextRow;
                    guardColumn = nextColumn;
                    if (foundLoop(guardRow, guardColumn, direction, positionsVisited)) {
                        foundLoop = "yes";
                        canMove = false;
                    }
                    positionsVisited.add(guardRow + "," + guardColumn + "," + direction);
                }
            }
            catch (Exception e) {
                canMove = false;
            }
        }

        positionsVisited.add(foundLoop);
        return positionsVisited;
    }
    public static String turn(String currentDirection) {
        if (currentDirection.equals("north")) {
            return "east";
        }
        if (currentDirection.equals("east")) {
            return "south";
        }
        if (currentDirection.equals("south")) {
            return "west";
        }
        if (currentDirection.equals("west")) {
            return "north";
        } else {
            return "";
        }
    }

    public static boolean foundLoop(int r, int c, String direction, ArrayList<String> positionsVisited) {
        String newPosition = r + "," + c + "," + direction;
        return positionsVisited.contains(newPosition);
    }

    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.equals(""))
                    fileData.add(line);
            }
            return fileData;
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }
}