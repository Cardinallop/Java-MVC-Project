/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classrosterc64.service;

/**
 *
 * @author boburmurtozaev
 */
public class DuplicateIdException extends Exception{
    
    public DuplicateIdException(String message) {
        super(message);
    }
    
    public DuplicateIdException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
