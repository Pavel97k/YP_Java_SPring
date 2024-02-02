package com.example.itogproject.Repositories;

import com.example.itogproject.Models.Employee;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Employee_repository extends JpaRepository<Employee, Long> {
   Employee findUserByUsername(String username);
}
