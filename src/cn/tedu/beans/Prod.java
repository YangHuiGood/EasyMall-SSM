package cn.tedu.beans;

public class Prod {
	private int id;
	private String name;
	private double price;
	private String cname;// 保存商品名称
	private int pnum;
	private String imgurl;
	private String description;
	private ProdCategory prodCategory;
	public Prod() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Prod(int id, String name, double price, String cname, int pnum,
			String imgurl, String description, ProdCategory prodCategory) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.cname = cname;
		this.pnum = pnum;
		this.imgurl = imgurl;
		this.description = description;
		this.prodCategory = prodCategory;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ProdCategory getProdCategory() {
		return prodCategory;
	}
	public void setProdCategory(ProdCategory prodCategory) {
		this.prodCategory = prodCategory;
	}
	@Override
	public String toString() {
		return "Prod [id=" + id + ", name=" + name + ", price=" + price
				+ ", cname=" + cname + ", pnum=" + pnum + ", imgurl=" + imgurl
				+ ", description=" + description + ", prodCategory="
				+ prodCategory + "]";
	}
	
}
