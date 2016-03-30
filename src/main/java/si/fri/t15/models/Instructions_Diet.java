package si.fri.t15.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Instructions_Diet implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="idInstuctions_Diet", length=4, nullable=false, updatable=false, unique=true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="Name", length=45, nullable=false)
	private String name;
	
	@Column(name="Duration", nullable=false)
	private int duration;
	
	@Column(name="Text", length=225, nullable=false)
	private int text;
	
	@ManyToOne
	private Diet diet;
}
