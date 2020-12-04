import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class AdventOfCode {

    private static final File one = new File("dayOne.txt");
    private static final File two = new File("dayTwo.txt");
    private static final File three = new File("dayThree.txt");

    public static void main(String[] args) throws IOException {
//        System.out.println(dayOne());
//        System.out.println(dayTwo());
        System.out.println(dayThree());
    }


    public static int dayOne() throws FileNotFoundException {
        Scanner s = new Scanner(one);
        Scanner scanCount = new Scanner(one);
        int count = 0;

        while (scanCount.hasNextInt()) {
            count++;
            scanCount.nextInt();
        }
        int[] nums = new int[count];

        for (int i = 0; i < count; i++) {
            nums[i] = s.nextInt();
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                for (int k = 0; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 2020 && (i != j && i != k && j != k)) {
                        return nums[i] * nums[j] * nums[k];
                    }
                }
            }
        }
        return 0;
    }

    public static int dayTwo() throws FileNotFoundException {
        Scanner s = new Scanner(two);
        Scanner scanCount = new Scanner(two);
        int count = 0, validCount = 0;

        while (scanCount.hasNextLine()) {
            count++;
            scanCount.nextLine();
        }
        String[] lines = new String[count];

        for (int i = 0; i < count; i++) {
            lines[i] = s.nextLine();
        }
        String[] decrypt;
        for (int i = 0; i < count; i++) {
            decrypt = separate(lines[i]);
            int buffCount = 0;

//            int letterVal = countLetter(decrypt[2].charAt(0), decrypt[3]);

            if (decrypt[3].length() >= Integer.parseInt(decrypt[1])) {
                if (decrypt[3].charAt(Integer.parseInt(decrypt[0]) - 1) == decrypt[2].charAt(0)) {
                    buffCount++;
                }
                if (decrypt[3].charAt(Integer.parseInt(decrypt[1]) - 1) == decrypt[2].charAt(0)) {
                    buffCount++;
                }
                if (buffCount == 1)
                    validCount++;
            }
        }
        return validCount;
    }

    public static int dayThree() throws IOException {
        int countOne = 0, countThree = 0, countFive = 0, countSeven = 0, countSkip = 0, lineCount = 0;
        String buff;
        BufferedReader reader = new BufferedReader(new FileReader(three));

        int currentX = 0;
        while ((buff = reader.readLine()) != null) {
            if (buff.charAt(currentX) == '#') {
                countOne++;
            }

            currentX++;
            if (currentX >= buff.length())
                currentX = currentX % buff.length();
        }
        reader.close();


        currentX = 0;
        reader = new BufferedReader(new FileReader(three));
        while ((buff = reader.readLine()) != null) {
            if (buff.charAt(currentX) == '#') {
                countThree++;
            }

            currentX += 3;
            if (currentX >= buff.length())
                currentX = currentX % buff.length();
        }
        reader.close();

        currentX = 0;
        reader = new BufferedReader(new FileReader(three));
        while ((buff = reader.readLine()) != null) {
            if (buff.charAt(currentX) == '#') {
                countFive++;
            }

            currentX += 5;
            if (currentX >= buff.length())
                currentX = currentX % buff.length();
        }
        reader.close();

        currentX = 0;
        reader = new BufferedReader(new FileReader(three));
        while ((buff = reader.readLine()) != null) {
            if (buff.charAt(currentX) == '#') {
                countSeven++;
            }
            System.out.println(currentX);
            currentX += 7;
            if (currentX >= buff.length())
                currentX = currentX % buff.length();
        }
        reader.close();

        currentX = 0;
        reader = new BufferedReader(new FileReader(three));
        while ((buff = reader.readLine()) != null) {
            if (lineCount % 2 == 0) {
                if (buff.charAt(currentX) == '#') {
                    countSkip++;
                }

                currentX++;
                if (currentX >= buff.length())
                    currentX = currentX % buff.length();
            }
            lineCount++;
        }

        reader.close();
//        System.out.println(countOne);
//        System.out.println(countThree);
//        System.out.println(countFive);
//        System.out.println(countSeven);
//        System.out.println(countSkip);
        return countOne * countThree * countFive * countSeven * countSkip;
    }

    public static String[] separate(String line) {
        String[] decrypted = new String[4];

        decrypted[0] = line.substring(0, line.indexOf("-"));
        decrypted[1] = line.substring(line.indexOf("-") + 1, line.indexOf(" "));
        decrypted[2] = line.substring(line.indexOf(" ") + 1, line.indexOf(":"));
        decrypted[3] = line.substring(line.indexOf(":") + 2);

        return decrypted;
    } // day 2

    public static int countLetter(char letter, String password) {
        int count = 0;
        for (int i = 0; i < password.length(); i++)
            if (password.charAt(i) == letter)
                count++;
        return count;
    } // day 2
}