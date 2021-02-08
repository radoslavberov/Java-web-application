/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FormulaOne.service;

import com.example.FormulaOne.model.Driver;
import com.example.FormulaOne.repository.DriverRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Radoko
 */
@Service
public class DriverService {
    @Autowired
    private DriverRepository repository;
    
    public List<Driver> listAll() {
        return repository.findAll();
    }
    
    public void create(Driver driver) {
        repository.save(driver);
    }
    
    public Driver updateid(Long id) {
        return repository.findById(id).get();
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Object findByKeyword(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
