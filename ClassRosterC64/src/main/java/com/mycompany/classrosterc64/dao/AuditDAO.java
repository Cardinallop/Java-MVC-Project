/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classrosterc64.dao;

/**
 *
 * @author boburmurtozaev
 */
public interface AuditDAO {
    
    public void writeAuditEntry(String entry) throws PersistenceException;
    
}
