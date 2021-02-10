package beans;

import dao.DbConnection;
import dao.GenericDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginBean extends GenericDAO {

    private String username;
    private String password;
    //userinfo - salvam informatiile referitoare la useri
    private UserBean userInfo;

    //metoda validUser va returna un string cu tipul userului sau failure in cazul in care userul nu exista
    public String validUser() throws Exception {
        ResultSet resultSet;
        String returnString = "failure";
        try {
            Statement statement = connection.con.createStatement();
            String query = "SELECT * FROM  Users WHERE "
                    + "username='" + username + "' and password='" + password + "'";
            resultSet = statement.executeQuery(query);

            //daca exista un rezultat in urma query-ului executat pe baza de date
            if (resultSet.next()) {
                //luam urmatoarele din baza de date
                int idDb = resultSet.getInt("id");
                String usernameDb = resultSet.getString("username");
                String passwordDb = resultSet.getString("password");
                String typeDb = resultSet.getString("type");
                String firstNameDb = resultSet.getString("firstName");
                String lastNameDb = resultSet.getString("lastName");

                //salvam intr-un obiect de tip user bean valorile din baza de date
                UserBean userInfo = new UserBean(
                        idDb,
                        usernameDb,
                        passwordDb,
                        typeDb,
                        firstNameDb,
                        lastNameDb);
                this.setUserInfo(userInfo);

                //override with admin is user is admin or with user if user type
                if (typeDb.equals("admin")) {
                    returnString = "admin";
                } else {
                    returnString = "user";
                }
                System.out.println(returnString);
            } else {
                returnString = "failure";
            }
        } catch (SQLException s) {
            System.out.println(s);
        }
        // returnString va returna admin, user sau failure
        return returnString;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserBean userInfo) {
        this.userInfo = userInfo;
    }
}
