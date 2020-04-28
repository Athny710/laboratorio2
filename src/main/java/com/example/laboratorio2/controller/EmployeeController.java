package com.example.laboratorio2.controller;

import com.example.laboratorio2.Entity.Department;
import com.example.laboratorio2.Entity.Employee;
import com.example.laboratorio2.Entity.Job;
import com.example.laboratorio2.repository.DepartmentRepository;
import com.example.laboratorio2.repository.EmployeeRepository;
import com.example.laboratorio2.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    JobRepository jobRepository;

    @GetMapping(value={"","/listar"})
    public String listar(Model model){
        List<Employee> lista = employeeRepository.findAll();
        model.addAttribute("lista", lista);
        return "employee/listar";
    }
    @PostMapping("/save")
    public String guardarEmp(Employee emp) {
        if (emp.getEmployeeid()==null) {
            List<Employee> listaEmp = employeeRepository.findAll(Sort.by("employeeid").descending());
            Employee emp_mayorId = listaEmp.get(0);
            String mayorId = emp_mayorId.getEmployeeid();
            //mayorId=206_AC
            String[] idSplit = mayorId.split("_");
            int mayoridNum = Integer.valueOf(idSplit[0]);
            String idNumstr=String.valueOf(mayoridNum+1);

            Optional<Department> optdepartment= departmentRepository.findById(emp.getDepartment_id());
            Department dep=optdepartment.get();
            String dSN=dep.getDepartmentshortname();

            String idFinal= idNumstr +"_" +dSN;
            emp.setEmployeeid(idFinal);
        }

        employeeRepository.save(emp);
        return "redirect:/employee";
    }

    @GetMapping("/edit")
    public String editarEmp(@RequestParam("id") String id,
                            Model model){
        Optional<Employee> opt = employeeRepository.findById(id);
        if (opt.isPresent()) {
            Employee employee =opt.get();

            List<Job> listaJob = jobRepository.findAll();
            List<Department> listaDep = departmentRepository.findAll();
            List<Employee> listaMan = employeeRepository.findAll();
            model.addAttribute("listaJob", listaJob);
            model.addAttribute("listaDep", listaDep);
            model.addAttribute("listaMan", listaMan);
            model.addAttribute("employee", employee);
            return "employee/editar";
        } else {
            return "redirect:/employee";
        }
    }
}
