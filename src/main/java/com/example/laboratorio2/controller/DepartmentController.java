package com.example.laboratorio2.controller;

import com.example.laboratorio2.Entity.Department;
import com.example.laboratorio2.Entity.Job;
import com.example.laboratorio2.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(value = "/nuevo")
    public String nuevoDepartment(){
        return "department/crear";
    }


    @PostMapping(value="/guardar")
    public String guardarDepartment(Department department, RedirectAttributes attr){
        if (department.getDepartmentshortname().length()<3) {
            if (department.getDepartmentid() == 0) {
                List<Department> list = departmentRepository.findAll(Sort.by("departmentid").descending());
                Department ultimoDepartment = list.get(0);
                department.setDepartmentid(ultimoDepartment.getDepartmentid() + 10);
                attr.addFlashAttribute("msg", "Departamento creado exitosamente");
            } else {
                attr.addFlashAttribute("msg", "Departamento " + department.getDepartmentname() + " actualizado exitosamente");
            }

        }else{
            attr.addFlashAttribute("msg", "Nombre corto no debe ser más de 2 letras");
            return "redirect:/nuevo";
        }

        departmentRepository.save(department);
        return "redirect:/department";
    }


    @GetMapping("/editar")
    public String editarDepartment(Model model,
                            @RequestParam("id") int id) {

        Optional<Department> optDepartment = departmentRepository.findById(id);

        if (optDepartment.isPresent()) {
            Department department = optDepartment.get();
            model.addAttribute("department", department);
            return "department/editar";
        } else {
            return "redirect:/department/listar";
        }
    }


    @GetMapping("/borrar")
    public String borrarDepartment(@RequestParam("id") int id,
                                   RedirectAttributes attr){

        Optional<Department> opt = departmentRepository.findById(id);

        if (opt.isPresent()) {
            departmentRepository.deleteById(id);
            return "redirect:/department";
        }else{
            return "redirect:/department";
        }
    }
}
