package pl.portfolio.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.portfolio.businessLogic.validations.ValidatedObject;
import pl.portfolio.domain.model.User;
import pl.portfolio.domain.service.UserService;

@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<ValidatedObject<User>> register(@RequestBody User user) {
        ValidatedObject<User> savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, savedUser.getStatusByErrors(HttpStatus.CREATED));
    }

}
