package calendarproject.api;


import calendarproject.core.SimpleEntity;
import calendarproject.core.SimpleEntityRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableJpaAuditing
@EntityScan("calendarproject.core")
@EnableJpaRepositories("calendarproject.core")
@RestController
@SpringBootApplication(scanBasePackages = "calendarproject")
//간단한 테스트
public class ApiApplication {
    private final SimpleEntityRepository repository;

    public ApiApplication(SimpleEntityRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<SimpleEntity> findAll(){
        return repository.findAll();
    }

    @PostMapping("/save")
    public SimpleEntity saveOne(){
        final SimpleEntity e = new SimpleEntity();
        e.setName("hello");
        return repository.save(e);
    }
    public static void main(String[] args){
        SpringApplication.run(ApiApplication.class,args);
    }
}
