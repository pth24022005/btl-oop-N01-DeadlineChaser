package com.myproject.student.management.dao;

import com.myproject.student.management.model.Class;
import com.myproject.student.management.util.DatabaseConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO {
    public List<Class> getAllClasses() {
        List<Class> classList = new ArrayList<>();
        String sql = "SELECT * FROM classes";
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Class cls = new Class(
                    rs.getString("class_id"),
                    rs.getString("class_name"),
                    rs.getString("department")
                );
                classList.add(cls);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classList;
    }
}