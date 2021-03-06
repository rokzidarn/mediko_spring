package si.fri.t15.base.models;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.collections.CollectionUtils;

@Entity
public abstract class UserData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static Set<String> relationshipTypes = new TreeSet<>(Arrays.asList(new String[] { "Brat/sestra", "Oče/mati",
			"Dedek/babica", "Krušni starš", "Partner/partnerka", "Skrbnik" }));

	@Id
	@Column(length=4, nullable=false, updatable=false, unique=true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;
	
	@Column(name="First_Name", length=15, nullable=false, updatable=true)
	protected String first_name;

	@Column(name="Last_Name", length=15, nullable=false, updatable=true)
	protected String last_name;
	
	@Column(nullable=false, updatable=true)
	protected String phoneNumber;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public static Set<String> getRelationshipTypes() {
		return relationshipTypes;
	}
	
}
