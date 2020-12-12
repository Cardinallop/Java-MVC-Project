/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classrosterc64;

import com.mycompany.classrosterc64.controller.Controller;
import com.mycompany.classrosterc64.dao.AuditDAO;
import com.mycompany.classrosterc64.dao.AuditDAO_imple;
import com.mycompany.classrosterc64.dao.DAO;
import com.mycompany.classrosterc64.dao.DAO_file_imple;
import com.mycompany.classrosterc64.service.ServiceLayer;
import com.mycompany.classrosterc64.service.ServiceLayer_imple;
import com.mycompany.classrosterc64.ui.UserIO;
import com.mycompany.classrosterc64.ui.UserIO_console_imple;
import com.mycompany.classrosterc64.ui.View;

/**
 *
 * @author boburmurtozaev
 */
public class App {
    
    public static void main(String[] args) {
        
        UserIO myIO = new UserIO_console_imple();
        
        View myView = new View(myIO);
        
        DAO myDAO = new DAO_file_imple();
        
        AuditDAO myAudit = new AuditDAO_imple();
        
        ServiceLayer myService = new ServiceLayer_imple(myDAO, myAudit);
        
        Controller controller = new Controller(myView, myService);
        controller.run();
        
        //Controller has a run method that starts its ochestration of everything 
    }   
    
}
