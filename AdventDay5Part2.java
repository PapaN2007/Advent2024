import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class AdventDay5Part2 {
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
                if (second.get(i).contains(before) && second.get(i).contains(after) && second.get(i).indexOf(before) > second.get(i).indexOf(after)) {
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
        System.out.println("Part 1: " + amount);


        ArrayList<ArrayList<String>> incorrect = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < second.size(); i++) {
            boolean good = true;
            for (int j = 0; j < first.size(); j++) {
                String line = first.get(j);
                String before = line.substring(0, line.indexOf("|"));
                String after = line.substring(line.indexOf("|") + 1);
                if (second.get(i).contains(before) && second.get(i).contains(after) && second.get(i).indexOf(before) > second.get(i).indexOf(after)) {
                    good = false;
                }
            }
            if (!good){
                String[] line = second.get(i).split(",");
                incorrect.add(new ArrayList<>(Arrays.asList(line)));
            }
        }
        for (int i = 0; i < incorrect.size(); i++){
            for (int j = 0; j < first.size(); j++){
                String line = first.get(j);
                String before = line.substring(0, line.indexOf("|"));
                String after = line.substring(line.indexOf("|") + 1);
                if (incorrect.get(i).contains(before)
                        && incorrect.get(i).contains(after)
                        && incorrect.get(i).indexOf(before) > incorrect.get(i).indexOf(after)
                ) {
                    ArrayList<String> tempArray = incorrect.get(i);
                    int afterIndex = tempArray.indexOf(after);
                    int beforeIndex = tempArray.indexOf(before);
                    tempArray.remove(beforeIndex);
                    if (afterIndex != 0) {
                        tempArray.add(afterIndex, before);
                    } else {
                        tempArray.addFirst(before);
                    }
                    incorrect.set(i, tempArray);
                }
            }
        }


        for (int i = 0; i < incorrect.size(); i++){
            for (int j = 0; j < first.size(); j++){
                String line = first.get(j);
                String before = line.substring(0, line.indexOf("|"));
                String after = line.substring(line.indexOf("|") + 1);
                if (incorrect.get(i).contains(before)
                        && incorrect.get(i).contains(after)
                        && incorrect.get(i).indexOf(before) > incorrect.get(i).indexOf(after)
                ) {
                    ArrayList<String> tempArray = incorrect.get(i);
                    int afterIndex = tempArray.indexOf(after);
                    int beforeIndex = tempArray.indexOf(before);
                    tempArray.remove(beforeIndex);
                    if (afterIndex != 0) {
                        tempArray.add(afterIndex, before);
                    } else {
                        tempArray.addFirst(before);
                    }
                    incorrect.set(i, tempArray);
                }
            }
        }


        for (ArrayList<String> str : incorrect) {
            count += Integer.parseInt(str.get(str.size() / 2));
        }
        System.out.println("Part 2: " + count);
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

