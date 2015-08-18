package us.wylder.solsticechallenge.data;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
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

    public Details(){

    }

    public Details(int employeeId, boolean favorite, String largeImageURL, String email,
                   String website, Address address) {

        setEmployeeId(employeeId);
        setFavorite(favorite);
        setLargeImageURL(largeImageURL);
        setEmail(email);
        setWebsite(website);
        setAddress(address);
    }

    public Details(JSONObject j) throws JSONException{
        setEmployeeId(j.getInt("employeeId"));
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

}



