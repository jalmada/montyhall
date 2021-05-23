package com.xaratustra.montyhall.entity;

public class RunResult {
    private int selected;
    private int revealed;
    private int switchedTo;
    private boolean result;

    public RunResult(
        int selected,
        int revealed,
        int switchedTo,
        boolean result) {
        
        this.selected = selected;
        this.revealed = revealed;
        this.switchedTo = switchedTo;
        this.result = result;
    }

    public int getSelected() { return selected; }
    public int getRevealed() { return revealed; }
    public int getSwitchedTo(){ return switchedTo; }
    public boolean getResult(){ return result; }

    public String toString(){
        return String.format("Selected: %s Host Poisoned: %s Switched To: %s Result: %s", selected, revealed, switchedTo, (result ? "Good" : "Bad"));
    }

    public String toString(boolean verbose){
        if(!verbose){
            return toString();
        } else {
            StringBuilder strb = new StringBuilder();

            strb.append("========================================================");
            strb.append(String.format("Host says option no. %s is bad\n", revealed));
            strb.append(String.format("User switches to %s\n", switchedTo));
            strb.append(String.format("The option is: %s\n", (result ? "Good" : "Bad")));

            return strb.toString();
        }
    }
}
