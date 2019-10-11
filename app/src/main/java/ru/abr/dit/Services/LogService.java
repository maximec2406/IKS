package ru.abr.dit.Services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Autowired
    private Logger log;

    public void addErrorLog(Exception e, String msg){
        log.error(msg);
        log.error(e.getMessage() + "\n" + e.getStackTrace());
    }

    public void addErrorLog(Exception e){
        log.error(e.getMessage() + "\n" + e.getStackTrace());
    }


    public void addInfoLog(String msg){
        log.info(msg);
    }


    public void addWarnLog(String msg){
        log.warn(msg);
    }


}
