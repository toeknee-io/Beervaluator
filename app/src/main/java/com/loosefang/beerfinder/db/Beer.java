package com.loosefang.beerfinder.db;

public class Beer {

    private long beerId;
	private String name;
	private String store;
    private double alcPct;
    private double alcQty;
    private double price;
    private String desc;
    private double rating;
    private String strRating;

    public long getBeerId() {
        return beerId;
    }
    public void setBeerId(long beerId) {
        this.beerId = beerId;
    }
    public double getAlcPct() {
        return alcPct;
    }
    public void setAlcPct(double alcPct) {

        this.alcPct = alcPct;
    }
    public double getAlcQty() {
        return alcQty;
    }
    public void setAlcQty(double alcQty) {
        this.alcQty = alcQty;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public double getRating() {
        strRating = String.format("%.1f", rating);
        this.rating = Double.valueOf(strRating);
		return rating;
	}
	public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return
                "\n" +
                "Beer: " + this.getName() + "\n" +
                "Location: " + this.getStore() + "\n" +
                "Percentage: " + this.getAlcPct() + "\n" +
                "Quantity: " + this.getAlcQty() + "\n" +
                "Price: " + this.getPrice() + "\n" +
                "Rating: " + this.getRating() + "\n";
    }

}
