package main.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "favorite")
public class Favorite {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "uid")
	private int uid;

	@Column(name = "bid")
	private String bid;
	
	@Column(name = "init_time", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date initTime;
	

	public Date getInitTime() {
		return initTime;
	}

	public void setInitTime(Date initTime) {
		this.initTime = initTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}
}
