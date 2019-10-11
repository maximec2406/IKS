package ru.abr.dit.Configurations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;

@EnableJpaRepositories
@EnableWebMvc
@Configuration
@ComponentScan("ru.abr.dit.*")
@EnableTransactionManagement
@PropertySource("classpath:corrconn.properties")
@Import(SOAPConnectionConfiguration.class)
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

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

    @Bean
    public EntityManagerFactory createEntityManagerFactory(){
        return Persistence.createEntityManagerFactory("TestPersistUnit");
    }

    @Bean
    public PlatformTransactionManager transactionManager (@Autowired EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("GET", "POST", "PUT", "DELETE");
    }

    @Bean
    public Logger createLogger(){
        return Logger.getRootLogger();
    }

    @Bean
    public ViewResolver createViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setContentType("charset=UTF-8");
        resolver.setPrefix("/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Bean
    public SOAPConnectionFactory createSOAPConnectionFactory(@Autowired Logger logger){

        SOAPConnectionFactory soapConnectionFactory = null;
        try {
            soapConnectionFactory = SOAPConnectionFactory.newInstance();
        } catch (SOAPException e) {
            logger.error("Не удалось создать SOAPConnectionFactory" + e.getMessage() + "\n" + e.getStackTrace());
        }
        return soapConnectionFactory;
    }

    @Bean
    public MessageFactory createMessageFactory(@Autowired Logger logger){

        MessageFactory messageFactory = null;

        try {
            messageFactory = MessageFactory.newInstance();
        } catch (SOAPException e) {
            logger.error("Не удалось создать MessageFactory"+ e.getMessage() + "\n" + e.getStackTrace());
        }

        return messageFactory;
    }

}
