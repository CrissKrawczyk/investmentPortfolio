package pl.portfolio.businessLogic;

import pl.portfolio.domain.controller.dto.portfolio.DataForDistributionCompute;

public class No100PercentItemsForDistribution extends RuntimeException {

    //TODO translation and error handler on front-end
    public No100PercentItemsForDistribution(DataForDistributionCompute parent) {
        super("Brak 100% dla " + parent.name());
    }

}
