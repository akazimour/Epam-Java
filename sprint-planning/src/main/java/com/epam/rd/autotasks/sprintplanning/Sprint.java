package com.epam.rd.autotasks.sprintplanning;

import com.epam.rd.autotasks.sprintplanning.tickets.Bug;
import com.epam.rd.autotasks.sprintplanning.tickets.Ticket;
import com.epam.rd.autotasks.sprintplanning.tickets.UserStory;

public class Sprint {

    private int capacity;
    private int ticketsLimit;
    private int estimatedTicketNumber =0;
    private int currentCapacity=0;
    private Ticket[] tickets =new Ticket[1];

    public Sprint(int capacity, int ticketsLimit) {
        this.capacity = capacity;
        this.ticketsLimit = ticketsLimit;
    }

    public boolean addUserStory(UserStory userStory) {
        if (userStory==null || userStory.isCompleted() || estimatedTicketNumber >= ticketsLimit || currentCapacity+userStory.getEstimate() > capacity) {
            return false;
        } else {
            if (userStory.dependencies == null) {
                return addStory(userStory);
            } else if (tickets == null && !(userStory.dependencies == null))
                return false;
            else {
                int c = 0;
                for (int i = 0; i < userStory.dependencies.length; i++) {
                    for (int j = 0; j < tickets.length; j++) {
                        if (tickets[j] == userStory.dependencies[i])
                            c++;
                    }
                    if (c > 0)
                        c = 0;
                    else
                        return false;
                }
                return addStory(userStory);
            }
        }
    }

    public boolean addBug(Bug bugReport) {
        if (bugReport==null)
            return false;
        if(estimatedTicketNumber >= ticketsLimit || bugReport.userStory==null|| bugReport.getEstimate()+currentCapacity>capacity){
            return false;
        }
        else {
            if (bugReport.isCompleted())
                return false;
            estimatedTicketNumber++;
            currentCapacity = currentCapacity + bugReport.getEstimate();
            Ticket[] copy = new Ticket[estimatedTicketNumber];
            System.arraycopy(tickets, 0, copy, 0, tickets.length);
            copy[estimatedTicketNumber - 1] = bugReport;
            tickets = new Ticket[estimatedTicketNumber];
            System.arraycopy(copy, 0, tickets, 0, copy.length);
            return true;
        }
    }

    public Ticket[] getTickets() {
        Ticket[] copy = new Ticket[tickets.length];
        System.arraycopy(tickets,0,copy,0,copy.length);
        return copy;
    }

    public int getTotalEstimate() {
        return currentCapacity;
    }

    public boolean addStory(UserStory userStory){
        estimatedTicketNumber++;
        currentCapacity += userStory.getEstimate();
        Ticket[] arrCopy = new Ticket[estimatedTicketNumber];
        System.arraycopy(tickets, 0,arrCopy, 0,tickets.length);
        arrCopy[estimatedTicketNumber-1]=userStory;
        tickets = new Ticket[estimatedTicketNumber];
        System.arraycopy(arrCopy,0,tickets,0,arrCopy.length);
        return true;
    }
}
