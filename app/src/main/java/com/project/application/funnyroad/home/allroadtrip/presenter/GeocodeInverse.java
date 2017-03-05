package com.project.application.funnyroad.home.allroadtrip.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.project.application.funnyroad.detailroadtripnew.presenter.ItineraireTask;
import com.project.application.funnyroad.home.model.Departure;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by you on 03/03/2017.
 */

public class GeocodeInverse {

    String placeId;
    private String placeName = "";

    TextView destination;
    Activity activity;

    public GeocodeInverse(String placeId){
        this.placeId = placeId;
    }

    /**
     * {@inheritDoc}
     */

    protected String doInBackground(Void... voids) {
        try {
            String url = "https://maps.googleapis.com/maps/api/geocode/xml?place_id="+ this.placeId + "&key=AIzaSyCS_TEWx2Np-booaLt0IrUS4e3ETAa3Pps";
            final InputStream stream = new URL(url).openStream();

            Log.d("GeocodeInverse", "doInBackground: "+url);
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
                return placeName;
            }
            Log.d("GeocodeInverse", "getNameOfPLace: recup doc success");
            final Element element = (Element) document.getElementsByTagName("formatted_address").item(0);
            placeName = element.getTextContent();
            return placeName;
        }
        catch (Exception e) {
            return placeName;
        }
    }


}


