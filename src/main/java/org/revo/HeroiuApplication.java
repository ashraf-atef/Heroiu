package org.revo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@SpringBootApplication
public class HeroiuApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeroiuApplication.class, args);
    }
}

@Entity
class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

interface UserRepository extends CrudRepository<User, Long> {

}

@RestController
class Api {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public Iterable<User> userList() {
        User s = new User();
        s.setName(UUID.randomUUID().toString());
        userRepository.save(s);
        return userRepository.findAll();
    }
}
