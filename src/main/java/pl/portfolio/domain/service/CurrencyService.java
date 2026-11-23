package pl.portfolio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.portfolio.domain.model.AccountKind;
import pl.portfolio.domain.model.Currency;
import pl.portfolio.domain.repository.CurrencyRepository;

import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public List<Currency> listCurrencies() {
        return currencyRepository.findAll();
    }

    public Currency getCurrency(Integer id) {
        return currencyRepository.getReferenceById(id);
    }

}
