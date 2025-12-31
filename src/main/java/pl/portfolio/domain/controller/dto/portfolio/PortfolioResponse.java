package pl.portfolio.domain.controller.dto.portfolio;

import java.util.List;

public record PortfolioResponse(List<AccountInPortfolioResponse> accounts, List<GroupInPortfolioResponse> groups) {
}
