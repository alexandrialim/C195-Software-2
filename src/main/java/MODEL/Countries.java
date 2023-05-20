package MODEL;

public class Countries {
    private int country_id;
    public String country;

    /**
     *
     * @param country_id
     * @param country
     */
    public Countries(int country_id, String country){
        this.country = country;
        this.country_id = country_id;
    }

    /**
     *
     * @return country id number
     */
    public int getCountry_id(){
        return country_id;
    }

    /**
     * @return country name
     */
    public String getCountry(){
        return country;
    }
}
