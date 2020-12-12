/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classrosterc64.service;

import com.mycompany.classrosterc64.dao.AuditDAO;
import com.mycompany.classrosterc64.dao.DAO;
import com.mycompany.classrosterc64.dao.PersistenceException;
import com.mycompany.classrosterc64.dto.Student;
import java.util.List;

/**
 *
 * @author boburmurtozaev
 */
public class ServiceLayer_imple implements ServiceLayer {
    
    
    //A component to be used for Student CRUD operations
    private DAO dao;
    
    private AuditDAO audit;

    public ServiceLayer_imple(DAO dao, AuditDAO audit) {
        this.dao = dao;
        this.audit = audit;
    }
    
    private void validateStudentData(Student student) throws
        DataValidationException {

    if (student.getFirstName() == null
            || student.getFirstName().trim().length() == 0
            || student.getLastName() == null
            || student.getLastName().trim().length() == 0
            || student.getCohort() == null
            || student.getCohort().trim().length() == 0) {

        throw new DataValidationException(
                "ERROR: All fields [First Name, Last Name, Cohort] are required.");
    }
}
    
    

    @Override
    public void createStudent(Student student) throws DuplicateIdException, DataValidationException, PersistenceException {
        
        if(dao.getStudent(student.getStudentId())!=null){
            throw new DuplicateIdException("ERROR: Could not create student. Student Id "+student.getStudentId()+" already exists");
        }
        
        validateStudentData(student);
        
        dao.addStudent(student.getStudentId(), student);
        
        audit.writeAuditEntry(
            "Student " + student.getStudentId() + " CREATED.");
    }

    @Override
    public List<Student> getAllStudents() throws PersistenceException {
        return dao.getAllStudents();
    }

    @Override
    public Student getStudent(String studentId) throws PersistenceException {
        return dao.getStudent(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws PersistenceException {
        
        audit.writeAuditEntry(
            "Student " + studentId + " REMOVED.");
        
        return dao.removeStudent(studentId);
    }
    
}
