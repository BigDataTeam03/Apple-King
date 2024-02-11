package dto;

public class MemberDto {
	// Filed

	String cust_id;
	String cust_pw;
	String name;
	String tel;
	String email;
	String address;
	int cust_rank;
	String reg_date;
	String deact_date;

	// Constructor

	public MemberDto() {
		// TODO Auto-generated constructor stub
	}

	// 고객 리스트를 에 보이기위한 Dto 리스트
	public MemberDto(String cust_id, String name, String tel, String email, String address, int cust_rank,
			String reg_date) {
		super();
		this.cust_id = cust_id;
		this.name = name;
		this.tel = tel;
		this.email = email;
		this.address = address;
		this.cust_rank = cust_rank;
		this.reg_date = reg_date;
	}

	public MemberDto(String cust_id, String cust_pw, String name, String tel, String email, String address,
			int cust_rank, String reg_date, String deact_date) {
		super();
		this.cust_id = cust_id;
		this.cust_pw = cust_pw;
		this.name = name;
		this.tel = tel;
		this.email = email;
		this.address = address;
		this.cust_rank = cust_rank;
		this.reg_date = reg_date;
		this.deact_date = deact_date;
	}

	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	public String getCust_pw() {
		return cust_pw;
	}

	public void setCust_pw(String cust_pw) {
		this.cust_pw = cust_pw;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCust_rank() {
		return cust_rank;
	}

	public void setCust_rank(int cust_rank) {
		this.cust_rank = cust_rank;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getDeact_date() {
		return deact_date;
	}

	public void setDeact_date(String deact_date) {
		this.deact_date = deact_date;
	}

	// Method

}
