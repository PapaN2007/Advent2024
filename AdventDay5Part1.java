import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdventDay5Part1 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/input");
        ArrayList<String> first = new ArrayList<>();
        ArrayList<String> second = new ArrayList<>();
        for (int i = 0; i < fileData.size(); i++) {
            if (fileData.get(i).contains("|")) {
                first.add(fileData.get(i));
            } else {
                second.add(fileData.get(i));
            }
        }
        ArrayList<String> correct = new ArrayList<>();
        ArrayList<String[]> correctArray = new ArrayList<>();
        int amount = 0;
        for (int i = 0; i < second.size(); i++) {
            boolean good = true;
            for (int j = 0; j < first.size(); j++) {
                String line = first.get(j);
                String before = line.substring(0, line.indexOf("|"));
                String after = line.substring(line.indexOf("|") + 1);
                if (second.get(i).contains(before)
                        && second.get(i).contains(after)
                        && second.get(i).indexOf(before) > second.get(i).indexOf(after)) {
                    good = false;
                }
            }
            if (good == true) {
                correct.add(second.get(i));
            }
        }
        for (int i = 0; i < correct.size(); i++) {
            String[] arr = correct.get(i).split(",");
            correctArray.add(arr);
        }

        for (int i =0; i < correctArray.size(); i++) {
            amount += Integer.parseInt(correctArray.get(i)[correctArray.get(i).length / 2]);
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