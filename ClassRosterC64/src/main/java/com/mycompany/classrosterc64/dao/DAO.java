/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classrosterc64.dao;

import com.mycompany.classrosterc64.dto.Student;
import java.util.List;

/**
 *
 * @author boburmurtozaev
 */
public interface DAO {
    
    Student addStudent(String studentId, Student student) throws PersistenceException;
    
    List<Student> getAllStudents() throws PersistenceException;
    
    Student getStudent(String studentId) throws PersistenceException;
    
    Student removeStudent(String studentId) throws PersistenceException;
    
}
