package us.wylder.solsticechallenge.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import us.wylder.solsticechallenge.CircleTransform;
import us.wylder.solsticechallenge.R;
import us.wylder.solsticechallenge.data.Contact;

/**
 * Created by Matt on 8/17/2015.
 */
public class ContactArrayAdapter extends ArrayAdapter {
    private static final String TAG = ContactArrayAdapter.class.getSimpleName();

    public ContactArrayAdapter(Context context, ArrayList<Contact> contacts) {
        super(context, 0, contacts);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Contact curContact = (Contact) getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_contact,
                    parent, false);

        }

        TextView contactNameView = (TextView) convertView.findViewById(R.id.contact_name);
        TextView contactNumberView = (TextView) convertView.findViewById(R.id.contact_number);
        ImageView contactImageView = (ImageView) convertView.findViewById(R.id.contact_image);

        contactNameView.setText(curContact.getName().toString());
        contactNumberView.setText(curContact.getHomeNumber().toString());
        Picasso.with(getContext()).load(curContact.getSmallImageURL()).transform(new CircleTransform()).into(contactImageView);
        Log.d(TAG, "getView " + curContact.getSmallImageURL());

        return convertView;
    }
}
