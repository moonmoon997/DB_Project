
public class Food {
	String maincatagory;
	String middleclass;
	String foodname;
	String resname;
	
	public Food(String maincatagory, String middleclass, String foodname, String resname) {
		this.maincatagory=maincatagory;
		this.middleclass=middleclass;
		this.foodname=foodname;
		this.resname=resname;
	}
	public String get_maincatagory() {
		return this.maincatagory;
	}
	public String get_middleclass() {
		return this.middleclass;
	}
	public String get_foodname() {
		return this.foodname;
	}
	public String get_resname() {
		return this.resname;
	}
}
