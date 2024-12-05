import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdventDay4Part1 {
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
        int amount = 0;
        for (int row = 0; row < grid.length; row++){
            for (int col = 0; col < grid[0].length; col++){
                if (row + 3 < grid.length && col + 3 < grid[0].length && grid[row][col].equals("X") && grid[row+1][col+1].equals("M") && grid[row+2][col+2].equals("A") && grid[row+3][col+3].equals("S")){
                    amount++;
                }
                if (row + 3 < grid.length && grid[row][col].equals("X") && grid[row+1][col].equals("M") && grid[row+2][col].equals("A") && grid[row+3][col].equals("S")){
                    amount++;
                }
                if (row + 3 < grid.length && col - 3 >= 0 && grid[row][col].equals("X") && grid[row+1][col-1].equals("M") && grid[row+2][col-2].equals("A") && grid[row+3][col-3].equals("S")){
                    amount++;
                }
                if (row - 3 >= 0 && col - 3 >= 0 && grid[row][col].equals("X") && grid[row-1][col-1].equals("M") && grid[row-2][col-2].equals("A") && grid[row-3][col-3].equals("S")){
                    amount++;
                }
                if (row - 3 >= 0 && grid[row][col].equals("X") && grid[row-1][col].equals("M") && grid[row-2][col].equals("A") && grid[row-3][col].equals("S")){
                    amount++;
                }
                if (row - 3 >= 0 && col + 3 < grid[0].length && grid[row][col].equals("X") && grid[row-1][col+1].equals("M") && grid[row-2][col+2].equals("A") && grid[row-3][col+3].equals("S")){
                    amount++;
                }
                if (col + 3 < grid[0].length && grid[row][col].equals("X") && grid[row][col+1].equals("M") && grid[row][col+2].equals("A") && grid[row][col+3].equals("S")){
                    amount++;
                }

                if (col - 3 >= 0 && grid[row][col].equals("X") && grid[row][col-1].equals("M") && grid[row][col-2].equals("A") && grid[row][col-3].equals("S")){
                    amount++;
                }


            }
        }
        System.out.println(amount);
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
        } catch (FileNotFoundException e) {
            return fileData;
        }
    }
}