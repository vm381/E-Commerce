package modelOld;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SaleItem database table.
 * 
 */
@Entity
@NamedQuery(name="SaleItem.findAll", query="SELECT s FROM SaleItem s")
public class SaleItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSaleItem;

	private int amount;

	//bi-directional many-to-one association to Item
	@ManyToOne
	@JoinColumn(name="Item_idItem")
	private Item item;

	//bi-directional many-to-one association to Purchase
	@ManyToOne
	@JoinColumn(name="Purchase_idPurchase")
	private Purchase purchase;

	public SaleItem() {
	}

	public int getIdSaleItem() {
		return this.idSaleItem;
	}

	public void setIdSaleItem(int idSaleItem) {
		this.idSaleItem = idSaleItem;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Purchase getPurchase() {
		return this.purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

}