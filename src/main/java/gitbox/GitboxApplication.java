package gitbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories("gitbox.Repositry")
@EntityScan("gitbox.models")
@ComponentScan("gitbox")
public class GitboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(GitboxApplication.class, args);
    }
}
