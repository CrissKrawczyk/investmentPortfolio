package pl.portfolio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.portfolio.domain.model.User;
import pl.portfolio.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = this.repository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        List<String> roles = new ArrayList<>();
        roles.add("user");

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                AuthorityUtils.createAuthorityList(roles));
    }

}
