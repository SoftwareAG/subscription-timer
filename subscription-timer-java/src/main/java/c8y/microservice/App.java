package c8y.microservice;

import com.cumulocity.microservice.autoconfigure.MicroserviceApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@MicroserviceApplication
@EntityScan(basePackages = "c8y.microservice.models")
public class App {
    public static void main (String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false"); // Fix for issue with devtool: https://github.com/SoftwareAG/cumulocity-microservice-archetype/issues/11
        SpringApplication.run(App.class, args);
    }
}
