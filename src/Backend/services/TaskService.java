package src.Backend.services;


import src.Backend.Status;
import src.Backend.Task;

import java.util.ArrayList;
import java.util.StringJoiner;

public class TaskService {

    private final ArrayList<Task> tasks;

    public TaskService() {
        this.tasks = new ArrayList<>();
        Task task = new Task("Salomber", "salomlash", Status.IN_PROGRESS);
        tasks.add(task);
    }

    public boolean create(Task task) {
        return tasks.add(task);
    }


    public String getTask(int index){
        StringJoiner stringJoiner = new StringJoiner("");
        for (int i =0; i<tasks.size(); i++) {
            if (tasks.get(i) instanceof Task task && i == index){
                stringJoiner.add("1- Name of Task: "+task.getName()+"\n")
                        .add("2- Description: "+task.getDescription()+"\n")
                        .add("3- Status of Task: "+task.getDescription());
            }
        }
        return stringJoiner.toString();
    }

    public void delete(Integer anInt) throws IndexOutOfBoundsException{
        tasks.remove(tasks.get(anInt));
    }


    public void update(int num, int index, Object data) {
        switch (num) {
            case 1:
                tasks.get(index).setName((String)data);
                break;
            case 2:
                tasks.get(index).setDescription((String)data);
                break;
            case 3:
                tasks.get(index).setStatus((Status) data);
                break;
        }
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> res = new ArrayList<>();
        for(int i =0; i<tasks.size(); i++) {Object o = tasks.get(i);
            if (o instanceof Task task){
                res.add(task);
            }
        }
        return res;
    }


}
