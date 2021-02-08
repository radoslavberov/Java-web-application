/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FormulaOne.repository;

import com.example.FormulaOne.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Radoko
 */
public interface DriverRepository extends JpaRepository<Driver, Long> {
    
}
