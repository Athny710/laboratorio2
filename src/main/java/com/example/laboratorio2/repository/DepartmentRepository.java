package com.example.laboratorio2.repository;

import com.example.laboratorio2.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    Department findByAndDepartmentname(String y);
}
