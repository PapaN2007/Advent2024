import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class AdventDay17Part1 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/input");
        int register_a = 0;
        int register_b = 0;
        int register_c = 0;
        ArrayList<Integer> programInstructions = new ArrayList<>();


        for (String line : fileData) {
            if (line.contains("Register A")) {
                register_a = Integer.parseInt(line.split(": ")[1]);
            }
            if (line.contains("Register B")) {
                register_b = Integer.parseInt(line.split(": ")[1]);
            }
            if (line.contains("Register C")) {
                register_c = Integer.parseInt(line.split(": ")[1]);
            }
            if (line.contains("Program: ")) {
                String[] num = line.split(": ")[1].split(",");
                for (int j = 0; j < num.length; j++) {
                    programInstructions.add(Integer.parseInt(num[j]));
                }
            }
        }
        ArrayList<Integer> output = (ArrayList<Integer>) doProgram(programInstructions.stream().mapToInt(i -> i).toArray(), register_a, register_b, register_c);

        String answer = output.toString().replace("[", "").replace("]", "").replace(" ", "");
        System.out.println(answer);
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


    public static int getComboOperand(int operand, int a, int b, int c) {
        if (operand >= 0 && operand <= 3) {
            return operand;
        } else if (operand == 4) {
            return a;
        } else if (operand == 5) {
            return b;
        } else if (operand == 6) {
            return c;
        }
        return -1;
    }


    public static List<Integer> doProgram(int[] programInstructions, int register_a, int register_b, int register_c) {
        int i = 0;
        ArrayList<Integer> output = new ArrayList<>();
        while (i < programInstructions.length - 1) {
            boolean skip = false;
            int opcode = programInstructions[i];
            int operand = programInstructions[i + 1];
            if (opcode == 0) {
                register_a = register_a / (1 << getComboOperand(operand, register_a, register_b, register_c));
            }
            if (opcode == 1) {
                register_b = register_b ^ operand;
            }
            if (opcode == 2) {
                register_b = getComboOperand(operand, register_a, register_b, register_c) % 8;
            }
            if (opcode == 3) {
                if (register_a != 0) {
                    i = operand;
                    skip = true;
                }
            }
            if (opcode == 4) {
                register_b = register_b ^ register_c;
            }
            if (opcode == 5) {
                output.add(getComboOperand(operand, register_a, register_b, register_c) % 8);
            }
            if (opcode == 6) {
                register_b = register_a / (1 << getComboOperand(operand, register_a, register_b, register_c));
            }
            if (opcode == 7) {
                register_c = register_a / (1 << getComboOperand(operand, register_a, register_b, register_c));
            }
            if (!skip) {
                i += 2;
            }
        }
        return output;
    }
}



