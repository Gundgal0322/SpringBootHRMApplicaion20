package com.csi.controller;


import com.csi.exception.RecordNotFountException;
import com.csi.model.Employee;
import com.csi.service.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Employee employee) {
        employeeServiceImpl.signUp(employee);
        return ResponseEntity.ok("Data Inserted Succesfully");
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId, @PathVariable String empPassword) {
        return ResponseEntity.ok(employeeServiceImpl.signIn(empEmailId, empPassword));
    }

    @GetMapping("/getdatabyid/{empId}")
    public ResponseEntity<Optional> getDataById(@PathVariable long empId) {
        return ResponseEntity.ok(employeeServiceImpl.getDataById(empId));
    }

    @GetMapping("/getdatabyfirstname/{empFirstName}")
    public ResponseEntity<List<Employee>> getDataByEmpFirstName(@PathVariable String empFirstName) {
        return ResponseEntity.ok(employeeServiceImpl.getDataByFirstName(empFirstName));
    }

    @GetMapping("/getdatabyfirstnameandlastname/{empFirstName}/{empLastName}")
    public ResponseEntity<List<Employee>> getDataByEmpFirstNameAndEmpLastName(@PathVariable String empFirstName, @PathVariable String empLastName) {
        return ResponseEntity.ok(employeeServiceImpl.getDataByEmpFirstNameAndEmpLastName(empFirstName, empLastName));
    }

    @GetMapping("/getsdatabyempcontactnumber/{empContactNumber}")
    public ResponseEntity<List<Employee>> getDatabyEmpContactNumber(@PathVariable long empContactNumber) {
        return ResponseEntity.ok(Collections.singletonList(employeeServiceImpl.getDataByEmpContactNumber(empContactNumber)));
    }

    @GetMapping("/getdatabyempemailid/{empEmailId}")
    public ResponseEntity<List<Employee>> getDataByEmpEmailId(@PathVariable String empEmailId) {
        return ResponseEntity.ok(Collections.singletonList(employeeServiceImpl.getDataByEmpEmailId(empEmailId)));
    }

    @PostMapping("/savebulkofdata")
    public ResponseEntity<List<Employee>> saveBulkOfData(@Valid @RequestBody List<Employee> employees) {
        return ResponseEntity.ok(employeeServiceImpl.saveBulkOfData(employees));
    }

    @GetMapping("/sortbyid")
    public ResponseEntity<List<Employee>> sortDataById() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream()
                .sorted(Comparator.comparing(Employee::getEmpId)).collect(Collectors.toList()));
    }

    @GetMapping("/sortbyfirstname")
    public ResponseEntity<List<Employee>> sortByFirstName() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream()
                .sorted(Comparator.comparing(Employee::getEmpFirstName)).collect(Collectors.toList()));
    }

    @GetMapping("/sortbylastname")
    public ResponseEntity<List<Employee>> sortByLastName() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream()
                .sorted(Comparator.comparing(Employee::getEmpLastName)).collect(Collectors.toList()));
    }

    @GetMapping("/sortbysalary")
    public ResponseEntity<List<Employee>> sortBySalary() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream()
                .sorted(Comparator.comparingDouble(Employee::getEmpsalary)).collect(Collectors.toList()));
    }

    @GetMapping("/filterbysalary/{empSalary}")
    public ResponseEntity<List<Employee>> filterBySalary(@PathVariable double empSalary) {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream()
                .filter(emp -> emp.getEmpsalary() >= empSalary).collect(Collectors.toList()));
    }

    @PutMapping("/updatedata/{empId}")
    public ResponseEntity<Employee> updateData(@PathVariable long empId, @RequestBody Employee employee) throws Throwable {

        Employee employee1 = (Employee) employeeServiceImpl.getDataById(empId).orElseThrow(()-> new RecordNotFountException("Employee Id does Not exist"));

        employee1.setEmpFirstName(employee1.getEmpFirstName());
        employee1.setEmpLastName(employee.getEmpLastName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpsalary(employee.getEmpsalary());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpPassword(employee1.getEmpPassword());


        return new ResponseEntity<>(employeeServiceImpl.updateData(employee1), HttpStatus.CREATED);

    }

    @DeleteMapping("/deletedatabyid/{empId}")
    public ResponseEntity<String> deleteDataById(@PathVariable long empId){
        employeeServiceImpl.deleteDataById(empId);
        return ResponseEntity.ok("Record Deleted Successfully");
    }

    @DeleteMapping("/deletealldata")
    public ResponseEntity<String> deleteAllData() {
        employeeServiceImpl.deleteAllData();
        return ResponseEntity.ok("All record Deleted Successfully");
    }
    }
