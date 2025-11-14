package tasks.integration;

import helper.Func;
import tasks.integration.method.GaussThree;
import tasks.integration.method.IntegrationMethod;

import java.math.BigDecimal;

public class Integration {
    private BigDecimal lowerBound;
    private BigDecimal upperBound;
    private Func<BigDecimal, BigDecimal> func;
    private IntegrationMethod integrationMethod;

    public Integration(){
        integrationMethod = new GaussThree();
    }

    public BigDecimal getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(BigDecimal lowerBound) {
        this.lowerBound = lowerBound;
    }

    public BigDecimal getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(BigDecimal upperBound) {
        this.upperBound = upperBound;
    }

    public Func<BigDecimal, BigDecimal> getFunc() {
        return func;
    }

    public void setFunc(Func<BigDecimal, BigDecimal> func) {
        this.func = func;
    }

    public IntegrationMethod getIntegrationMethod() {
        return integrationMethod;
    }

    public void setIntegrationMethod(IntegrationMethod integrationMethod) {
        this.integrationMethod = integrationMethod;
    }

    public BigDecimal calculateIntegral(BigDecimal lowerBound, BigDecimal upperBound, Func<BigDecimal,BigDecimal> func){
        if(integrationMethod == null)
            throw new NullPointerException("Integration method is null!");
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.func = func;
        return integrationMethod.calculateIntegral(lowerBound,upperBound,func);
    }

    public BigDecimal calculateIntegral(){
        if(lowerBound == null)
            throw new NullPointerException("Lower bound is null!");
        if(upperBound == null)
            throw new NullPointerException("Upper bound is null!");
        if(func == null)
            throw new NullPointerException("Function is null!");
        if(integrationMethod == null)
            throw new NullPointerException("Integration method is null!");

        return integrationMethod.calculateIntegral(lowerBound,upperBound,func);
    }
}
