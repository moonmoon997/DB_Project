
public class Users {
	String ID;
	String PW;
	String name;
	String phone;
	String grade;
	
	public Users(String ID, String PW, String name, String phone, String grade) {
		this.ID=ID;
		this.PW=PW;
		this.name=name;
		this.phone=phone;
		this.grade=grade;
	}
	public String get_ID() {
		return this.ID;
	}
	public String get_PW() {
		return this.PW;
	}
	public String get_name() {
		return this.name;
	}
	public String get_phone() {
		return this.phone;
	}
	public String get_grade() {
		return this.grade;
	}
}
