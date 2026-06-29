package com.gcu.activity.model;

public enum TaskStatus {
    NOT_STARTED("Not Started"),
    IN_PROGRESS("In Progress"),
    COMPLETE("Complete"),
    BLOCKED("Blocked");

    private final String displayName;

    TaskStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}