package si.fri.t15.models.user;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import si.fri.t15.base.models.UserData;
import si.fri.t15.models.UserRole;
import si.fri.t15.models.UserRole.Role;;

@Entity
@NamedQueries({
	@NamedQuery(name="User.findAll", query="SELECT u FROM User u"),
	@NamedQuery(name="User.findAllWithoutUserData", query="SELECT u FROM User u WHERE u.data = null AND u.username LIKE :search ")
})

public class User implements UserDetails, CredentialsContainer{
	
	private static final long serialVersionUID = 1L;
	
	public enum UserType {
		USER, NURSE, DOCTOR, ADMIN;
	}
	
	@Id
	@Column(name="Username", length=45, unique=true, nullable=false)
	private String username;

	@Column(name="Password", length=60, nullable=false, updatable=true)
	private String password;
	
	@Column(name="LastLogin", updatable=true)
	private Timestamp lastLogin;
	
	@Column(name="PasswordResetToken", length=15, updatable=true, unique=true)
	private String passwordResetToken;
	
	@Column(name="RegistrationDate")
	private Date registrationDate;
	
	@OneToOne
	private UserData data;
	
	@ManyToMany
	@JoinTable(
		      name="user_user_roles",
		      joinColumns=@JoinColumn(name="U_ID", referencedColumnName="username"),
		      inverseJoinColumns=@JoinColumn(name="R_ID", referencedColumnName="role"))
	private Set<UserRole> userRoles = new HashSet<>(0);
	
	@Column
	private boolean isAccountNonExpired;
	
	@Column
	private boolean isAccountNonLocked;
	
	@Column
	private boolean isCredentialsNonExpired;
	
	@Column
	private boolean isEnabled;
	
	//SelectedPatient 
	
	transient private PatientData selectedPatient;
	transient private String firstAndLastName;

	


	public String getFirstAndLastName() {
		if(data != null)
			return data.getFirst_name()+" "+data.getLast_name();
		return "";
	}

	@Override
	public void eraseCredentials() {
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (UserRole role : userRoles) {
			authorities.add(new SimpleGrantedAuthority(role.getRole()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getPasswordResetToken() {
		return passwordResetToken;
	}

	public void setPasswordResetToken(String passwordResetToken) {
		this.passwordResetToken = passwordResetToken;
	}

	@JsonIgnore
	public UserData getData() {
		return data;
	}

	public void setData(UserData data) {
		this.data = data;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	public PatientData getSelectedPatient() {
		return selectedPatient;
	}

	public void setSelectedPatient(PatientData selectedPatient) {
		this.selectedPatient = selectedPatient;
	}
	
	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public UserType getUserType() {
		boolean admin = false;
		UserType type = UserType.USER;
		for (UserRole userRole : userRoles) {
			if (userRole.equals(Role.ROLE_ADMIN)) {
				admin = true;
			} else if (userRole.equals(Role.ROLE_DOCTOR)) {
				type = UserType.DOCTOR;
			} else if (userRole.equals(Role.ROLE_NURSE)) {
				type = UserType.NURSE;
			}
		}
		if (admin) {
			return UserType.ADMIN;
		} else {
			return type;
		}
	}
	
}