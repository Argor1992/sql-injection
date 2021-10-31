package com.example.application.backend.data;

import java.time.LocalDate;

public class Salary {
    private int salaryId;
    private String employee_id;
    private int amount;
    private int taxes;
    private int children;
    private boolean married;
    private int month;
    private int year;

    public Salary(int salaryId, String employee_id, int amount, int taxes, int children, boolean married, int month, int year) {
        this.salaryId = salaryId;
        this.employee_id = employee_id;
        this.amount = amount;
        this.taxes = taxes;
        this.children = children;
        this.married = married;
        this.month = month;
        this.year = year;
    }

    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTaxes() {
        return taxes;
    }

    public void setTaxes(int taxes) {
        this.taxes = taxes;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getHeader() {
        return "From: " + LocalDate.of(year, month, 1);
    }
}
