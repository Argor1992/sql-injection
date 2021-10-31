package com.example.application.unprotectedwebsite.database;

import com.example.application.core.backend.DBMain;
import com.example.application.core.backend.data.Employee;
import com.example.application.core.backend.data.Role;
import com.example.application.core.backend.data.Salary;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UnprotectedEmployeeService {
    private final DBMain dbMain;

    public UnprotectedEmployeeService(DBMain dbMain) {
        this.dbMain = dbMain;
    }

    public Optional<Employee> getCurrentUser() {
        try {
            ResultSet rs = dbMain.executeQuery("select * from Employee where id=\"92063\"");
            if (rs.next()) {
                Employee employee = new Employee(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        Role.valueOf(rs.getString(6))
                );
                return Optional.of(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Employee> getUser(String id) {
        try {
            ResultSet rs = dbMain.executeQuery("select * from Employee where id=\""+id+"\"");
            if (rs.next()) {
                Employee employee = new Employee(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        Role.valueOf(rs.getString(6))
                );
                return Optional.of(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Salary> getSalaries(String id) {
        try {
            ResultSet rs = dbMain.executeQuery("select * from Salary where employee_id=\"" + id + "\"");
            List<Salary> salaries = new ArrayList<>();
            while (rs.next()) {
                salaries.add( new Salary(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getBoolean(6),
                        rs.getInt(7),
                        rs.getInt(8)
                ));
            }
            return salaries;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<Salary> getSalariesByMonth(String id, String date) {
        String query = "select * from Salary where employee_id=\"" + id + "\"";
        if (date != null && !date.isEmpty()) {
            query += " and month=" + date;
        }
        try {
            System.err.println(query);
            ResultSet rs = dbMain.executeQuery(query);
            List<Salary> salaries = new ArrayList<>();
            while (rs.next()) {
                salaries.add( new Salary(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getBoolean(6),
                        rs.getInt(7),
                        rs.getInt(8)
                ));
            }
            return salaries;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public boolean storeNewSalary(
            String id, String amount, String taxes, String children, boolean married, String month, String year) {
        if (id == null || amount == null || taxes == null || children == null || month == null ||
        year == null || id.isEmpty() || amount.isEmpty() || taxes.isEmpty() || children.isEmpty() ||
        month.isEmpty() || year.isEmpty())
            return false;

        String sql = "insert into Salary (employee_id, amount, taxes, children, married, month, year) values (" +
                "\"" + id + "\", " + amount + ", " + taxes + ", " + children + ", " + married + ", " + month +
                ", " + year + ")";

        System.err.println(sql);
        try {
            return dbMain.executeUpdate(sql) >= 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
