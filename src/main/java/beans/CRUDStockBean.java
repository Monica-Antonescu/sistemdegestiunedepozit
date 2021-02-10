package beans;

import javax.faces.context.FacesContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class CRUDStockBean extends StockInfoBean {

    private int idToEdit;
    private String nameAdd;
    private int quantityAdd;
    private String measurementAdd;
    private String locationAdd;
    private double priceAdd;


    //delete selected stock from DB - return deleteSucces or deleteFail
    public void deleteStock() {
        Map paramMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int idToDelete = 0;
        String idToDeleteFromUi = (String) paramMap.get("idStockDeSters");
        idToDelete = Integer.parseInt(idToDeleteFromUi.trim());
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.con.prepareStatement("delete from Stock where id = ?");
            preparedStatement.setInt(1, idToDelete);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while deleting Stock!" + e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    public void updateStockInfo() {
        PreparedStatement preparedStatement = null;
        StockBean stockBeanEdit = new StockBean();
        stockBeanEdit.setId(this.getIdToEdit());
        stockBeanEdit.setName(this.getNameAdd());
        stockBeanEdit.setQuantity(this.getQuantityAdd());
        stockBeanEdit.setMeasurement(this.getMeasurementAdd());
        stockBeanEdit.setLocation(this.getLocationAdd());
        stockBeanEdit.setPrice(this.getPriceAdd());
        try {
            preparedStatement = connection.con.prepareStatement("update Stock set name = ?, quantity = ?, measurement = ?, location = ?, price = ? where id = ?");

            preparedStatement.setString(1, stockBeanEdit.getName());
            preparedStatement.setInt(2, stockBeanEdit.getQuantity());
            preparedStatement.setString(3, stockBeanEdit.getMeasurement());
            preparedStatement.setString(4, stockBeanEdit.getLocation());
            preparedStatement.setDouble(5, stockBeanEdit.getPrice());
            preparedStatement.setInt(6, stockBeanEdit.getId());

            preparedStatement.executeUpdate();
            cleanStock();
        } catch (SQLException e) {
            System.out.println("Stock was not updated!");
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    /**
     * add stock into DB
     *
     * @return message if success or fail
     */
    public void addStock() {
        StockBean stockBean = new StockBean();
        //obiectul stockBean cu valorile care vin pe frontend
        stockBean.setId(this.getIdToEdit());
        stockBean.setName(this.getNameAdd());
        stockBean.setQuantity(this.getQuantityAdd());
        stockBean.setMeasurement(this.getMeasurementAdd());
        stockBean.setLocation(this.getLocationAdd());
        stockBean.setPrice(this.getPriceAdd());

        PreparedStatement preparedStatement = null;
        try {
            //insert in db cu param nepusi
            preparedStatement = connection.con.prepareStatement("insert into Stock(id, name, quantity, measurement, location, price) values(?, ?, ?, ?, ?, ?)");
            //adaugam valorile semnelor de intrebare de mai sus (parametrii)
            preparedStatement.setInt(1, stockBean.getId());
            preparedStatement.setString(2, stockBean.getName());
            preparedStatement.setInt(3, stockBean.getQuantity());
            preparedStatement.setString(4, stockBean.getMeasurement());
            preparedStatement.setString(5, stockBean.getLocation());
            preparedStatement.setDouble(6, stockBean.getPrice());
            preparedStatement.executeUpdate();
            cleanStock();
        } catch (SQLException e) {
            System.out.println("Error while adding Stock!" + e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    //metoda precompleteaza campurile de editat cu informatia despre produs-ul pe care s-a dat click (aduce din baza de date informatia)
    public void setEditFields() {
        //salvam informatia de pe frontend intr-o mapa
        Map paramMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        //salvam id-ul din mapa (care e luata din frontend) intr-un string
        String idToEditFromUI = (String) paramMap.get("idStockDeEditat");
        //convertim idul de la string la int
        int idToEditStock = Integer.parseInt(idToEditFromUI);
        //aplelam metoda getOneStock care face read din baza de date la un singur stock select * from stock where id = ?
        StockBean stockBean = getOneStock(idToEditStock);

        //daca a returnat un ID valid atunci obiectul stockbean nu e gol si atunci completam fieldurile
        if (stockBean != null) {
            this.setIdToEdit(stockBean.getId());
            this.setNameAdd(stockBean.getName());
            this.setQuantityAdd(stockBean.getQuantity());
            this.setMeasurementAdd(stockBean.getMeasurement());
            this.setLocationAdd(stockBean.getLocation());
            this.setPriceAdd(stockBean.getPrice());
        }
    }

    public StockBean getOneStock(int id) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        StockBean stockBean = new StockBean();

        try {
            preparedStatement = connection.con.prepareStatement("SELECT * from Stock where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                stockBean.setId(resultSet.getInt("id"));
                stockBean.setName(resultSet.getString("name"));
                stockBean.setQuantity(resultSet.getInt("quantity"));
                stockBean.setMeasurement(resultSet.getString("measurement"));
                stockBean.setLocation(resultSet.getString("location"));
                stockBean.setPrice(resultSet.getDouble("price"));
            } else {
                System.out.println("No stock found for id " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error while getting stock " + id);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
        return stockBean;
    }

    /**
     * clean stock after adding or updating
     */
    private void cleanStock() {
        this.setIdToEdit(0);
        this.setNameAdd(null);
        this.setQuantityAdd(0);
        this.setMeasurementAdd(null);
        this.setLocationAdd(null);
        this.setPriceAdd(0);
    }

    public String goToUsers() {
        return "userAdmin";
    }

    public int getIdToEdit() {
        return idToEdit;
    }

    public void setIdToEdit(int idToEdit) {
        this.idToEdit = idToEdit;
    }

    public String getNameAdd() {
        return nameAdd;
    }

    public void setNameAdd(String nameAdd) {
        this.nameAdd = nameAdd;
    }

    public int getQuantityAdd() {
        return quantityAdd;
    }

    public void setQuantityAdd(int quantityAdd) {
        this.quantityAdd = quantityAdd;
    }

    public String getMeasurementAdd() {
        return measurementAdd;
    }

    public void setMeasurementAdd(String measurementAdd) {
        this.measurementAdd = measurementAdd;
    }

    public String getLocationAdd() {
        return locationAdd;
    }

    public void setLocationAdd(String locationAdd) {
        this.locationAdd = locationAdd;
    }

    public double getPriceAdd() {
        return priceAdd;
    }

    public void setPriceAdd(double priceAdd) {
        this.priceAdd = priceAdd;
    }
}
