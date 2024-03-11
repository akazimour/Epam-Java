package com.epam.rd.autotasks;

import java.util.ArrayList;
import java.util.List;

public class TaskCarousel {

    private int capacity;
    private int counter = 0;
    private List<Task> taskList = new ArrayList<>();

    public TaskCarousel(int capacity) {

        if (capacity<=0) {
            throw new UnsupportedOperationException();
        }
        this.capacity = capacity;

    }

    public boolean addTask(Task task) {
      if (isFull()==true || task== null || task.isFinished()==true){
          return false;
      }
        taskList.add(task);
        return true;
    }

    public boolean execute() {
       if (isEmpty())
           return false;
       isDone();
       taskList.get(counter).execute();
       if (taskList.get(counter).isFinished()){
           taskList.remove(counter);
       }else {
           counter++;
       }
       return true;
    }

    public boolean isFull() {
      return taskList.size()==capacity;
    }

    public boolean isEmpty() {
       if (taskList.size()==0){
           return true;
       }
       return false;
    }
    private void isDone(){
        if (counter >= taskList.size()){
            counter = 0;
        }
    }

}
