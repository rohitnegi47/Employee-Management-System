package net.javaguides.ems.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.mapper.EmployeeMapper;
import net.javaguides.ems.repository.EmployeeRepository;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service                                                            // tells  spring container to create spring bean for this class
@AllArgsConstructor                                                 // use lombok so dont have to create manual constructor
public class EmployeeServiceImpl implements EmployeeService {

 private EmployeeRepository employeeRepository;                     // add constructor dependencies @AllArgsConstructor


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

                                                                       // to convert employeeDto into employee jpa entity
                                                                       // cuz we have to add it into database
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto); // convert dto into entity
        Employee savedEmployee = employeeRepository.save(employee);    // calling save , to save into DB
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);         // convert entity response into Dto
    }


    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

                                                                      // get from repository
        Employee employee =employeeRepository.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee by this id not exits:" + employeeId));
                                                                     // convert entity into dto
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {

        Employee employee= employeeRepository.findById(employeeId)   // find and get that object
                .orElseThrow(()-> new ResourceNotFoundException("Employee is not exits with given id: "+ employeeId));
        employee.setFirstName(updateEmployee.getFirstName());        // then set the update employee dto
        employee.setLastName(updateEmployee.getLastName());
        employee.setEmail(updateEmployee.getEmail());
        Employee updateEmployeeObj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).
                orElseThrow(()-> new ResourceNotFoundException("Employee is not exits with given id: "+ employeeId));
        employeeRepository.deleteById(employeeId);
    }


}
