package 이승후_중간고사_A_0427;

class person{
	String id,name,tel,kind;
	
	public person(String a,String b,String c,String d) {
		this.id = a;
		this.name = b;
		this.tel = c;
		this.kind = d;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	
}
