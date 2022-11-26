package ru.cs.vsu.pertsev;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {
    public ConsoleApplication() throws IOException {
        //Получение путей на ввод/вывод из консоли
        InputArgs inputArgs = parseCmdParameters();
        Logic logic = new Logic();


        //Чтение из файла
        List<Candy> inputCandiesList = FileUtils.readCandiesFromFile(inputArgs.getInputFilePath());
        Integer moneys = FileUtils.readMoneysFromFile(inputArgs.getInputFilePath());
        //Обработка данных
        AnswerTransmitter answerTransmitter = logic.solution(inputCandiesList, moneys);

        //Запись в файл
        FileUtils.writeAnswerIntoFile(answerTransmitter, inputArgs.getOutputFilePath());
    }
    public InputArgs parseCmdParameters() {
        Scanner scanner = new Scanner(System.in);
        String[] filePaths = scanner.nextLine().split(" ");

        return new InputArgs(filePaths[0], filePaths[1]);
    }
}
