package ru.abr.dit.Configurations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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

@EnableJpaRepositories
@EnableWebMvc
@Configuration
@ComponentScan("ru.abr.dit.*")
@EnableTransactionManagement
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

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
        registry.addMapping("/**").allowedOrigins("http://localhost:4200");
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

}
