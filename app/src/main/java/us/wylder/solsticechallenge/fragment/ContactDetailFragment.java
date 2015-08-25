package us.wylder.solsticechallenge.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import us.wylder.solsticechallenge.CircleTransform;
import us.wylder.solsticechallenge.R;
import us.wylder.solsticechallenge.activity.ContactDetailActivity;
import us.wylder.solsticechallenge.activity.ContactListActivity;
import us.wylder.solsticechallenge.data.Contact;
import us.wylder.solsticechallenge.data.Details;


/**
 * A fragment representing a single Contact detail screen.
 * This fragment is either contained in a {@link ContactListActivity}
 * in two-pane mode (on tablets) or a {@link ContactDetailActivity}
 * on handsets.
 */
public class ContactDetailFragment extends Fragment {

    private static final String TAG = ContactDetailFragment.class.getSimpleName();

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The content this fragment is presenting.
     */
    private Contact contact;
    private Details details;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ContactDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {

            contact = getArguments().getParcelable(ARG_ITEM_ID);
            if(contact != null){
                getDetails(contact.getDetailsURL());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact_detail, container, false);

        if (contact != null) {

            SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.y");
            Log.d(TAG, "onCreateView " + contact.getBirthdate());

                    ((TextView) rootView.findViewById(R.id.name)).setText(contact.getName());
            ((TextView) rootView.findViewById(R.id.company)).setText(contact.getCompany());
            ((TextView) rootView.findViewById(R.id.birthdate)).setText(
                    sdf.format(new Date(contact.getBirthdate())));
            ((TextView) rootView.findViewById(R.id.phone_home)).setText(contact.getHomeNumber());
            ((TextView) rootView.findViewById(R.id.phone_mobile)).setText(contact.getMobilePhone());
            ((TextView) rootView.findViewById(R.id.phone_work)).setText(contact.getWorkPhone());
        }
        else{
            Log.d(TAG, "Contact null");
        }

        return rootView;
    }

    private void getDetails(String url){

        //FIXME: This is getting a new RequestQueue, not from a singleton.
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        Log.d(TAG, "getDetails about to call volley");
        /*
         * FIXME: Replace JsonObjectRequest with a custom request,
         * Converting from JSONObjects to Contacts doubles the number of objects used and wastes time
         */
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                       try{
                           details = new Details(response);
                           updateView();
                       } catch (JSONException e){
                           Log.e(TAG, "onResponse " + e);
                       }
                        Log.d(TAG, "onResponse " + details);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Volley error: " + error.getCause());
                    }
                });

        queue.add(jsArrayRequest);
    }

    private void updateView(){

            if (!getActivity().isFinishing()) {
                Picasso.with(getActivity().getApplicationContext()).load(
                        details.getLargeImageURL()).transform(new CircleTransform()).
                        into((ImageView) getActivity().findViewById(R.id.imageView));
                ((TextView) getActivity().findViewById(R.id.email)).setText(details.getEmail());
                ((TextView) getActivity().findViewById(R.id.website)).setText(details.getWebsite());
                ((TextView) getActivity().findViewById(R.id.address)).setText(details.getAddress().toString());
            }

    }

}
