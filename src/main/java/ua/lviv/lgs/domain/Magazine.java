package ua.lviv.lgs.domain;

import java.util.Objects;
import javax.persistence.*;

@Entity

@Table(name="Magazine")
public class Magazine {
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="pages")
	private Integer pages;
	
	@Column(name="information")
	private String information;
	
	@Column(name="price")
	private double  price;
	
	public Magazine(Integer id, String name, Integer pages, String information, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.pages = pages;
		this.information = information;
		this.price = price;
	}
	
	public Magazine(String name, Integer pages, String information, Double price) {
		super();
		this.name = name;
		this.pages = pages;
		this.information = information;
		this.price = price;
	}

	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, information, name, pages, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Magazine other = (Magazine) obj;
		return id == other.id && Objects.equals(information, other.information) && Objects.equals(name, other.name)
				&& pages == other.pages && price == other.price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", pages=" + pages + ", information=" + information + ", price="
				+ price + "]";
	}
	
	
}
