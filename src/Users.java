
public class Users {
	String ID;
	String PW;
	String name;
	String phone;
	
	public Users(String ID, String PW, String name, String phone) {
		this.ID=ID;
		this.PW=PW;
		this.name=name;
		this.phone=phone;
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
}
