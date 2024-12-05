import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdventDay2Part1 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/input");
        int safeNumbers = 0;
        for (String sample : fileData) {
            String[] line = sample.split(" ");
            boolean good = true;
            boolean Increasing = Integer.parseInt(line[0]) - Integer.parseInt(line[1]) > 0;

            for (int i = 0; i < line.length - 1; i++){
                if (Integer.parseInt(line[i]) - Integer.parseInt(line[i+1]) < 0){
                    if (Increasing){
                        good = false;
                    }
                }
                if (Integer.parseInt(line[i]) - Integer.parseInt(line[i+1]) > 0){
                    if (!Increasing){
                        good = false;
                    }
                }
                if (Math.abs(Integer.parseInt(line[i]) - Integer.parseInt(line[i+1])) > 3 ){
                    good = false;
                }

                if (Integer.parseInt(line[i]) - Integer.parseInt(line[i+1]) == 0){
                    good = false;
                }
            }
            if (good){
                safeNumbers++;
            }
        }
        System.out.println(safeNumbers);
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