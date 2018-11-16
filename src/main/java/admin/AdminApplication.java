package admin;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan(basePackages = "core.dao")
@ComponentScan("core")
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class AdminApplication {
    public static void main(String args[]){
        SpringApplication.run(AdminApplication.class,args);
    }
}
