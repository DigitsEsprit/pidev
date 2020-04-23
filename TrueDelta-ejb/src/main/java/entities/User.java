package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import at.favre.lib.crypto.bcrypt.BCrypt;



//import at.favre.lib.rypto.bcrypt.BCrypt;


@SuppressWarnings("serial")
@Entity
@Table(name="USERS")
public class User implements Serializable{
@Id
@GeneratedValue( strategy = GenerationType.IDENTITY )
@Column(name="ID_USER")
	private int id_user; 
@Column(name="FIRST_NAME")
	private String first_name ;
@Column(name="LAST_NAME")
	private String last_name ;
@Column(name="E_MAIL")
	private String email;
@Column(name="PHONE_NUMBER")
	private int phone_number ;
@Column(name="PASSWORD")
	private String password ;
@Column(name="ADRESSE")
	private String adresse ;
@Column(name="TOKEN")
private String verifToken;
@Column(name="IS_VALID")
private boolean isValid;
@Column(name="ROLE")
@Enumerated(EnumType.STRING)
	private Role role;
//client
@Column(name="RIB")
private int rib;
//AM
@Column(name="CLASSIFICATION")
@Enumerated(EnumType.STRING)
private Classification classification; 
//broker
@Column(name="BOURSE")
private String bourse;
public int getId_user() {
	return id_user;
}
public void setId_user(int id_user) {
	this.id_user = id_user;
}
public String getFirst_name() {
	return first_name;
}
public void setFirst_name(String first_name) {
	this.first_name = first_name;
}
public String getLast_name() {
	return last_name;
}
public void setLast_name(String last_name) {
	this.last_name = last_name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getPhone_number() {
	return phone_number;
}
public void setPhone_number(int phone_number) {
	this.phone_number = phone_number;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	/*String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
	this.password = bcryptHashString;*/
	this.password =password;
}
public String getAdresse() {
	return adresse;
}
public void setAdresse(String adresse) {
	this.adresse = adresse;
}
public Role getRole() {
	return role;
}
public void setRole(Role role) {
	this.role = role;
}
public int getRib() {
	return rib;
}
public void setRib(int rib) {
	this.rib = rib;
}
public Classification getClassification() {
	return classification;
}
public void setClassification(Classification classification) {
	this.classification = classification;
}
public String getBourse() {
	return bourse;
}
public void setBourse(String bourse) {
	this.bourse = bourse;
}



public String getVerifToken() {
	return verifToken;
}
public void setVerifToken(String verifToken) {
	this.verifToken = verifToken;
}
public boolean isValid() {
	return isValid;
}
public void setValid(boolean isValid) {
	this.isValid = isValid;
}
public User() {

}
public User(int id_user, String first_name, String last_name, String email, int phone_number, String password,
		String adresse, Role role, int rib, Classification classification, String bourse) {
	super();
	this.id_user = id_user;
	this.first_name = first_name;
	this.last_name = last_name;
	this.email = email;
	this.phone_number = phone_number;
	this.password = password;
	this.adresse = adresse;
	this.role = role;
	this.rib = rib;
	this.classification = classification;
	this.bourse = bourse;
}
@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
private List<Contract> Contracts;
@OneToOne(mappedBy="user")
private Portfolio portfolio;
@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
private List<Complain> complains;
@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
private List<Feedback> feedbacks;

@Override
public String toString() {
	return "User [role=" + role + ", classification=" + classification + "]";
}


}
