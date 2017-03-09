package com.project.application.funnyroad.profil.view.view;

/**
 * Created by sameur on 02/02/2017.
 */

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.common.Utility;
import com.project.application.funnyroad.login.model.User;
import com.project.application.funnyroad.profil.view.presenter.PresenterProfil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfilFragment extends Fragment implements IServiceProfil{

    public static final int MY_PERMISSIONS_REQUEST_ACCESS = 2;

    // chemin vers l'emplacement de la sauvegarde de l'image
    static String path = "/data/data/com.example.oraberkane.appliplatine/app_imageDir";

    @BindView(R.id.editTextFirstName)
    EditText editTextFirstName;
    @BindView(R.id.editTextLastName)
    EditText editTextLastName;
    @BindView(R.id.editTextEMail)
    EditText editTextEmail;
    @BindView(R.id.editTextUserName)
    EditText editTextUserName;
    @BindView(R.id.imageProfil)
    ImageView imageProfil;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.buttSave)
    Button buttSave;

    PresenterProfil presenterProfil;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    private Set<String> userInformation = new HashSet<>();
    int value;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Retourne votre fichier layout
        // Changer R.layout.yourlayoutfilename pour vos fragments
        View view = inflater.inflate(R.layout.profil_fragment, container, false);

        ButterKnife.bind(this , view);

        this.getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        presenterProfil = new PresenterProfil(this);
        value = Utility.getIdUser(getActivity());

        presenterProfil.getInformationUser(value); // on recupere les information de l'utilisateur a partir de la BD

        Log.d("TAG", "onCreateView: id : "+ value);
        /*editTextEmail.setText(Utility.getInformationUser(getActivity() , "email"));
        editTextFirstName.setText(Utility.getInformationUser(getActivity() , "personName"));
        editTextLastName.setText(Utility.getInformationUser(getActivity() , "lastName"));
        editTextUserName.setText(Utility.getInformationUser(getActivity() , "userName"));
        */
        loadImageFromStorage(path);

        return view;
    }

    @OnClick(R.id.buttSave)
    public void updateProfil(){
        String firebaseId = Utility.getInformationUser(getActivity() , "firebaseId");
        User user = new User(value , editTextEmail.getText().toString() , firebaseId ,
                editTextFirstName.getText().toString(), editTextLastName.getText().toString() ,
                editTextUserName.getText().toString() , null );

        presenterProfil.updateInformationsUser(user);
    }

    @OnClick(R.id.imageProfil)
    public void loadImageProfile(){
        selectImage();
    }

    @Override
    public void showLoading(boolean bool) {
        if (bool){
            progressBar.setVisibility(View.VISIBLE);
        }
        else{
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showInformationsUser(User user){
        /*Utility.storeInformationUser(getActivity() , "email" , user.getMail());
        Utility.storeInformationUser(getActivity() , "personName" , user.getFirtsName());
        Utility.storeInformationUser(getActivity() , "lastName" , user.getLastName());
        Utility.storeInformationUser(getActivity() , "userName" , user.getUsername());
        */

        editTextFirstName.setText(user.getFirtsName());
        editTextLastName.setText(user.getLastName());
        editTextEmail.setText(user.getMail());
        editTextUserName.setText(user.getUsername());

        if(user.getId() != Utility.getIdUser(getActivity())){
            editTextFirstName.setEnabled(false);
            editTextLastName.setEnabled(false);
            editTextEmail.setEnabled(false);
            editTextUserName.setEnabled(false);
            buttSave.setVisibility(View.GONE);

        }

        if (!(path.equals(""))){
            loadImageFromStorage(path);
        }

    }

    private void loadImageFromStorage(String path)
    {
        try {
            File f=new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            imageProfil.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void errorGetInformations(String msg) {
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



    /**************************************** la selection de l'image **************************/





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
        Bitmap bm = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

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
        path = Utility.saveToInternalStorage(getContext() , bm);
        imageProfil.setImageBitmap(bm);
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
        path = Utility.saveToInternalStorage(getContext() , bm);
        Log.d("ProfilFragment", "onSelectFromGalleryResult: "+ path);
        imageProfil.setImageBitmap(bm);
    }
}
