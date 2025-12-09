package tasks.systems;

import helper.DFunc;
import helper.Func;
import tasks.differentiation.Differentiation;

import java.math.BigDecimal;
import java.math.MathContext;

public class ModifiedNewton {
    private DFunc<BigDecimal,BigDecimal,BigDecimal> F;
    private DFunc<BigDecimal,BigDecimal,BigDecimal> G;
    private BigDecimal x;
    private BigDecimal y;
    private BigDecimal eps;
    private int maxIterations;
    private int iteration;
    private BigDecimal jacobian;
    private BigDecimal DFx,DFy,DGx,DGy;

    public ModifiedNewton(DFunc<BigDecimal, BigDecimal, BigDecimal> f, DFunc<BigDecimal, BigDecimal, BigDecimal> g, BigDecimal x, BigDecimal y, BigDecimal eps, int maxIterations) {
        if(f == null)
            throw new NullPointerException("First function is null!");
        if(g == null)
            throw new NullPointerException("Second function is null!");
        if(x == null)
            throw new NullPointerException("x is null!");
        if(y == null)
            throw new NullPointerException("y is null!");
        if(eps == null)
            throw new NullPointerException("Epsilon is null!");
        if(maxIterations <= 0)
            throw new IllegalArgumentException("The number of iterations must be positive!");

        F = f;
        G = g;
        this.x = x;
        this.y = y;
        this.eps = eps;
        this.maxIterations = maxIterations;

        jacobian = jacobian();

        if(jacobian.compareTo(BigDecimal.ZERO) == 0)
            throw new ArithmeticException("Jacobian is zero!");

        solve();
    }

    private void solve() {
        iteration = 0;

        Func<BigDecimal,BigDecimal> abs = arg -> arg.compareTo(BigDecimal.ZERO)>0?arg:arg.negate();

        BigDecimal prevNorm = abs.calculate(F.calculate(x, y)).add(abs.calculate(G.calculate(x, y)));

        while (((abs.calculate(F.calculate(x,y)).compareTo(eps)>0 ||
                abs.calculate(G.calculate(x,y)).compareTo(eps)>0)) &&
                iteration < maxIterations) {

            BigDecimal Fval = F.calculate(x, y);
            BigDecimal Gval = G.calculate(x, y);

            BigDecimal currNorm = abs.calculate(Fval).add(abs.calculate(Gval));
            if (currNorm.compareTo(prevNorm) > 0) {
                jacobian = jacobian();
                System.err.println("Recalculating the Jacobian at "+(iteration+1)+" iteration!");
                if (jacobian.compareTo(BigDecimal.ZERO) == 0)
                    throw new ArithmeticException("Jacobian became zero during iteration " + iteration);
            }

            BigDecimal Dx = Fval.multiply(DGy).subtract(DFy.multiply(Gval)).negate();
            BigDecimal Dy = Gval.multiply(DFx).subtract(DGx.multiply(Fval)).negate();

            x = x.add(Dx.divide(jacobian, MathContext.DECIMAL128));
            y = y.add(Dy.divide(jacobian, MathContext.DECIMAL128));

            prevNorm = currNorm;

            iteration++;
        }

        if (iteration >= maxIterations)
            System.err.println("Method did not converge in " + maxIterations + " iterations!");
    }

    private BigDecimal jacobian(){
        Func<BigDecimal,BigDecimal> funcFx = x -> F.calculate(x, y);
        Func<BigDecimal,BigDecimal> funcFy = y -> F.calculate(x, y);
        Func<BigDecimal,BigDecimal> funcGx = x -> G.calculate(x, y);
        Func<BigDecimal,BigDecimal> funcGy = y -> G.calculate(x, y);

        BigDecimal step = new BigDecimal("0.000001");

         DFx = Differentiation.centralDiff(funcFx,x,step);
         DFy = Differentiation.centralDiff(funcFy,y,step);
         DGx = Differentiation.centralDiff(funcGx,x,step);
         DGy = Differentiation.centralDiff(funcGy,y,step);

        return DFx.multiply(DGy).subtract(DGx.multiply(DFy));
    }

    public BigDecimal getX() {
        return x;
    }

    public BigDecimal getY() {
        return y;
    }

    public int getIteration() {
        return iteration;
    }
}
