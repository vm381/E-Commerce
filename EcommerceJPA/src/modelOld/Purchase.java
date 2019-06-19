package modelOld;

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

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private double total;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="User_username")
	private User user;

	//bi-directional many-to-one association to SaleItem
	@OneToMany(mappedBy="purchase", fetch = FetchType.EAGER)
	private Set<SaleItem> saleItems;

	public Purchase() {
		saleItems = new HashSet<>();
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

	public Set<SaleItem> getSaleItems() {
		return this.saleItems;
	}

	public void setSaleItems(Set<SaleItem> saleItems) {
		this.saleItems = saleItems;
	}

	public SaleItem addSaleItem(SaleItem saleItem) {
		getSaleItems().add(saleItem);
		saleItem.setPurchase(this);

		return saleItem;
	}

	public SaleItem removeSaleItem(SaleItem saleItem) {
		getSaleItems().remove(saleItem);
		saleItem.setPurchase(null);

		return saleItem;
	}

}