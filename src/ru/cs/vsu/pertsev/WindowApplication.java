package ru.cs.vsu.pertsev;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WindowApplication extends JFrame {
    private final String projectDir = System.getProperty("user.dir");
    private JPanel mainPanel;
    private JButton addRowBtn;
    private JButton deleteRowBtn;
    private JTable table;
    private JButton getDataFromInputFileBtn;
    private JButton saveDataIntoOutputFileBtn;
    private JButton startSolutionBtn;
    private JTextField textFieldInputFilePath;
    private JButton chooseInputFilepath;
    private JTextField textFieldOutputFilePath;
    private JButton chooseOutputFilepath;
    private JTextField moneyTextField;

    public WindowApplication() throws HeadlessException {
        this.setTitle("BebraCompany: Task 10");
        this.setPreferredSize(new Dimension(768, 480));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);

        //Инициализируем пустой InputArgs, который потом заполним и Logic
        InputArgs inputArgs = new InputArgs("", "");
        Logic logic = new Logic();

        //Получаем DefaultTableModel для удобной работы с таблицей
        DefaultTableModel tableModel = new DefaultTableModel(2, 2);
        table.setModel(tableModel);

        //Устанавливаем заголовки таблицы
        Object[] headers = new String[]{"Name", "Price"};
        tableModel.setColumnIdentifiers(headers);


        //Описываем события кнопок
        addRowBtn.addActionListener(e -> {
            tableModel.setRowCount(tableModel.getRowCount() + 1);
        });

        deleteRowBtn.addActionListener(e -> {
            tableModel.setRowCount(tableModel.getRowCount() - 1);
        });

        getDataFromInputFileBtn.addActionListener(e -> {
            String inputFilePath = textFieldInputFilePath.getText();
            inputArgs.setInputFilePath(inputFilePath);

            try {
                fillJTableFromList(tableModel, FileUtils.readCandiesFromFile(inputArgs.getInputFilePath()));
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            try {
                moneyTextField.setText(FileUtils.readMoneysFromFile(inputArgs.getInputFilePath()).toString());
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            ;
        });


        startSolutionBtn.addActionListener(e -> {
            List<Candy> candyList = readListFromJTable(tableModel);
            AnswerTransmitter answerTransmitter = logic.solution(candyList, Integer.valueOf(moneyTextField.getText()));

            moneyTextField.setText(answerTransmitter.getMoneys().toString());
            fillJTableFromList(tableModel, answerTransmitter.getCandyList());
        });

        saveDataIntoOutputFileBtn.addActionListener(e -> {
            List<Candy> candyList = readListFromJTable(tableModel);
            Integer money = Integer.valueOf(moneyTextField.getText());

            AnswerTransmitter answerTransmitter = new AnswerTransmitter(candyList, money);

            inputArgs.setOutputFilePath(textFieldOutputFilePath.getText());

            try {
                FileUtils.writeAnswerIntoFile(answerTransmitter, inputArgs.getOutputFilePath());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });

        chooseInputFilepath.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(projectDir);
            fileChooser.setDialogTitle("Выберите файл");
            int result = fileChooser.showOpenDialog(null);
            if(result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                textFieldInputFilePath.setText(file.getPath());
            }
        });

        chooseOutputFilepath.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(projectDir);
            fileChooser.setDialogTitle("Выберите файл");
            int result = fileChooser.showSaveDialog(null);
            if(result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                textFieldOutputFilePath.setText(file.getPath());
            }
        });


        this.pack();
        this.setVisible(true);
    }

    private static void fillJTableFromList(DefaultTableModel tableModel, List<Candy> candyList) {
        tableModel.setRowCount(candyList.size());
        for(int i = 0; i < candyList.size(); i++) {
            Candy currCandy = candyList.get(i);
            tableModel.setValueAt(currCandy.getName(), i, 0);
            tableModel.setValueAt(currCandy.getPrice(), i, 1);
        }
    }

    public static List<Candy> readListFromJTable(DefaultTableModel tableModel) {
        List<Candy> candyList = new ArrayList<>();

        for(int i = 0; i < tableModel.getRowCount(); i++) {
            String name = (String) tableModel.getValueAt(i, 0);
            Integer price = (Integer) tableModel.getValueAt(i, 1);
            Candy currCandy = new Candy(name, price);
            candyList.add(currCandy);
        }

        return candyList;
    }
}
