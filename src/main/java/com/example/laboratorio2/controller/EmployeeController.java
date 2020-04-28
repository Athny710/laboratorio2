package com.example.laboratorio2.controller;

import com.example.laboratorio2.Entity.Employee;
import com.example.laboratorio2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;
    @GetMapping(value={"","/listar"})
    public String listar(Model model){
        List<Employee> lista = employeeRepository.findAll();
        model.addAttribute("lista", lista);
        return "employee/listar";
    }
}
