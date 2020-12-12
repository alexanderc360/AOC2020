import java.io.*;
import java.lang.invoke.SwitchPoint;
import java.util.*;

public class AdventOfCode {

    private static final File one = new File("dayOne.txt");
    private static final File two = new File("dayTwo.txt");
    private static final File three = new File("dayThree.txt");
    private static final File four = new File("dayFour.txt");
    private static final File five = new File("dayFive.txt");
    private static final File six = new File("daySix.txt");
    private static final File seven = new File("daySeven.txt");
    private static final File eight = new File("dayEight.txt");
    private static final File nine = new File("dayNine.txt");
    private static final File ten = new File("dayTen.txt");
    private static final File eleven = new File("dayEleven.txt");
    private static final File twelve = new File("dayTwelve.txt");

    public static void main(String[] args) throws IOException {
//        System.out.println(dayOne());
//        System.out.println(dayTwo());
//        System.out.println(dayThree());
//        System.out.println(dayFour());
//        System.out.println(dayFive());
//        System.out.println(daySix());
//        System.out.println(daySeven());
//        dayEight();
//        System.out.println(dayNine());
//        dayTen();
//        System.out.println(dayEleven());
        System.out.println(dayTwelve());
    }

    public static int dayOne() throws IOException {
        String buff;
        BufferedReader reader = new BufferedReader(new FileReader(one));
        ArrayList<Integer> nums = new ArrayList<>();

        while ((buff = reader.readLine()) != null) {
            nums.add(Integer.parseInt(buff));
        }

        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.size(); j++) {
                for (int k = 0; k < nums.size(); k++) {
                    if (nums.get(i) + nums.get(j) + nums.get(k) == 2020 && (i != j && i != k && j != k)) {
                        return nums.get(i) * nums.get(j) * nums.get(k);
                    }
                }
            }
        }
        return 0;
    }

    public static int dayTwo() throws IOException { //294
        String buff;
        BufferedReader reader = new BufferedReader(new FileReader(two));
        int validCount = 0;

        String[] decrypt;
        while ((buff = reader.readLine()) != null) {
            decrypt = separate(buff);
            int buffCount = 0;


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

    public static long dayThree() throws IOException {
        long countOne = 0, countThree = 0, countFive = 0, countSeven = 0, countSkip = 0, lineCount = 0;
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
        return countOne * countThree * countFive * countSeven * countSkip;
    }

    public static int dayFour() throws IOException {
        int count = 0;
        BufferedReader reader = new BufferedReader(new FileReader(four));
        String buff, passportString = "";

        ArrayList<String[]> passPorts = new ArrayList<>();
        ArrayList<String[]> part2 = new ArrayList<>();
        while ((buff = reader.readLine()) != null) {
            passportString = passportString.concat(buff).concat(" ");
            if (buff.isEmpty()) {
                passPorts.add(passportString.split(" "));
                passportString = "";
            }
        }
        passPorts.add(passportString.split(" "));

        boolean boolBuff;
        for (String[] passPort : passPorts) {
            Arrays.sort(passPort);
            boolBuff = true;

            if ((passPort.length == 8))
                part2.add(passPort);
            else if (passPort.length == 7) {
                for (String value : passPort) {
                    if (value.contains("cid")) {
                        boolBuff = false;
                        break;
                    }
                }
                if (boolBuff)
                    part2.add(passPort);
            }
        }

        count = part2.size();
        for (String[] passPort : part2) {
            for (String s : passPort)
                if (!check(s)) {
                    System.out.println(s);
                    count--;
                    break;
                }
        }


        return count;
    }

    public static int dayFive() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(five));
        String buff;
        int rowTop, rowBottom, rowMid, rowResult;
        int colTop, colBottom, colMid, colResult;
        int max = 0, result, mySeat = 0;
        ArrayList<Integer> seats = new ArrayList<>();
        while ((buff = reader.readLine()) != null) {
            rowTop = 127;
            rowBottom = colBottom = 0;
            rowResult = colResult = 0;
            colTop = 7;
            for (int i = 0; i < buff.length(); i++) {
                rowMid = (rowTop + rowBottom) / 2;
                colMid = (colTop + colBottom) / 2;


                if (buff.charAt(i) == 'B') {
                    if (rowMid % 2 != 0)
                        rowMid++;
                    rowBottom = rowMid;
                } else if (buff.charAt(i) == 'F') {
                    rowTop = rowMid;
                } else if (buff.charAt(i) == 'R') {
                    if (colMid % 2 != 0)
                        colMid++;
                    colBottom = colMid;
                } else if (buff.charAt(i) == 'L') {
                    colTop = colMid;
                }
                if (i == 6) {
                    if (buff.charAt(i) == 'B')
                        rowResult = rowTop;
                    else if (buff.charAt(i) == 'F')
                        rowResult = rowBottom;
                } else if (i == 9) {
                    if (buff.charAt(i) == 'R')
                        colResult = colTop;
                    else if (buff.charAt(i) == 'L')
                        colResult = colBottom;
                }
            }
            result = (rowResult * 8) + colResult;
            seats.add(result);
//            if (result > max)
//                max = result;
        }
        Collections.sort(seats);
        for (int i = 1; i < seats.size(); i++)
            if (seats.get(i) - seats.get(i - 1) == 2)
                mySeat = seats.get(i) - 1;

        return mySeat;
    }

    public static int daySix() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(six));
        String buff;
        int total = 0, groupTotal;
//        ArrayList<Character> group = new ArrayList<>(); //part 1
        ArrayList<String> group = new ArrayList<>();

        while ((buff = reader.readLine()) != null) {
            //part 1
//            for (int i = 0; i < buff.length(); i++) {
//                System.out.println(buff.charAt(i));
//                if (!group.contains(buff.charAt(i))) {
//                    group.add(buff.charAt(i));
//                }
//            }
//            if (buff.isEmpty()) {
//                total += group.size();
//                group.clear();
//            }
            group.add(buff);
            if (buff.isEmpty()) {
                group.remove(group.size() - 1);
                total += getTotal(group);
                group.clear();
            }

        }
        total += getTotal(group);
        return total;
    }

    public static long daySeven() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(seven));
        HashMap<String, String> bags = new HashMap<>();
        HashSet<String> paths = new HashSet<>();
        String buff;
        while ((buff = reader.readLine()) != null) {
            bags.put(buff.substring(0, buff.indexOf(" bag")), buff.substring(buff.indexOf("contain") + 7));
        }


//        paths.add("shiny gold");
//        String stringBuff;
//        boolean anythingNew = true;
//        ArrayList<String> thingsToAdd = new ArrayList<>();
//        while (anythingNew) {
//            anythingNew = false;
//            for (Map.Entry entry : bags.entrySet()) {
//                for (String s : paths) {
//                    stringBuff = entry.getValue().toString();
//                    if (stringBuff.contains(s)) {
//                        if (!paths.contains((String) entry.getKey())) {
//                            thingsToAdd.add((String) entry.getKey());
//                            anythingNew = true;
//                        }
//                    }
//                }
//                paths.addAll(thingsToAdd);
//                thingsToAdd.clear();
//            }
//        }

        return countBags(bags, "shiny gold") - 1;
    }

    public static void dayEight() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(eight));
        ArrayList<String> instructions = new ArrayList<>();
        ArrayList<Integer> repeated = new ArrayList<>();
        String buff, current;
        int accumulator = 0;

        while ((buff = reader.readLine()) != null) {
            instructions.add(buff);
        }
        ArrayList<String> stringBuff = new ArrayList<>(instructions);

        for (int i = 0; i < instructions.size(); i++) {
            if (end(stringBuff, i))
                System.out.println("instruction: " + i);
            stringBuff = new ArrayList<>(instructions);
        }

//Part 1
//        for (int i = 0; i < instructions.size(); i++) {
//            if (repeated.contains(i)){
////                System.out.println(accumulator);
//                  break;
//              }
//            repeated.add(i);
//            current = instructions.get(i);
////            System.out.println(i + " " + current);
//            System.out.println(Integer.parseInt(current.substring(4)));
//            switch (current.substring(0, 3)) {
//                case "acc":
//                    accumulator += Integer.parseInt(current.substring(4));
//                    break;
//                case "jmp":
//                    i += (Integer.parseInt(current.substring(4)) - 1);
//                    break;
//                case "nop":
//                    break;
//            }
//        }
    }

    public static long dayNine() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(nine));
        ArrayList<Long> nums = new ArrayList<>();
        ArrayList<Long> list = new ArrayList<>();
        ArrayList<Integer> invalid = new ArrayList<>();
        String buff;
        long part1 = 0;

        while ((buff = reader.readLine()) != null) {
            nums.add(Long.parseLong(buff));
        }
        for (int i = 0; i < nums.size() - 25; i++) {
            if (!preamble(nums, i, i + 24)) {
                part1 = nums.get(i + 25);
//                System.out.println(part1);
            }
        }

        int iterator = 0, part2;
        for (int i = 0; i < nums.size(); i++) {
            iterator = i;
            part2 = 0;
            list = new ArrayList<>();
            while (part2 < part1) {
                part2 += nums.get(iterator);
                list.add(nums.get(iterator));

                if (part2 == part1) {
                    Collections.sort(list);
                    return list.get(0) + list.get(list.size() - 1);
                }
                iterator++;
            }
        }
        return 0;
    }

    public static void dayTen() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(ten));
        String buff;
        ArrayList<Integer> adapters = new ArrayList<>();
        adapters.add(0);
        while ((buff = reader.readLine()) != null) {
            adapters.add(Integer.parseInt(buff));
        }
        long[] paths = new long[adapters.size()];
        int oneCount = 0, threeCount = 0;

        Collections.sort(adapters);
        System.out.println(adapters);
        paths[0] = 1;
        for (int i = 0; i < adapters.size(); i++) {
            if (adapters.contains(adapters.get(i) - 1)) {
                paths[i] += paths[adapters.indexOf(adapters.get(i) - 1)];
            }
            if (adapters.contains(adapters.get(i) - 2)) {
                paths[i] += paths[adapters.indexOf(adapters.get(i) - 2)];
            }
            if (adapters.contains(adapters.get(i) - 3)) {
                paths[i] += paths[adapters.indexOf(adapters.get(i) - 3)];
            }
        }
        System.out.println(Arrays.toString(paths));

        System.out.println("combos: " + paths[paths.length - 1]);
        //part 1
//        for (int i = 0; i < adapters.size() - 1; i++) {
//            if (adapters.get(i + 1) - adapters.get(i) == 1) {
//                oneCount++;
//            } else if (adapters.get(i + 1) - adapters.get(i) == 3) {
//                threeCount++;
//            }
//        }
//
//        System.out.println((threeCount + 1) * oneCount);
    }

    public static int dayEleven() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(eleven));
        String buff;
        ArrayList<String> lines = new ArrayList<>();

        while ((buff = reader.readLine()) != null) {
            lines.add(buff);
        }

        char[][] seats = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                seats[i][j] = lines.get(i).charAt(j);
            }
        }

        boolean done = false;
        char[][] arrayBuff;

        while (!done) {
            arrayBuff = arrayChange(seats);
            if (arrayBuff == null)
                done = true;
            else {
                seats = arrayChange(seats);
            }
        }
        return filledCount(seats);
    }

    public static int dayTwelve() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(twelve));
        String buff;
        ArrayList<String> directions = new ArrayList<>();
        while ((buff = reader.readLine()) != null) {
            directions.add(buff);
        }

        Coordinate boat = new Coordinate();
        Coordinate wayPoint = new Coordinate(10, 1);
        char currentDirec = 'E', direc;
        int dist, tempX, tempY;

        for (String direction : directions) {
            direc = direction.charAt(0);
            dist = Integer.parseInt(direction.substring(1));

            switch (direc) {
                case 'N':
                    wayPoint.setY(wayPoint.getY() + dist);
                    break;
                case 'S':
                    wayPoint.setY(wayPoint.getY() - dist);
                    break;
                case 'E':
                    wayPoint.setX(wayPoint.getX() + dist);
                    break;
                case 'W':
                    wayPoint.setX(wayPoint.getX() - dist);
                    break;
                case 'L':
                    for (int i = 0; i < dist / 90; i++) {
                        tempY = wayPoint.getY();
                        wayPoint.setY(wayPoint.getX());
                        wayPoint.setX(tempY * -1);
                        switch (currentDirec) {
                            case 'N':
                                currentDirec = 'W';
                                break;
                            case 'S':
                                currentDirec = 'E';
                                break;
                            case 'E':
                                currentDirec = 'N';
                                break;
                            case 'W':
                                currentDirec = 'S';
                                break;
                        }
                    }
                    break;
                case 'R':
                    for (int i = 0; i < dist / 90; i++) {
                        tempX = wayPoint.getX();
                        wayPoint.setX(wayPoint.getY());
                        wayPoint.setY(tempX * -1);
                        switch (currentDirec) {
                            case 'N':
                                currentDirec = 'E';
                                break;
                            case 'S':
                                currentDirec = 'W';
                                break;
                            case 'E':
                                currentDirec = 'S';
                                break;
                            case 'W':
                                currentDirec = 'N';
                                break;
                        }
                    }
                    break;
                case 'F':
                    boat.setX(boat.getX() + (wayPoint.getX() * dist));
                    boat.setY(boat.getY() + (wayPoint.getY() * dist));
                    break;
            }
            //part 1
//            switch (direc) {
//                case 'E':
//                    boat.setX(boat.getX() + dist);
//                    break;
//                case 'W':
//                    boat.setX(boat.getX() - dist);
//                    break;
//                case 'N':
//                    boat.setY(boat.getY() + dist);
//                    break;
//                case 'S':
//                    boat.setY(boat.getY() - dist);
//                    break;
//                case 'F':
//                    switch (currentDirec) {
//                        case 'E':
//                            boat.setX(boat.getX() + dist);
//                            break;
//                        case 'W':
//                            boat.setX(boat.getX() - dist);
//                            break;
//                        case 'N':
//                            boat.setY(boat.getY() + dist);
//                            break;
//                        case 'S':
//                            boat.setY(boat.getY() - dist);
//                            break;
//                    }
//                    break;
//                case 'L':
//                    for (int i = 0; i < dist / 90; i++)
//                        switch (currentDirec) {
//                            case 'E':
//                                currentDirec = 'N';
//                                break;
//                            case 'W':
//                                currentDirec = 'S';
//                                break;
//                            case 'N':
//                                currentDirec = 'W';
//                                break;
//                            case 'S':
//                                currentDirec = 'E';
//                                break;
//                        }
//                    break;
//                case 'R':
//                    for (int i = 0; i < dist / 90; i++)
//                        switch (currentDirec) {
//                            case 'E':
//                                currentDirec = 'S';
//                                break;
//                            case 'W':
//                                currentDirec = 'N';
//                                break;
//                            case 'N':
//                                currentDirec = 'E';
//                                break;
//                            case 'S':
//                                currentDirec = 'W';
//                                break;
//                        }
//                    break;
//            }
        }

        return Math.abs(boat.getX()) + Math.abs(boat.getY());
    }

    public static int filledCount(char[][] array) {
        int total = 0;
        for (char[] chars : array) {
            for (int j = 0; j < array[0].length; j++)
                if (chars[j] == '#')
                    total++;
        }
        return total;
    } // day 11

    public static char[][] arrayChange(char[][] seats) {
        boolean done = true;
        char[][] buff = new char[seats.length][seats[0].length];
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                if (seats[i][j] == 'L' && isFilled(seats, i, j) == 0) {
                    buff[i][j] = '#';
                    done = false;
                } else if (seats[i][j] == '#' && isFilled(seats, i, j) >= 5) {
                    buff[i][j] = 'L';
                    done = false;
                } else
                    buff[i][j] = seats[i][j];
            }
        }
        if (done) {
            buff = null;
        }
        return buff;
    } // day 11

    public static int isFilled(char[][] array, int x, int y) {
        int filled = 0;
        int i = x, j = y;
        //right
        while (j >= 0 && j < array[0].length) {
            if (j != y) {
                if (array[i][j] == '#') {
                    filled++;
                    break;
                } else if (array[i][j] == 'L')
                    break;
            }
            j++;
        }
        i = x;
        j = y;
        //left
        while (j >= 0 && j < array[0].length) {
            if (j != y) {
                if (array[i][j] == '#') {
                    filled++;
                    break;
                } else if (array[i][j] == 'L')
                    break;
            }
            j--;
        }
        i = x;
        j = y;
        //down
        while (i >= 0 && i < array.length) {
            if (i != x) {
                if (array[i][j] == '#') {
                    filled++;
                    break;
                } else if (array[i][j] == 'L')
                    break;
            }
            i++;
        }
        i = x;
        j = y;
        //up
        while (i >= 0 && i < array.length) {
            if (i != x) {
                if (array[i][j] == '#') {
                    filled++;
                    break;
                } else if (array[i][j] == 'L')
                    break;
            }
            i--;
        }
        i = x;
        j = y;
        //diag up-right
        while (i >= 0 && i < array.length && j >= 0 && j < array[0].length) {
            if (i != x && j != y) {
                if (array[i][j] == '#') {
                    filled++;
                    break;
                } else if (array[i][j] == 'L')
                    break;
            }
            i++;
            j--;
        }
        i = x;
        j = y;
        //diag up-left
        while (i >= 0 && i < array.length && j >= 0 && j < array[0].length) {
            if (i != x && j != y) {
                if (array[i][j] == '#') {
                    filled++;
                    break;
                } else if (array[i][j] == 'L')
                    break;
            }
            i--;
            j--;
        }
        i = x;
        j = y;
        //diag down-right
        while (i >= 0 && i < array.length && j >= 0 && j < array[0].length) {
            if (i != x && j != y) {
                if (array[i][j] == '#') {
                    filled++;
                    break;
                } else if (array[i][j] == 'L')
                    break;
            }
            i++;
            j++;
        }
        i = x;
        j = y;
        //diag down-left
        while (i >= 0 && i < array.length && j >= 0 && j < array[0].length) {
            if (i != x && j != y) {
                if (array[i][j] == '#') {
                    filled++;
                    break;
                } else if (array[i][j] == 'L')
                    break;
            }
            i--;
            j++;
        }


        //part 1
//        for (int i = x - 1; i <= x + 1; i++) {
//            for (int j = y - 1; j <= y + 1; j++) {
//                if (i >= 0 && j >= 0 && i < array.length && j < array[0].length) {
//                    if (array[i][j] == '#')
//                        filled++;
//                }
//            }
//        }
//        if (array[x][y] == '#')
//            filled--;
        return filled;
    } // day 11

    public static boolean preamble(ArrayList<Long> nums, int start, int stop) {
        boolean valid = false;
        for (int i = start; i < stop + 1; i++) {
            for (int j = start; j < stop + 1; j++) {
                if (nums.get(i) + nums.get(j) == nums.get(stop + 1) && i != j) {
                    valid = true;
                    break;
                }
            }
        }
        return valid;
    } // day 9

    private static boolean end(ArrayList<String> list, int i) {
        ArrayList<Integer> repeated = new ArrayList<>();
        String current;
        int accumulator = 0;

        if (list.get(i).startsWith("nop"))
            list.set(i, "jmp" + list.get(i).substring(3));
        else if (list.get(i).startsWith("jmp"))
            list.set(i, "nop" + list.get(i).substring(3));

        for (int j = 0; j < list.size(); j++) {
            if (repeated.contains(j)) {
                return false;
            }
            repeated.add(j);
            current = list.get(j);
            switch (current.substring(0, 3)) {
                case "acc":
                    accumulator += Integer.parseInt(current.substring(4));
                    break;
                case "jmp":
                    j += (Integer.parseInt(current.substring(4)) - 1);
                    break;
                case "nop":
                    break;
            }
        }
        System.out.println("accumulator: " + accumulator);
        return true;
    } // day 8

    private static long countBags(HashMap<String, String> bags, String bagType) {
        long numOfSubBags = 1;
        String buff = bags.get(bagType);
        for (int i = 0; i < buff.length(); i++) {
            if (buff.charAt(i) >= 48 && buff.charAt(i) <= 57) {
                numOfSubBags += ((buff.charAt(i) - 48) * countBags(bags, buff.substring(i + 2,
                        buff.indexOf("bag", i)).trim()));
            }
        }
        return numOfSubBags;
    } // day 7

    private static int getTotal(ArrayList<String> group) {
        int groupTotal = 0;
        boolean inEvery;
        char charBuff;
        for (int i = 0; i < group.get(0).length(); i++) {
            inEvery = true;
            charBuff = group.get(0).charAt(i);
            for (String s : group) {
                if (s.indexOf(charBuff) == -1) {
                    inEvery = false;
                    break;
                }
            }
            if (inEvery)
                groupTotal++;
        }

        return groupTotal;
    } // day 6

    public static boolean check(String item) {
        String val, type;
        int buff;
        type = item.substring(0, 3);
        val = item.substring(item.indexOf(":") + 1);
        switch (type) {
            case "byr":
                buff = Integer.parseInt(val);
                return (buff >= 1920 && buff <= 2002);
            case "ecl":
                return (val.equals("amb") || val.equals("blu") || val.equals("brn")
                        || val.equals("gry") || val.equals("grn")
                        || val.equals("hzl") || val.equals("oth"));
            case "eyr":
                buff = Integer.parseInt(val);
                return (buff >= 2020 && buff <= 2030);
            case "hcl":
                if (val.charAt(0) == '#' && val.length() == 7) {
                    for (int i = 1; i < val.length(); i++) {
                        if (!((val.charAt(i) >= 48 && val.charAt(i) <= 57) || (val.charAt(i) >= 97 && val.charAt(i) <= 102))) {
                            return false;
                        }
                    }
                } else
                    return false;
                return true;
            case "hgt":
                if (val.contains("cm") && val.length() == 5) {
                    buff = Integer.parseInt(val.substring(0, 3));
                    return (buff >= 150 && buff <= 193);
                } else if (val.contains("in") && val.length() == 4) {
                    buff = Integer.parseInt(val.substring(0, 2));
                    return (buff >= 59 && buff <= 76);
                } else
                    return false;
            case "iyr":
                buff = Integer.parseInt(val);
                return (buff >= 2010 && buff <= 2020);
            case "pid":
                if (val.length() == 9) {
                    for (int i = 0; i < 9; i++) {
                        if (!(val.charAt(i) >= 48 && val.charAt(i) <= 57)) {
                            return false;
                        }
                    }
                } else
                    return false;
                return true;
        }

        return true;
    } // day 4

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