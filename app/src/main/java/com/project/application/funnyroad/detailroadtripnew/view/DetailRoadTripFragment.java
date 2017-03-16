package com.project.application.funnyroad.detailroadtripnew.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.project.application.funnyroad.R;
import com.project.application.funnyroad.addplace.view.AddPlaceActivity;
import com.project.application.funnyroad.common.Utility;
import com.project.application.funnyroad.detailroadtripnew.model.Post;
import com.project.application.funnyroad.detailroadtripnew.presenter.GeocodeInverse;
import com.project.application.funnyroad.detailroadtripnew.presenter.ListPhotosRoadAdapter;
import com.project.application.funnyroad.detailroadtripnew.presenter.PresenterDetailRoadTrip;
import com.project.application.funnyroad.home.model.Departure;
import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.detailroadtripnew.presenter.ListEndroitAdapter;
import com.project.application.funnyroad.home.view.ActivityHome2;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.mime.TypedFile;

/**
 * Created by oa on 18/02/2017.
 */

public class DetailRoadTripFragment extends Fragment implements OnMapReadyCallback, IServiceDetailRoadTrip,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    @BindView(R.id.textViewDetailDepart)
    AutoCompleteTextView textViewDetailDepart;
    @BindView(R.id.textViewDetailDestination)
    AutoCompleteTextView textViewDetailDestination;
    @BindView(R.id.textViewDetailDescription)
    EditText textViewDetailDescription;
    @BindView(R.id.scrollView)
    NestedScrollView mScrollView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.textViewListPlaceEmpty)
    TextView textViewListPlaceEmpty;
    @BindView(R.id.butDeleteRoadTrip)
    Button butDeleteRoadTrip;
    @BindView(R.id.butUpload)
    Button butUpload;
    @BindView(R.id.addPlace)
    Button buttonAddPlace;
    @BindView(R.id.recycler_view_list_endroit)
    RecyclerView recycler_view_list_endroit;
    @BindView(R.id.butChoosePhotoRoad)
    Button butChoosePhotoRoad;
    @BindView(R.id.buttonAddPhotoToRoad)
    Button buttonAddPhotoToRoad;
    @BindView(R.id.recyclerViewPhotosRoad)
    RecyclerView recyclerViewPhotosRoad;

    private RoadTrip roadTrip;
    private SupportMapFragment gMap;
    PresenterDetailRoadTrip presenterDetailRoadTrip;
    private GoogleApiClient mGoogleApiClient;
    private AutoCompleteAdapter autoCompleteAdapter;
    private AutoCompletePlace place;
    private AutoCompletePlace placeBegin;
    private boolean destinationChanged = false;
    private boolean isComeFromFollowFragment;
    private ArrayList<com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place> listPlace;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    private TypedFile typedFile;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_detail_road_trip, container, false);

        ButterKnife.bind(this, view);


        presenterDetailRoadTrip = new PresenterDetailRoadTrip(this);

        autoCompleteAdapter = new AutoCompleteAdapter(this.getActivity());

        textViewDetailDestination.setAdapter(autoCompleteAdapter);
        textViewDetailDepart.setAdapter(autoCompleteAdapter);

        textViewDetailDestination.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                place = (AutoCompletePlace) parent.getItemAtPosition(position);
                textViewDetailDestination.setText(place.getDescription());
                destinationChanged = true;
            }
        });

        textViewDetailDepart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                place = (AutoCompletePlace) parent.getItemAtPosition(position);
                textViewDetailDepart.setText(place.getDescription());
            }
        });

        mGoogleApiClient = new GoogleApiClient
                .Builder(getActivity())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        Intent intent = getActivity().getIntent();
        if (intent != null) {
            isComeFromFollowFragment = intent.getBooleanExtra("isComeFromFollow" , false);
            // le road trip qu'on est entrain de visualiser
            roadTrip = (RoadTrip) intent.getSerializableExtra("roadTripSelected");
            fillFragment(roadTrip);
        }

        recycler_view_list_endroit.setNestedScrollingEnabled(false);
        pictureAddedSuccess();

        gMap = ((SupportMapFragment)this.getChildFragmentManager().findFragmentById(R.id.mapRoadTrip));
        gMap.getMapAsync(this);

        /*((WorkaroundMapFragment) this.getChildFragmentManager().findFragmentById(R.id.mapRoadTrip))
                .setListener(new WorkaroundMapFragment.OnTouchListener() {
            @Override
            public void onTouch() {
                mScrollView.requestDisallowInterceptTouchEvent(true);
            }
        });*/

        return view;
    }

    @Override
    public void fillFragment(RoadTrip roadTrip){
        Geocoder geocoder = new Geocoder(this.getActivity());
        try {
            if(roadTrip.getBegin() != null) {
                //Ici on récupère la premiere adresse trouvée grace à la position que l'on a récupéré
                List<Address> adresses = geocoder.getFromLocation(roadTrip.getBegin().getLatitude(), roadTrip.getBegin().getLongitude(), 1);
                if (adresses != null && adresses.size() == 1) {
                    Address adresse = adresses.get(0);
                    //Si le geocoder a trouver une adresse, alors on l'affiche
                    textViewDetailDepart.setText(adresse.getLocality());
                } else {
                    //sinon on affiche un message d'erreur
                    textViewDetailDepart.setText("L'adresse n'a pu être déterminée");
                }
            }

        } catch (IOException e) {
            textViewDetailDepart.setText("L'adresse n'a pu être déterminée");
        }

        textViewDetailDescription.setText(roadTrip.getName());
        // si on est pas proprietaire du road trip on desactive les editText
        if(roadTrip.getOwner().getId() != Utility.getIdUser(getActivity())){
            textViewDetailDepart.setEnabled(false);
            textViewDetailDestination.setEnabled(false);
            textViewDetailDescription.setEnabled(false);
        }
        else if (roadTrip.getOwner().getId() == Utility.getIdUser(getActivity()) || isComeFromFollowFragment){
            buttonAddPlace.setVisibility(View.VISIBLE);
            butUpload.setVisibility(View.VISIBLE);
            butDeleteRoadTrip.setVisibility(View.VISIBLE);
        }

        presenterDetailRoadTrip.getAllPlacesByRoadId(roadTrip.getId());
    }

    @OnClick(R.id.addPlace)
    public void goToAddPlace(){
        Intent intent = new Intent(this.getContext() , AddPlaceActivity.class);
        intent.putExtra("roadTrip" , roadTrip.getId());
        startActivity(intent);
    }

    @OnClick(R.id.butDeleteRoadTrip)
    public void deleteRoadTrip(){
        presenterDetailRoadTrip.deleteRoadTrip(roadTrip.getId());
    }

    @OnClick(R.id.butUpload)
    public void uploadRoadTripInformation(){
        Departure d = Utility.getLocationFromAddress(getActivity() , textViewDetailDepart.getText().toString());
        d.setLatitude(d.getLatitude() / Math.pow(10,6)); // on divise par un million pour avoir la bonne latitude et longitude
        d.setLongitude(d.getLongitude() / Math.pow(10,6));
        if (destinationChanged == false){
            RoadTrip newRoadTrip = new RoadTrip(roadTrip.getId(), textViewDetailDescription.getText().toString(), d, roadTrip.getDestination() , null);
            presenterDetailRoadTrip.updateRoadTrip(newRoadTrip);
        }
        else if(destinationChanged == true){
            RoadTrip newRoadTrip = new RoadTrip(roadTrip.getId(), textViewDetailDescription.getText().toString(), d, place.getId() , null);
            presenterDetailRoadTrip.updateRoadTrip(newRoadTrip);
        }
        else{
            Toast.makeText(getContext() , "La ville d'arrivée doit être un élément de la liste " , Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        new GeocodeInverse(this.getContext(), googleMap, listPlace,
               roadTrip.getBegin(), roadTrip.getDestination() , textViewDetailDestination).execute();
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
    public void getListPlacesSuccess(ArrayList<com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place> places){
        listPlace = places;
        ListEndroitAdapter mListEndroitAdapter = new ListEndroitAdapter(places , roadTrip);
        recycler_view_list_endroit.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycler_view_list_endroit.setItemAnimator(new DefaultItemAnimator());
        recycler_view_list_endroit.setAdapter(mListEndroitAdapter);
    }


    @Override
    public void getListFailed(String msg) {
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
    public void listPlacesEmpty() {
        textViewListPlaceEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void goToListRoadTrip() {
        Intent intent = new Intent(getActivity() , ActivityHome2.class);
        startActivity(intent);
    }

    @Override
    public void deleteRoadTripFailed(String msg) {
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
    public void getInformationRoadTrip(RoadTrip roadTripR) {
        textViewDetailDepart.setText(Utility.getAdressByLatLng(getActivity(),roadTripR.getBegin().getLatitude(),roadTripR.getBegin().getLongitude()));
        textViewDetailDestination.setText(textViewDetailDestination.getText());
        textViewDetailDescription.setText(roadTripR.getName());
        // si on est pas proprietaire du road trip on desactive les editText
        if(roadTripR.getOwner().getId() != Utility.getIdUser(getActivity())){
            textViewDetailDepart.setEnabled(false);
            textViewDetailDestination.setEnabled(false);
            textViewDetailDescription.setEnabled(false);
        }
        else if (roadTripR.getOwner().getId() == Utility.getIdUser(getActivity())){
            textViewDetailDepart.setEnabled(true);
            textViewDetailDestination.setEnabled(true);
            textViewDetailDescription.setEnabled(true);
            butDeleteRoadTrip.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if(autoCompleteAdapter != null)
            autoCompleteAdapter.setGoogleApiClient(mGoogleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(connectionResult.getErrorMessage());
        builder.setCancelable(true);
        builder.setPositiveButton("Réessayer" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mGoogleApiClient.connect();
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /****************************************************** Choisir photo *****************************/

    @OnClick(R.id.butChoosePhotoRoad)
    public void choosePhoto(){
        selectImage();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Take Photo")) {
                        cameraIntent();
                    }
                    else if( userChoosenTask.equals("Choose from Library")) {
                        galleryIntent();
                    }
                } else {
                    //code for deny
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
            typedFile = new TypedFile("multipart/form-data",destination);
            buttonAddPhotoToRoad.setVisibility(View.VISIBLE);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), data.getData());
                Uri uriImageSelected = data.getData();
                typedFile = new TypedFile("multipart/form-data",new File( getRealPathFromURI(uriImageSelected)));
                buttonAddPhotoToRoad.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * Recuperer le chemin de la photo selectionnée
     * @param contentURI
     * @return
     */
    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getActivity().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    String m_Text="";
    @OnClick(R.id.buttonAddPhotoToRoad)
    public void addPostandPhoto(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle("Description");

        // Set up the input
        final EditText input = new EditText(this.getContext());
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("ajouter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                if (m_Text.equals("")){
                    Toast.makeText(getContext() , " la description est obligatoire" , Toast.LENGTH_LONG).show();
                }
                else{
                    presenterDetailRoadTrip.sendPost(new Post(m_Text , roadTrip.getId()));
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();


    }

    @Override
    public void postAddSuccess(Post post){
        presenterDetailRoadTrip.addPictureToRoad(post.getId() , typedFile);
    }


    @Override
    public void pictureAddedSuccess(){
        presenterDetailRoadTrip.getAllPosts();
    }

    @Override
    public void allPostSuccess(ArrayList<Post> posts){
        ArrayList<Integer> picturesId = new ArrayList<>();
        Post pcurrent = null;
        for(Post p : posts){
            if (p.getRoadtripId() == roadTrip.getId()){
                if(p.getPictures() != null && p.getPictures().size() != 0 ) {
                    picturesId.addAll(p.getPictures());
                    pcurrent = p;
                }
            }
        }
        ListPhotosRoadAdapter mAdapter = new ListPhotosRoadAdapter(picturesId , pcurrent , getContext());
        recyclerViewPhotosRoad.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        recyclerViewPhotosRoad.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPhotosRoad.setAdapter(mAdapter);
    }

    /**********************************************************************/
    @Override
    public void onStart() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
        super.onStart();

    }

    @Override
    public void onStop() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    @Override
    public void onResume(){
        super.onResume();
        presenterDetailRoadTrip.getRoadTripById(roadTrip.getId());
    }
}
