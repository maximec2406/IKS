package ru.abr.dit.Services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LogService {

    @Qualifier("root")
    private Logger log;

    @Qualifier("request")
    private Logger reqLog;

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

    public void addRequest(String requestId, String sessionId, String body){
        reqLog.info( "[" + new Date().toString() +"]" + " " + "Отправлен запрос. " +
                "RequestId : '" + requestId + "', " +
                "SessionId : '" + sessionId + "'\n" +
                body
        );
    }

    public void addRsponce(String responseId, String requestId, String sessionId, String body){
        reqLog.info( "[" + new Date().toString() +"]" + " " + "Получен ответ. " +
                "ResponseId : '" + responseId + "', " +
                "RequestId : '" + requestId + "', " +
                "SessionId : '" + sessionId + "'\n" +
                body
        );
    }


}
