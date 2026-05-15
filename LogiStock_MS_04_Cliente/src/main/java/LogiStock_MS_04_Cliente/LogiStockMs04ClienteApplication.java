package LogiStock_MS_04_Cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "LogiStock_MS_04_Cliente")
@EnableJpaRepositories(basePackages = "LogiStock_MS_04_Cliente.repository")
@EntityScan(basePackages = "LogiStock_MS_04_Cliente.model")
public class LogiStockMs04ClienteApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogiStockMs04ClienteApplication.class, args);
    }
}