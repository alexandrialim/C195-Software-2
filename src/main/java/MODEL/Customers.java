package MODEL;

import java.time.LocalDateTime;

public class Customers {
    private int customer_id;
    public String customer_name;
    public String address;
    public String zipcode;
    public String phone_number;
    public LocalDateTime create_date;
    public String created_by;
    public LocalDateTime last_update;
    public String last_updated_by;
    public int division_id;

    public int getCustomer_id() {
        return customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
}
