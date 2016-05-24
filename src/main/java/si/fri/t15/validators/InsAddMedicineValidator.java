package si.fri.t15.validators;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

public class InsAddMedicineValidator implements Validator{
	@Override
	public boolean supports(Class<?> c) {
		return c.isAssignableFrom(SignUpForm.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "medicine", "field.required", "Vnesite veljavno zdravilo");
		ValidationUtils.rejectIfEmpty(errors, "instruction_medicine", "field.required", "Vnesite navodilo");			
	}		
}
