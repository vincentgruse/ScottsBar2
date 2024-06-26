package Entities;

import Models.TransactionCustProd;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static Helper.DatabaseHelper.connection;

public class Transactions {
    public Long transactionID;
    public Date occurredAt;
    public BigDecimal total;
    public String paymentMethod;
    public Integer employeeSSN;
    public Long customerID;

    public Long getTransactionID() {
        return transactionID;
    }

    public Date getOccuredAt() {
        return occurredAt;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Integer getEmployeeSSN() {
        return employeeSSN;
    }

    public Long getCustomerID() {
        return customerID;
    }

    // Setter methods
    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    public void setOccuredAt(Date occuredAt) {
        this.occurredAt = occuredAt;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setEmployeeSSN(Integer employeeSSN) {
        this.employeeSSN = employeeSSN;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }


    public long insertTransaction(Transactions transaction) {
        String query = "INSERT INTO Transactions (OccuredAt, Total, PaymentMethod, EmployeeSSN, CustomerID) VALUES (?, ?, ?, ?, ?)";
        long transactionId = 0;
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setTimestamp(1, new java.sql.Timestamp(transaction.getOccuredAt().getTime()));
            statement.setBigDecimal(2, transaction.getTotal());
            statement.setString(3, transaction.getPaymentMethod());
            statement.setInt(4, transaction.getEmployeeSSN());
            statement.setLong(5, transaction.getCustomerID());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    transactionId = generatedKeys.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactionId;
    }


    public void deleteTransaction(long transactionID) {
        String query = "DELETE FROM Transactions WHERE TransactionID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, transactionID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Transactions getTransactionById(long transactionID) {
        String query = "SELECT * FROM Transactions WHERE TransactionID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, transactionID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Transactions transaction = new Transactions();
                transaction.setTransactionID(resultSet.getLong("TransactionID"));
                transaction.setOccuredAt(resultSet.getTimestamp("OccuredAt"));
                transaction.setTotal(resultSet.getBigDecimal("Total"));
                transaction.setPaymentMethod(resultSet.getString("PaymentMethod"));
                transaction.setEmployeeSSN(resultSet.getInt("EmployeeSSN"));
                transaction.setCustomerID(resultSet.getLong("CustomerID"));
                return transaction;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Transactions> getTransactionByCustomerId(long customerId) {
        List<Transactions> transactions = new ArrayList<>();
        String query = "SELECT * FROM Transactions WHERE CustomerID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Transactions transaction = new Transactions();
                transaction.setTransactionID(resultSet.getLong("TransactionID"));
                transaction.setOccuredAt(resultSet.getTimestamp("OccuredAt"));
                transaction.setTotal(resultSet.getBigDecimal("Total"));
                transaction.setPaymentMethod(resultSet.getString("PaymentMethod"));
                transaction.setEmployeeSSN(resultSet.getInt("EmployeeSSN"));
                transaction.setCustomerID(resultSet.getLong("CustomerID"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public List<Transactions> getAllTransactions() {
        List<Transactions> transactions = new ArrayList<>();
        String query = "SELECT * FROM Transactions";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Transactions transaction = new Transactions();
                transaction.setTransactionID(resultSet.getLong("TransactionID"));
                transaction.setOccuredAt(resultSet.getTimestamp("OccuredAt"));
                transaction.setTotal(resultSet.getBigDecimal("Total"));
                transaction.setPaymentMethod(resultSet.getString("PaymentMethod"));
                transaction.setEmployeeSSN(resultSet.getInt("EmployeeSSN"));
                transaction.setCustomerID(resultSet.getLong("CustomerID"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public List<TransactionCustProd> getAllTransactionsJoined() {
        List<TransactionCustProd> transactions = new ArrayList<>();
        String query = "SELECT t.TransactionID, t.OccuredAt, t.Total, t.PaymentMethod,c.FirstName, COUNT(tp.TransactionID) AS TotalProds FROM Transactions t JOIN LoyaltyMember c on t.CustomerID = c.CustomerID JOIN TransactionProducts tp ON t.TransactionID = tp.TransactionID GROUP BY t.TransactionID, t.OccuredAt, t.Total, t.PaymentMethod ";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TransactionCustProd transaction = new TransactionCustProd();
                transaction.setTransactionID(resultSet.getLong("TransactionID"));
                transaction.setOccuredAt(resultSet.getTimestamp("OccuredAt"));
                transaction.setTotal(resultSet.getBigDecimal("Total"));
                transaction.setPaymentMethod(resultSet.getString("PaymentMethod"));
                transaction.customerName = resultSet.getString("FirstName");
                transaction.totalProducts = resultSet.getInt("TotalProds");
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public void updateTransaction(Transactions transaction) {
        String query = "UPDATE Transactions SET OccuredAt = ?, Total = ?, PaymentMethod = ?, EmployeeSSN = ?, CustomerID = ? WHERE TransactionID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setTimestamp(1, new java.sql.Timestamp(transaction.getOccuredAt().getTime()));
            statement.setBigDecimal(2, transaction.getTotal());
            statement.setString(3, transaction.getPaymentMethod());
            statement.setInt(4, transaction.getEmployeeSSN());
            statement.setLong(5, transaction.getCustomerID());
            statement.setLong(6, transaction.getTransactionID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
