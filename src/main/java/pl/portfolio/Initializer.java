package pl.portfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.portfolio.domain.model.Currency;
import pl.portfolio.domain.model.User;
import pl.portfolio.domain.repository.CurrencyRepository;
import pl.portfolio.domain.repository.UserRepository;
import pl.portfolio.domain.service.UserService;

@Component
public class Initializer implements CommandLineRunner {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public void run(String... strings) {
        if (!userRepository.findAll().isEmpty())
            return;
        initializeUsers();
        initializeCurrencies();
    }

    private void initializeUsers() {
        User user = new User();
        user.setForename("user");
        user.setSurname("user");
        user.setPassword("admin123!USER!");
        user.setLogin("user");
        this.userService.createUser(user);
    }

    private void initializeCurrencies() {
        Currency pln = new Currency();
        pln.setCode("PLN");
        pln.setName("Złoty");
        this.currencyRepository.save(pln);

        Currency euro = new Currency();
        euro.setCode("EUR");
        euro.setName("Euro");
        this.currencyRepository.save(euro);
    }
}