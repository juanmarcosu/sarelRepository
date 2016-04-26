package com.sarel.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sarel.web.model.Employee;
import com.sarel.web.service.EmployeeService;
import com.sarel.web.model.ExpedienteLaboratorio;
import com.sarel.web.model.Paciente;
import com.sarel.web.model.PerfilLipido;
import com.sarel.web.model.Sexo;
import com.sarel.web.model.User;
import com.sarel.web.model.UserProfile;
import com.sarel.web.service.ExpedienteLaboratorioService;
import com.sarel.web.service.PacienteService;
import com.sarel.web.service.PerfilLipidoService;
import com.sarel.web.service.UserProfileService;
import com.sarel.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Controller
public class AppController {
	
	@Autowired
    UserProfileService userProfileService;
     
    @Autowired
    UserService userService;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		model.addAttribute("greeting", "Hola, Bienvenido");
		model.addAttribute("user", getPrincipal());
		return "welcome";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "admin";
	}

	@RequestMapping(value = "/db", method = RequestMethod.GET)
	public String dbaPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "dba";
	}

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "accessDenied";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
	
	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public String newRegistration(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return "newuser";
    }
	
	/*
     * This method will be called on form submission, handling POST request It
     * also validates the user input
     */
    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String saveRegistration(@Valid User user,
            BindingResult result, ModelMap model) {
 
        if (result.hasErrors()) {
            System.out.println("Se encontraron errores");
            return "newuser";
        }
        userService.save(user);
         
        System.out.println("First Name : "+user.getFirstName());
        System.out.println("Last Name : "+user.getLastName());
        System.out.println("SSO ID : "+user.getSsoId());
        System.out.println("Password : "+user.getPassword());
        System.out.println("Email : "+user.getEmail());
        System.out.println("Checking UsrProfiles....");
        if(user.getUserProfiles()!=null){
            for(UserProfile profile : user.getUserProfiles()){
                System.out.println("Profile : "+ profile.getType());
            }
        }
         
        model.addAttribute("success", "Usuario " + user.getFirstName() + " a sido creado exitosamente");
        return "registrationsuccess";
    }

	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	@ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }

	@Autowired
	EmployeeService service;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String listEmployees(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		List<Employee> employees = service.findAllEmployees();
		model.addAttribute("employees", employees);
		return "allemployees";
	}
	
	@Autowired
    PacienteService pacienteService;
	
	@RequestMapping(value = { "/buscarPaciente" }, method = RequestMethod.GET)
	public String lista20Pacientes(ModelMap model, @RequestParam("nombre") String pNombre, @RequestParam("apellido") String pApellido
			, @RequestParam("carne") String pCarne ) {
		model.addAttribute("user", getPrincipal());
		boolean parameters = false;
		List<Paciente> pacientes = null;
		Map<String, Object> params = new HashMap<String, Object>();
		if(pNombre!=null && !pNombre.isEmpty()){
			params.put("nombre", pNombre);
			parameters = true;
		}
		if(pApellido!=null && !pApellido.isEmpty()){
			params.put("apellido", pApellido);
			parameters = true;
		}
		if(pCarne!=null && !pCarne.isEmpty()){
			params.put("carne", pCarne);
			parameters = true;
		}
		if(parameters){
			pacientes = pacienteService.findByCriteria(params);
		}
		model.addAttribute("pacientes", pacientes);
		return "buscarPaciente";
	}
	
	@Autowired
    PerfilLipidoService perfilLipidoService;
	
	@Autowired
    ExpedienteLaboratorioService expedienteService;
	
	@RequestMapping(value = { "/verExpedienteLaboratorio" }, method = RequestMethod.GET)
	public String verExpedientePersona(@RequestParam("idPaciente") Integer idPaciente, ModelMap model) {
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expediente = expedienteService.findByIdPaciente(idPaciente);
		if(expediente == null){
			Paciente paciente = pacienteService.findById(idPaciente);
			ExpedienteLaboratorio expedienteTemp = new ExpedienteLaboratorio();
			expedienteTemp.setIdPaciente(paciente.getIdPaciente());
			expedienteTemp.setNombres(paciente.getNombre());
			expedienteTemp.setApellidos(paciente.getApellido());
			expedienteTemp.setFechaNacimiento(paciente.getFechaNac());
			expedienteTemp.setCarne(paciente.getCarne());
			expedienteTemp.setDireccion(paciente.getDireccion());
			expedienteTemp.setTelefono(paciente.getTelefono());
			expedienteTemp.setMovil(paciente.getMovil());
			expedienteTemp.setEmail(paciente.getEmail());
			if(paciente.getSexo()==1){
				expedienteTemp.setSexo(Sexo.HOMBRE);
			}else{
				expedienteTemp.setSexo(Sexo.MUJER);
			}
			expedienteService.saveExpedienteLaboratorio(expedienteTemp);
			expediente = expedienteService.findByIdPaciente(idPaciente);
		}
		List<PerfilLipido> labs = perfilLipidoService.findByIdExpediente(expediente.getId());
		model.addAttribute("expediente", expediente);
		model.addAttribute("labs", labs);
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/eliminarPerfilLipido" }, method = RequestMethod.GET)
	public String eliminarPerfilLipido(ModelMap model, @RequestParam("idExpediente") int idExpediente, @RequestParam("idPerfilLipido") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		perfilLipidoService.deletePerfilLipido(perfilLipidoService.findById(idPerfil));
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		List<PerfilLipido> labs = perfilLipidoService.findByIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/consultarPerfilLipido" }, method = RequestMethod.GET)
	public String consultarPerfilLipido(ModelMap model, @RequestParam("idPerfilLipido") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PerfilLipido perfilLipido = perfilLipidoService.findById(idPerfil);
		model.addAttribute("perfilLipido", perfilLipido);
		model.addAttribute("soloConsulta", true);
		return "addPerfilLipido";
	}
	
	@RequestMapping(value = { "/editarPerfilLipido" }, method = RequestMethod.GET)
	public String editarPerfilLipido(ModelMap model, @RequestParam("idPerfilLipido") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PerfilLipido perfilLipido = perfilLipidoService.findById(idPerfil);
		model.addAttribute("perfilLipido", perfilLipido);
		model.addAttribute("edit", true);
		return "addPerfilLipido";
	}
	
	@RequestMapping(value = { "/editarPerfilLipido" }, method = RequestMethod.POST)
	public String modificarPerfilLipido(ModelMap model, @Valid PerfilLipido perfilLipido) {
		model.addAttribute("user", getPrincipal());
		perfilLipidoService.updatePerfilLipido(perfilLipido);
		ExpedienteLaboratorio expediente = expedienteService.findById(perfilLipido.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<PerfilLipido> labs = perfilLipidoService.findByIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/agregarPerfilLipido" }, method = RequestMethod.GET)
	public String nuevoPerfilLipido(ModelMap model, @RequestParam("idExpediente") int idExpediente) {
		model.addAttribute("user", getPrincipal());
		PerfilLipido perfilLipido = new PerfilLipido();
		model.addAttribute("perfilLipido", perfilLipido);
		model.addAttribute("idExpediente", idExpediente);
		model.addAttribute("edit", false);
		return "addPerfilLipido";
	}
	
	@RequestMapping(value = { "/agregarPerfilLipido" }, method = RequestMethod.POST)
	public String guardarPerfilLipido(@Valid PerfilLipido perfilLipido, 
			ModelMap model) {
		
		perfilLipidoService.savePerfilLipido(perfilLipido);
		model.addAttribute("user", getPrincipal());
		model.addAttribute("success", "Laboratorio Perfil Lipido Numero: " + perfilLipido.getId() + " creado Exitosamente");
		return "success";
	}

	/*
	 * This method will list all existing employees.
	 */
	/*@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listEmployees(ModelMap model) {

		List<Employee> employees = service.findAllEmployees();
		model.addAttribute("employees", employees);
		return "allemployees";
	}*/

	/*
	 * This method will provide the medium to add a new employee.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newEmployee(ModelMap model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		model.addAttribute("edit", false);
		return "registration";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveEmployee(@Valid Employee employee, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [ssn] should be implementing custom @Unique annotation 
		 * and applying it on field [ssn] of Model class [Employee].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!service.isEmployeeSsnUnique(employee.getId(), employee.getSsn())){
			FieldError ssnError =new FieldError("employee","ssn",messageSource.getMessage("non.unique.ssn", new String[]{employee.getSsn()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "registration";
		}
		
		service.saveEmployee(employee);

		model.addAttribute("success", "Estudiante " + employee.getName() + " agregado Exitosamente");
		return "success";
	}


	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/edit-{ssn}-employee" }, method = RequestMethod.GET)
	public String editEmployee(@PathVariable String ssn, ModelMap model) {
		Employee employee = service.findEmployeeBySsn(ssn);
		model.addAttribute("employee", employee);
		model.addAttribute("edit", true);
		return "registration";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{ssn}-employee" }, method = RequestMethod.POST)
	public String updateEmployee(@Valid Employee employee, BindingResult result,
			ModelMap model, @PathVariable String ssn) {

		if (result.hasErrors()) {
			return "registration";
		}

		if(!service.isEmployeeSsnUnique(employee.getId(), employee.getSsn())){
			FieldError ssnError =new FieldError("employee","ssn",messageSource.getMessage("non.unique.ssn", new String[]{employee.getSsn()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "registration";
		}

		service.updateEmployee(employee);

		model.addAttribute("success", "Estudiante " + employee.getName()	+ " actualizado Exitosamente...");
		return "success";
	}

	
	/*
	 * This method will delete an employee by it's SSN value.
	 */
	@RequestMapping(value = { "/delete-{ssn}-employee" }, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable String ssn) {
		service.deleteEmployeeBySsn(ssn);
		return "redirect:/list";
	}

}
