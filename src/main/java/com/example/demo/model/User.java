package com.example.demo.model;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String email;
    private String pseudo;
    private String password;
    private String role;
    private Blob image;
    private String nameImage;

    public User() {

    }

    public User(String email, String password) throws SerialException, SQLException {
        this.email = email;
        this.password = password;
        this.role = "USER";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return this.pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void setImage(byte[] image, String nameimage) throws SerialException, SQLException {
        Blob blob = new SerialBlob(image );
        this.image = blob;
        this.nameImage = nameimage;
    }

    public byte[] getImage() throws SQLException {
        
        int blobLength = (int) this.image.length();  
        byte[] blobAsBytes = this.image.getBytes(1, blobLength);

        return blobAsBytes;
    }

    public String getNameImage() {
        return this.nameImage;
    }

    public int getId(){
        return this.id;
    }

}