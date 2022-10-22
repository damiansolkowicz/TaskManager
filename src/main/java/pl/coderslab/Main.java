package pl.coderslab;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    static String[][] tasks;
    static final String FILE_NAME = "tasks.csv";
    static final String[] OPTIONS = {"add", "remove", "list", "exit"};

    public static void main(String[] args) {
        tasks = loadDataToTab(FILE_NAME);
        printOptions(OPTIONS);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            switch (input) {
                case "exit":
                    exit(tasks);
                    System.exit(0);
                    break;
                case "add":
                    newTask();
                    break;
                case "remove":
                    break;
                case "list":
                    printTab(tasks);
                    break;
                default:
                    System.out.println("Please select a correct option.");
            }
            printOptions(OPTIONS);
        }

    }

    public static void printOptions(String[] tab) {
        System.out.println(ConsoleColors.BLUE);
        System.out.println("Please select an option: " + ConsoleColors.RESET);
        for (String option : tab) {
            System.out.println(option);
        }
    }

    public static String[][] loadDataToTab(String fileName) {
        String[][] tasks = new String[1][3];

        File file = new File(FILE_NAME);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String singles = scanner.nextLine();
                String[] words = singles.split(", ");
                for (int i = 0; i < words.length; i++) {

                }
                tasks = addNewItem(tasks, words);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    private static void newTask() {
        // String [][] tasks=new String[0][3];
        String[] singleTask = new String[2];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please add task description");
        String description = scanner.nextLine();
        System.out.println("Please add task due date");
        String dueDate = scanner.nextLine();
        System.out.println("Is your task important: true/false");
        String isImportant = scanner.nextLine();
        singleTask = new String[]{description, dueDate, isImportant};
        tasks=addNewItem(tasks,singleTask);


}
    public static void printTab(String[][] tasks) {

        for (int i = 0; i < tasks.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < tasks[i].length; j++) {
                System.out.print(tasks[i][j] + " ");
            }
            System.out.println();
        }

    }
    public static void exit(String[][] tasks) {


        String line = null;
        try (PrintWriter printWriter = new PrintWriter(FILE_NAME)) {
            for (int i = 0; i < tasks.length; i++) {
                line = StringUtils.join(tasks[i], ", ");
                printWriter.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error write to file");
        }
    }

    public static String[][] addNewItem(String[][] arr, String[] arr2) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = arr2;
        return arr;
}
}