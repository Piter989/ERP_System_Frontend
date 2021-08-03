package sample.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sample.handler.AuthenticationResultHandler;
import sample.dto.OperatorAuthenticationResultDto;
import sample.dto.OperatorCredentialsDto;

public class AuthenticatiorImplo implements Authenticatior {

    private static final String AUTHENTICATION_URL = "http://localhost:8080/verify_operator_credentials";

    private  final RestTemplate restTemplate;

    public AuthenticatiorImplo(){

        restTemplate = new RestTemplate();

    }

    @Override
    public void authenticator(OperatorCredentialsDto operatorCredentialsDto, AuthenticationResultHandler authenticationResultHandler) {
       Runnable authenticationTask = ()->{
           processAuthentication(operatorCredentialsDto, authenticationResultHandler);
       };
       Thread authenticationThread = new Thread(authenticationTask);
       authenticationThread.setDaemon(true);
       authenticationThread.start();

    }

    private void processAuthentication(OperatorCredentialsDto operatorCredentialsDto, AuthenticationResultHandler authenticationResultHandler) {

       // ResponseEntity<OperatorAuthenticationResultDto> responseEntity = restTemplate.postForEntity(AUTHENTICATION_URL, operatorCredentialsDto, OperatorAuthenticationResultDto.class);
       OperatorAuthenticationResultDto dto = new OperatorAuthenticationResultDto();
       dto.setAuthenticated(true);
       dto.setFirstName("Piotr");
       dto.setLastName("Surma");
       dto.setIdOperator(1L);
        authenticationResultHandler.handle(dto);
    }
}
