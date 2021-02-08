/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FormulaOne.repository;

import com.example.FormulaOne.model.Driver;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Radoko
 */
public interface DriverRepository extends JpaRepository<Driver, Long> {
     @Query(value="select * from competitors e where e.driver_firstname like %:keyword% or e.driver_lastname like %:keyword%", nativeQuery=true)
    List<Driver> findByKeyword(@Param("keyword") String keyword);
}
