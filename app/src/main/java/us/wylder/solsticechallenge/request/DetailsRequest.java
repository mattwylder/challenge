package us.wylder.solsticechallenge.request;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import us.wylder.solsticechallenge.data.Details;

/**
 * Created by Matt on 8/17/2015.
 */
public class DetailsRequest extends Request<Details> {

    ObjectMapper mapper;

    public DetailsRequest( String url, Response.Listener listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        mapper = new ObjectMapper();
    }

    @Override
    protected Response<Details> parseNetworkResponse(NetworkResponse networkResponse) {
        try{
            String json = new String( networkResponse.data);
            JsonNode root = mapper.readTree(json);
            return Response.success(
                    mapper.readValue(json, Details.class),
                    HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e){
            return Response.error(new ParseError(e));
        } catch (IOException e){
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(Details details) {

    }
}
