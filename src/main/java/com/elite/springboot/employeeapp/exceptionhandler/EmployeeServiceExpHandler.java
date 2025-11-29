package com.elite.springboot.employeeapp.exceptionhandler;


import com.elite.springboot.employeeapp.errorobjects.EmployeeError;
import com.elite.springboot.employeeapp.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeServiceExpHandler {

    @ExceptionHandler
    public ResponseEntity<EmployeeError> handleException(EmployeeNotFoundException exception){
        EmployeeError studentExe = new EmployeeError();

        studentExe.setStatus(HttpStatus.NOT_FOUND.value());
        studentExe.setMsg(exception.getMessage());
        studentExe.setTimeStamp(String.valueOf(System.currentTimeMillis()));

        return new ResponseEntity<>(studentExe, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeError> handleException(Exception exception){
        EmployeeError studentExe = new EmployeeError();

        studentExe.setStatus(HttpStatus.BAD_REQUEST.value());
        studentExe.setMsg(exception.getMessage());
        studentExe.setTimeStamp(String.valueOf(System.currentTimeMillis()));

        return new ResponseEntity<>(studentExe, HttpStatus.BAD_REQUEST);
    }
}
