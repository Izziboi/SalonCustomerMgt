package de.ietu.iefriseursalon.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Dies ist die Hauptkundenklasse, von der aus die Daten aller Kunden erstellt werden.
 */
public class Customer {

    //Region Konstanten
    //Ende der Region Konstanten

    //Region Attribute
    private int customerId;
    private StringProperty username;
    private StringProperty password;
    private StringProperty name;
    private StringProperty address;
    private StringProperty email;
    private StringProperty phone;
    //Ende der Region Attribute

    //Region Konstruktoren
    public Customer(String username, String password, String name, String address, String email, String phone) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
    }


    public Customer(int customerId, String username, String password, String name, String address, String email, String phone) {
        this(username, password, name, address, email, phone);
        this.customerId = customerId;
    }
    //Ende der Region Konstruktoren

    //Region Methoden
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {return username.get(); }

    public StringProperty usernameProperty() {return username; }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {return  password.get(); }

    public StringProperty passwordProperty() {return password; }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", username='" + username.get() + '\'' +
                ", password='" + password.get() + '\'' +
                ", name='" + name.get() + '\'' +
                ", address='" + address.get() + '\'' +
                ", email='" + email.get() + '\'' +
                ", phone='" + phone.get() + '\'' +
                '}';
    }

    //Ende der Region Methoden
}
