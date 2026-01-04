package pl.portfolio.domain.controller.dto.portfolio;

import java.util.List;

public record PaymentDistribution(DistributionObjectType type, String name, List<PaymentDistribution> items, double value) {
}
