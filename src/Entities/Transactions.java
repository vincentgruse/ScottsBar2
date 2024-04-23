package Entities;

import java.math.BigDecimal;
import java.util.Date;

public class Transactions {
    public Long transactionID;
    public Date occurredAt;
    public BigDecimal total;
    public String paymentMethod;
    public Integer employeeSSN;
    public Long customerID;
}
