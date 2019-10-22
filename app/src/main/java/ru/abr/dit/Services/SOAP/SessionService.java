package ru.abr.dit.Services.SOAP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abr.dit.Services.SOAP.UPGRequest.LoginService;
import ru.abr.dit.Services.SOAP.UPGRequest.PreLoginService;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Service
public class SessionService {

    @Autowired
    LoginService loginService;

    @Autowired
    PreLoginService preLoginService;

    public String getSessionId() throws UnsupportedEncodingException {
        return preLoginService.sendPreLoginRequest();
    }

    public String getRequestId(){
        return UUID.randomUUID().toString();
    }


}
