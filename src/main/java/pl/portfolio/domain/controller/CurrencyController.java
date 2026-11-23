package pl.portfolio.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.portfolio.domain.controller.dto.DictionaryListResponse;
import pl.portfolio.domain.model.AccountKind;
import pl.portfolio.domain.model.Currency;
import pl.portfolio.domain.service.AccountKindService;
import pl.portfolio.domain.service.CurrencyService;

import java.util.List;

@Controller
@RequestMapping("/api/currency")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @GetMapping
    @RequestMapping("list")
    public ResponseEntity<DictionaryListResponse> list() {
        List<Currency> kinds = currencyService.listCurrencies();
        return new ResponseEntity<>(DictionaryListResponse.forObjects(kinds), HttpStatus.OK);
    }

}
