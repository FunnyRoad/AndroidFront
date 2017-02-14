package com.project.application.funnyroad.newroadtrip.visualroadtrip.view;

/**
 * Created by oraberkane on 04/02/2017.
 */


import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Endroit;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class ItineraireTask extends AsyncTask<Void, Integer, Boolean>  {

        /*******************************************************/
        /** CONSTANTES.
         /*******************************************************/
        private static final String TOAST_MSG = "Calcul de l'itinéraire en cours";
        private static final String TOAST_ERR_MAJ = "Impossible de trouver un itinéraire";

        /*******************************************************/
        /** ATTRIBUTS.
         /*******************************************************/
        private Context context;
        private GoogleMap gMap;
        private String editDepart;
        private String editArrivee;
        private String[] listCities;
        private Endroit[] listEndroit;
        private final ArrayList<LatLng> lstLatLng = new ArrayList<LatLng>();

        /*******************************************************/
        /** METHODES / FONCTIONS.
         /*******************************************************/
        /**
         * Constructeur.
         * @param context
         * @param gMap
         * @param editDepart
         * @param editArrivee
         */
        public ItineraireTask(final Context context, final GoogleMap gMap, final String editDepart, final String editArrivee) {
            this.context = context;
            this.gMap= gMap;
            this.editDepart = editDepart;
            this.editArrivee = editArrivee;
        }

        public ItineraireTask(final Context context, final GoogleMap gMap, String[] listCities) {
            this.context = context;
            this.gMap= gMap;
            this.listCities = listCities;

        }

        public ItineraireTask(final Context context, final GoogleMap gMap, Endroit[] listEndroit) {
            this.context = context;
            this.gMap= gMap;
            this.listEndroit = listEndroit;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void onPreExecute() {
            Toast.makeText(context, TOAST_MSG, Toast.LENGTH_LONG).show();
        }

        /***
         * {@inheritDoc}
         */
        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                //Construction de l'url à appeler
                final StringBuilder url = new StringBuilder("http://maps.googleapis.com/maps/api/directions/xml?sensor=false&language=fr");
                url.append("&origin=");
                url.append(this.listEndroit[0].getLatitude()+","+this.listEndroit[0].getLongitude());
                //url.append("Lille".replace(' ', '+'));
                url.append("&destination=");
                url.append(this.listEndroit[1].getLatitude()+","+this.listEndroit[1].getLongitude());
                //url.append("marseille".replace(' ', '+'));
                /*url.append("&waypoints=");
                for(String s : listCities) {
                    url.append(s+"|");
                }
                url.deleteCharAt(url.length()-1);
                */
                Log.d("TAG", "doInBackground: " +url);
                //Appel du web service
                final InputStream stream = new URL(url.toString()).openStream();

                //Traitement des données
                final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                documentBuilderFactory.setIgnoringComments(true);

                final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

                final Document document = documentBuilder.parse(stream);
                document.getDocumentElement().normalize();

                //On récupère d'abord le status de la requête
                final String status = document.getElementsByTagName("status").item(0).getTextContent();
                if(!"OK".equals(status)) {
                    return false;
                }

                final Element elementRoute = (Element) document.getElementsByTagName("route").item(0);
                final NodeList nodeListLeg = elementRoute.getElementsByTagName("leg");
                for(int j = 0 ; j < nodeListLeg.getLength() ; j++){
                    //On récupère les steps
                    final Element elementLeg = (Element) document.getElementsByTagName("leg").item(j);
                    Log.d("tag", "leg2: " + document.getElementsByTagName("leg").item(1));
                    final NodeList nodeListStep = elementLeg.getElementsByTagName("step");
                    final int length = nodeListStep.getLength();

                    for(int i=0; i<length; i++) {
                        final Node nodeStep = nodeListStep.item(i);
                        if(nodeStep.getNodeType() == Node.ELEMENT_NODE) {
                            final Element elementStep = (Element) nodeStep;
                            //On décode les points du XML
                            Log.d("tag", "point1: " + elementStep.getElementsByTagName("points").item(0).getTextContent());
                            //Log.d("tag", "point2: " + elementStep.getElementsByTagName("points").item(1).getTextContent());
                            decodePolylines(elementStep.getElementsByTagName("points").item(0).getTextContent());
                        }
                    }
                }

                return true;
            }
            catch(final Exception e) {
                return false;
            }
        }

        /**
         * Méthode qui décode les points en latitudes et longitudes
         * @param encodedPoints
         */
        private void decodePolylines(final String encodedPoints) {
            int index = 0;
            int lat = 0, lng = 0;

            while (index < encodedPoints.length()) {
                int b, shift = 0, result = 0;

                do {
                    b = encodedPoints.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } while (b >= 0x20);

                int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                lat += dlat;
                shift = 0;
                result = 0;

                do {
                    b = encodedPoints.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } while (b >= 0x20);

                int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                lng += dlng;

                lstLatLng.add(new LatLng((double)lat/1E5, (double)lng/1E5));
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void onPostExecute(final Boolean result) {
            if(!result) {
                Toast.makeText(context, TOAST_ERR_MAJ, Toast.LENGTH_SHORT).show();
            }
            else {
                //On déclare le polyline, c'est-à-dire le trait (ici bleu) que l'on ajoute sur la carte pour tracer l'itinéraire
                final PolylineOptions polylines = new PolylineOptions();
                polylines.color(Color.BLUE);

                //On construit le polyline
                for(final LatLng latLng : lstLatLng) {
                    polylines.add(latLng);
                }

                for(LatLng l : lstLatLng ){
                    Log.d("tableulgt", "onPostExecute: " + l.toString());
                }
                //On déclare un marker vert que l'on placera sur le départ
                final MarkerOptions markerA = new MarkerOptions();
                markerA.position(lstLatLng.get(0));
                markerA.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

                /*final MarkerOptions markerZ = new MarkerOptions();
                markerZ.position(lstLatLng.get(0));
                markerZ.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
                */

                //On déclare un marker rouge que l'on mettra sur l'arrivée
                final MarkerOptions markerB = new MarkerOptions();
                markerB.position(lstLatLng.get(lstLatLng.size()-1));
                markerB.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                //On met à jour la carte
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lstLatLng.get(0), 10));
                gMap.addMarker(markerA);
                gMap.addPolyline(polylines);
                gMap.addMarker(markerB);
                /*gMap.addPolyline(polylines);
                gMap.addMarker(markerB);*/
            }
        }
    }

