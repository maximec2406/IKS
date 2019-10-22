package ru.abr.dit.Configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.abr.dit.DAO.UPGDAO;

@Configuration
@PropertySource("classpath:corrconn.properties")
public class SOAPConnectionConfiguration {

    @Value("${corr.host}")
    private String host;
    @Value("${corr.port}")
    private String port;
    @Value("${corr.login}")
    private String login;
    @Value("${corr.pass}")
    private String pass;
    @Value("${corr.protocol}")
    private String protocol;
    @Value("${corr.path}")
    private String path;
    @Value("${corr.namespaceURI}")
    private String namespaceURI;
    @Value("${corr.namespace}")
    private String namespace;
    @Value("${corr.servicename}")
    private String servicename;

    @Bean
    public UPGDAO createUPGDAO(){
        return new UPGDAO(host,  port,  login,  pass,  protocol,  path,  namespaceURI,  namespace, servicename);
    }
}
