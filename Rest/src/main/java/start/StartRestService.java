package start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.util.Properties;

@ComponentScan("services")
@SpringBootApplication
public class StartRestService {
    public static void main(String[] args) {
        SpringApplication.run(start.StartRestService.class, args);
    }
    @Primary
    @Bean(name = "properties")
    public Properties getBdProperties() {
        Properties props = new Properties();
        try {
            props.load(StartRestService.class.getResourceAsStream("/server.properties"));

            //props.load(new FileReader("server.properties"));
            System.out.println("Server properties set. ");
            props.list(System.out);
        } catch (IOException e) {
            System.err.println("Configuration file bd.cong not found" + e);

        }
        return props;
    }



}
