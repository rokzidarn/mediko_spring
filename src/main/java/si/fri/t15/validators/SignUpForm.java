package si.fri.t15.validators;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class SignUpForm {
	
	@NotNull
    @Email
    private String username;
	
    @NotNull
    @Size(min=6, max=16)
    @Pattern(regexp="/^[0-9a-zA-Z-_]{6,16}$/")
    private String password;
    
    @NotNull
    @Size(min=6, max=16)
    @Pattern(regexp="/^[0-9a-zA-Z-_]{6,16}$/")
    private String repeatpassword;
     
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	public String getRepeatpassword() {
		return repeatpassword;
	}

	public void setRepeatpassword(String repeatpassword) {
		this.repeatpassword = repeatpassword;
	}
}