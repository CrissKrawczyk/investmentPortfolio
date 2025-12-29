package pl.portfolio.domain.model.webApiModels;

import java.util.List;

public record PortfolioResponse(List<AccountResponse> accounts) {
}
