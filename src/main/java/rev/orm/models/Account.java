package rev.orm.models;

import java.util.Objects;
//Delete this class once finished
public class Account {
	
	private String accountName;
	private String accountLastName;
	private String userName;
	private String userEmail;
	private int zipcode;


	public Account() {
		super();
	}

	public Account(String accountName, String accountLastName, String userName, String userEmail, int zipcode) {
		super();
		this.accountName = accountName;
		this.accountLastName = accountLastName;
		this.userName = userName;
		this.userEmail = userEmail;
		this.zipcode = zipcode;
	}


	public String getAccountName() {
		return accountName;
	}


	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	public String getAccountLastName() {
		return accountLastName;
	}


	public void setAccountLastName(String accountLastName) {
		this.accountLastName = accountLastName;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public int getZipcode() {
		return zipcode;
	}


	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}


	@Override
	public int hashCode() {
		return Objects.hash(accountLastName, accountName, userEmail, userName, zipcode);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(accountLastName, other.accountLastName) && Objects.equals(accountName, other.accountName)
				&& Objects.equals(userEmail, other.userEmail) && Objects.equals(userName, other.userName)
				&& zipcode == other.zipcode;
	}


	@Override
	public String toString() {
		return "Account [accountName=" + accountName + ", accountLastName=" + accountLastName + ", userName=" + userName
				+ ", userEmail=" + userEmail + ", zipcode=" + zipcode + "]";
	}
	
}
