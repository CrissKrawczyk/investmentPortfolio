package pl.portfolio.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.portfolio.businessLogic.DataForDistributionResolver;
import pl.portfolio.businessLogic.PaymentDistributionCalculator;
import pl.portfolio.domain.controller.dto.portfolio.DataForDistributionCompute;
import pl.portfolio.domain.controller.dto.portfolio.PaymentDistribution;
import pl.portfolio.domain.controller.dto.portfolio.PaymentDistributionRequest;
import pl.portfolio.domain.controller.dto.portfolio.PortfolioResponse;
import pl.portfolio.domain.model.Portfolio;
import pl.portfolio.domain.service.PortfolioService;

@Controller
@RequestMapping("/api/portfolio")
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

    @GetMapping
    public ResponseEntity<PortfolioResponse> getUserPortfolio() {
        Portfolio currentUserPortfolio = portfolioService.getCurrentUserPortfolio();
        return new ResponseEntity<>(PortfolioResponse.forPortfolio(currentUserPortfolio), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/dataToComputeDistribution")
    public ResponseEntity<DataForDistributionCompute> getUserDataToComputeDistribution() {
        Portfolio currentUserPortfolio = portfolioService.getCurrentUserPortfolio();
        DataForDistributionCompute data = new DataForDistributionResolver(currentUserPortfolio).getData();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping("/computePaymentDistribution")
    public ResponseEntity<PaymentDistribution> computePaymentDistribution(@RequestBody PaymentDistributionRequest request) {
        Portfolio currentUserPortfolio = portfolioService.getCurrentUserPortfolio();
        PaymentDistribution data = new PaymentDistributionCalculator(request.data(), request.paymentValue()).compute();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
