package dev.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dev.entities.Collegue;
import dev.entities.enumerations.Role;

/**
 * Structure modèlisant un collègue servant à communiquer avec l'extérieur (WEB API).
 */
public class CollegueDto {

    private String email;
    private String nom;
    private String prenom;
    private List<Role> roles = new ArrayList<>();

    public CollegueDto(Collegue col) {
        this.email = col.getEmail();
        this.nom = col.getNom();
        this.prenom = col.getPrenom();
        this.roles = col.getRoles().stream().map(roleCollegue -> roleCollegue.getRole()).collect(Collectors.toList());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
