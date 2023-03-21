package com.csi.dao;

import com.csi.model.Employee;
import com.csi.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeDaoImpl {

    @Autowired
    EmployeeRepo employeeRepoImpl;

    public Employee signUp(Employee employee){
        return  employeeRepoImpl.save(employee);
    }

    public boolean signIn(String empEmailId, String empPassword){
        boolean flag = false;

        Employee employee =  employeeRepoImpl.findByEmpEmailIdAndEmpPassword(empEmailId, empPassword);

        if (employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)){
            flag = true;
        }
        return  flag;
    }

    public Optional getDataById(long empId){
        return employeeRepoImpl.findById(empId);
    }

    public List<Employee> getDataByFirstName(String empFirstName){
        return employeeRepoImpl.findByEmpFirstName(empFirstName);
    }

    public List<Employee> getDataByEmpFirstNameAndEmpLastName(String empFirstName, String empLastName){
        return employeeRepoImpl.findByEmpFirstNameAndEmpLastName(empFirstName, empLastName);
    }
    public Employee getDataByEmpContactNumber(long empContactNumber){
        return employeeRepoImpl.findByEmpContactNumber(empContactNumber);
    }

    public Employee getDataByEmpEmailId(String empEmailId){
        return employeeRepoImpl.findByEmpEmailId(empEmailId);
    }

    public Employee getDataByEmpEmailIdAndEmpPasswrod(String empEmailId, String empPassword){
        return employeeRepoImpl.findByEmpEmailIdAndEmpPassword(empEmailId, empPassword);
    }

    public Employee getAllData(){
        return (Employee) employeeRepoImpl.findAll();
    }

    public List<Employee> saveBulkOfData(List<Employee> employees){
       return employeeRepoImpl.saveAll(employees);
    }

    public Employee updateData(Employee employee){
        return (Employee) employeeRepoImpl.save(employee);
    }

    public void deleteDataById(long empId){
        employeeRepoImpl.deleteById(empId);
    }

    public void deleteAllData(){
       employeeRepoImpl.deleteAll();
    }

}
