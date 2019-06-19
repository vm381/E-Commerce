package modelOld;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the User database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String username;

	private String address;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	private String city;

	private String country;

	private String firstName;

	private String lastName;

	private String password;

	private String phone;

	//bi-directional many-to-one association to Purchase
	@OneToMany(mappedBy="user")
	private List<Purchase> purchases;

	//bi-directional many-to-many association to Role
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="User_has_Role"
		, joinColumns={
			@JoinColumn(name="User_username")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Role_idRole")
			}
		)
	private Set<Role> roles;

	//bi-directional many-to-one association to Wishlist
	@OneToMany(mappedBy="user")
	private List<Wishlist> wishlists;

	public User() {
		this.roles = new HashSet<>();
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Purchase> getPurchases() {
		return this.purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public Purchase addPurchas(Purchase purchas) {
		getPurchases().add(purchas);
		purchas.setUser(this);

		return purchas;
	}

	public Purchase removePurchas(Purchase purchas) {
		getPurchases().remove(purchas);
		purchas.setUser(null);

		return purchas;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<Wishlist> getWishlists() {
		return this.wishlists;
	}

	public void setWishlists(List<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}

	public Wishlist addWishlist(Wishlist wishlist) {
		getWishlists().add(wishlist);
		wishlist.setUser(this);

		return wishlist;
	}

	public Wishlist removeWishlist(Wishlist wishlist) {
		getWishlists().remove(wishlist);
		wishlist.setUser(null);

		return wishlist;
	}
	
	public void addRole(Role r) {
		this.roles.add(r);
	}

}