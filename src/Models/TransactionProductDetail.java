package Models;

import Entities.TransactionProducts;

import java.math.BigDecimal;

public class TransactionProductDetail extends TransactionProducts {
    public String SKU;
    public String productName;
    public BigDecimal unitPrice;
    public BigDecimal discount;
}
