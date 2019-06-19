package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the Purchase database table.
 * 
 */
@Entity
@NamedQuery(name="Purchase.findAll", query="SELECT p FROM Purchase p")
public class Purchase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPurchase;

	@Temporal(TemporalType.DATE)
	private Date date;

	private double total;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="User_username")
	private User user;

	//bi-directional many-to-many association to Item
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name="Purchase_has_Item"
		, joinColumns={
			@JoinColumn(name="Purchase_idPurchase")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Item_idItem")
			}
		)
	private Set<Item> items;

	public Purchase() {
		items = new HashSet<>();
	}

	public int getIdPurchase() {
		return this.idPurchase;
	}

	public void setIdPurchase(int idPurchase) {
		this.idPurchase = idPurchase;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Item> getItems() {
		return this.items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPurchase;
		long temp;
		temp = Double.doubleToLongBits(total);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	public void addItem(Item i) {
		this.getItems().add(i);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Purchase other = (Purchase) obj;
		if (idPurchase != other.idPurchase)
			return false;
		if (Double.doubleToLongBits(total) != Double.doubleToLongBits(other.total))
			return false;
		return true;
	}

}