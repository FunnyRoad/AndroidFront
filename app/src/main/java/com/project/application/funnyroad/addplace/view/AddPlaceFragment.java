package com.project.application.funnyroad.addplace.view;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.addplace.presenter.PresenterAddPlace;
import com.project.application.funnyroad.common.Utility;
import com.project.application.funnyroad.common.UtilityCheckPermissionGPS;
import com.project.application.funnyroad.detailroadtripnew.view.DetailRoadTripActivity;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by you on 18/02/2017.
 */

public class AddPlaceFragment extends Fragment implements LocationListener, AdapterView.OnItemSelectedListener, IServiceAddPlace {

    public static final int MY_PERMISSIONS_REQUEST_ACCESS = 2;

    private LocationManager lManager;
    private Location location;

    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.textView8)
    TextView textView8;
    @BindView(R.id.textView9)
    TextView textView9;
    @BindView(R.id.imageViewUpload)
    ImageView ivImage;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.editTextDescriptionPlace)
    EditText editTextDescriptionPlace;
    @BindView(R.id.buttonAddPlace)
    Button buttonAddPlace;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.uploadPhoto)
    Button uploadPhoto;
    @BindView(R.id.butAddImageToPlace)
    Button butAddImageToPlace;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private Button btnSelect;
    private String userChoosenTask;
    private String typePlace;
    private PresenterAddPlace presenterAddPlace;
    private Place placeAdded;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_place, container, false);

        ButterKnife.bind(this, view);

        presenterAddPlace = new PresenterAddPlace(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.type_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        //On récupère le service de localisation
        lManager = (LocationManager) this.getActivity().getSystemService(Context.LOCATION_SERVICE);



        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
        typePlace = (String) parent.getItemAtPosition(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    /*********************************************FIND POSITION********************************************************************/
    @OnClick(R.id.buttonDisplayPosition)
    public void obtenirPosition() {
        UtilityCheckPermissionGPS.checkPermission(getContext());
        lManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 0, this);
    }

    private void afficherLocation() {
        textView7.setText(String.valueOf(location.getLatitude()));
        textView8.setText(String.valueOf(location.getLongitude()));
    }

    public void onLocationChanged(Location location) {
        UtilityCheckPermissionGPS.checkPermission(getContext());
        // New location has now been determined
        this.location = location;
        //on l'affiche
        afficherLocation();
        afficherAdresse();
        //et on spécifie au service que l'on ne souhaite plus avoir de mise à jour
        lManager.removeUpdates(this);
    }

    private void afficherAdresse() {

        //Le geocoder permet de récupérer ou chercher des adresses
        //grace à un mot clé ou une position
        Geocoder geo = new Geocoder(this.getActivity());
        try {
            //Ici on récupère la premiere adresse trouvée grace à la position que l'on a récupéré
            List<Address> adresses = geo.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            if (adresses != null && adresses.size() == 1) {
                Address adresse = adresses.get(0);
                //Si le geocoder a trouver une adresse, alors on l'affiche
                textView9.setText(adresse.getLocality());
                buttonAddPlace.setVisibility(View.VISIBLE);
            } else {
                //sinon on affiche un message d'erreur
                textView9.setText("L'adresse n'a pu être déterminée");
            }
        } catch (IOException e) {
            e.printStackTrace();
            textView9.setText("L'adresse n'a pu être déterminée");
        }
    }


    public void onProviderDisabled(String provider) {
        UtilityCheckPermissionGPS.checkPermission(getContext());
        //Lorsque la source (GPS ou réseau GSM) est désactivé
        //on affiche un Toast pour le signaler à l'utilisateur
        Toast.makeText(this.getContext(),
                String.format("La source \"%s\" a été désactivé", provider),
                Toast.LENGTH_SHORT).show();
        lManager.removeUpdates(this);

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }


/************************************************* ajouter la place dans la base de données *****************/

    @OnClick(R.id.buttonAddPlace)
    public void addPlace(){
        Place place = new Place(textView9.getText().toString() , Double.valueOf(textView7.getText().toString()) ,
                Double.valueOf(textView8.getText().toString()) , editTextDescriptionPlace.getText().toString() ,0 , typePlace);

        presenterAddPlace.addPlace(place);

    }


/***************************************************AJOUT DUNE PHOTO*****************************************************/

    @OnClick(R.id.uploadPhoto)
    public void goToUploadPhoto(){
        selectImage();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
            case UtilityCheckPermissionGPS.MY_PERMISSIONS_REQUEST_ACCESS_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    obtenirPosition();

                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result=Utility.checkPermission(getContext());

                if (items[item].equals("Take Photo")) {
                    userChoosenTask ="Take Photo";
                    if(result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask ="Choose from Library";
                    if(result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        butAddImageToPlace.setVisibility(View.VISIBLE);
        ivImage.setImageBitmap(thumbnail);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        butAddImageToPlace.setVisibility(View.VISIBLE);
        ivImage.setImageBitmap(bm);
    }


    @Override
    public void showLoading(boolean bool) {
        if(bool){
            progressBar.setVisibility(View.VISIBLE);
        }
        else{
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void addPlaceSuccess(Place place) {
        placeAdded = place;
        Intent intent = getActivity().getIntent();
        int roadTripId = intent.getIntExtra("roadTripId", -1);
        presenterAddPlace.addPlaceToRoadTrip(roadTripId , place.getId());
    }

    @Override
    public void addPlaceFailed(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(msg);
        builder.setCancelable(true);
        builder.setPositiveButton("Fermer" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void addPlaceToRoadTripSuccess(){
        uploadPhoto.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.butAddImageToPlace)
    public void addImageToPlace(){
        presenterAddPlace.addImageToPlace(placeAdded.getId());
    }

    @Override
    public void addImageToPlaceSuccess(){
        Intent intent = new Intent(getActivity() , DetailRoadTripActivity.class);
        startActivity(intent);
    }
}
