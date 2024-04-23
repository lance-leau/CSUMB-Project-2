package com.example.mapwithmarker.Utils;

import android.util.Log;

import java.util.ArrayList;

public class Steps {
    private ArrayList<TripStepView> steps;
    private int size;

    public Steps() {
        this.steps = new ArrayList<TripStepView>();
        this.size = 0;
    }

    public ArrayList<TripStepView> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<TripStepView> steps) {
        this.steps = steps;
    }

    public int getSize() {
        return size;
    }

    public void setStepCount(int stepCount) {
        this.size = stepCount;
    }

    public void addStep(TripStepView step) {
        this.getSteps().add(step);
        this.size ++;
    }

    public void moveStepUp(int rank) {
        if (rank != 0 && rank < size) {
            TripStepView temp = this.steps.get(rank-1);
            this.steps.set(rank-1, this.steps.get(rank));
            this.steps.get(rank-1).setRank(rank-1);
            this.steps.set(rank, temp);
            this.steps.get(rank).setRank(rank);
        }
    }

    public void moveStepDown(int rank) {
        if (0 <= rank && rank != size-1) {
            TripStepView temp = this.steps.get(rank+1);
            this.steps.set(rank+1, this.steps.get(rank));
            this.steps.get(rank+1).setRank(rank+1);
            this.steps.set(rank, temp);
            this.steps.get(rank).setRank(rank);
        }
    }

    public void removeStep(int index){
        for (int i = index; i < size; i++) {
            steps.get(i).setRank(i-1);
        }
        this.steps.remove(index);
        this.size--;
    }

    public void insertStep(TripStepView step, int index) {
        for (int i = index; i < size; i++) {
            steps.get(i).setRank(i+1);
        }
        this.steps.add(index, step);
        this.size++;
    }

    @Override
    public String toString() {
        String ret = "Steps : ";
        for (TripStepView tsv : this.steps) {
            ret += "(" + tsv.getRank() + tsv.stepTextView.getText().toString() + ") ";
        }
        return ret;
    }
}
