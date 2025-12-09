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
    private BigDecimal step;

    public Integration(){
        integrationMethod = new GaussThree();
    }

    public BigDecimal getStep() {
        return step;
    }

    public void setStep(BigDecimal step) {
        this.step = step;
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

    public BigDecimal calculateIntegral(BigDecimal lowerBound, BigDecimal upperBound, Func<BigDecimal,BigDecimal> func, BigDecimal step){
        if(integrationMethod == null)
            throw new NullPointerException("Integration method is null!");
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.func = func;
        this.step = step;
        return integrationMethod.calculateIntegral(lowerBound,upperBound,func, step);
    }

    public BigDecimal calculateIntegral(){
        if(integrationMethod == null)
            throw new NullPointerException("Integration method is null!");

        return integrationMethod.calculateIntegral(lowerBound,upperBound,func,step);
    }
}
