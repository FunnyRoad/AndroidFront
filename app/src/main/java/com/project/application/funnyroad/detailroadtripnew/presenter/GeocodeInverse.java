package com.project.application.funnyroad.detailroadtripnew.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.project.application.funnyroad.home.model.Departure;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by you on 03/03/2017.
 */

public class GeocodeInverse extends AsyncTask<Void, Integer , Boolean>{

    String placeId;
    public String placeName = "";
    EditText destination;
    private Departure departure;
    private Context context;
    private GoogleMap gMap;
    private String editDepart;
    private String editArrivee;
    private String[] listCities;
    private ArrayList<Place> listPlace;
    public GeocodeInverse(Context context , GoogleMap gMap , ArrayList<Place> listPlace, Departure depart,
                          String placeId , EditText destination){
        this.placeId = placeId;
        this.destination = destination;

        this.context = context;
        this.gMap= gMap;
        this.listPlace = listPlace;
        this.departure = depart;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onPreExecute() {
        Log.d("Geocode", "onPreExecute: ");
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            String url = "https://maps.googleapis.com/maps/api/geocode/xml?place_id="+ this.placeId + "&key=AIzaSyCS_TEWx2Np-booaLt0IrUS4e3ETAa3Pps";
            final InputStream stream = new URL(url).openStream();

            //Traitement des données
            final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setIgnoringComments(true);

            final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            final Document document = documentBuilder.parse(stream);
            document.getDocumentElement().normalize();

            //On récupère d'abord le status de la requête
            final String status = document.getElementsByTagName("status").item(0).getTextContent();
            if (!"OK".equals(status)) {
                Log.d("TAG", "getNameOfPLace: document pas bon");
                return false;
            }
            Log.d("GeocodeInverse", "getNameOfPLace: recup doc success");
            final Element element = (Element) document.getElementsByTagName("formatted_address").item(0);
            placeName = element.getTextContent();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void onPostExecute(final Boolean result){
        if (!result) {
            Log.d("geocode", "onPostExecute: " + result);
        } else {
            destination.setText(placeName);

            Log.d("geocode", "onPostExecute: nom de la place" + placeName);
            new ItineraireTask(this.context, this.gMap, this.listPlace,
                    this.departure,this.placeName).execute();
        }
    }

}


