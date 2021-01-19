package ru.enterprise.patterns;

import java.util.Date;


 /*
	Заменить конструкторы методами создания с понятным назначением, 
	кторорые возвращают экземпляры обьектов.
 */

public class Loan {
    private double _commitment;
    private final double _outstanding;
    private final int _riskRating;
    private final Date _maturity;
    private final Date _expiry;
    private CapitalStrategy _capitalStrategy;


    public static Loan firstcreate (double _commitment,int _riskRating, double _outstanding, Date _maturity){
        return new Loan (null,_commitment,0.00, _riskRating, _maturity,null);
    }


    public static Loan secondcreate (double _commitment,int _riskRating, Date expiry, Date _maturity, double _outstanding){
        return new Loan (null,_commitment, 0.00, _riskRating, _maturity, expiry);
    }


    public static Loan tirdcreate (double _commitment, double _outstanding, int _riskRating, Date _maturity, Date _expiry){
        return new Loan (null, _commitment, _outstanding, _riskRating, _maturity, _expiry);
    }

    public static Loan thorecreate (CapitalStrategy capitalStrategy, double commitment,int riskRating, Date maturity, Date expiry){
        return new Loan (capitalStrategy, commitment, 0.00, riskRating, maturity, expiry);
    }


    private Loan(CapitalStrategy capitalStrategy, double commitment,
                double outstanding, int riskRating,
                Date maturity, Date expiry) {
        this._commitment = commitment;
        this._outstanding = outstanding;
        this._riskRating = riskRating;
        this._maturity = maturity;
        this._expiry = expiry;
        this._capitalStrategy = capitalStrategy;

        if (capitalStrategy == null) {
            if (expiry == null)
                this._capitalStrategy = new CapitalStrategyTermLoan();
            else if (maturity == null)
                this._capitalStrategy = new CapitalStrategyRevolver();
            else
                this._capitalStrategy = new CapitalStrategyRCTL();
        }
    }

    public CapitalStrategy get_capitalStrategy() {
        return _capitalStrategy;
    }
}
