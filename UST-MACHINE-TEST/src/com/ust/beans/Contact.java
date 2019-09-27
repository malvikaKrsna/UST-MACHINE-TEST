package com.ust.beans;

public class Contact {
	private int contactId;
	private String contactName;
	private int vendorId;
	private String department;
	private String email;
	private String phone;
	public int getContactId() {
		return contactId;
	}
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Contact(int contactId, String contactName, int vendorId,
			String department, String email, String phone) {
		super();
		this.contactId = contactId;
		this.contactName = contactName;
		this.vendorId = vendorId;
		this.department = department;
		this.email = email;
		this.phone = phone;
	}
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", contactName="
				+ contactName + ", vendorId=" + vendorId + ", department="
				+ department + ", email=" + email + ", phone=" + phone + "]";
	}
	
	
	
	

}
