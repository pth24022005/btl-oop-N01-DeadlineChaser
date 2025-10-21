package com.myproject.student.management.service;

import com.myproject.student.management.dao.SubjectDAO;
import com.myproject.student.management.model.Subject;
import java.util.List;

public class SubjectService {
    private SubjectDAO subjectDAO;

    public SubjectService() {
        this.subjectDAO = new SubjectDAO();
    }

    public List<Subject> getAllSubjects() {
        return subjectDAO.getAllSubjects();
    }
}