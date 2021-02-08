/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FormulaOne.controllers;

import com.example.FormulaOne.model.Driver;
import com.example.FormulaOne.service.DriverService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Radoko
 */
@Controller
public class DriverController {
    @Autowired
    private DriverService service;
    
    @GetMapping("/")
    public String viewIndexPage(Model model, String keyword) {
        List<Driver> driversList = service.listAll();
        model.addAttribute("getAllDrivers", driversList);
        
        if(keyword != null) {
            model.addAttribute("getAllDrivers", service.findByKeyword(keyword));
        }
        else {
            model.addAttribute("getAllFootballers", driversList);
        }
        
        return "index";
    }
    @RequestMapping("/")
    public String viewIndexPage(Model model) {
        List<Driver> driversList = service.listAll();
        model.addAttribute("getAllDrivers", driversList);
        return "index";
    }
    
    @RequestMapping("/new_add")
    public String viewNewFootballerForm(Model model) {
        Driver driver = new Driver();
        model.addAttribute("driver", driver);
        return "insert";
    }
    
    @RequestMapping(value = "/save_driver", method = RequestMethod.POST)
    public String addNewFootballer(@ModelAttribute("driver") Driver driver) {
        service.create(driver);
        return "redirect:/";
    }
    
    @RequestMapping("/edit/{id}")
    public ModelAndView viewEditDriverForm(@PathVariable(name = "id") long id) {
        
        ModelAndView mav = new ModelAndView("update");
        Driver driver = service.updateid(id);
        mav.addObject("driver", driver);
        return mav;
    }
    
    @RequestMapping("/delete/{id}")
    public String deleteDriver(@PathVariable(name="id") long id) {
       service.delete(id);
       return "redirect:/";
    }
}
