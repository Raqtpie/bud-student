package top.turingteam.budstudent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Raqtpie
 */
@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
@EnableTransactionManagement
public class BudStudentApplication {

    public static void main(String[] args) {
        SpringApplication.run(BudStudentApplication.class, args);
    }

}
