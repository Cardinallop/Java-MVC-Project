/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classrosterc64.controller;

import com.mycompany.classrosterc64.dao.DAO;
import com.mycompany.classrosterc64.dao.PersistenceException;
import com.mycompany.classrosterc64.dto.Student;
import com.mycompany.classrosterc64.service.DataValidationException;
import com.mycompany.classrosterc64.service.DuplicateIdException;
import com.mycompany.classrosterc64.service.ServiceLayer;
import com.mycompany.classrosterc64.ui.View;
import java.util.List;

/**
 *
 * @author boburmurtozaev
 *
 *
 * We'll follow the same pattern for this use case: (back-end to front-end
 * style)
 *
 * Update the DAO implementation Update the view Update the controller
 *
 *
 */
public class Controller {

    //A component to interact with the user
    private View view;

    //A component to check business rules and to store the objects
    private ServiceLayer service;

    public Controller(View view, ServiceLayer service) {
        this.view = view;
        this.service = service;
    }


    public void run() {

        boolean keepGoing = true;

        int menuSelection = 0;
        
        try{

        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    listStudents();
                    break;
                case 2:
                    createStudent();
                    break;
                case 3:
                    viewStudent();
                    break;
                case 4:
                    removeStudent();
                    break;
                case 5:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }

        }

        exitMessage();
        
        }catch(PersistenceException e){
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createStudent()  throws PersistenceException {
        view.displayCreateStudentBanner();
        boolean hasErrors = false;
        do{
        Student newStudent = view.getNewStudentInfo();
        try{
        service.createStudent(newStudent);
        view.displayCreateSuccessBanner();
        hasErrors = false;
        }catch(DuplicateIdException | DataValidationException e){
            hasErrors = true;
            view.displayErrorMessage(e.getMessage());
        }
        }while(hasErrors);
    }

    private void listStudents() throws PersistenceException {
        view.displayDisplayAllBanner();
        List<Student> studentList = service.getAllStudents();
        view.displayStudentList(studentList);
    }

    private void viewStudent()  throws PersistenceException{
        view.displayDisplayStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = service.getStudent(studentId);
        view.displayStudent(student);
    }

    private void removeStudent()  throws PersistenceException{
        view.displayRemoveStudentBanner();
        String studentId = view.getStudentIdChoice();
        service.removeStudent(studentId);
        view.displayRemoveSuccessBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
