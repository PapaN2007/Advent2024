import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdventDay11Part1 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/input");
        String line = fileData.get(0);
        ArrayList<Long> newStones =  new ArrayList<>();
        String[] stones = line.split(" ");
        for (int i = 0; i < stones.length; i++){
            long number = Long.parseLong(stones[i]);
            newStones.add(number);
        }
        for (int j = 0; j < 25; j++) {
            for (int i = 0; i < newStones.size(); i++) {
                String numberString = newStones.get(i) + "";
                if ((newStones.get(i)) == 0) {
                    newStones.set(i, 1L);
                }
                else if (numberString.length() % 2 == 0) {
                    int middle = String.valueOf(newStones.get(i)).length() / 2;
                    String num1 = String.valueOf(newStones.get(i)).substring(0, middle);
                    String num2 = String.valueOf(newStones.get(i)).substring(middle);
                    newStones.set(i, Long.parseLong(num1));
                    newStones.add(i + 1, Long.parseLong(num2));
                    i++;
                } else {
                    newStones.set(i, newStones.get(i) * 2024);
                }
            }
        }
        System.out.println(newStones.size());
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