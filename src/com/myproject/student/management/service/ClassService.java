package com.myproject.student.management.service;

import com.myproject.student.management.dao.ClassDAO;
import com.myproject.student.management.model.Class;
import java.util.List;

public class ClassService {
    private ClassDAO classDAO;

    public ClassService() {
        this.classDAO = new ClassDAO();
    }

    public List<Class> getAllClasses() {
        return classDAO.getAllClasses();
    }
}