package MODEL;

public class Users {
    private int user_id;
    private String user_name;
    public String pw;

    public Users(int user_id, String user_name, String pw){
        this.user_id = user_id;
        this.user_name = user_name;
        this.pw = pw;
    }

    /**
     *
     * @return user id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     *
     * @return username
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     *
     * @return password
     */
    public String getPw() {
        return pw;
    }
}
