import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class AdventOfCode {

    private static final File one = new File("dayOne.txt");
    private static final File two = new File("dayTwo.txt");

    public static void main(String[] args) throws FileNotFoundException {
//        System.out.println(dayOne());
//        System.out.println(dayTwo());
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
