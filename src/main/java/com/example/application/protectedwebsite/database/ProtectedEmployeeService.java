package com.example.application.protectedwebsite.database;

import com.example.application.core.backend.DBMain;
import com.example.application.core.backend.data.Employee;
import com.example.application.core.backend.data.Role;
import com.example.application.core.backend.data.Salary;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProtectedEmployeeService {
    private final DBMain dbMain;

    public ProtectedEmployeeService(DBMain dbMain) {
        this.dbMain = dbMain;
    }

    public Optional<Employee> getCurrentUser() {
        return getUser("92063");
    }

    public Optional<Employee> getUser(String id) {
        try {
            PreparedStatement stmt = dbMain.preparedStatement("select * from Employee where id=?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
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
            PreparedStatement stmt = dbMain.preparedStatement("select * from Salary where employee_id=?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
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
        String query = "select * from Salary where employee_id=?";
        if (date != null && !date.isEmpty()) {
            query += " and month=?";
        }
        try {
            PreparedStatement stmt = dbMain.preparedStatement(query);
            stmt.setString(1, id);
            if (date != null && !date.isEmpty()) {
                stmt.setInt(2, Integer.parseInt(date));
            }
            ResultSet rs = stmt.executeQuery();
            System.err.println(query);
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
        } catch (SQLException | NumberFormatException e) {
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
                "?, ?, ?, ?, ?, ?, ?)";

        System.err.println(sql);
        try {
            PreparedStatement stmt = dbMain.preparedStatement(sql);
            stmt.setString(1, id);
            stmt.setInt(2, Integer.parseInt(amount));
            stmt.setInt(3, Integer.parseInt(taxes));
            stmt.setInt(4, Integer.parseInt(children));
            stmt.setBoolean(5, married);
            stmt.setInt(6, Integer.parseInt(month));
            stmt.setInt(7, Integer.parseInt(year));
            return stmt.executeUpdate() >= 1;
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }
}
