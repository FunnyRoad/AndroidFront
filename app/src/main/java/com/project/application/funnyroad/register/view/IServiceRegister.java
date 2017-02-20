package com.project.application.funnyroad.register.view;

/**
 * Created by oraberkane on 02/02/2017.
 */

public interface IServiceRegister {


    // affiche l'erreur sur le champs texte email
    public void emailInvalid(String msg);
    // affiche l'erreur sur le champs texte password
    public void passwordInvalid(String msg);
    // affiche l'erreur sur le champs mot de passe de confirmation
    public void confirmPasswordInvalid(String msg);
    // affiche le loader si bool = true,  le cache sinon
    public void showLoading(boolean bool);
    // redirection vers home
    public void registrationSuccess();
    // afficher une alert avec un message d'erreur
    public void registrationFailed(String msg);

}
