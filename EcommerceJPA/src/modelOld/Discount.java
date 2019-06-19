package modelOld;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Discount database table.
 * 
 */
@Entity
@NamedQuery(name="Discount.findAll", query="SELECT d FROM Discount d")
public class Discount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDiscount;

	@Temporal(TemporalType.DATE)
	private Date date;

	private int percent;

	//bi-directional many-to-many association to Item
	@ManyToMany(mappedBy="discounts")
	private List<Item> items;

	public Discount() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idDiscount;
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
		Discount other = (Discount) obj;
		if (idDiscount != other.idDiscount)
			return false;
		return true;
	}

	public int getIdDiscount() {
		return this.idDiscount;
	}

	public void setIdDiscount(int idDiscount) {
		this.idDiscount = idDiscount;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getPercent() {
		return this.percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}