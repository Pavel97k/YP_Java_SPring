package com.example.itogproject.Config;

import com.example.itogproject.Models.Employee;
import com.example.itogproject.Repositories.Employee_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private Employee_repository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employee = Optional.ofNullable(employeeRepository.findUserByUsername(username));
            return employee.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + "not found "));
    }
}
