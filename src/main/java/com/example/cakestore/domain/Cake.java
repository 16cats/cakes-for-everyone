package com.example.cakestore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Cake {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String dietaryInformation;
	private String description;
	private double price;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "categoryid")
	private Category category;
	
	public Cake () {}

	public Cake(String name, String dietaryInformation, String description, double price, Category category) {
		super();
		this.name = name;
		this.dietaryInformation = dietaryInformation;
		this.description = description;
		this.price = price;
		this.category = category;
	}

	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDietaryInformation() {
		return dietaryInformation;
	}



	public void setdietaryInformation(String dietaryInformation) {
		this.dietaryInformation = dietaryInformation;
	}
	
	
	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Cake [id=" + id + ", name=" + name + ", dietaryInformation=" + dietaryInformation + ", description="
				+ description + ", price=" + price + ", category=" + category + "]";
	}
	
}
