import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdventDay3Part1 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/input");
        System.out.println(fileData);
        int amount = 0;
        ArrayList<String> allMatches = new ArrayList<String>();
        ArrayList<String> allNums = new ArrayList<String>();
        for (int i = 0; i < fileData.size(); i++) {
            String regex = "mul\\([0-9]{0,3},[0-9]{0,3}\\)";
            Matcher m = Pattern.compile(regex).matcher(fileData.get(i));
            while (m.find()) {
                allMatches.add(m.group());
            }
        }
        for (int i = 0; i < allMatches.size(); i++){
            String regex = "([0-9]{1,3},[0-9]{1,3})";
            Matcher m = Pattern.compile(regex).matcher(allMatches.get(i));
            while (m.find()) {
                allNums.add(m.group());
            }
        }
        for (int i = 0; i < allNums.size(); i++){
            String[] splitSample = allNums.get(i).split(",");
            int num1 = Integer.parseInt(splitSample[0]);
            int num2 = Integer.parseInt(splitSample[1]);
            amount += num1 * num2;
        }
        System.out.println(allMatches);
        System.out.println(allNums);
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