package us.wylder.solsticechallenge.data;


/**
 * TODO: Parcelable problems. Everyone has the same weird birthday, no one has phone numbers
 * TODO: Try to figure Jackson out. If not, do GSON, but get rid of the middleman objects you have now
 * TODO: clean try/catch and setters
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Matt on 8/17/2015.
 */
public class Contact implements Parcelable{
    private static final String TAG = Contact.class.getSimpleName();
    private String name;
    private int employeeId;
    private String company;
    private String detailsURL;
    private String smallImageURL;
    private long birthdate;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;

    public Contact(String name, int employeeId, String company, String detailsURL,
                   String smallImageURL, String birthdate, ArrayList<String> phoneNumbers){
        Log.d(TAG, "Contact constructor");
        setName(name);
        setEmployeeId(employeeId);
        setCompany(company);
        setDetailsURL(detailsURL);
        setSmallImageURL(smallImageURL);
        setBirthdate(birthdate);
        setPhoneNumbers(phoneNumbers);
    }

    public Contact (Parcel in){
        setName(in.readString());
        setEmployeeId(in.readInt());
        setCompany(in.readString());
        setDetailsURL(in.readString());
        setBirthdate(in.readLong());
        setHomePhone(in.readString());
        setMobilePhone(in.readString());
        setWorkPhone(in.readString());
    }

    public Contact (JSONObject j) throws JSONException {
        setName(j.getString("name"));
        setEmployeeId(j.getInt("employeeId"));
        setCompany(j.getString("company"));
        setDetailsURL(j.getString("detailsURL"));
        setSmallImageURL(j.getString("smallImageURL"));
        setBirthdate(j.getString("birthdate"));
        JSONObject phone = j.getJSONObject("phone");
        try{
            setHomePhone(phone.getString("home"));
        } catch (JSONException e){
            Log.d(TAG, "Contact " + e);
            setHomePhone(null);
        }

        try{
            setMobilePhone(phone.getString("mobile"));
        } catch (JSONException e){
            Log.d(TAG, "Contact " + e);
            setMobilePhone(null);
        }
        try{
            setWorkPhone(phone.getString("work"));
        } catch (JSONException e){
            setWorkPhone(null);
        }
    }

    public String getName(){
        return this.name;
    }

    public String getHomeNumber(){
        return homePhone;
    }

    public String getSmallImageURL(){
        return this.smallImageURL;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getCompany() {
        return company;
    }

    public String getDetailsURL() {
        return detailsURL;
    }

    public long getBirthdate() {
        return birthdate;
    }

    public String getMobilePhone(){
        return this.mobilePhone;
    }

    public String getWorkPhone(){
        return this.workPhone;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    private void setCompany(String company) {
        this.company = company;
    }

    private void setDetailsURL(String detailsURL) {
        this.detailsURL = detailsURL;
    }

    private void setSmallImageURL(String smallImageURL) {
        this.smallImageURL = smallImageURL;
    }

    private void setBirthdate(String birthdate) {
        this.birthdate = Long.parseLong(birthdate);
    }

    private void setBirthdate(long birthdate){
        this.birthdate = birthdate;
    }

    private void setPhoneNumbers(ArrayList<String> phoneNumbers) {
        setHomePhone(phoneNumbers.get(0));
        setMobilePhone(phoneNumbers.get(1));
        setWorkPhone(phoneNumbers.get(2));
    }

    private void setHomePhone(String homePhone){
        this.homePhone = homePhone;
        Log.d(TAG, "setHomePhone " + homePhone);
    }

    private void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        Log.d(TAG, "setMobilePhone " + mobilePhone);
    }

    private void setWorkPhone(String workPhoneIn) {
        this.workPhone = workPhoneIn;
        Log.d(TAG, "setWorkPhone " + workPhoneIn);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(employeeId);
        dest.writeString(company);
        dest.writeString(detailsURL);
        dest.writeString(smallImageURL);
        dest.writeLong(birthdate);
        dest.writeString(homePhone);
        dest.writeString(mobilePhone);
        dest.writeString(workPhone);
        Log.d(TAG, "writeToParcel " + homePhone);
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>(){
        @Override
        public Contact createFromParcel(Parcel in){
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size){
            return new Contact[size];
        }
    };
}
