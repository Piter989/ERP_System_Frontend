package sample.rest;

import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sample.handler.DeleteEmployeeHandler;
import sample.handler.SaveEmployeeHandler;
import sample.dto.EmployeeDto;

import java.util.Arrays;
import java.util.List;

public class EmployeeRestClient {

    private static final String EMPLOYEES_URL = "http://localhost:8080/employees";

    private final RestTemplate restTemplate;

    public EmployeeRestClient(){
        restTemplate = new RestTemplate();
    }

    public List<EmployeeDto> getEmployees(){
        ResponseEntity<EmployeeDto[]> employeesResponseEntity = restTemplate.getForEntity(EMPLOYEES_URL, EmployeeDto[].class);
        return Arrays.asList(employeesResponseEntity.getBody());
    }

    public void saveEmployee(EmployeeDto dto, SaveEmployeeHandler handler) {
        ResponseEntity<EmployeeDto> responseEntity = restTemplate.postForEntity(EMPLOYEES_URL, dto, EmployeeDto.class);
        if (HttpStatus.OK.equals(responseEntity.getStatusCode())){
            handler.handle();
        }else{
            //TODO implements
        }

    }

    public EmployeeDto getEmployees(Long idEmployee) {
        String url = EMPLOYEES_URL + "/" + idEmployee;
        ResponseEntity<EmployeeDto> responseEntity = restTemplate.getForEntity(url, EmployeeDto.class);
        if (HttpStatus.OK.equals(responseEntity.getStatusCode())){
            return responseEntity.getBody();
        }else{
            throw new RuntimeException("Can't load employee");
        }
    }

    public void deleteEmployee(Long idEmployee, DeleteEmployeeHandler handler) {
        restTemplate.delete(EMPLOYEES_URL + "/" + idEmployee);
        handler.handle();
    }
}
