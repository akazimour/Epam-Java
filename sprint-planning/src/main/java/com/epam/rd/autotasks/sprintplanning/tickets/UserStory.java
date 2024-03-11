package com.epam.rd.autotasks.sprintplanning.tickets;

public class UserStory extends Ticket {

    public UserStory[] dependencies;

    public UserStory(int id, String name, int estimate, UserStory... dependsOn) {
        super(id, name, estimate);
        this.dependencies = dependsOn;
    }

    @Override
    public void complete() {
        if(dependencies.length == 0){
            completed = true;
        }
        else{
            int c = 0;
            for(UserStory u : dependencies){
                if(u.completed == false){
                    c++;
                    break;
                }
            }
            if(c == 0){
                completed = true;
            }
        }
    }

    public UserStory[] getDependencies() {
        UserStory[] copy = new UserStory[dependencies.length];
        System.arraycopy(dependencies,0,copy,0,copy.length);
        return copy;
    }

    @Override
    public String toString() {
        return "[US "+getId()+"] "+getName();
    }
}
