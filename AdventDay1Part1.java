

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;


public class AdventDay1Part1 {
    public static void main(String[] args) {


        ArrayList<String> fileData = getFileData("src/input");
        System.out.println(fileData);
        int amount = 0;
        ArrayList<String> Left = new ArrayList<>();
        ArrayList<String> Right = new ArrayList<>();
        for (int i = 0; i < fileData.size(); i++){
            String[] splitSample = fileData.get(i).split("   ");
            Left.add(splitSample[0]);
            Right.add(splitSample[1]);
        }
        Collections.sort(Left);
        Collections.sort(Right);




        for (int i = 0; i < Left.size(); i++){
            int number = Integer.parseInt(Left.get(i));
            int number2 = Integer.parseInt(Right.get(i));
            amount += Math.abs(number2 - number);
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
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }
}
