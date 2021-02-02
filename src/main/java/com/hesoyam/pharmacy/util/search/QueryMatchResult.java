package com.hesoyam.pharmacy.util.search;

public class QueryMatchResult {
    private boolean matches;
    private double grade;

    public QueryMatchResult(boolean matches, double grade) {
        this.matches = matches;
        this.grade = grade;
    }

    public QueryMatchResult(boolean matches) {
        if(!matches){
            this.matches = false;
            this.grade = 0.0;
        }
    }

    public QueryMatchResult() {
    }

    public boolean getMatches() {
        return matches;
    }

    public void setMatches(boolean matches) {
        this.matches = matches;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
