package tasks.interpolation.method;

import helper.Pair;

import java.math.BigDecimal;
import java.util.List;

public interface InterpolationMethod {
    BigDecimal calculateY(BigDecimal x, List<Pair<BigDecimal,BigDecimal>> points);
}
