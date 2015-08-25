package us.wylder.solsticechallenge.data;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Java Object that defines the extra details privided by a {@link Contact}'s detailsURL
 *
 * Created by Matt on 8/17/2015.
 */
public class Details {
    private static final String TAG = Details.class.getSimpleName();
    private int employeeId;
    private boolean favorite;
    private String largeImageURL;
    private String email;
    private String website;
    private Address address;

    /**
     * Default constructor required for Jackson
     */
    public Details(){

    }

    /*
     * Constructor used for debugging
     */
    public Details(int employeeId, boolean favorite, String largeImageURL, String email,
                   String website, Address address) {

        setEmployeeId(employeeId);
        setFavorite(favorite);
        setLargeImageURL(largeImageURL);
        setEmail(email);
        setWebsite(website);
        setAddress(address);
    }

    /*
     * FIXME: The nested try/catch block in this block feels dirty try moving those checks into setters
     */
    public Details(JSONObject j) throws JSONException{
        setEmployeeId(j.getInt("employeeId"));

       //Some users have their "favorite" value set as 1 or 0 instead of true or false.
        try{
            setFavorite(j.getBoolean("favorite"));
        } catch (JSONException e){
            try {
                setFavorite(j.getInt("favorite") == 1);
            } catch (JSONException f){
                Log.e(TAG, "Details " + e +" "+ f);
                setFavorite(false);
            }
        }

        setLargeImageURL(j.getString("largeImageURL"));
        setEmail(j.getString("email"));
        setWebsite(j.getString("website"));
        setAddress(j.getJSONObject("address"));
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public Address getAddress() {
        return address;
    }

    private void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    private void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    private void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private void setWebsite(String website) {
        this.website = website;
    }

    private void setAddress(Address address) {
        this.address = address;
    }

    private void setAddress(JSONObject jsonObject) throws JSONException{
        this.address = new Address(jsonObject);
    }

    public class Address {
        private String street;
        private String city;
        private String state;
        private String country;
        private String zip;
        private double latitude;
        private double longitude;

        /**
         * Default constructor needed for Jackson
         */
        public Address() {
        }

        public Address(String street, String city, String state, String country, String zip,
                       double latitude, double longitude) {
            setStreet(street);
            setCity(city);
            setState(state);
            setCountry(country);
            setZip(zip);
            setLatitude(latitude);
            setLongitude(longitude);
        }

        public Address(JSONObject j) throws JSONException {
            setStreet(j.getString("street"));
            setCity(j.getString("city"));
            setState(j.getString("state"));
            setCountry(j.getString("country"));
            setZip(j.getString("zip"));
            setLatitude(j.getDouble("latitude"));
            setLongitude(j.getDouble("longitude"));
        }

        public String toString() {
            return street + '\n' + city + ", " + state + " " + zip + '\n' + country;
        }

        private void setStreet(String street) {
            this.street = street;
        }

        private void setCity(String city) {
            this.city = city;
        }

        private void setState(String state) {
            this.state = state;
        }

        private void setCountry(String country) {
            this.country = country;
        }

        private void setZip(String zip) {
            this.zip = zip;
        }

        private void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        private void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }

}



