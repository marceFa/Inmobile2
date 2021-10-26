package com.example.inmobile.modelo;

public class LoginView {
    private String Email;
    private String Clave;

    public LoginView() {

    }

    public LoginView(String Email, String Clave) {
        this.Email = Email;
        this.Clave = Clave;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        this.Clave = clave;
    }
}
