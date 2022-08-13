package ua.lviv.lgs.domain;


import java.sql.Date;
import java.util.Objects;
import javax.persistence.*;

@Entity

@Table(name="Bucket")
public class Bucket {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="user_id" , referencedColumnName ="id")
	private Integer user_id;
	
	@ManyToOne
	@JoinColumn(name="product_id"  , referencedColumnName ="id")
	private Integer product_id;
	
	@JoinColumn(name="purchase_date" )
	private Date purchase_date;
	public Bucket(Integer id, Integer user_id, Integer product_id, Date purchese_date) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.product_id = product_id;
		this.purchase_date = purchese_date;
	}
	public Bucket(Integer user_id, Integer product_id, Date date) {
		super();
		this.user_id = user_id;
		this.product_id = product_id;
		this.purchase_date =  date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer product_id) {
		this.user_id = product_id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Date getPurchase_date() {
		return  (Date) purchase_date;
	}
	public void setPurchase_date(Date purchase_date) {
		this.purchase_date = purchase_date;
	}
	@Override
	public int hashCode() {
		return Objects.hash(product_id, id, purchase_date, user_id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bucket other = (Bucket) obj;
		return Objects.equals(product_id, other.product_id) && Objects.equals(id, other.id)
				&& Objects.equals(purchase_date, other.purchase_date) && Objects.equals(user_id, other.user_id);
	}
	@Override
	public String toString() {
		return "Bucket [id=" + id + ", user_id=" + user_id + ", product_id=" + product_id + ", purchase_date="
				+ purchase_date + "]";
	}
	

}
