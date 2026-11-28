package pl.portfolio.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.portfolio.MessageSourceConfiguration;
import pl.portfolio.businessLogic.validations.ValidatedObject;
import pl.portfolio.domain.controller.dto.DictionaryListResponse;
import pl.portfolio.domain.controller.dto.Transaction.TransactionResponse;
import pl.portfolio.domain.controller.dto.position.PositionResponse;
import pl.portfolio.domain.model.*;
import pl.portfolio.domain.service.AccountService;
import pl.portfolio.domain.service.PositionService;
import pl.portfolio.domain.service.TransactionService;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/positionKind")
public class PositionKindController {

    @Autowired
    AccountService accountService;

    @Autowired
    PositionService positionService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    MessageSourceConfiguration messageSourceConfiguration;

    @GetMapping
    @RequestMapping("list")
    public ResponseEntity<DictionaryListResponse> list() {
        ResourceBundleMessageSource messageSource = messageSourceConfiguration.messageSource();
        DictionaryListResponse list = DictionaryListResponse.forEnums(Arrays.asList(PositionKind.values()), "PositionKind", messageSource);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
