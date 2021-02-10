package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenericDAO {
    protected static DbConnection connection;

    // connect to database, if connection fails log error
    static {
        try {
            connection = new DbConnection();
            connection.connect();
        } catch (Exception e) {
            System.out.println("Unable to configure access to datasource!");
        }
    }

    protected void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("Error while closing result set:" + e);
            }
        }
    }

    protected void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error while closing prepared statement:" + e);
            }
        }
    }
}