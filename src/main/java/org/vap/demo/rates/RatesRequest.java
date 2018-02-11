package org.vap.demo.rates;

/**
 * @author Vahe Pezeshkian
 *         February 11, 2018
 */
public class RatesRequest {
    private String currency;
    private String type;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
