package com.main.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "certifyId", nullable = false, unique = true)
	private int certifyId;

	@Column(name = "certifyCode")
	private String certifyCode = "INSTACODE2021";

	@Column(name = "studentFname", nullable = false)
	private String studentFname;

	@Column(name = "studentLname", nullable = false)
	private String studentLname;

	@Column(name = "companyName", nullable = false)
	private String companyName;

	@Column(name = "joinDate", nullable = false)
	private Date joinDate;

	@Column(name = "exitDate", nullable = false)
	private Date exitDate;

	@Column(name = "companyEmail", nullable = false)
	private String companyEmail;

	@Column(name = "companyWebsite", nullable = false)
	private String companyWebsite;

	@Lob
	@Column(name = "studImage", length = Integer.MAX_VALUE, nullable = true)
	private byte[] studImage;

	public Company() {
	}

	public int getCertifyId() {
		return certifyId;
	}

	public void setCertifyId(int certifyId) {
		this.certifyId = certifyId;
	}

	public String getCertifyCode() {
		return certifyCode;
	}

	public void setCertifyCode(String certifyCode) {
		this.certifyCode = certifyCode;
	}

	public String getStudentFname() {
		return studentFname;
	}

	public void setStudentFname(String studentFname) {
		this.studentFname = studentFname;
	}

	public String getStudentLname() {
		return studentLname;
	}

	public void setStudentLname(String studentLname) {
		this.studentLname = studentLname;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getExitDate() {
		return exitDate;
	}

	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public byte[] getStudImage() {
		return studImage;
	}

	public void setStudImage(byte[] studImage) {
		this.studImage = studImage;
	}

}
