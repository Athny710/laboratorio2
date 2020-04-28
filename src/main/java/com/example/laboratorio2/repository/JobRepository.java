package com.example.laboratorio2.repository;

import com.example.laboratorio2.Entity.Department;
import com.example.laboratorio2.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,String> {


 }
