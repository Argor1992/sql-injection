package com.example.application.backend.data;

public enum Role {
    EMPLOYEE, CEO, INTERN, WORKING_STUDENT;

    public String getUiText() {
        switch (this) {
            case CEO:
                return "CEO";
            case INTERN:
                return "Intern";
            case EMPLOYEE:
                return "Employee";
            case WORKING_STUDENT:
                return "Working Student";
            default:
                return "";
        }
    }
}
