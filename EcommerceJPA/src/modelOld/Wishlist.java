package modelOld;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Wishlist database table.
 * 
 */
@Entity
@NamedQuery(name="Wishlist.findAll", query="SELECT w FROM Wishlist w")
public class Wishlist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idWish;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAdded;

	//bi-directional many-to-one association to Item
	@ManyToOne
	@JoinColumn(name="Item_idItem")
	private Item item;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="User_username")
	private User user;

	public Wishlist() {
	}

	public int getIdWish() {
		return this.idWish;
	}

	public void setIdWish(int idWish) {
		this.idWish = idWish;
	}

	public Date getDateAdded() {
		return this.dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}