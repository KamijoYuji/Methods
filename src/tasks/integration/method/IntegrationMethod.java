package tasks.integration.method;

import helper.Func;

import java.math.BigDecimal;

public interface IntegrationMethod {
    BigDecimal calculateIntegral(BigDecimal lowerBound, BigDecimal upperBound, Func<BigDecimal,BigDecimal> func, BigDecimal step);
}
