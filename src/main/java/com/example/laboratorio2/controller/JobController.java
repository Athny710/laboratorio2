package com.example.laboratorio2.controller;

import com.example.laboratorio2.Entity.Department;
import com.example.laboratorio2.Entity.Employee;
import com.example.laboratorio2.Entity.Job;
import com.example.laboratorio2.repository.DepartmentRepository;
import com.example.laboratorio2.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/job")
public class JobController {

    @Autowired
    JobRepository jobRepository;
    DepartmentRepository departmentRepository;

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

    @GetMapping("/guardar")
    public String guardar(Job job, @RequestParam("department") String dep, RedirectAttributes attr){
        String name = departmentRepository.findByAndDepartmentname(dep).getDepartmentshortname();
        String id = job.getJob_title()+"_"+name;
        job.setJob_id(id);
        List<Job> l = jobRepository.findByJob_id(id);
        if(l.isEmpty()){
            attr.addFlashAttribute("msg", "Usuario creado exitosamente");
            return "redirect:/job";
        }else{
            attr.addFlashAttribute("msg", "El id del usuario ya existe");
            return "redirect:/job/agregar";
        }
    }


    @GetMapping("/agregar")
    public String nuevoJob(){
        return "job/agregar";
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
