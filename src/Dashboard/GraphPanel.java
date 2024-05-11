package Dashboard;

import Helper.DatabaseHelper;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ui.ApplicationFrame;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

import java.sql.SQLException;




public class GraphPanel extends ApplicationFrame {


    // SQL Query to get revenue for last month and one year
    // We can either show number of transactions or the actual dollar amount that is earned
    private static String monthRevenueSQLQuery(String type) {
        String valueExpression = type.equals("count") ? "COUNT(*)" :
                "SUM(tp.Quantity * p.UnitPrice * (1 - p.Discount) * (1 - tp.OverallDiscount))";
        return "SELECT DATE_FORMAT(t.OccuredAt, '%Y-%m') AS Month, " + valueExpression + " AS Value " +
                "FROM Transactions t " +
                "JOIN TransactionProducts tp ON t.TransactionID = tp.TransactionID " +
                "JOIN Product p ON tp.SKU = p.SKU " +
                "GROUP BY Month " +
                "ORDER BY Month";
    }
    private static String yearRevenueSQLQuery(String type) {
        String valueExpression = type.equals("count") ? "COUNT(*)" :
                "SUM(tp.Quantity * p.UnitPrice * (1 - p.Discount) * (1 - tp.OverallDiscount))";
        return "SELECT DATE_FORMAT(t.OccuredAt, '%Y') AS Year, " + valueExpression + " AS Value " +
                "FROM Transactions t " +
                "JOIN TransactionProducts tp ON t.TransactionID = tp.TransactionID " +
                "JOIN Product p ON tp.SKU = p.SKU " +
                "GROUP BY Year " +
                "ORDER BY Year";
    }

    // Data sets created from SQL query
    // Call this twice, once for year and once for month, and can add more time periods or other aggregations
    // if desired
    private static DefaultCategoryDataset createDataset(String type, String aggregationPeriod) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String sql = null;

        DatabaseHelper.setup();

        if (DatabaseHelper.connection != null) {
            try (Statement stmt = DatabaseHelper.connection.createStatement()) {
                if (aggregationPeriod.equals("year")) {
                    sql = yearRevenueSQLQuery(type);
                } else if (aggregationPeriod.equals("month")) {
                    sql = monthRevenueSQLQuery(type);
                }

                ResultSet results = stmt.executeQuery(sql);
                while (results.next()) {
                    String period = results.getString(aggregationPeriod.equals("year") ? "Year" : "Month");
                    double value = results.getDouble("Value");
                    dataset.addValue(value, type, period);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Could not connect to the database");
        }
        return dataset;
    }

    // Visual Setup
    public GraphPanel(String title) {
        super(title);
        JPanel chartPanel = createGraphPanel();
        JPanel contentPanel = createContentPanel(new JPanel(), new JPanel());
        contentPanel.add(chartPanel, BorderLayout.CENTER);
        this.setContentPane(contentPanel);
        this.pack();
        //RefineryUtilities.centerFrameOnScreen(this);
        this.setVisible(true);
    }

    private static JPanel createContentPanel(JPanel titlePanel, JPanel buttonPanel) {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(titlePanel, BorderLayout.NORTH);
        contentPanel.add(buttonPanel, BorderLayout.WEST);
        contentPanel.add(new JPanel(), BorderLayout.CENTER);
        return contentPanel;
    }

    public static JPanel createGraphPanel() {
        // Create the datasets
        // Change this to "count" if we want number of transactions instead, maybe a toggle
        // switch on the UI
        DefaultCategoryDataset salesDataset = createDataset("sales", "month");
        DefaultCategoryDataset revenueDataset = createDataset("sales", "year");

        // Create the charts
        JFreeChart monthRevenueChart = ChartFactory.createLineChart(
                "Sales per month",
                "Day",
                "Monthly Revenue",
                salesDataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        JFreeChart yearRevenueChart = ChartFactory.createLineChart(
                "Total Revenue",
                "Month",
                "Revenue",
                revenueDataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // Put the charts in panels
        ChartPanel monthRevenueChartPanel = new ChartPanel(monthRevenueChart);
        ChartPanel yearRevenueChartPanel = new ChartPanel(yearRevenueChart);

        // Panel to hold both charts
        JPanel chartPanel = new JPanel(new GridLayout(1, 2));
        chartPanel.add(monthRevenueChartPanel);
        chartPanel.add(yearRevenueChartPanel);

        return chartPanel;
    }

    // Here only for testing
    public static void main(String[] args) {
        GraphPanel dashboard = new GraphPanel("Sales and Revenue");
        dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
