package ru.cs.vsu.pertsev;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileUtils {
    
    public static Integer readMoneysFromFile(String filePath) throws FileNotFoundException {
        FileReader fileReader = new FileReader(filePath);
        Scanner scanner = new Scanner(fileReader);
        int moneys = 0;
        try {
            moneys = scanner.nextInt();
        } finally {
            ;
        }

        return moneys;

    }
    public static List<Candy> readCandiesFromFile(String filePath) throws FileNotFoundException {
        FileReader fileReader = new FileReader(filePath);
        Scanner scanner = new Scanner(fileReader);

        List<Candy> candyList = new ArrayList<>();


        while (scanner.hasNextLine()) {
            try {
                String[] supportArray = scanner.nextLine().split(" ");
                Candy candy = new Candy(supportArray[0], Integer.valueOf(supportArray[1]));

                candyList.add(candy);
            } catch (ArrayIndexOutOfBoundsException exception) {
                continue;
            }
        }

        return candyList;
    }

    public static void writeAnswerIntoFile(AnswerTransmitter answerTransmitter, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        List<Candy> candyList = answerTransmitter.getCandyList();
        Integer moneys = answerTransmitter.getMoneys();

        String finalString = candyList.stream().map(e -> e.getName()).collect(Collectors.joining("\n")) + "\n" + moneys.toString();

        fw.write(finalString);
        fw.close();
    }
}
