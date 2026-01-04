package pl.portfolio.businessLogic;

import pl.portfolio.domain.controller.dto.portfolio.DataForDistributionCompute;
import pl.portfolio.domain.controller.dto.portfolio.PaymentDistribution;

import java.util.ArrayList;
import java.util.List;

public class PaymentDistributionCalculator {

    private final DataForDistributionCompute data;
    private final double paymentValue;

    public PaymentDistributionCalculator(DataForDistributionCompute data, double paymentValue) {
        this.data = data;
        this.paymentValue = paymentValue;
    }

    public PaymentDistribution compute() {
        return computeParent(data, paymentValue);
    }

    public PaymentDistribution computeParent(DataForDistributionCompute parent, double payment) {
        List<PaymentDistribution> items = computeItems(parent, payment);
        return new PaymentDistribution(parent.type(), parent.name(), items, payment);
    }

    public List<PaymentDistribution> computeItems(DataForDistributionCompute parent, double payment) {
        List<DataForDistributionCompute> items = parent.items();
        List<PaymentDistribution> result = new ArrayList<>();
        double parentValue = parent.value();
        double wantedParentValue = parentValue + payment;
        for (DataForDistributionCompute item : items) {
            double itemValue = item.value();
            double wantedValue = wantedParentValue * item.percentageValue() / 100;
            if (wantedValue <= itemValue) {
                continue;
            }
            double itemPayment = wantedValue - itemValue;
            if (itemPayment >= payment) {
                result.add(computeParent(item, payment));
                break;
            }
            result.add(computeParent(item, itemPayment));
            payment -= itemPayment;
        }
        return result;
    }

}
