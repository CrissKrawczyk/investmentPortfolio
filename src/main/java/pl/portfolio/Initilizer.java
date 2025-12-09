package pl.portfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.portfolio.domain.model.User;
import pl.portfolio.domain.repository.UserRepository;

@Component
public class Initilizer implements CommandLineRunner {

    @Autowired
    UserRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public void run(String... strings) {
        if (!repository.findAll().isEmpty())
            return;
        User user = new User();
        user.setForename("user");
        user.setSurname("user");
        user.setPassword(passwordEncoder.encode("user"));
        user.setLogin("user");
        this.repository.save(user);
    }
}