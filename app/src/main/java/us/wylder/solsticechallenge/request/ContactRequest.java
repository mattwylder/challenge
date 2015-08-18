package us.wylder.solsticechallenge.request;

//import com.android.volley.RequestQueue;
//import com.android.volley.toolbox.Volley;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import us.wylder.solsticechallenge.data.Contact;

/**
 * Created by Matt on 8/17/2015.
 */
public class ContactRequest<T> extends Request<T> {

    private static final String TAG = ContactRequest.class.getSimpleName();

    private final Class<T> clazz;
    private final Map<String, String> headers;
    private final Response.Listener<T> listener;


    public ContactRequest(String url, Class<T> clazz, Map<String, String> headers,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        try{
            String jsonSource = new String(
                    networkResponse.data,
                    HttpHeaderParser.parseCharset(networkResponse.headers));
            ObjectMapper mapper = new ObjectMapper();
            List<Contact> contacts = mapper.readValue(
                    jsonSource,
                    mapper.getTypeFactory().constructCollectionType(
                            List.class, Contact.class));

        } catch (UnsupportedEncodingException e){
            return Response.error(new ParseError(e));
        } catch (IOException e){
            return Response.error(new ParseError(e));
        }


        return null;
    }

    @Override
    protected void deliverResponse(T t) {
    }


}
