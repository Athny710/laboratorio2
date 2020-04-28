package com.example.laboratorio2.controller;

import com.example.laboratorio2.Entity.Job;
import com.example.laboratorio2.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/job")
public class JobController {

    @Autowired
    JobRepository jobRepository;
    @GetMapping(value={"","/listar"})
    public String listar(Model model){
        List<Job> lista = jobRepository.findAll();
        model.addAttribute("lista", lista);
        return "job/lista";
    }
}
