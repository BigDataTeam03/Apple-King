package dto;

import java.sql.Timestamp;

public class customerDto {
		//Filed
	
			String cust_id;
			String cust_pw;
			String name;
			String tel;
			String email;
			String address;
			int rank;
			Timestamp reg_date;
			Timestamp deact_date;

	
			
			
			//Constructor
			
			public customerDto() {
				// TODO Auto-generated constructor stub
			}

			public customerDto(String cust_id, String cust_pw, String name, String tel, String email, String address,
					int rank, Timestamp reg_date, Timestamp deact_date) {
				super();
				this.cust_id = cust_id;
				this.cust_pw = cust_pw;
				this.name = name;
				this.tel = tel;
				this.email = email;
				this.address = address;
				this.rank = rank;
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

			public int getRank() {
				return rank;
			}

			public void setRank(int rank) {
				this.rank = rank;
			}

			public Timestamp getReg_date() {
				return reg_date;
			}

			public void setReg_date(Timestamp reg_date) {
				this.reg_date = reg_date;
			}

			public Timestamp getDeact_date() {
				return deact_date;
			}

			public void setDeact_date(Timestamp deact_date) {
				this.deact_date = deact_date;
			}
			
			
			

		//Method
		
	
}
