package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the Item database table.
 * 
 */
@Entity
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idItem;

	private String description;

	private String name;

	private String picture;

	private double price;

	private int stock;

	//bi-directional many-to-many association to Discount
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name="Discount_has_Item"
		, joinColumns={
			@JoinColumn(name="Item_idItem")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Discount_idDiscount")
			}
		)
	private Set<Discount> discounts;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="Category_idCategory")
	private Category category;

	//bi-directional many-to-many association to Purchase
	@ManyToMany(mappedBy="items", fetch = FetchType.EAGER)
	private Set<Purchase> purchases;

	//bi-directional many-to-one association to Wishlist
	@OneToMany(mappedBy="item", fetch = FetchType.EAGER)
	private Set<Wishlist> wishlists;

	public Item() {
		discounts = new HashSet<>();
		purchases = new HashSet<>();
		wishlists = new HashSet<>();
	}

	public int getIdItem() {
		return this.idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Set<Discount> getDiscounts() {
		return this.discounts;
	}

	public void setDiscounts(Set<Discount> discounts) {
		this.discounts = discounts;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Purchase> getPurchases() {
		return this.purchases;
	}

	public void setPurchases(Set<Purchase> purchases) {
		this.purchases = purchases;
	}

	public Set<Wishlist> getWishlists() {
		return this.wishlists;
	}

	public void setWishlists(Set<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}

	public Wishlist addWishlist(Wishlist wishlist) {
		getWishlists().add(wishlist);
		wishlist.setItem(this);

		return wishlist;
	}

	public Wishlist removeWishlist(Wishlist wishlist) {
		getWishlists().remove(wishlist);
		wishlist.setItem(null);

		return wishlist;
	}
	
	public void addDiscount(Discount d) {
		this.getDiscounts().add(d);
	}
	
	public void addPurchase(Purchase p) {
		this.getPurchases().add(p);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + idItem;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + stock;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (idItem != other.idItem)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (stock != other.stock)
			return false;
		return true;
	}

}