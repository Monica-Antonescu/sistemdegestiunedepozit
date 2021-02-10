package beans;

import dao.GenericDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class StockInfoBean extends GenericDAO {
    private final double TVA = 1.19;
    private ArrayList<StockBean> stock;

    // ia informatii din baza de date si le pune in stock bean
    public ArrayList<StockBean> getStockDB() {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        ArrayList<StockBean> result = new ArrayList<>();

        // ia informatiile din tabela de stoc si le salvam in stock bean
        try {
            preparedStatement = connection.con.prepareStatement("SELECT * FROM Stock");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                StockBean stockBean = new StockBean();
                stockBean.setId(resultSet.getInt("id"));
                stockBean.setName(resultSet.getString("name"));
                stockBean.setQuantity(resultSet.getInt("quantity"));
                stockBean.setMeasurement(resultSet.getString("measurement"));
                stockBean.setLocation(resultSet.getString("location"));
                stockBean.setPrice(resultSet.getDouble("price"));
                calculateAllPrices(stockBean);
                //adauga la result toate stockBean-urile obtinute
                result.add(stockBean);
            }
        } catch (SQLException e) {
            System.out.println("Connection error couldn't get stock");
        } finally {
            // inchidem conexiunea baza de date
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        setStock(result);
        return result;
    }

    public ArrayList<StockBean> getStock() {
        getStockDB();
        return stock;
    }

    public void setStock(ArrayList<StockBean> stock) {
        this.stock = stock;
    }


     //calculeaza toate preturile TVA si pretul total

    private StockBean calculateAllPrices(StockBean stockBean) {
        //DecimalFormat va afisa 3 zecimale la TVA si 2 zecimale la pretul total
        DecimalFormat formatterTva = new DecimalFormat("#0.000");
        DecimalFormat formatterTotalPrice = new DecimalFormat("#0.00");
        stockBean.setPriceWithTva(formatterTva.format(stockBean.getPrice() * TVA));
        stockBean.setTotalPrice(formatterTotalPrice.format(stockBean.getPrice() * TVA * stockBean.getQuantity()));
        //return stock bean with tva and total price formatted
        return stockBean;
    }
}
