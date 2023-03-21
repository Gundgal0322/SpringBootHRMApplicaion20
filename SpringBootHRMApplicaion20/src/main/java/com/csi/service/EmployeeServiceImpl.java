package com.csi.service;


import com.csi.dao.EmployeeDaoImpl;
import com.csi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl {


    @Autowired
    EmployeeDaoImpl employeeDaoImpl;

    public Employee signUp(Employee employee){
        return employeeDaoImpl.signUp(employee);
    }

    public boolean signIn(String empEmailId, String empPassword){
        return employeeDaoImpl.signIn(empEmailId,empPassword);
    }

    public Optional getDataById(long empId){
        return employeeDaoImpl.getDataById(empId);
    }

    public List<Employee> getDataByFirstName(String empFirstName){
        return employeeDaoImpl.getDataByFirstName(empFirstName);
    }

    public List<Employee> getDataByEmpFirstNameAndEmpLastName(String empFirstName, String empLastName){
        return employeeDaoImpl.getDataByEmpFirstNameAndEmpLastName(empFirstName, empLastName);
    }
    public Employee getDataByEmpContactNumber(long empContactNumber){
        return  employeeDaoImpl.getDataByEmpContactNumber(empContactNumber);
    }

    public Employee getDataByEmpEmailId(String empEmailId){
        return employeeDaoImpl.getDataByEmpEmailId(empEmailId);
    }

    public Employee getDataByEmpEmailIdAndEmpPassword(String empEmailId, String empPassword){
        return employeeDaoImpl.getDataByEmpEmailIdAndEmpPasswrod(empEmailId, empPassword);
    }

    public List<Employee> getAllData(){
        return (List<Employee>) employeeDaoImpl.getAllData();
    }

    public List<Employee> saveBulkOfData(List<Employee> employees){
         return  employeeDaoImpl.saveBulkOfData(employees);
    }

    public Employee updateData(Employee employee){
        return employeeDaoImpl.updateData(employee);
    }

    public void deleteDataById(long empId){
        employeeDaoImpl.deleteDataById(empId);
    }

    public void deleteAllData(){
        employeeDaoImpl.deleteAllData();
    }
}
