
public class Restaurant {
	String resname;
	String resfoodinfo;
	String resavgstar;
	int resreview;
	
	public Restaurant(String resname, String resfoodinfo, String resavgstar, int resreview) {
		this.resname=resname;
		this.resfoodinfo=resfoodinfo;
		this.resavgstar=resavgstar;
		this.resreview=resreview;	
	}
	public String get_resname() {
		return this.resname;
	}
	public String get_resfoodinfo() {
		return this.resfoodinfo;
	}
	public String get_resavgstar() {
		return this.resavgstar;
	}
	public int get_resreview() {
		return this.resreview;
	}
	
}
