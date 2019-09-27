package com.ust.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ust.beans.Login;
import com.ust.beans.Vendor;






public class MainDaoService {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	//verify login
	public Login selectRole(String username, String password) {
		String sql = "select userId from login where username='"
				+ username
				+ "' and password='" + password + "'";
		return template.queryForObject(sql, new Object[] {},
				new BeanPropertyRowMapper<Login>(Login.class));
	}
	
	//view vendors 
	
	public List<Vendor> getVendor() {
		return template
				.query("select vendorId,vendorName,address,location,service,pincode from vendor where isActive=1",
						new RowMapper<Vendor>() {
							public Vendor mapRow(ResultSet rs, int row)
									throws SQLException {
								Vendor v = new Vendor();
								v.setVendorId(rs.getInt(1));
								v.setVendorName(rs.getString(2));
								v.setAddress(rs.getString(3));
								v.setLocation(rs.getString(4));
								v.setService(rs.getString(5));
								v.setPincode(rs.getInt(6));
								return v;
							}
						});
	}
	
	//view vendor by vendor name
	public List<Vendor> getVendorByName(String vendorName) {
			return template
					.query("select vendorId,vendorName,address,location,service,pincode from vendor where isActive=1 and vendorName='"+ vendorName + "'",
							new RowMapper<Vendor>() {
								public Vendor mapRow(ResultSet rs, int row)
										throws SQLException {
									Vendor v = new Vendor();
									v.setVendorId(rs.getInt(1));
									v.setVendorName(rs.getString(2));
									v.setAddress(rs.getString(3));
									v.setLocation(rs.getString(4));
									v.setService(rs.getString(5));
									v.setPincode(rs.getInt(6));
									return v;
								}
							});
		}
	//details by id
	public List<Vendor> getContact(int vendorId) {
		return template.query("select contactId,contactName,department,vendorId,email,phone from details where vendorId="+vendorId+"", new RowMapper<Vendor>() {
			public Vendor mapRow(ResultSet rs, int row)
					throws SQLException {
				Vendor vc = new Vendor();
				vc.setContactId(rs.getInt(1));
				vc.setContactName(rs.getString(2));
				vc.setDepartment(rs.getString(3));
				vc.setVendorId(rs.getInt(4));
				vc.setEmail(rs.getString(5));
				vc.setPhone(rs.getString(6));
				return vc;
			}
		});
		}

	
	//to Add vendors
	
	public int addVendor(Vendor v) {

		String sql1 = "insert into vendor(vendorName,address,location,service,pincode,isActive) values "
				+ "('"
				+ v.getVendorName()
				+ "','"
				+ v.getAddress()
				+ "','"
				+ v.getLocation()
				+ "','"
				+ v.getService()
				+ "',"
				+ v.getPincode()
				+ ","
				+ 1
				+ ")";

		 template.update(sql1);
		 
		 Integer maxId = getSequence();
		 String sql2="insert into details(vendorId,contactName,department,email,phone) values ("
				 + maxId
					+ ",'"
					+ v.getVendorName()
					+ "','"
					+ v.getDepartment()
					+ "','"
					+ v.getEmail()
					+ "','" + v.getPhone() + "')";
		 return template.update(sql2);

				 
		 
	}
	
	// to get vendor by vendor id
	private Integer getSequence() {
		Integer seq;
		String sql = "select MAX(vendorId)from vendor";
		seq = template.queryForObject(sql, new Object[] {}, Integer.class);
		return seq;
	}
	
	//view vendor by id
	public Vendor getVendorById(int vendorId) {
		String sql="select vc.vendorId,vc.vendorName,vc.address,vc.location,"
				+ "vc.service,vc.pincode,cd.contactName,cd.department,cd.email,cd.phone,cd.contactId "
				+ "from vendor vc join details cd on vc.vendorId=cd.vendorId where vc.isActive=1 and"
				+ " vc.vendorId='"+ vendorId + "'";
		return template.queryForObject(sql, new Object[] {},
				new BeanPropertyRowMapper<Vendor>(Vendor.class));
						
	}



	// update vendor
	public int updateVendor(int vendorId, Vendor v) {

		String sql = "update vendor set vendorName='" + v.getVendorName()
				+ "' ,address='" + v.getAddress() + "' ,location='"
				+ v.getLocation() + "',service='" + v.getService() + "',pincode=" + v.getPincode() + ",isActive=1 "
				+ "where vendorId =" + vendorId;
		System.out.println("sql"+sql);
		template.update(sql);

		Integer maxId = getSequence();

		String sql1 = "update details set vendorId=" + vendorId + ",contactName='"
				+ v.getContactName() + "',department='"
				+ v.getDepartment() + "',email='"
				+ v.getEmail() + "',phone='" + v.getPhone() + "'where contactId = " + v.getContactId();

		return template.update(sql1);
	}
	//disable vendor
	public int disableVendor(int vendorId) {

		String sql = "update vendor set isActive='0' where vendorId="
				+ vendorId + "";

		return template.update(sql);
	}
	
	
}
