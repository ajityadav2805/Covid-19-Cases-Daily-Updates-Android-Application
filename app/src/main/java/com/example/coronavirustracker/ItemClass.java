package com.example.coronavirustracker;

public class ItemClass {
    private long totalActiveCases;
    private long totalRecoveredCases;
    private long totalConfirmedCases;
    private long totalDeathsCases;
    private String state;

    public ItemClass(long totalActiveCases, long totalRecoveredCases, long totalConfirmedCases, long totalDeathsCases,String state) {
        this.totalActiveCases = totalActiveCases;
        this.totalRecoveredCases = totalRecoveredCases;
        this.totalConfirmedCases = totalConfirmedCases;
        this.totalDeathsCases = totalDeathsCases;
        this.state=state;
    }

    public long getTotalActiveCases() {
        return totalActiveCases;
    }

    public void setTotalActiveCases(long totalActiveCases) {
        this.totalActiveCases = totalActiveCases;
    }

    public long getTotalRecoveredCases() {
        return totalRecoveredCases;
    }

    public void setTotalRecoveredCases(long totalRecoveredCases) {
        this.totalRecoveredCases = totalRecoveredCases;
    }

    public long getTotalConfirmedCases() {
        return totalConfirmedCases;
    }

    public void setTotalConfirmedCases(long totalConfirmedCases) {
        this.totalConfirmedCases = totalConfirmedCases;
    }

    public long getTotalDeathsCases() {
        return totalDeathsCases;
    }

    public void setTotalDeathsCases(long totalDeathsCases) {
        this.totalDeathsCases = totalDeathsCases;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
