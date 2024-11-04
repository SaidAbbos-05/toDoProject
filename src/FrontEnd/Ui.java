package src.FrontEnd;


import src.Backend.Status;
import src.Backend.Task;
import src.Backend.services.TaskService;

import java.util.ArrayList;


public class Ui {
    private static final TaskService taskService =new TaskService();


    public static void main(String[] args) {
        System.out.println("Welcome to My Task App");
        taskMenu();
    }


    private static void taskMenu() {
        System.out.println("Hi ");
        taskLoop:
        while (true) {
            System.out.println("""
                       ===== Task menu =====
                       1.Show Tasks
                       2.Add Task
                       3.Delete
                       4.Update
                       0.Log Out
                       """);
            Integer choose = Ui_Util.getInt("Choose : ");
            switch (choose) {
                case 1 -> {
                    showTasks();
                }
                case 2 -> {
                    addTask();
                }
                case 3 -> {
                    deleteTask();
                }
                case 4 -> {
                    updateTask();
                }
                case 0 -> {
                    System.out.println("Bye Bye ...");
                    break taskLoop;
                }
                default -> {
                }
            }
        }
    }



    private static void updateTask() {
        System.out.println("""
                       ===== UPDATE MENU =====
                       List of TASKS:
                       """
        );
        
        showTasks(1);
        int choose = Ui_Util.getInt("Choose task for changing: ") - 1;
        showTask(choose);
        int num = Ui_Util.getInt("Choose once for change: ");
        Object data = new Object();
        if (num == 1) {
            data = Ui_Util.getStr("Enter new name: ");
        } else if (num == 2) {
            data = Ui_Util.getStr("Enter new Description: ");
        } else if (num == 3) {
            System.out.println(Status.getAllValuesAsString());
            Integer statusOrder = Ui_Util.getInt("Choose new status: ");
            data = Status.getValueByOrder(statusOrder);
        }
        taskService.update(num, choose, data);
        System.out.println("Successfully Updated !!!!!");
    }

    private static void deleteTask() {
        System.out.println("""
                        ===== DELETE MENU =====
                        List of TASKS:
                        """);
        showTasks(1);
        Integer anInt = Ui_Util.getInt("Choose: ");
        try {
            taskService.delete(anInt - 1);
        } catch (IndexOutOfBoundsException i) {
            System.out.println("Entering index or data went wrong ❌❌❌ \n" + i.getMessage());
        }
        System.out.println("Successfully deleted 👋👋👋");
    }

    private static void addTask() {
        System.out.println("Please Enter Task Info: ");
        String name = Ui_Util.getStr("Name: ");
        String description = Ui_Util.getStr("Description: ");
        System.out.println("Status:");
        System.out.println(Status.getAllValuesAsString());
        Integer statusOrder = Ui_Util.getInt("Choose status: ");
        Status status = Status.getValueByOrder(statusOrder);

        Task task = new Task(name, description, status);


        boolean b = taskService.create(task);
        if (b) {
            System.out.println("Successfully Created🎆🎆🎆🎆");
        } else {
            System.out.println("Failed, Something Wrong❌❌❌");
        }
    }

    private static void showTasks() {
        System.out.println("==============================================");
        ArrayList<Task> all = taskService.getAllTasks();
        for (Task task : all) {
            System.out.println(task);
        }
        System.out.println("==============================================");
    }

    private static void showTasks( int num ){
        System.out.println("==============================================");
        ArrayList<Task> all = taskService.getAllTasks();
        for (Task task : all) {
            System.out.println((num++) + "-" + task);
        }
        System.out.println("==============================================");
    }


    private static void showTask( int index){
        System.out.println(taskService.getTask(index));
    }


};

