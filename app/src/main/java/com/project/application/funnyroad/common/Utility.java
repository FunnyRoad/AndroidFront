package com.project.application.funnyroad.common;
        import android.Manifest;
        import android.annotation.TargetApi;
        import android.app.Activity;
        import android.content.Context;
        import android.content.ContextWrapper;
        import android.content.DialogInterface;
        import android.content.SharedPreferences;
        import android.content.pm.PackageManager;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.location.Address;
        import android.location.Geocoder;
        import android.os.Build;
        import android.preference.PreferenceManager;
        import android.support.v4.app.ActivityCompat;
        import android.support.v4.content.ContextCompat;
        import android.support.v7.app.AlertDialog;
        import android.util.Log;

        import com.project.application.funnyroad.home.model.Departure;
        import com.project.application.funnyroad.login.model.User;

        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.util.HashSet;
        import java.util.List;
        import java.util.Set;

/**
 */
public class Utility {
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    public static int idUserStored;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean checkPermission(final Context context)
    {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if(currentAPIVersion>=android.os.Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("External storage permission is necessary");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();

                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static void storeIdUser(Activity activity , int idUser){
        idUserStored = idUser;
        Log.d("Utility", "isLoginSuccess: id login: " + idUser);
        /*SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("idUser", idUser );
        editor.commit();
        */
    }

    public static int getIdUser(Activity activity){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        int idUser = sharedPref.getInt("idUser", -1);
        Log.d("Utility", "getIdUser: id login: après " + idUserStored);
        return idUserStored;
    }

    public static void storeFirebaseId(Activity activity , String firebaseId){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("firebaseId", firebaseId );
        editor.commit();
    }

    public static String getFirebaseId(Activity activity){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        String firebaseId = sharedPref.getString("firebaseId","");
        return firebaseId;
    }

    public static void storeInformationUser(Activity activity , String key , String value){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public static String getInformationUser(Activity activity , String key){
        String value = PreferenceManager.getDefaultSharedPreferences(activity).getString(key , "");
        //SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        //String userInformation = sharedPref.getString(key , "");
        return value;
    }

    public static String saveToInternalStorage(Context ctx , Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(ctx);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 90, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    public static String getAdressByLatLng(Activity activity , double latitude , double longitude){
        String namePlace = "";
        Geocoder geocoder = new Geocoder(activity);
        try {
                //Ici on récupère la premiere adresse trouvée grace à la position que l'on a récupéré
                List<Address> adresses = geocoder.getFromLocation(latitude, longitude, 1);
                if (adresses != null && adresses.size() == 1) {
                    Address adresse = adresses.get(0);
                    namePlace = adresse.getLocality() + " " + adresse.getCountryName();
                    return namePlace;
                    //Si le geocoder a trouver une adresse, alors on l'affiche
                } else {
                    //sinon on affiche un message d'erreur
                }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return namePlace;
    }

    public static Departure getLocationFromAddress(Activity activity ,String strAddress){

        Geocoder coder = new Geocoder(activity);
        List<Address> address;
        Departure p1 = null;

        try {
            address = coder.getFromLocationName(strAddress,5);
            if (address==null) {
                return null;
            }
            Address location=address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new Departure((double) (location.getLatitude() * 1E6),
                    (double) (location.getLongitude() * 1E6) , "");

            return p1;
        }
        catch(IOException e){
            e.getMessage();
        }

        return p1;
    }
}
