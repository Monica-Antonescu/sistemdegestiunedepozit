package beans;

import javax.faces.context.FacesContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CRUDUserBean extends LoginBean {

    private ArrayList<UserBean> users;
    private String usernameAdd;
    private String passwordAdd;
    private String firstNameAdd;
    private String lastNameAdd;
    private String typeAdd;
    private int idToAdd;
    public List<String> userTypes = Arrays.asList("admin", "user");


     //getAllUsers from DB - return ArrayList<UserBean>
    public ArrayList<UserBean> getAllUsers() {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        ArrayList<UserBean> result = new ArrayList<>();
        try {
            preparedStatement = connection.con.prepareStatement("SELECT * FROM  Users");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UserBean userBean = new UserBean();
                userBean.setId(resultSet.getInt("id"));
                userBean.setUsername(resultSet.getString("username"));
                userBean.setPassword(resultSet.getString("password"));
                userBean.setFirstName(resultSet.getString("firstName"));
                userBean.setLastName(resultSet.getString("lastName"));
                userBean.setType(resultSet.getString("type"));
                result.add(userBean);
            }
        } catch (SQLException e) {
            System.out.println("Connection error database " + e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        this.setUsers(result);
        return result;
    }


    //delete user from DB
    public void deleteUser() {
        Map paramMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String idFromInterface = (String) paramMap.get("idUserDeSters");
        int idToDelete = Integer.parseInt(idFromInterface.trim());

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.con.prepareStatement("delete from Users where id = ?");
            preparedStatement.setInt(1, idToDelete);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while deleting User! " + e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    //add user in db
    public void addUser() {
        UserBean userBean = new UserBean();
        userBean.setId(this.getIdToAdd());
        userBean.setUsername(this.getUsernameAdd());
        userBean.setPassword(this.getPasswordAdd());
        userBean.setFirstName(this.getFirstNameAdd());
        userBean.setLastName(this.getLastNameAdd());
        userBean.setType(this.getTypeOfUseAdd());

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.con.prepareStatement("insert into Users(id, username, password, firstName, lastName, type) values(?, ?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, userBean.getId());
            preparedStatement.setString(2, userBean.getUsername());
            preparedStatement.setString(3, userBean.getPassword());
            preparedStatement.setString(4, userBean.getFirstName());
            preparedStatement.setString(5, userBean.getLastName());
            preparedStatement.setString(6, userBean.getType());
            preparedStatement.executeUpdate();
            cleanUser();
        } catch (SQLException e) {
            System.out.println("Cannot add user " + e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    //update in db user info
    public void updateUserInfo() {
        UserBean userBeanEdit = new UserBean();
        userBeanEdit.setId(this.getIdToAdd());
        userBeanEdit.setFirstName(this.getFirstNameAdd());
        userBeanEdit.setLastName(this.getLastNameAdd());
        userBeanEdit.setPassword(this.getPasswordAdd());
        userBeanEdit.setUsername(this.getUsernameAdd());
        userBeanEdit.setType(this.getTypeOfUseAdd());

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.con.prepareStatement("update Users set username = ?, password = ?, firstName = ?, lastName = ?, type = ? where id = ?");

            preparedStatement.setString(1, userBeanEdit.getUsername());
            preparedStatement.setString(2, userBeanEdit.getPassword());
            preparedStatement.setString(3, userBeanEdit.getFirstName());
            preparedStatement.setString(4, userBeanEdit.getLastName());
            preparedStatement.setString(5, userBeanEdit.getType());
            preparedStatement.setInt(6, userBeanEdit.getId());
            preparedStatement.executeUpdate();
            cleanUser();
        } catch (SQLException e) {
            System.out.println("Error updating user " + e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    //clean user after adding or updating
    private void cleanUser() {
        this.setIdToAdd(0);
        this.setUsernameAdd(null);
        this.setPasswordAdd(null);
        this.setFirstNameAdd(null);
        this.setLastNameAdd(null);
        this.setTypeOfUseAdd(null);
    }

    public String goToStock() {
        return "stockAdmin";
    }

    public ArrayList<UserBean> getUsers() {
        return this.getAllUsers();
    }

    public void setUsers(ArrayList<UserBean> users) {
        this.users = users;
    }

    public String getUsernameAdd() {
        return usernameAdd;
    }

    public void setUsernameAdd(String usernameAdd) {
        this.usernameAdd = usernameAdd;
    }

    public String getPasswordAdd() {
        return passwordAdd;
    }

    public void setPasswordAdd(String passwordAdd) {
        this.passwordAdd = passwordAdd;
    }

    public String getFirstNameAdd() {
        return firstNameAdd;
    }

    public void setFirstNameAdd(String firstNameAdd) {
        this.firstNameAdd = firstNameAdd;
    }

    public String getLastNameAdd() {
        return lastNameAdd;
    }

    public void setLastNameAdd(String lastNameAdd) {
        this.lastNameAdd = lastNameAdd;
    }

    public String getTypeOfUseAdd() {
        return typeAdd;
    }

    public void setTypeOfUseAdd(String type) {
        this.typeAdd = type;
    }

    public int getIdToAdd() {
        return idToAdd;
    }

    public void setIdToAdd(int idToAdd) {
        this.idToAdd = idToAdd;
    }

    public List<String> getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(List userTypes) {
        this.userTypes = userTypes;
    }
}
