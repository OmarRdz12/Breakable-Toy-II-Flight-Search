package encora.breakableII.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Price {
    private String currency;
    private String total;
    private String base;
    private String grandTotal;
    private List<Fees> fees;

    public List<Fees> getFees() {
        return fees;
    }

    public void setFees(List<Fees> fees) {
        this.fees = fees;
    }

    public String getCurrency() {
        return currency;
    }

    public String getTotal() {
        return total;
    }

    public String getBase() {
        return base;
    }

    public String getGrandTotal() {
        return grandTotal;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }
}
