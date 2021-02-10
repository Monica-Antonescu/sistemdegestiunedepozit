package beans;

public class UserBean {
    private int id;
    private String username;
    private String password;
    private String type;
    private String firstName;
    private String lastName;

    //constructor fara argumente
    public UserBean() {

    }

    //constructor cu argumente
    public UserBean(int id,
                    String username,
                    String password,
                    String type,
                    String firstName,
                    String lastName
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // getter setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
