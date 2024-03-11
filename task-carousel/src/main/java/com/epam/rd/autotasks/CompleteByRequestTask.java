package com.epam.rd.autotasks;

public class CompleteByRequestTask implements Task {
    private boolean complete = false;
    private boolean completeSign = false;


    @Override
    public void execute() {
        complete = completeSign;
    }

    @Override
    public boolean isFinished() {
        return complete;
    }

    public void complete() {
        completeSign = true;
    }
}
