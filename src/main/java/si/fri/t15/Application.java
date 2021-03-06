package si.fri.t15;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import si.fri.t15.dao.UserDetailsService;
import si.fri.t15.login.controllers.SessionEndedListener;
import si.fri.t15.validators.AddDietValidator;
import si.fri.t15.validators.AddDiseaseValidator;
import si.fri.t15.validators.AddMedCenterValidator;
import si.fri.t15.validators.AddMedicineValidator;
import si.fri.t15.validators.ChangePasswordValidator;
import si.fri.t15.validators.CreateMedicalWorkerValidator;
import si.fri.t15.validators.CreatePatientValidator;
import si.fri.t15.validators.ForgottenPasswordValidator;
import si.fri.t15.validators.DeleteDietValidator;
import si.fri.t15.validators.DeleteDiseaseValidator;
import si.fri.t15.validators.DeleteMedCenterValidator;
import si.fri.t15.validators.DeleteMedicineValidator;
import si.fri.t15.validators.DoctorProfileValidator;
import si.fri.t15.validators.InsAddDietValidator;
import si.fri.t15.validators.InsAddDiseaseValidator;
import si.fri.t15.validators.InsAddMedicineValidator;
import si.fri.t15.validators.InsDelDietValidator;
import si.fri.t15.validators.InsDelDiseaseValidator;
import si.fri.t15.validators.InsDelMedicineValidator;
import si.fri.t15.validators.InsertDiagnosisValidator;
import si.fri.t15.validators.InsertDietValidator;
import si.fri.t15.validators.InsertDiseaseValidator;
import si.fri.t15.validators.InsertMedicineValidator;
import si.fri.t15.validators.InsertReasonValidator;
import si.fri.t15.validators.InsertResultValidator;
import si.fri.t15.validators.MedAddDiseaseValidator;
import si.fri.t15.validators.MedDelDiseaseValidator;
import si.fri.t15.validators.SelectDoctorValidator;
import si.fri.t15.validators.SignUpValidator;

@EnableGlobalMethodSecurity(securedEnabled = true)
//@Configuration
//@ComponentScan(basePackages={"si.fri.t15.dao", "si.fri.t15.base.security"})
@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/*
	 * This is the configuration for the messageSource bean, which when
	 * Autowired in a controller, gives us access to translation key values for
	 * the user's locale. NOTE: inside template files you can use a macro to get
	 * the value directly, example: #springMessage("login.title") will return
	 * the appropriate value according to the user's current locale.
	 */
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:translations/login/login");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}

	/*
	 * This is the configuration for the localeResolver bean, which uses a
	 * SessionLocaleResolver; that resolver allows us to remember the users'
	 * locale for the duration of their session.
	 */
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(new Locale("sl_SI"));
		return slr;
	}

	/*
	 * This is the configuration for the localeChangeInterceptor, which
	 * intercepts all requests from users before they arrive at the intended
	 * controller. If the request contains a request parameter with the name,
	 * defined in the configuration, then it uses the localeResolver bean to
	 * change the user's locale to the value specified in the request. Example:
	 * localhost:8080/login?lang=sl
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	/*
	 * Here we register the above localeChangeInterceptor with the
	 * InterceptorRegistry, so that requests go through it.
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsService();
	}
	
	@Bean
	public SignUpValidator signUpValidator() {
		return new SignUpValidator();
	}
	
	@Bean
	public CreateMedicalWorkerValidator createMedicalWorkerValidator() {
		return new CreateMedicalWorkerValidator();
	}
	
	@Bean
	public CreatePatientValidator createPatientValidator(){
		return new CreatePatientValidator();
	}
	
	@Bean
	public ChangePasswordValidator changePasswordValidator(){
		return new ChangePasswordValidator();
	}
	
	@Bean
	public ForgottenPasswordValidator forgottenPasswordValidator(){
		return new ForgottenPasswordValidator();
	}
	
	@Bean
	public DoctorProfileValidator doctorProfileValidator() {
		return new DoctorProfileValidator();
	}
	
	@Bean
	public SelectDoctorValidator selectDoctorValidator(){
		return new SelectDoctorValidator();
	}
	
	@Bean
	public InsertReasonValidator insertReasonValidator(){
		return new InsertReasonValidator();
	}
	
	@Bean
	public InsertDiseaseValidator insertDiseaseValidator(){
		return new InsertDiseaseValidator();
	}
	
	@Bean
	public InsertDietValidator insertDietValidator(){
		return new InsertDietValidator();
	}
	
	@Bean
	public InsertMedicineValidator insertMedicineValidator(){
		return new InsertMedicineValidator();
	}
	
	@Bean
	public InsertDiagnosisValidator insertDiagnosisValidator(){
		return new InsertDiagnosisValidator();
	}
	
	@Bean
	public InsertResultValidator insertResultValidator(){
		return new InsertResultValidator();
	}
	
	@Bean
	public InsAddDietValidator insAddDietValidator(){
		return new InsAddDietValidator();
	}
	
	@Bean
	public InsAddDiseaseValidator insAddDiseaseValidator(){
		return new InsAddDiseaseValidator();
	}
	
	@Bean
	public InsAddMedicineValidator insAddMedicineValidator(){
		return new InsAddMedicineValidator();
	}
	
	@Bean
	public InsDelMedicineValidator insDelMedicineValidator(){
		return new InsDelMedicineValidator();
	}
	
	@Bean
	public InsDelDiseaseValidator insDelDiseaseValidator(){
		return new InsDelDiseaseValidator();
	}
	
	@Bean
	public InsDelDietValidator insDelDietValidator(){
		return new InsDelDietValidator();
	}
	
	@Bean
	public MedAddDiseaseValidator medAddDiseaseValidator(){
		return new MedAddDiseaseValidator();
	}
	
	@Bean
	public MedDelDiseaseValidator medDelDiseaseValidator(){
		return new MedDelDiseaseValidator();
	}
	
	@Bean
	public AddDiseaseValidator addDiseaseValidator(){
		return new AddDiseaseValidator();
	}
	
	@Bean
	public AddDietValidator addDietValidator(){
		return new AddDietValidator();
	}
	
	@Bean
	public AddMedicineValidator addMedicineValidator(){
		return new AddMedicineValidator();
	}
	
	@Bean
	public AddMedCenterValidator addMedCenterValidator(){
		return new AddMedCenterValidator();
	}
	
	@Bean
	public DeleteDiseaseValidator deleteDiseaseValidator(){
		return new DeleteDiseaseValidator();
	}
	
	@Bean
	public DeleteDietValidator deleteDietValidator(){
		return new DeleteDietValidator();
	}
	
	@Bean
	public DeleteMedicineValidator deleteMedicineValidator(){
		return new DeleteMedicineValidator();
	}
	
	@Bean
	public DeleteMedCenterValidator deleteMedCenterValidator(){
		return new DeleteMedCenterValidator();
	}
	
	@Bean
	public SessionEndedListener sessionEndedListener() {
		return new SessionEndedListener();
	}
	
	@Bean
	public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
	    return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
	}
}