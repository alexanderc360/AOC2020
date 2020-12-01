import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AdventOfCode {

    private static final File one = new File("dayOne.txt");

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(dayOne());
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
}
