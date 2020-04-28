package com.example.laboratorio2.controller;

import com.example.laboratorio2.Entity.Department;
import com.example.laboratorio2.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping(value={"","/listar"})
    public String listar(Model model){
        List<Department> lista = departmentRepository.findAll();
        model.addAttribute("lista", lista);
        return "department/lista";
    }
}
