package pl.portfolio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.portfolio.businessLogic.validations.ValidatedObject;
import pl.portfolio.domain.model.Account;
import pl.portfolio.domain.model.AccountGroup;
import pl.portfolio.domain.model.AccountKind;
import pl.portfolio.domain.model.Portfolio;
import pl.portfolio.domain.repository.AccountKindRepository;
import pl.portfolio.domain.repository.AccountRepository;

import java.util.List;

@Service
public class AccountKindService {

    @Autowired
    private AccountKindRepository accountKindRepository;

    public List<AccountKind> listKinds() {
        return accountKindRepository.findAll();
    }

    public AccountKind getKind(Integer id) {
        return accountKindRepository.getReferenceById(id);
    }

}
