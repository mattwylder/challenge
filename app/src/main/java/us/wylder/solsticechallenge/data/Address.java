package us.wylder.solsticechallenge.data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Matt on 8/17/2015.
 */
public class Address{
    private String street;
    private String city;
    private String state;
    private String country;
    private String zip;
    private double latitude;
    private double longitude;

    public Address(){}

    public Address(String street, String city, String state, String country, String zip,
                   double latitude, double longitude){
        setStreet(street);
        setCity(city);
        setState(state);
        setCountry(country);
        setZip(zip);
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public Address(JSONObject j) throws JSONException{
        setStreet(j.getString("street"));
        setCity(j.getString("city"));
        setState(j.getString("state"));
        setCountry(j.getString("country"));
        setZip(j.getString("zip"));
        setLatitude(j.getDouble("latitude"));
        setLongitude(j.getDouble("longitude"));
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getZip() {
        return zip;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
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
