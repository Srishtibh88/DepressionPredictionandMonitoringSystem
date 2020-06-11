package com.ashish.emotionoptimizer;

public class PatientHistory {

    private String prediction;

    public PatientHistory(String prediction){
        this.prediction = prediction;
    }

    public String getPredictedDepression() {
        return prediction;
    }

}
