package com.sarel.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
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
import com.sarel.web.model.AcidoUrico;
import com.sarel.web.model.ColesterolTrigliceridos;
import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.model.ExpedienteLaboratorio;
import com.sarel.web.model.GlucosaPreYPost;
import com.sarel.web.model.Paciente;
import com.sarel.web.model.ParametroExportacion;
import com.sarel.web.model.PerfilLipidico;
import com.sarel.web.model.PruebaEmbarazo;
import com.sarel.web.model.PruebaSerologica;
import com.sarel.web.model.PruebaVDRL;
import com.sarel.web.model.Resultado;
import com.sarel.web.model.ResultadoLaboratorioVO;
import com.sarel.web.model.Sexo;
import com.sarel.web.model.TipoLaboratorio;
import com.sarel.web.model.User;
import com.sarel.web.model.UserProfile;
import com.sarel.web.service.AcidoUricoService;
import com.sarel.web.service.ColesterolTrigliceridosService;
import com.sarel.web.service.ExpedienteLaboratorioService;
import com.sarel.web.service.GlucosaPreYPostService;
import com.sarel.web.service.PacienteService;
import com.sarel.web.service.PerfilLipidicoService;
import com.sarel.web.service.PruebaEmbarazoService;
import com.sarel.web.service.PruebaSerologicaService;
import com.sarel.web.service.PruebaVDRLService;
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
	
	@ModelAttribute("laboratoristas")
    public List<User> initializeLaboratoristas() {
        return userService.findAllUsersByRol("LABORATORISTA");
    }
	
	@ModelAttribute("posiblesResultados")
    public List<Resultado> initializePosiblesResultados() {
		List<Resultado> posiblesResultados = new ArrayList<Resultado>();
		for (Resultado unResultado :Resultado.values()){
			posiblesResultados.add(unResultado);
		}
        return posiblesResultados;
    }
	
	@ModelAttribute("tiposLaboratorio")
    public List<TipoLaboratorio> initializeTiposLaboratorio() {
		List<TipoLaboratorio> tipos = new ArrayList<TipoLaboratorio>();
		for (TipoLaboratorio unTipo :TipoLaboratorio.values()){
			tipos.add(unTipo);
		}
        return tipos;
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
    PerfilLipidicoService perfilLipidicoService;
	
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
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("expediente", expediente);
		model.addAttribute("labs", labs);
		return "verExpedienteLaboratorio";
	}
	
	/* Fin Perfil Lipidico */
	
	@RequestMapping(value = { "/eliminarPERFIL_LIPIDICO" }, method = RequestMethod.GET)
	public String eliminarPerfilLipidico(ModelMap model, @RequestParam("idExpediente") int idExpediente, @RequestParam("idPERFIL_LIPIDICO") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PerfilLipidico nuevoPerfilLipidico = perfilLipidicoService.findById(idPerfil);
		nuevoPerfilLipidico.setEstado(EstadoResultadoLaboratorio.ELIMINADO);
		perfilLipidicoService.updatePerfilLipidico(nuevoPerfilLipidico);
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Perfil Lipido Numero: " + idPerfil + " eliminado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/consultarPERFIL_LIPIDICO" }, method = RequestMethod.GET)
	public String consultarPerfilLipidico(ModelMap model, @RequestParam("idPERFIL_LIPIDICO") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PerfilLipidico perfilLipidico = perfilLipidicoService.findById(idPerfil);
		model.addAttribute("perfilLipidico", perfilLipidico);
		ExpedienteLaboratorio expediente = expedienteService.findById(perfilLipidico.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("soloConsulta", true);
		return "addPerfilLipidico";
	}
	
	@RequestMapping(value = { "/editarPERFIL_LIPIDICO" }, method = RequestMethod.GET)
	public String editarPerfilLipidico(ModelMap model, @RequestParam("idPERFIL_LIPIDICO") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PerfilLipidico perfilLipidico = perfilLipidicoService.findById(idPerfil);
		ExpedienteLaboratorio expediente = expedienteService.findById(perfilLipidico.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("perfilLipidico", perfilLipidico);
		model.addAttribute("edit", true);
		return "addPerfilLipidico";
	}
	
	@RequestMapping(value = { "/editarPERFIL_LIPIDICO" }, method = RequestMethod.POST)
	public String modificarPerfilLipidico(ModelMap model, @Valid PerfilLipidico perfilLipidico, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addPerfilLipidico";
		}
		
		model.addAttribute("user", getPrincipal());
		perfilLipidicoService.updatePerfilLipidico(perfilLipidico);
		ExpedienteLaboratorio expediente = expedienteService.findById(perfilLipidico.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Perfil Lipido Numero: " + perfilLipidico.getId() + " editado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/agregarPERFIL_LIPIDICO" }, method = RequestMethod.GET)
	public String nuevoPerfilLipidico(ModelMap model, @RequestParam("idExpediente") int idExpediente) {
		model.addAttribute("user", getPrincipal());
		//model.addAttribute("laboratoristas", userService.findAllUsersByRol("LABORATORISTA"));
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		PerfilLipidico perfilLipidico = new PerfilLipidico();
		model.addAttribute("perfilLipidico", perfilLipidico);
		model.addAttribute("idExpediente", idExpediente);
		model.addAttribute("edit", false);
		return "addPerfilLipidico";
	}
	
	@RequestMapping(value = { "/agregarPERFIL_LIPIDICO" }, method = RequestMethod.POST)
	public String guardarPerfilLipidico(@Valid PerfilLipidico perfilLipidico, BindingResult result, 
			ModelMap model) {
		
		if (result.hasErrors()) {
			return "addPerfilLipidico";
		}
		
		perfilLipidicoService.savePerfilLipidico(perfilLipidico);
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expediente = expedienteService.findById(perfilLipidico.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Perfil Lipidico Numero: " + perfilLipidico.getId() + " creado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/imprimirPERFIL_LIPIDICO" }, method = RequestMethod.GET)
	public String imprimirPerfilLipidico(HttpServletResponse response, ModelMap model, @RequestParam("idPERFIL_LIPIDICO") int idPerfil) throws JRException, IOException {
		model.addAttribute("user", getPrincipal());
		PerfilLipidico perfilLipidico = perfilLipidicoService.findById(idPerfil);
		ExpedienteLaboratorio expediente = expedienteService.findById(perfilLipidico.getIdExpediente());
		model.addAttribute("expediente", expediente);
		
		List<ParametroExportacion> exportacion= new ArrayList<ParametroExportacion>();
		ParametroExportacion mpe = new ParametroExportacion();
		if(mpe.getParameters() == null){
			mpe.setParameters(new HashMap<String,Object>());
		}
		exportacion.add(mpe);
		JasperReport report = JasperCompileManager.compileReport(new ClassPathResource("jrxml/tuberculosis.jrxml").getInputStream());
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("logoSIGSA", new ClassPathResource("jrxml/logoSIGSA.png").getInputStream());
		params.put("logoMSP", new ClassPathResource("jrxml/mspas2.jpg").getInputStream());
		JasperPrint myJRprintReportObject = JasperFillManager.fillReport(report, params, new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(exportacion));
		
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=helloWorldReport.pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(myJRprintReportObject, outStream);
		
		return null;
	}
	
	/* Fin Perfil Lipidico */

	/* Inicia Prueba Embarazo */
	
	@Autowired
    PruebaEmbarazoService pruebaEmbarazoService;
	
	@RequestMapping(value = { "/eliminarPRUEBA_EMBARAZO" }, method = RequestMethod.GET)
	public String eliminarPruebaEmbarazo(ModelMap model, @RequestParam("idExpediente") int idExpediente, @RequestParam("idPRUEBA_EMBARAZO") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebaEmbarazo nuevoPruebaEmbarazo = pruebaEmbarazoService.findById(idPerfil);
		nuevoPruebaEmbarazo.setEstado(EstadoResultadoLaboratorio.ELIMINADO);
		pruebaEmbarazoService.updatePruebaEmbarazo(nuevoPruebaEmbarazo);
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Prueba de Embarazo Numero: " + idPerfil + " eliminado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/consultarPRUEBA_EMBARAZO" }, method = RequestMethod.GET)
	public String consultarPruebaEmbarazo(ModelMap model, @RequestParam("idPRUEBA_EMBARAZO") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebaEmbarazo pruebaEmbarazo = pruebaEmbarazoService.findById(idPerfil);
		model.addAttribute("pruebaEmbarazo", pruebaEmbarazo);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaEmbarazo.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("soloConsulta", true);
		return "addPruebaEmbarazo";
	}
	
	@RequestMapping(value = { "/editarPRUEBA_EMBARAZO" }, method = RequestMethod.GET)
	public String editarPruebaEmbarazo(ModelMap model, @RequestParam("idPRUEBA_EMBARAZO") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebaEmbarazo pruebaEmbarazo = pruebaEmbarazoService.findById(idPerfil);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaEmbarazo.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("pruebaEmbarazo", pruebaEmbarazo);
		model.addAttribute("edit", true);
		return "addPruebaEmbarazo";
	}
	
	@RequestMapping(value = { "/editarPRUEBA_EMBARAZO" }, method = RequestMethod.POST)
	public String modificarPruebaEmbarazo(ModelMap model, @Valid PruebaEmbarazo pruebaEmbarazo, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addPruebaEmbarazo";
		}
		
		model.addAttribute("user", getPrincipal());
		pruebaEmbarazoService.updatePruebaEmbarazo(pruebaEmbarazo);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaEmbarazo.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Prueba de Embarazo Numero: " + pruebaEmbarazo.getId() + " editado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/agregarPRUEBA_EMBARAZO" }, method = RequestMethod.GET)
	public String nuevoPruebaEmbarazo(ModelMap model, @RequestParam("idExpediente") int idExpediente) {
		model.addAttribute("user", getPrincipal());
		//model.addAttribute("laboratoristas", userService.findAllUsersByRol("LABORATORISTA"));
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		PruebaEmbarazo pruebaEmbarazo = new PruebaEmbarazo();
		model.addAttribute("pruebaEmbarazo", pruebaEmbarazo);
		model.addAttribute("idExpediente", idExpediente);
		model.addAttribute("edit", false);
		return "addPruebaEmbarazo";
	}
	
	@RequestMapping(value = { "/agregarPRUEBA_EMBARAZO" }, method = RequestMethod.POST)
	public String guardarPruebaEmbarazo(@Valid PruebaEmbarazo pruebaEmbarazo, BindingResult result, 
			ModelMap model) {
		
		if (result.hasErrors()) {
			return "addPruebaEmbarazo";
		}
		
		pruebaEmbarazoService.savePruebaEmbarazo(pruebaEmbarazo);
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaEmbarazo.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Resultado de Prueba de Embarazo Numero: " + pruebaEmbarazo.getId() + " creado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	/* Fin Prueba Embarazo */
	
	
	/* Fin Acido Urico */

	@Autowired
    	AcidoUricoService acidoUricoService;
	
	@RequestMapping(value = { "/eliminarACIDO_URICO" }, method = RequestMethod.GET)
	public String eliminarAcidoUrico(ModelMap model, @RequestParam("idExpediente") int idExpediente, @RequestParam("idACIDO_URICO") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		AcidoUrico nuevoAcidoUrico = acidoUricoService.findById(idPerfil);
		nuevoAcidoUrico.setEstado(EstadoResultadoLaboratorio.ELIMINADO);
		acidoUricoService.updateAcidoUrico(nuevoAcidoUrico);
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Acido Urico Numero: " + idPerfil + " eliminado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/consultarACIDO_URICO" }, method = RequestMethod.GET)
	public String consultarAcidoUrico(ModelMap model, @RequestParam("idACIDO_URICO") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		AcidoUrico acidoUrico = acidoUricoService.findById(idPerfil);
		model.addAttribute("acidoUrico", acidoUrico);
		ExpedienteLaboratorio expediente = expedienteService.findById(acidoUrico.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("soloConsulta", true);
		return "addAcidoUrico";
	}
	
	@RequestMapping(value = { "/editarACIDO_URICO" }, method = RequestMethod.GET)
	public String editarAcidoUrico(ModelMap model, @RequestParam("idACIDO_URICO") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		AcidoUrico acidoUrico = acidoUricoService.findById(idPerfil);
		ExpedienteLaboratorio expediente = expedienteService.findById(acidoUrico.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("acidoUrico", acidoUrico);
		model.addAttribute("edit", true);
		return "addAcidoUrico";
	}
	
	@RequestMapping(value = { "/editarACIDO_URICO" }, method = RequestMethod.POST)
	public String modificarAcidoUrico(ModelMap model, @Valid AcidoUrico acidoUrico, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addAcidoUrico";
		}
		
		model.addAttribute("user", getPrincipal());
		acidoUricoService.updateAcidoUrico(acidoUrico);
		ExpedienteLaboratorio expediente = expedienteService.findById(acidoUrico.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Acido Urico Numero: " + acidoUrico.getId() + " editado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/agregarACIDO_URICO" }, method = RequestMethod.GET)
	public String nuevoAcidoUrico(ModelMap model, @RequestParam("idExpediente") int idExpediente) {
		model.addAttribute("user", getPrincipal());
		//model.addAttribute("laboratoristas", userService.findAllUsersByRol("LABORATORISTA"));
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		AcidoUrico acidoUrico = new AcidoUrico();
		model.addAttribute("acidoUrico", acidoUrico);
		model.addAttribute("idExpediente", idExpediente);
		model.addAttribute("edit", false);
		return "addAcidoUrico";
	}
	
	@RequestMapping(value = { "/agregarACIDO_URICO" }, method = RequestMethod.POST)
	public String guardarAcidoUrico(@Valid AcidoUrico acidoUrico, BindingResult result, 
			ModelMap model) {
		
		if (result.hasErrors()) {
			return "addAcidoUrico";
		}
		
		acidoUricoService.saveAcidoUrico(acidoUrico);
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expediente = expedienteService.findById(acidoUrico.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Acido Urico Numero: " + acidoUrico.getId() + " creado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	/* Fin Acido Urico */
	
	/* Fin Prueba VDRL */

	@Autowired
    PruebaVDRLService pruebaVDRLService;
	
	@RequestMapping(value = { "/eliminarPRUEBA_VDRL" }, method = RequestMethod.GET)
	public String eliminarPruebaVDRL(ModelMap model, @RequestParam("idExpediente") int idExpediente, @RequestParam("idPRUEBA_VDRL") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebaVDRL nuevoPruebaVDRL = pruebaVDRLService.findById(idPerfil);
		nuevoPruebaVDRL.setEstado(EstadoResultadoLaboratorio.ELIMINADO);
		pruebaVDRLService.updatePruebaVDRL(nuevoPruebaVDRL);
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Prueba VDRL Numero: " + idPerfil + " eliminado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/consultarPRUEBA_VDRL" }, method = RequestMethod.GET)
	public String consultarPruebaVDRL(ModelMap model, @RequestParam("idPRUEBA_VDRL") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebaVDRL pruebaVDRL = pruebaVDRLService.findById(idPerfil);
		model.addAttribute("pruebaVDRL", pruebaVDRL);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaVDRL.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("soloConsulta", true);
		return "addPruebaVDRL";
	}
	
	@RequestMapping(value = { "/editarPRUEBA_VDRL" }, method = RequestMethod.GET)
	public String editarPruebaVDRL(ModelMap model, @RequestParam("idPRUEBA_VDRL") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebaVDRL pruebaVDRL = pruebaVDRLService.findById(idPerfil);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaVDRL.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("pruebaVDRL", pruebaVDRL);
		model.addAttribute("edit", true);
		return "addPruebaVDRL";
	}
	
	@RequestMapping(value = { "/editarPRUEBA_VDRL" }, method = RequestMethod.POST)
	public String modificarPruebaVDRL(ModelMap model, @Valid PruebaVDRL pruebaVDRL, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addPruebaVDRL";
		}
		
		model.addAttribute("user", getPrincipal());
		pruebaVDRLService.updatePruebaVDRL(pruebaVDRL);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaVDRL.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Prueba VDRL Numero: " + pruebaVDRL.getId() + " editado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/agregarPRUEBA_VDRL" }, method = RequestMethod.GET)
	public String nuevoPruebaVDRL(ModelMap model, @RequestParam("idExpediente") int idExpediente) {
		model.addAttribute("user", getPrincipal());
		//model.addAttribute("laboratoristas", userService.findAllUsersByRol("LABORATORISTA"));
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		PruebaVDRL pruebaVDRL = new PruebaVDRL();
		model.addAttribute("pruebaVDRL", pruebaVDRL);
		model.addAttribute("idExpediente", idExpediente);
		model.addAttribute("edit", false);
		return "addPruebaVDRL";
	}
	
	@RequestMapping(value = { "/agregarPRUEBA_VDRL" }, method = RequestMethod.POST)
	public String guardarPruebaVDRL(@Valid PruebaVDRL pruebaVDRL, BindingResult result, 
			ModelMap model) {
		
		if (result.hasErrors()) {
			return "addPruebaVDRL";
		}
		
		pruebaVDRLService.savePruebaVDRL(pruebaVDRL);
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaVDRL.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Prueba VDRL Numero: " + pruebaVDRL.getId() + " creado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	/* Fin Prueba VDRL */
	
	
	/* Inicio Colesterol-Trigliceridos */

	@Autowired
    ColesterolTrigliceridosService colesterolTrigliceridosService;
	
	@RequestMapping(value = { "/eliminarCOLESTEROL_TRIGLICERIDOS" }, method = RequestMethod.GET)
	public String eliminarColesterolTrigliceridos(ModelMap model, @RequestParam("idExpediente") int idExpediente, @RequestParam("idCOLESTEROL_TRIGLICERIDOS") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		ColesterolTrigliceridos nuevoColesterolTrigliceridos = colesterolTrigliceridosService.findById(idPerfil);
		nuevoColesterolTrigliceridos.setEstado(EstadoResultadoLaboratorio.ELIMINADO);
		colesterolTrigliceridosService.updateColesterolTrigliceridos(nuevoColesterolTrigliceridos);
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Cho-Tri Numero: " + idPerfil + " eliminado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/consultarCOLESTEROL_TRIGLICERIDOS" }, method = RequestMethod.GET)
	public String consultarColesterolTrigliceridos(ModelMap model, @RequestParam("idCOLESTEROL_TRIGLICERIDOS") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		ColesterolTrigliceridos colesterolTrigliceridos = colesterolTrigliceridosService.findById(idPerfil);
		model.addAttribute("colesterolTrigliceridos", colesterolTrigliceridos);
		ExpedienteLaboratorio expediente = expedienteService.findById(colesterolTrigliceridos.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("soloConsulta", true);
		return "addColesterolTrigliceridos";
	}
	
	@RequestMapping(value = { "/editarCOLESTEROL_TRIGLICERIDOS" }, method = RequestMethod.GET)
	public String editarColesterolTrigliceridos(ModelMap model, @RequestParam("idCOLESTEROL_TRIGLICERIDOS") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		ColesterolTrigliceridos colesterolTrigliceridos = colesterolTrigliceridosService.findById(idPerfil);
		ExpedienteLaboratorio expediente = expedienteService.findById(colesterolTrigliceridos.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("colesterolTrigliceridos", colesterolTrigliceridos);
		model.addAttribute("edit", true);
		return "addColesterolTrigliceridos";
	}
	
	@RequestMapping(value = { "/editarCOLESTEROL_TRIGLICERIDOS" }, method = RequestMethod.POST)
	public String modificarColesterolTrigliceridos(ModelMap model, @Valid ColesterolTrigliceridos colesterolTrigliceridos, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addColesterolTrigliceridos";
		}
		
		model.addAttribute("user", getPrincipal());
		colesterolTrigliceridosService.updateColesterolTrigliceridos(colesterolTrigliceridos);
		ExpedienteLaboratorio expediente = expedienteService.findById(colesterolTrigliceridos.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Cho-Tri Numero: " + colesterolTrigliceridos.getId() + " editado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/agregarCOLESTEROL_TRIGLICERIDOS" }, method = RequestMethod.GET)
	public String nuevoColesterolTrigliceridos(ModelMap model, @RequestParam("idExpediente") int idExpediente) {
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		ColesterolTrigliceridos colesterolTrigliceridos = new ColesterolTrigliceridos();
		model.addAttribute("colesterolTrigliceridos", colesterolTrigliceridos);
		model.addAttribute("idExpediente", idExpediente);
		model.addAttribute("edit", false);
		return "addColesterolTrigliceridos";
	}
	
	@RequestMapping(value = { "/agregarCOLESTEROL_TRIGLICERIDOS" }, method = RequestMethod.POST)
	public String guardarColesterolTrigliceridos(@Valid ColesterolTrigliceridos colesterolTrigliceridos, BindingResult result, 
			ModelMap model) {
		
		if (result.hasErrors()) {
			return "addColesterolTrigliceridos";
		}
		
		colesterolTrigliceridosService.saveColesterolTrigliceridos(colesterolTrigliceridos);
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expediente = expedienteService.findById(colesterolTrigliceridos.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Cho-Tri Numero: " + colesterolTrigliceridos.getId() + " creado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	/* Fin Colesterol-Trigliceridos */
	
	/* Fin GlucosaPreYPost */

	@Autowired
    GlucosaPreYPostService glucosaPreYPostService;
	
	@RequestMapping(value = { "/eliminarGLUCOSA_PRE_Y_POST" }, method = RequestMethod.GET)
	public String eliminarGlucosaPreYPost(ModelMap model, @RequestParam("idExpediente") int idExpediente, @RequestParam("idGLUCOSA_PRE_Y_POST") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		GlucosaPreYPost nuevoGlucosaPreYPost = glucosaPreYPostService.findById(idPerfil);
		nuevoGlucosaPreYPost.setEstado(EstadoResultadoLaboratorio.ELIMINADO);
		glucosaPreYPostService.updateGlucosaPreYPost(nuevoGlucosaPreYPost);
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Glucosa Pre-Pp Numero: " + idPerfil + " eliminado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/consultarGLUCOSA_PRE_Y_POST" }, method = RequestMethod.GET)
	public String consultarGlucosaPreYPost(ModelMap model, @RequestParam("idGLUCOSA_PRE_Y_POST") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		GlucosaPreYPost glucosaPreYPost = glucosaPreYPostService.findById(idPerfil);
		model.addAttribute("glucosaPreYPost", glucosaPreYPost);
		ExpedienteLaboratorio expediente = expedienteService.findById(glucosaPreYPost.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("soloConsulta", true);
		return "addGlucosaPreYPost";
	}
	
	@RequestMapping(value = { "/editarGLUCOSA_PRE_Y_POST" }, method = RequestMethod.GET)
	public String editarGlucosaPreYPost(ModelMap model, @RequestParam("idGLUCOSA_PRE_Y_POST") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		GlucosaPreYPost glucosaPreYPost = glucosaPreYPostService.findById(idPerfil);
		ExpedienteLaboratorio expediente = expedienteService.findById(glucosaPreYPost.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("glucosaPreYPost", glucosaPreYPost);
		model.addAttribute("edit", true);
		return "addGlucosaPreYPost";
	}
	
	@RequestMapping(value = { "/editarGLUCOSA_PRE_Y_POST" }, method = RequestMethod.POST)
	public String modificarGlucosaPreYPost(ModelMap model, @Valid GlucosaPreYPost glucosaPreYPost, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addGlucosaPreYPost";
		}
		
		model.addAttribute("user", getPrincipal());
		glucosaPreYPostService.updateGlucosaPreYPost(glucosaPreYPost);
		ExpedienteLaboratorio expediente = expedienteService.findById(glucosaPreYPost.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Glucosa Pre-Pp Numero: " + glucosaPreYPost.getId() + " editado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/agregarGLUCOSA_PRE_Y_POST" }, method = RequestMethod.GET)
	public String nuevoGlucosaPreYPost(ModelMap model, @RequestParam("idExpediente") int idExpediente) {
		model.addAttribute("user", getPrincipal());
		//model.addAttribute("laboratoristas", userService.findAllUsersByRol("LABORATORISTA"));
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		GlucosaPreYPost glucosaPreYPost = new GlucosaPreYPost();
		model.addAttribute("glucosaPreYPost", glucosaPreYPost);
		model.addAttribute("idExpediente", idExpediente);
		model.addAttribute("edit", false);
		return "addGlucosaPreYPost";
	}
	
	@RequestMapping(value = { "/agregarGLUCOSA_PRE_Y_POST" }, method = RequestMethod.POST)
	public String guardarGlucosaPreYPost(@Valid GlucosaPreYPost glucosaPreYPost, BindingResult result, 
			ModelMap model) {
		
		if (result.hasErrors()) {
			return "addGlucosaPreYPost";
		}
		
		glucosaPreYPostService.saveGlucosaPreYPost(glucosaPreYPost);
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expediente = expedienteService.findById(glucosaPreYPost.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Glucosa Pre-Pp Numero: " + glucosaPreYPost.getId() + " creado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	/* Fin GlucosaPreYPost */
	
	
	/* Fin PruebaSerologica */

	@Autowired
    PruebaSerologicaService pruebaSerologicaService;
	
	@RequestMapping(value = { "/eliminarPRUEBA_SEROLOGICA" }, method = RequestMethod.GET)
	public String eliminarPruebaSerologica(ModelMap model, @RequestParam("idExpediente") int idExpediente, @RequestParam("idPRUEBA_SEROLOGICA") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebaSerologica nuevoPruebaSerologica = pruebaSerologicaService.findById(idPerfil);
		nuevoPruebaSerologica.setEstado(EstadoResultadoLaboratorio.ELIMINADO);
		pruebaSerologicaService.updatePruebaSerologica(nuevoPruebaSerologica);
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Pruebas Serologicas Numero: " + idPerfil + " eliminado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/consultarPRUEBA_SEROLOGICA" }, method = RequestMethod.GET)
	public String consultarPruebaSerologica(ModelMap model, @RequestParam("idPRUEBA_SEROLOGICA") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebaSerologica pruebaSerologica = pruebaSerologicaService.findById(idPerfil);
		model.addAttribute("pruebaSerologica", pruebaSerologica);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaSerologica.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("soloConsulta", true);
		return "addPruebaSerologica";
	}
	
	@RequestMapping(value = { "/editarPRUEBA_SEROLOGICA" }, method = RequestMethod.GET)
	public String editarPruebaSerologica(ModelMap model, @RequestParam("idPRUEBA_SEROLOGICA") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebaSerologica pruebaSerologica = pruebaSerologicaService.findById(idPerfil);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaSerologica.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("pruebaSerologica", pruebaSerologica);
		model.addAttribute("edit", true);
		return "addPruebaSerologica";
	}
	
	@RequestMapping(value = { "/editarPRUEBA_SEROLOGICA" }, method = RequestMethod.POST)
	public String modificarPruebaSerologica(ModelMap model, @Valid PruebaSerologica pruebaSerologica, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addPruebaSerologica";
		}
		
		model.addAttribute("user", getPrincipal());
		pruebaSerologicaService.updatePruebaSerologica(pruebaSerologica);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaSerologica.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Pruebas Serologicas Numero: " + pruebaSerologica.getId() + " editado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/agregarPRUEBA_SEROLOGICA" }, method = RequestMethod.GET)
	public String nuevoPruebaSerologica(ModelMap model, @RequestParam("idExpediente") int idExpediente) {
		model.addAttribute("user", getPrincipal());
		//model.addAttribute("laboratoristas", userService.findAllUsersByRol("LABORATORISTA"));
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		PruebaSerologica pruebaSerologica = new PruebaSerologica();
		model.addAttribute("pruebaSerologica", pruebaSerologica);
		model.addAttribute("idExpediente", idExpediente);
		model.addAttribute("edit", false);
		return "addPruebaSerologica";
	}
	
	@RequestMapping(value = { "/agregarPRUEBA_SEROLOGICA" }, method = RequestMethod.POST)
	public String guardarPruebaSerologica(@Valid PruebaSerologica pruebaSerologica, BindingResult result, 
			ModelMap model) {
		
		if (result.hasErrors()) {
			return "addPruebaSerologica";
		}
		
		if(pruebaSerologica.getResultadoAntiEstreptolisinaO().getName().equals("NEGATIVO") && pruebaSerologica.getResultadoFactorReumatoide().getName().equals("NEGATIVO") && pruebaSerologica.getResultadoProteinaCReactiva().getName().equals("NEGATIVO")){
			model.addAttribute("alerta", "Debe ingresar al menos uno resultado para poder guardar...");
			return "addPruebaSerologica";
		}
		
		pruebaSerologicaService.savePruebaSerologica(pruebaSerologica);
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaSerologica.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Pruebas Serologicas Numero: " + pruebaSerologica.getId() + " creado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	/* Fin PruebaSerologica */
	
	
	
	
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
	
	private List<ResultadoLaboratorioVO> obtenerTodosLosLaboratoriosPorIdExpediente(int idExpediente){
		List<ResultadoLaboratorioVO> resultados = new ArrayList<ResultadoLaboratorioVO>();
		
		for(TipoLaboratorio unTipo : TipoLaboratorio.values()){
			if(unTipo.getName().equals(TipoLaboratorio.PERFIL_LIPIDICO.getName())){
				List<PerfilLipidico> lipidicos = perfilLipidicoService.findByIdExpediente(idExpediente);
				for(PerfilLipidico unLipido : lipidicos){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLipido.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLipido.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLipido.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLipido.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.PRUEBA_EMBARAZO.getName())){
				List<PruebaEmbarazo> pruebaEmbarazo = pruebaEmbarazoService.findByIdExpediente(idExpediente);
				for(PruebaEmbarazo unaPruebaEmbarazo : pruebaEmbarazo){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unaPruebaEmbarazo.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unaPruebaEmbarazo.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unaPruebaEmbarazo.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unaPruebaEmbarazo.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.ACIDO_URICO.getName())){
				List<AcidoUrico> acidos = acidoUricoService.findByIdExpediente(idExpediente);
				for(AcidoUrico unAcidoUrico : acidos){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unAcidoUrico.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unAcidoUrico.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unAcidoUrico.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unAcidoUrico.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.PRUEBA_VDRL.getName())){
				List<PruebaVDRL> VDRLs = pruebaVDRLService.findByIdExpediente(idExpediente);
				for(PruebaVDRL unVDRL : VDRLs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unVDRL.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unVDRL.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unVDRL.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unVDRL.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.COLESTEROL_TRIGLICERIDOS.getName())){
				List<ColesterolTrigliceridos> labs = colesterolTrigliceridosService.findByIdExpediente(idExpediente);
				for(ColesterolTrigliceridos unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.GLUCOSA_PRE_Y_POST.getName())){
				List<GlucosaPreYPost> labs = glucosaPreYPostService.findByIdExpediente(idExpediente);
				for(GlucosaPreYPost unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.PRUEBA_SEROLOGICA.getName())){
				List<PruebaSerologica> labs = pruebaSerologicaService.findByIdExpediente(idExpediente);
				for(PruebaSerologica unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}
		}
		return resultados;
	}

}
