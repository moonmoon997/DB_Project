
public class Food {
	String maincatagory;
	String middleclass;
	String foodname;
	String resname;
	
	public Food(String foodname, String maincatagory, String middleclass, String resname) {
		this.foodname=foodname;
		this.maincatagory=maincatagory;
		this.middleclass=middleclass;
		this.resname=resname;
	}
	public String get_foodname() {
		return this.foodname;
	}
	public String get_maincatagory() {
		return this.maincatagory;
	}
	public String get_middleclass() {
		return this.middleclass;
	}
	public String get_resname() {
		return this.resname;
	}
}
