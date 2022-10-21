package pl.coderslab;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static final String FILE_NAME = "tasks.csv";
    static final String[] OPTIONS = {"add", "remove", "list", "exit"};

    public static void main(String[] args) {
        String[][] task = loadDataToTab(FILE_NAME);
         printOptions(OPTIONS);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            switch (input) {
                case "exit":
                    exit(task);
                    System.exit(0);
                    break;
                case "add":
                    newTask(task);
                    break;
                case "remove":
                    break;
                case "list":
                    printTab(task);
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
  String[][] task= new String[0][];

        File file = new File(FILE_NAME);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String singleline = scanner.nextLine();
                String[] words = singleline.split(", ");
                for (int i=0; i< words.length;i++){

                }  task=addNewItem(task,words);
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return task;
    }
    private static String[][] newTask(String[][] task){
        //   String[][] task=new String[0][];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please add task description");
        String description = scanner.nextLine();
        System.out.println("Please add task due date");
        String dueDate = scanner.nextLine();
        System.out.println("Is your task important: true/false");
        String isImportant = scanner.nextLine();
        String[] singleTask={description,dueDate,isImportant};
        task=addNewItem(task,singleTask);


        return task;
    }
    public static void printTab(String[][] task) {

        for (int i = 0; i < task.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < task[i].length; j++) {
                System.out.print(task[i][j] + " ");
            }
            System.out.println();
        }

    }
    public static void exit(String[][] task) {

        Path newFilePath = Paths.get("task.csv");
        String line = null;
        try (PrintWriter printWriter = new PrintWriter("task.csv")) {
            for (int i = 0; i < task.length; i++) {
                line = StringUtils.join(task[i], ", ");
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