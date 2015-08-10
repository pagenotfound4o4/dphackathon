package main.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tmp")
public class Tmp implements Serializable {
	private static final long serialVersionUID = 3618228488405994298L;
	@Id
	@GeneratedValue
	@Column(name = "uid")
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

