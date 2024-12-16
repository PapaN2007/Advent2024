import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdventDay4Part2 {
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
        for (int r = 0; r < grid.length; r++){
            for (int c = 0; c < grid[0].length; c++){
                int count = 0;
                if (r + 1 < grid.length && r - 1 >= 0 && c + 1 < grid[0].length && c - 1 >= 0 && grid[r-1][c-1].equals("M") && grid[r][c].equals("A") && grid[r+1][c+1].equals("S")){
                    count++;
                }
                if (r + 1 < grid.length && r - 1 >= 0 && c + 1 < grid[0].length && c - 1 >= 0 && grid[r-1][c+1].equals("M") && grid[r][c].equals("A") && grid[r+1][c-1].equals("S")){
                    count++;
                }
                if (r + 1 < grid.length && r - 1 >= 0 && c + 1 < grid[0].length && c - 1 >= 0 && grid[r+1][c+1].equals("M") && grid[r][c].equals("A") && grid[r-1][c-1].equals("S")){
                    count++;
                }
                if (r + 1 < grid.length && r - 1 >= 0 && c + 1 < grid[0].length && c - 1 >= 0 && grid[r+1][c-1].equals("M") && grid[r][c].equals("A") && grid[r-1][c+1].equals("S")){
                    count++;
                }
                if (count == 2){
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