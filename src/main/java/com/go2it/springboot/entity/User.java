package com.go2it.springboot.entity;

import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Lazy(true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
    private String login;
    private String password;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "customer")
    private List<Order> customerOrders;

    @OneToMany(mappedBy = "employee")
    private List<Order> employeeOrders;

    @OneToMany(mappedBy = "user")
    private List<Location> locations;

    @OneToMany(mappedBy = "customer")
    private List<Payment> customerPayments;

    @OneToMany(mappedBy = "employee")
    private List<Payment> employeePayments;

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int user_id) {
        this.userId = user_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phone_number) {
        this.phoneNumber = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUserId() == user.getUserId() &&
                getFirstName().equals(user.getFirstName()) &&
                getLastName().equals(user.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getFirstName(), getLastName());
    }

        public List<Order> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<Order> customerOrders) {
        this.customerOrders = customerOrders;
    }

    public List<Order> getEmployeeOrders() {
        return employeeOrders;
    }

    public void setEmployeeOrders(List<Order> employeeOrders) {
        this.employeeOrders = employeeOrders;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Payment> getCustomerPayments() {
        return customerPayments;
    }

    public void setCustomerPayments(List<Payment> customerPayments) {
        this.customerPayments = customerPayments;
    }

    public List<Payment> getEmployeePayments() {
        return employeePayments;
    }

    public void setEmployeePayments(List<Payment> employeePayments) {
        this.employeePayments = employeePayments;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
