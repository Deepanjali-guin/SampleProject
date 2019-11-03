package com.example.demo.controller;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
class EmployeeController {

    private final Logger log = LoggerFactory.getLogger(EmployeeController.class);
    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employees")
    Collection<Employee> employees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employee/{id}")
    ResponseEntity<?> getEmployee(@PathVariable Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/employee")
    ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) throws URISyntaxException {
        log.info("Request to create employee: {}", employee);
        Employee result = employeeRepository.save(employee);
        return ResponseEntity.created(new URI("/api/employee/" + result.getId()))
                .body(result);
    }

    @PostMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
    	System.out.println("Update Employee with ID = " + id + "...");

		Optional<Employee> employeeData = employeeRepository.findById(id);

		if (employeeData.isPresent()) {
			Employee _employee = employeeData.get();
			_employee.setName(employee.getName());
			_employee.setCompany(employee.getCompany());
			_employee.setDept(employee.getDept());
			_employee.setGender(employee.getGender());
				employeeRepository.save(_employee);
		} 
		return new ResponseEntity<>(employee, HttpStatus.CREATED);
	}

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        log.info("Request to delete employee: {}", id);
        employeeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}