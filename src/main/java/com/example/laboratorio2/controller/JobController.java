package com.example.laboratorio2.controller;

import com.example.laboratorio2.Entity.Employee;
import com.example.laboratorio2.Entity.Job;
import com.example.laboratorio2.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/edit")
    public String editarJob(Model model,
                            @RequestParam("id") String id) {

        Optional<Job> optShipper = jobRepository.findById(id);

        if (optShipper.isPresent()) {
            Job job = optShipper.get();
            model.addAttribute("job", job);
            return "job/editar";
        } else {
            return "redirect:/job/listar";
        }
    }

    @GetMapping("/borrar")
    public String borrarJob(@RequestParam("id") String id){
        Optional<Job> opt = jobRepository.findById(id);

        if (opt.isPresent()) {
            jobRepository.deleteById(id);
            return "redirect:/job";
        }else{
            return "redirect:/job";
        }
    }

}
