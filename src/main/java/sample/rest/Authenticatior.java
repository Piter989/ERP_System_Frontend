package sample.rest;

import sample.handler.AuthenticationResultHandler;
import sample.dto.OperatorCredentialsDto;

public interface Authenticatior {

    void authenticator(OperatorCredentialsDto userDetencialsDto, AuthenticationResultHandler authenticationResultHandler);

}
