package com.mourat.udemy.employeesdemo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mourat.udemy.employeesdemo.entity.Employee;
import com.mourat.udemy.employeesdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRESTController {


    private EmployeeService employeeService;
    private ObjectMapper objectMapper;


    public EmployeeRESTController(){

    }

    @Autowired
    public EmployeeRESTController(EmployeeService employeeService, ObjectMapper objectMapper) {
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
    }


    // Create Get mapping for "/employees"
    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return employeeService.findAll();
    }

    // Create Get mapping for "/employees/{employeeId}"
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId){
        return employeeService.findById(employeeId);
    }

    // Create Post mapping for "/employees"
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        // set id = 0 to save the employee
        employee.setId(0);

        // use service to save the employee
        return employeeService.save(employee);
    }

    // Create Put mapping for "/employees"
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        // use service to save the employee
        return employeeService.save(employee);
    }

    // Create Delete mapping for "/employees/{employeeId}"
    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable int employeeId){
        employeeService.deleteById(employeeId);
    }

    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId, @RequestBody Map<String, Object> patchPayload){

        Employee emp = employeeService.findById(employeeId);

        if(emp == null) throw new RuntimeException("Employee id not found - " + employeeId);

        if(patchPayload.containsKey("id")) throw  new RuntimeException("Employee id not allowed in request body - " + employeeId);

        return employeeService.save(apply(patchPayload, emp));
    }

    private Employee apply(Map<String, Object> patchPayload, Employee employee){

        ObjectNode employeeNode = objectMapper.convertValue(employee, ObjectNode.class);
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode, Employee.class);
    }
}
