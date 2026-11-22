package pl.portfolio.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.portfolio.businessLogic.DataForDistributionResolver;
import pl.portfolio.businessLogic.PaymentDistributionCalculator;
import pl.portfolio.businessLogic.validations.ValidatedObject;
import pl.portfolio.domain.controller.dto.portfolio.DataForDistributionCompute;
import pl.portfolio.domain.controller.dto.portfolio.PaymentDistribution;
import pl.portfolio.domain.controller.dto.portfolio.PaymentDistributionRequest;
import pl.portfolio.domain.controller.dto.portfolio.PortfolioResponse;
import pl.portfolio.domain.model.Portfolio;
import pl.portfolio.domain.service.PortfolioService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/portfolio")
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

    @PostMapping
    public ResponseEntity<ValidatedObject<Portfolio>> createTestPortfolio(@RequestBody Portfolio portfolio) {
        ValidatedObject<Portfolio> savedPortfolio = portfolioService.createTestPortfolio(portfolio);
        return new ResponseEntity<>(savedPortfolio, savedPortfolio.getStatusByErrors(HttpStatus.CREATED));
    }

    @DeleteMapping
    @RequestMapping("/{portfolioId}")
    public ResponseEntity<Void> deleteTestPortfolio(@PathVariable("portfolioId") Integer portfolioId) {
        portfolioService.deleteTestPortfolio(portfolioId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PortfolioResponse> getUserPortfolio(@RequestParam("portfolioId") Optional<Integer> portfolioId) {
        Portfolio portfolio = portfolioId
                .map(id -> portfolioService.getPortfolio(id))
                .orElse(portfolioService.getCurrentUserPortfolio());
        List<Portfolio> otherPortfolios = portfolioService.getCurrentUserPortfolios().stream()
                .filter(o -> !o.getId().equals(portfolio.getId()))
                .toList();
        return new ResponseEntity<>(PortfolioResponse.forPortfolio(portfolio, otherPortfolios), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/dataToComputeDistribution")
    public ResponseEntity<DataForDistributionCompute> getUserDataToComputeDistribution(@RequestParam("portfolioId") Optional<Integer> portfolioId) {
        Portfolio portfolio = portfolioId
                .map(id -> portfolioService.getPortfolio(id))
                .orElse(portfolioService.getCurrentUserPortfolio());
        DataForDistributionCompute data = new DataForDistributionResolver(portfolio).getData();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping("/computePaymentDistribution")
    public ResponseEntity<PaymentDistribution> computePaymentDistribution(@RequestBody PaymentDistributionRequest request) {
        PaymentDistribution data = new PaymentDistributionCalculator(request.data(), request.paymentValue()).compute();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
