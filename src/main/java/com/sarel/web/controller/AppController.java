package com.sarel.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sarel.web.model.AcidoUrico;
import com.sarel.web.model.AntigenoProstatico;
import com.sarel.web.model.AspectoHeces;
import com.sarel.web.model.AspectoOrina;
import com.sarel.web.model.CantidadPresente;
import com.sarel.web.model.ColesterolTrigliceridos;
import com.sarel.web.model.ColorOrina;
import com.sarel.web.model.Employee;
import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.model.ExamenHeces;
import com.sarel.web.model.ExamenOrina;
import com.sarel.web.model.ExpedienteLaboratorio;
import com.sarel.web.model.GlucosaPreYPost;
import com.sarel.web.model.HelicobacterPylori;
import com.sarel.web.model.HematologiaCompleta;
import com.sarel.web.model.HemoglobinaGlucosa;
import com.sarel.web.model.Paciente;
import com.sarel.web.model.PacienteVO;
import com.sarel.web.model.ParametroExportacion;
import com.sarel.web.model.PerfilLipidico;
import com.sarel.web.model.PruebaAlfafetoproteina;
import com.sarel.web.model.PruebaDengue;
import com.sarel.web.model.PruebaEmbarazo;
import com.sarel.web.model.PruebaPCR;
import com.sarel.web.model.PruebaSerologica;
import com.sarel.web.model.PruebaTSH;
import com.sarel.web.model.PruebaTiroidea;
import com.sarel.web.model.PruebaVDRL;
import com.sarel.web.model.PruebaVIH;
import com.sarel.web.model.PruebasHematologicas;
import com.sarel.web.model.PruebasRenales;
import com.sarel.web.model.Resultado;
import com.sarel.web.model.ResultadoLaboratorioVO;
import com.sarel.web.model.Sexo;
import com.sarel.web.model.TipoDengue;
import com.sarel.web.model.TipoLaboratorio;
import com.sarel.web.model.User;
import com.sarel.web.model.UserProfile;
import com.sarel.web.service.AcidoUricoService;
import com.sarel.web.service.AntigenoProstaticoService;
import com.sarel.web.service.ColesterolTrigliceridosService;
import com.sarel.web.service.EmployeeService;
import com.sarel.web.service.ExamenHecesService;
import com.sarel.web.service.ExamenOrinaService;
import com.sarel.web.service.ExpedienteLaboratorioService;
import com.sarel.web.service.GlucosaPreYPostService;
import com.sarel.web.service.HelicobacterPyloriService;
import com.sarel.web.service.HematologiaCompletaService;
import com.sarel.web.service.HemoglobinaGlucosaService;
import com.sarel.web.service.PacienteService;
import com.sarel.web.service.PerfilLipidicoService;
import com.sarel.web.service.PruebaAlfafetoproteinaService;
import com.sarel.web.service.PruebaDengueService;
import com.sarel.web.service.PruebaEmbarazoService;
import com.sarel.web.service.PruebaPCRService;
import com.sarel.web.service.PruebaSerologicaService;
import com.sarel.web.service.PruebaTSHService;
import com.sarel.web.service.PruebaTiroideaService;
import com.sarel.web.service.PruebaVDRLService;
import com.sarel.web.service.PruebaVIHService;
import com.sarel.web.service.PruebasHematologicasService;
import com.sarel.web.service.PruebasRenalesService;
import com.sarel.web.service.UserProfileService;
import com.sarel.web.service.UserService;
import com.sarel.web.util.FormatoExportacionPruebaLaboratorio;
import com.sarel.web.util.UtilsSarel;

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
		model.addAttribute("alert", "ACESSO DENEGADO... Consulte a su administrador");
		return "welcome";
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
            return "newuser";
        }
        userService.save(user);
        model.addAttribute("user", getPrincipal());
        model.addAttribute("message", "Usuario " + user.getSsoId() + " a sido creado exitosamente");
        return "welcome";
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
	
	@ModelAttribute("coloresOrina")
    public List<ColorOrina> initializeColoresOrina() {
		List<ColorOrina> colores = new ArrayList<ColorOrina>();
		for (ColorOrina unColorOrina :ColorOrina.values()){
			colores.add(unColorOrina);
		}
		return colores;
	}

	@ModelAttribute("aspectosOrina")
    public List<AspectoOrina> initializeAspectosOrina() {
		List<AspectoOrina> aspectos = new ArrayList<AspectoOrina>();
		for (AspectoOrina unAspectoOrina :AspectoOrina.values()){
			aspectos.add(unAspectoOrina);
		}
		return aspectos;
	}
	
	@ModelAttribute("aspectosHeces")
    public List<AspectoHeces> initializeAspectoHeces() {
		List<AspectoHeces> aspectos = new ArrayList<AspectoHeces>();
		for (AspectoHeces unAspectoHeces :AspectoHeces.values()){
			aspectos.add(unAspectoHeces);
		}
		return aspectos;
	}
	
	@ModelAttribute("cantidadPresente")
    public List<CantidadPresente> initializeCantidadPresente() {
		List<CantidadPresente> cantidad = new ArrayList<CantidadPresente>();
		for (CantidadPresente unaCantidadPresente :CantidadPresente.values()){
			cantidad.add(unaCantidadPresente);
		}
		return cantidad;
	}
	
	@ModelAttribute("sexos")
    public List<Sexo> initializeSexos() {
		List<Sexo> sexos = new ArrayList<Sexo>();
		for (Sexo unSexo :Sexo.values()){
			sexos.add(unSexo);
		}
		return sexos;
	}
	
	
	@ModelAttribute("tiposDeDengue")
    public List<TipoDengue> initializeTipoDengue() {
		List<TipoDengue> tiposDeDengue = new ArrayList<TipoDengue>();
		for (TipoDengue unTipoDengue :TipoDengue.values()){
			tiposDeDengue.add(unTipoDengue);
		}
		return tiposDeDengue;
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
		List<PacienteVO> pacientes = null;
		Map<String, Object> paramsPaciente = new HashMap<String, Object>();
		Map<String, Object> paramsExpediente = new HashMap<String, Object>();
		if(pNombre!=null && !pNombre.isEmpty()){
			paramsPaciente.put("nombre", pNombre);
			paramsExpediente.put("nombres", pNombre);
			parameters = true;
		}
		if(pApellido!=null && !pApellido.isEmpty()){
			paramsPaciente.put("apellido", pApellido);
			paramsExpediente.put("apellidos", pApellido);
			parameters = true;
		}
		if(pCarne!=null && !pCarne.isEmpty()){
			paramsPaciente.put("carne", pCarne);
			paramsExpediente.put("carne", pCarne);
			parameters = true;
		}
		if(parameters){
			pacientes = new ArrayList<PacienteVO>();
			paramsExpediente.put("estado", EstadoResultadoLaboratorio.ACTIVO);
			List<ExpedienteLaboratorio> expedientes = expedienteService.findByCriteria(paramsExpediente);
			for (ExpedienteLaboratorio unExpediente: expedientes){
				PacienteVO unPacienteTemp = new PacienteVO();
				unPacienteTemp.setIdPaciente(unExpediente.getIdPaciente());
				unPacienteTemp.setCarne(unExpediente.getCarne());
				unPacienteTemp.setNombre(unExpediente.getNombres());
				unPacienteTemp.setApellido(unExpediente.getApellidos());
				unPacienteTemp.setFechaNac(unExpediente.getFechaNacimiento());
				pacientes.add(unPacienteTemp);
			}
			//La base de datos actual solo soporta Integers 
			//Cuando agreguen soporte se habilitara esta parte
			if(paramsPaciente.get("carne") == null || (paramsPaciente.get("carne") != null && paramsPaciente.get("carne").toString().length() < 10)){
				List<Paciente> pacientesOld = pacienteService.findByCriteria(paramsPaciente);
				for(Paciente unPaciente: pacientesOld){
					PacienteVO unPacienteVO = new PacienteVO();
					unPacienteVO.setApellido(unPaciente.getApellido());
					unPacienteVO.setCarne(BigInteger.valueOf(unPaciente.getCarne()));
					unPacienteVO.setDireccion(unPaciente.getDireccion());
					unPacienteVO.setEmail(unPaciente.getEmail());
					unPacienteVO.setFechaNac(unPaciente.getFechaNac());
					unPacienteVO.setIdPaciente(unPaciente.getIdPaciente());
					unPacienteVO.setMovil(unPaciente.getMovil());
					unPacienteVO.setNombre(unPaciente.getNombre());
					unPacienteVO.setSexo(unPaciente.getSexo());
					unPacienteVO.setTelefono(unPaciente.getTelefono());
					if(!containsVO(pacientes, unPacienteVO)){
						pacientes.add(unPacienteVO);
					}
				}
			}
		}
		model.addAttribute("pacientes", pacientes);
		return "buscarPaciente";
	}
	
	private Boolean contains(List<Paciente> destino, Paciente pacienteChecked){
		for(Paciente unPaciente: destino){
			if(unPaciente.getIdPaciente()==pacienteChecked.getIdPaciente()){
				return true;
			}
			if(unPaciente.getCarne() != null && unPaciente.getCarne()==pacienteChecked.getCarne()){
				return true;
			}
		}
		return false;
	}
	
	private Boolean containsVO(List<PacienteVO> destino, PacienteVO pacienteChecked){
		for(PacienteVO unPaciente: destino){
			if(unPaciente.getIdPaciente()==pacienteChecked.getIdPaciente()){
				return true;
			}
			if(unPaciente.getCarne() != null && unPaciente.getCarne()==pacienteChecked.getCarne()){
				return true;
			}
		}
		return false;
	}
	
	@Autowired
    PerfilLipidicoService perfilLipidicoService;
	
	@Autowired
    ExpedienteLaboratorioService expedienteService;
	
	@RequestMapping(value = { "/verExpedienteLaboratorio" }, method = RequestMethod.GET)
	public String verExpedientePersona(@RequestParam("idPaciente") Integer idPaciente, @RequestParam("carne") BigInteger carne, 
			@RequestParam(value = "fechaInicial", required = false) String sFechaInicial, @RequestParam(value = "fechaFinal", required = false) String sFechaFinal
			, ModelMap model) {
		
		Date fechaInicial = null;
		Date fechaFinal = null;
		
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expediente = expedienteService.findByCarnet(carne);
		if(expediente == null){
			expediente = expedienteService.findByIdPaciente(idPaciente);
		}
		if(sFechaInicial!=null){
			try{
				fechaInicial=new SimpleDateFormat("dd/MM/yyyy").parse(sFechaInicial);
			}catch(Exception e){
				fechaInicial = null;
			}
		}
		if(sFechaFinal!=null){
			try{
				fechaFinal = new SimpleDateFormat("dd/MM/yyyy").parse(sFechaFinal);
			}catch(Exception e){
				fechaFinal = null;
			}
		}
		if(expediente == null){
			Paciente paciente = pacienteService.findById(idPaciente);
			ExpedienteLaboratorio expedienteTemp = new ExpedienteLaboratorio();
			expedienteTemp.setIdPaciente(paciente.getIdPaciente());
			expedienteTemp.setNombres(paciente.getNombre());
			expedienteTemp.setApellidos(paciente.getApellido());
			expedienteTemp.setFechaNacimiento(paciente.getFechaNac());
			expedienteTemp.setCarne(BigInteger.valueOf(paciente.getCarne()));
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
		List<ResultadoLaboratorioVO> labs = obtenerLaboratoriosPorExpedienteYFecha(expediente.getId(), fechaInicial, fechaFinal);
		model.addAttribute("expediente", expediente);
		model.addAttribute("labs", labs);
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/eliminarEXPEDIENTE_LABORATORIO" }, method = RequestMethod.GET)
	public String eliminarExpedienteLaboratorio(ModelMap model, @RequestParam("idExpediente") int idExpediente) {
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio nuevoExpedienteLaboratorio = expedienteService.findById(idExpediente);
		nuevoExpedienteLaboratorio.setEstado(EstadoResultadoLaboratorio.ELIMINADO);
		expedienteService.updateExpedienteLaboratorio(nuevoExpedienteLaboratorio);
		model.addAttribute("message", "Expediente Laboratorio Numero: " + idExpediente + " eliminado Exitosamente...");
		return "welcome";
	}
	
	@RequestMapping(value = { "/editarEXPEDIENTE_LABORATORIO" }, method = RequestMethod.GET)
	public String editarExpedienteLaboratorio(ModelMap model, @RequestParam("idEXPEDIENTE_LABORATORIO") int idExpedienteLaboratorio) {
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expedienteLaboratorio = expedienteService.findById(idExpedienteLaboratorio);
		model.addAttribute("expedienteLaboratorio", expedienteLaboratorio);
		model.addAttribute("edit", true);
		return "addExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/editarEXPEDIENTE_LABORATORIO" }, method = RequestMethod.POST)
	public String modificarExpedienteLaboratorio(ModelMap model, @Valid ExpedienteLaboratorio expedienteLaboratorio, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addExpedienteLaboratorio";
		}
		
		model.addAttribute("user", getPrincipal());
		expedienteService.updateExpedienteLaboratorio(expedienteLaboratorio);
		model.addAttribute("message", "Expediente Laboratorio Numero: " + expedienteLaboratorio.getId() + " editado Exitosamente...");
		return "welcome";
	}
	
	@RequestMapping(value = { "/agregarEXPEDIENTE_LABORATORIO" }, method = RequestMethod.GET)
	public String nuevoExpedienteLaboratorio(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expedienteLaboratorio = new ExpedienteLaboratorio();
		model.addAttribute("expedienteLaboratorio", expedienteLaboratorio);
		model.addAttribute("edit", false);
		return "addExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/agregarEXPEDIENTE_LABORATORIO" }, method = RequestMethod.POST)
	public String guardarExpedienteLaboratorio(@Valid ExpedienteLaboratorio expedienteLaboratorio, BindingResult result, 
			ModelMap model) {
		
		if (result.hasErrors()) {
			return "addExpedienteLaboratorio";
		}
		expedienteService.saveExpedienteLaboratorio(expedienteLaboratorio);
		model.addAttribute("user", getPrincipal());
		model.addAttribute("message", "Expediente Laboratorio Numero: " + expedienteLaboratorio.getId() + " creado Exitosamente...");
		return "welcome";
	}
	
	/* Fin ExpedienteLaboratorio */
	
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
	public String imprimirPerfilLipidico(HttpServletResponse response, ModelMap model, @RequestParam("idPERFIL_LIPIDICO") int idLaboratorio) throws JRException, IOException {
		model.addAttribute("user", getPrincipal());
		PerfilLipidico perfilLipidico = perfilLipidicoService.findById(idLaboratorio);
		ExpedienteLaboratorio expediente = expedienteService.findById(perfilLipidico.getIdExpediente());
		model.addAttribute("expediente", expediente);
		User quimicoBiologo = userService.findById(perfilLipidico.getIdQuimicoBiologo());
		
		List<ParametroExportacion> exportacion= new ArrayList<ParametroExportacion>();
		ParametroExportacion mpe = new ParametroExportacion();
		if(mpe.getParameters() == null){
			mpe.setParameters(new HashMap<String,Object>());
		}
		exportacion.add(mpe);
		JasperReport report = JasperCompileManager.compileReport(new ClassPathResource("jrxml/PerfilLipidico.jrxml").getInputStream());
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("logoUSALUD", new ClassPathResource("jrxml/logo_usalud.png").getInputStream());
		params.put("logoUSAC", new ClassPathResource("jrxml/logo_usac.png").getInputStream());
		params.put("titulo", "Perfil Lipídico".toUpperCase());
		params.put("nombrePaciente", expediente.getNombres().toUpperCase()+" "+expediente.getApellidos().toUpperCase()+" ");
		Date fecha = perfilLipidico.getFechaLaboratorio().toDate();
		params.put("fecha", new SimpleDateFormat("dd/MM/yyyy").format(fecha));
		params.put("colesterolTotal", (perfilLipidico.getColesterolTotal()==null)?"":perfilLipidico.getColesterolTotal().toString());
		params.put("colesterolAltaDensidad", (perfilLipidico.getColesterolAltaDensidad()==null)?"":perfilLipidico.getColesterolAltaDensidad().toString());
		params.put("colesterolBajaDensidad", (perfilLipidico.getColesterolBajaDensidad()==null)?"":perfilLipidico.getColesterolBajaDensidad().toString());
		params.put("colesterolMuyBajaDensidad", (perfilLipidico.getColesterolMuyBajaDensidad()==null)?"":perfilLipidico.getColesterolMuyBajaDensidad().toString());
		params.put("trigliceridos", (perfilLipidico.getTrigliceridos()==null)?"":perfilLipidico.getTrigliceridos().toString());
		params.put("indiceRiesgo", (perfilLipidico.getIndiceRiesgo()==null)?"":perfilLipidico.getIndiceRiesgo().toString());
		params.put("resistenciaInsulina", (perfilLipidico.getResistenciaInsulina()==null)?"":perfilLipidico.getResistenciaInsulina().toString());
		params.put("codigoPaciente", expediente.getCarne().toString().toUpperCase()+" ");
		params.put("quimicoBiologo", quimicoBiologo.getFirstName().toUpperCase()+" "+quimicoBiologo.getLastName().toUpperCase()+" ");
		params.put("sign", (quimicoBiologo.getUrlSing()==null?null:new ClassPathResource("jrxml/signs/"+quimicoBiologo.getUrlSing()).getInputStream()));
		params.put("sello", new ClassPathResource("jrxml/sello_usalud.png").getInputStream());
		
		FormatoExportacionPruebaLaboratorio formater = new FormatoExportacionPruebaLaboratorio(params);
		params = formater.getParametrosConFormato();
		
		JasperPrint myJRprintReportObject = JasperFillManager.fillReport(report, params, new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(exportacion));
		
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=PerfilLipidico_"+expediente.getCarne().toString()+"_"+new SimpleDateFormat("dd-MM-yyyy").format(fecha)+".pdf");

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
	
	@RequestMapping(value = { "/imprimirPRUEBA_EMBARAZO" }, method = RequestMethod.GET)
	public String imprimirPruebaEmbarazo(HttpServletResponse response, ModelMap model, @RequestParam("idPRUEBA_EMBARAZO") int idLaboratorio) throws JRException, IOException {
		model.addAttribute("user", getPrincipal());
		PruebaEmbarazo pruebaEmbarazo = pruebaEmbarazoService.findById(idLaboratorio);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaEmbarazo.getIdExpediente());
		model.addAttribute("expediente", expediente);
		User quimicoBiologo = userService.findById(pruebaEmbarazo.getIdQuimicoBiologo());
		
		List<ParametroExportacion> exportacion= new ArrayList<ParametroExportacion>();
		ParametroExportacion mpe = new ParametroExportacion();
		if(mpe.getParameters() == null){
			mpe.setParameters(new HashMap<String,Object>());
		}
		exportacion.add(mpe);
		JasperReport report = JasperCompileManager.compileReport(new ClassPathResource("jrxml/PruebaEmbarazo.jrxml").getInputStream());
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("logoUSALUD", new ClassPathResource("jrxml/logo_usalud.png").getInputStream());
		params.put("logoUSAC", new ClassPathResource("jrxml/logo_usac.png").getInputStream());
		params.put("titulo", "Prueba de Embarazo".toUpperCase());
		params.put("nombrePaciente", expediente.getNombres().toUpperCase()+" "+expediente.getApellidos().toUpperCase()+" ");
		Date fecha = pruebaEmbarazo.getFechaLaboratorio().toDate();
		params.put("fecha", new SimpleDateFormat("dd/MM/yyyy").format(fecha));
		params.put("resultado", pruebaEmbarazo.getResultado());
		params.put("codigoPaciente", expediente.getCarne().toString().toUpperCase()+" ");
		params.put("quimicoBiologo", quimicoBiologo.getFirstName().toUpperCase()+" "+quimicoBiologo.getLastName().toUpperCase()+" ");
		params.put("sign", (quimicoBiologo.getUrlSing()==null?null:new ClassPathResource("jrxml/signs/"+quimicoBiologo.getUrlSing()).getInputStream()));
		params.put("sello", new ClassPathResource("jrxml/sello_usalud.png").getInputStream());
		
		FormatoExportacionPruebaLaboratorio formater = new FormatoExportacionPruebaLaboratorio(params);
		params = formater.getParametrosConFormato();
		
		JasperPrint myJRprintReportObject = JasperFillManager.fillReport(report, params, new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(exportacion));
		
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=PruebaEmbarazo_"+expediente.getCarne().toString()+"_"+new SimpleDateFormat("dd-MM-yyyy").format(fecha)+".pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(myJRprintReportObject, outStream);
		
		return null;
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
	
	@RequestMapping(value = { "/imprimirACIDO_URICO" }, method = RequestMethod.GET)
	public String imprimirAcidoUrico(HttpServletResponse response, ModelMap model, @RequestParam("idACIDO_URICO") int idLaboratorio) throws JRException, IOException {
		model.addAttribute("user", getPrincipal());
		AcidoUrico acidoUrico = acidoUricoService.findById(idLaboratorio);
		ExpedienteLaboratorio expediente = expedienteService.findById(acidoUrico.getIdExpediente());
		model.addAttribute("expediente", expediente);
		User quimicoBiologo = userService.findById(acidoUrico.getIdQuimicoBiologo());
		
		List<ParametroExportacion> exportacion= new ArrayList<ParametroExportacion>();
		ParametroExportacion mpe = new ParametroExportacion();
		if(mpe.getParameters() == null){
			mpe.setParameters(new HashMap<String,Object>());
		}
		exportacion.add(mpe);
		JasperReport report = JasperCompileManager.compileReport(new ClassPathResource("jrxml/AcidoUrico.jrxml").getInputStream());
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("logoUSALUD", new ClassPathResource("jrxml/logo_usalud.png").getInputStream());
		params.put("logoUSAC", new ClassPathResource("jrxml/logo_usac.png").getInputStream());
		params.put("titulo", "Ácido Úrico".toUpperCase());
		params.put("nombrePaciente", expediente.getNombres().toUpperCase()+" "+expediente.getApellidos().toUpperCase()+" ");
		Date fecha = acidoUrico.getFechaLaboratorio().toDate();
		params.put("fecha", new SimpleDateFormat("dd/MM/yyyy").format(fecha));
		params.put("codigoPaciente", expediente.getCarne().toString().toUpperCase()+" ");
		params.put("quimicoBiologo", quimicoBiologo.getFirstName().toUpperCase()+" "+quimicoBiologo.getLastName().toUpperCase()+" ");
		params.put("sign", (quimicoBiologo.getUrlSing()==null?null:new ClassPathResource("jrxml/signs/"+quimicoBiologo.getUrlSing()).getInputStream()));
		params.put("acidoUrico", acidoUrico.getNivelAcidoUrico().toString());
		params.put("sello", new ClassPathResource("jrxml/sello_usalud.png").getInputStream());
		
		FormatoExportacionPruebaLaboratorio formater = new FormatoExportacionPruebaLaboratorio(params);
		params = formater.getParametrosConFormato();
		
		JasperPrint myJRprintReportObject = JasperFillManager.fillReport(report, params, new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(exportacion));
		
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=AcidoUrico_"+expediente.getCarne().toString()+"_"+new SimpleDateFormat("dd-MM-yyyy").format(fecha)+".pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(myJRprintReportObject, outStream);
		
		return null;
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
	
	@RequestMapping(value = { "/imprimirPRUEBA_VDRL" }, method = RequestMethod.GET)
	public String imprimirPruebaVDRL(HttpServletResponse response, ModelMap model, @RequestParam("idPRUEBA_VDRL") int idLaboratorio) throws JRException, IOException {
		model.addAttribute("user", getPrincipal());
		PruebaVDRL pruebaVDRL = pruebaVDRLService.findById(idLaboratorio);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaVDRL.getIdExpediente());
		model.addAttribute("expediente", expediente);
		User quimicoBiologo = userService.findById(pruebaVDRL.getIdQuimicoBiologo());
		
		List<ParametroExportacion> exportacion= new ArrayList<ParametroExportacion>();
		ParametroExportacion mpe = new ParametroExportacion();
		if(mpe.getParameters() == null){
			mpe.setParameters(new HashMap<String,Object>());
		}
		exportacion.add(mpe);
		JasperReport report = JasperCompileManager.compileReport(new ClassPathResource("jrxml/PruebaVDRL.jrxml").getInputStream());
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("logoUSALUD", new ClassPathResource("jrxml/logo_usalud.png").getInputStream());
		params.put("logoUSAC", new ClassPathResource("jrxml/logo_usac.png").getInputStream());
		params.put("titulo", "Prueba VDRL".toUpperCase());
		params.put("nombrePaciente", expediente.getNombres().toUpperCase()+" "+expediente.getApellidos().toUpperCase()+" ");
		Date fecha = pruebaVDRL.getFechaLaboratorio().toDate();
		params.put("fecha", new SimpleDateFormat("dd/MM/yyyy").format(fecha));
		params.put("resultado", pruebaVDRL.getResultado().getResultado());
		params.put("reaVDRL", (pruebaVDRL.getReaVDRL()==null)?"":pruebaVDRL.getReaVDRL().toString());
		params.put("nivelVDRL", (pruebaVDRL.getNivelVDRL()==null)?"":pruebaVDRL.getNivelVDRL().toString());
		params.put("codigoPaciente", expediente.getCarne().toString().toUpperCase()+" ");
		params.put("quimicoBiologo", quimicoBiologo.getFirstName().toUpperCase()+" "+quimicoBiologo.getLastName().toUpperCase()+" ");
		params.put("sign", (quimicoBiologo.getUrlSing()==null?null:new ClassPathResource("jrxml/signs/"+quimicoBiologo.getUrlSing()).getInputStream()));
		params.put("sello", new ClassPathResource("jrxml/sello_usalud.png").getInputStream());
		
		FormatoExportacionPruebaLaboratorio formater = new FormatoExportacionPruebaLaboratorio(params);
		params = formater.getParametrosConFormato();
		
		JasperPrint myJRprintReportObject = JasperFillManager.fillReport(report, params, new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(exportacion));
		
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=PruebaVDRL_"+expediente.getCarne().toString()+"_"+new SimpleDateFormat("dd-MM-yyyy").format(fecha)+".pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(myJRprintReportObject, outStream);
		
		return null;
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
	
	@RequestMapping(value = { "/imprimirCOLESTEROL_TRIGLICERIDOS" }, method = RequestMethod.GET)
	public String imprimirColesterolTrigliceridos(HttpServletResponse response, ModelMap model, @RequestParam("idCOLESTEROL_TRIGLICERIDOS") int idLaboratorio) throws JRException, IOException {
		model.addAttribute("user", getPrincipal());
		ColesterolTrigliceridos colesterolTrigliceridos = colesterolTrigliceridosService.findById(idLaboratorio);
		ExpedienteLaboratorio expediente = expedienteService.findById(colesterolTrigliceridos.getIdExpediente());
		model.addAttribute("expediente", expediente);
		User quimicoBiologo = userService.findById(colesterolTrigliceridos.getIdQuimicoBiologo());
		
		List<ParametroExportacion> exportacion= new ArrayList<ParametroExportacion>();
		ParametroExportacion mpe = new ParametroExportacion();
		if(mpe.getParameters() == null){
			mpe.setParameters(new HashMap<String,Object>());
		}
		exportacion.add(mpe);
		JasperReport report = JasperCompileManager.compileReport(new ClassPathResource("jrxml/ColesterolTrigliceridos.jrxml").getInputStream());
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("logoUSALUD", new ClassPathResource("jrxml/logo_usalud.png").getInputStream());
		params.put("logoUSAC", new ClassPathResource("jrxml/logo_usac.png").getInputStream());
		params.put("titulo", "Cho-Tri".toUpperCase());
		params.put("nombrePaciente", expediente.getNombres().toUpperCase()+" "+expediente.getApellidos().toUpperCase()+" ");
		Date fecha = colesterolTrigliceridos.getFechaLaboratorio().toDate();
		params.put("fecha", new SimpleDateFormat("dd/MM/yyyy").format(fecha));
		params.put("colesterol", (colesterolTrigliceridos.getColesterol()==null?"":colesterolTrigliceridos.getColesterol().toString()));
		params.put("trigliceridos", (colesterolTrigliceridos.getTrigliceridos()==null?"":colesterolTrigliceridos.getTrigliceridos().toString()));
		params.put("codigoPaciente", expediente.getCarne().toString().toUpperCase()+" ");
		params.put("quimicoBiologo", quimicoBiologo.getFirstName().toUpperCase()+" "+quimicoBiologo.getLastName().toUpperCase()+" ");
		params.put("sign", (quimicoBiologo.getUrlSing()==null?null:new ClassPathResource("jrxml/signs/"+quimicoBiologo.getUrlSing()).getInputStream()));
		params.put("sello", new ClassPathResource("jrxml/sello_usalud.png").getInputStream());
		
		FormatoExportacionPruebaLaboratorio formater = new FormatoExportacionPruebaLaboratorio(params);
		params = formater.getParametrosConFormato();
		
		JasperPrint myJRprintReportObject = JasperFillManager.fillReport(report, params, new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(exportacion));
		
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=ColesterolTrigliceridos_"+expediente.getCarne().toString()+"_"+new SimpleDateFormat("dd-MM-yyyy").format(fecha)+".pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(myJRprintReportObject, outStream);
		
		return null;
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
	
	@RequestMapping(value = { "/imprimirGLUCOSA_PRE_Y_POST" }, method = RequestMethod.GET)
	public String imprimirGlucosaPreYPost(HttpServletResponse response, ModelMap model, @RequestParam("idGLUCOSA_PRE_Y_POST") int idLaboratorio) throws JRException, IOException {
		model.addAttribute("user", getPrincipal());
		GlucosaPreYPost glucosaPreYPost = glucosaPreYPostService.findById(idLaboratorio);
		ExpedienteLaboratorio expediente = expedienteService.findById(glucosaPreYPost.getIdExpediente());
		model.addAttribute("expediente", expediente);
		User quimicoBiologo = userService.findById(glucosaPreYPost.getIdQuimicoBiologo());
		
		List<ParametroExportacion> exportacion= new ArrayList<ParametroExportacion>();
		ParametroExportacion mpe = new ParametroExportacion();
		if(mpe.getParameters() == null){
			mpe.setParameters(new HashMap<String,Object>());
		}
		exportacion.add(mpe);
		JasperReport report = JasperCompileManager.compileReport(new ClassPathResource("jrxml/GlucosaPreYPost.jrxml").getInputStream());
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("logoUSALUD", new ClassPathResource("jrxml/logo_usalud.png").getInputStream());
		params.put("logoUSAC", new ClassPathResource("jrxml/logo_usac.png").getInputStream());
		params.put("titulo", "Gluco pre-pp".toUpperCase());
		params.put("nombrePaciente", expediente.getNombres().toUpperCase()+" "+expediente.getApellidos().toUpperCase()+" ");
		Date fecha = glucosaPreYPost.getFechaLaboratorio().toDate();
		params.put("fecha", new SimpleDateFormat("dd/MM/yyyy").format(fecha));
		params.put("prePrandial", (glucosaPreYPost.getPrePrandial()==null)?"":glucosaPreYPost.getPrePrandial().toString());
		params.put("postPrandial", (glucosaPreYPost.getPostPrandial()==null)?"":glucosaPreYPost.getPostPrandial().toString());
		params.put("codigoPaciente", expediente.getCarne().toString().toUpperCase()+" ");
		params.put("quimicoBiologo", quimicoBiologo.getFirstName().toUpperCase()+" "+quimicoBiologo.getLastName().toUpperCase()+" ");
		params.put("sign", (quimicoBiologo.getUrlSing()==null?null:new ClassPathResource("jrxml/signs/"+quimicoBiologo.getUrlSing()).getInputStream()));
		params.put("sello", new ClassPathResource("jrxml/sello_usalud.png").getInputStream());
		
		FormatoExportacionPruebaLaboratorio formater = new FormatoExportacionPruebaLaboratorio(params);
		params = formater.getParametrosConFormato();
		
		JasperPrint myJRprintReportObject = JasperFillManager.fillReport(report, params, new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(exportacion));
		
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=GlucosaPreYPost_"+expediente.getCarne().toString()+"_"+new SimpleDateFormat("dd-MM-yyyy").format(fecha)+".pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(myJRprintReportObject, outStream);
		
		return null;
	}
	
	/* Fin GlucosaPreYPost */
	
	
	/* Fin `logica */

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
		
		pruebaSerologicaService.savePruebaSerologica(pruebaSerologica);
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaSerologica.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Pruebas Serologicas Numero: " + pruebaSerologica.getId() + " creado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/imprimirPRUEBA_SEROLOGICA" }, method = RequestMethod.GET)
	public String imprimirPruebaSerologica(HttpServletResponse response, ModelMap model, @RequestParam("idPRUEBA_SEROLOGICA") int idLaboratorio) throws JRException, IOException {
		model.addAttribute("user", getPrincipal());
		PruebaSerologica pruebaSerologica = pruebaSerologicaService.findById(idLaboratorio);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaSerologica.getIdExpediente());
		model.addAttribute("expediente", expediente);
		User quimicoBiologo = userService.findById(pruebaSerologica.getIdQuimicoBiologo());
		
		List<ParametroExportacion> exportacion= new ArrayList<ParametroExportacion>();
		ParametroExportacion mpe = new ParametroExportacion();
		if(mpe.getParameters() == null){
			mpe.setParameters(new HashMap<String,Object>());
		}
		exportacion.add(mpe);
		JasperReport report = JasperCompileManager.compileReport(new ClassPathResource("jrxml/PruebaSerologica.jrxml").getInputStream());
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("logoUSALUD", new ClassPathResource("jrxml/logo_usalud.png").getInputStream());
		params.put("logoUSAC", new ClassPathResource("jrxml/logo_usac.png").getInputStream());
		params.put("titulo", "Pruebas Serológicas".toUpperCase());
		params.put("nombrePaciente", expediente.getNombres().toUpperCase()+" "+expediente.getApellidos().toUpperCase()+" ");
		Date fecha = pruebaSerologica.getFechaLaboratorio().toDate();
		params.put("fecha", new SimpleDateFormat("dd/MM/yyyy").format(fecha));
		params.put("resultadoFactorReumatoide",pruebaSerologica.getResultadoFactorReumatoide()==null?"-":pruebaSerologica.getResultadoFactorReumatoide().toString());
		params.put("resultadoProteinaCReactiva",pruebaSerologica.getResultadoProteinaCReactiva()==null?"-":pruebaSerologica.getResultadoProteinaCReactiva().toString());
		params.put("resultadoAntiEstreptolisinaO",pruebaSerologica.getResultadoAntiEstreptolisinaO()==null?"-":pruebaSerologica.getResultadoAntiEstreptolisinaO().toString());
		params.put("factorReumatoide", (pruebaSerologica.getFactorReumatoide()==null)?"":pruebaSerologica.getFactorReumatoide().toString());
		params.put("proteinaCReactiva", (pruebaSerologica.getProteinaCReactiva()==null)?"":pruebaSerologica.getProteinaCReactiva().toString());
		params.put("antiEstreptolisinaO", (pruebaSerologica.getAntiEstreptolisinaO()==null)?"":pruebaSerologica.getAntiEstreptolisinaO().toString());
		params.put("codigoPaciente", expediente.getCarne().toString().toUpperCase()+" ");
		params.put("quimicoBiologo", quimicoBiologo.getFirstName().toUpperCase()+" "+quimicoBiologo.getLastName().toUpperCase()+" ");
		params.put("sign", (quimicoBiologo.getUrlSing()==null?null:new ClassPathResource("jrxml/signs/"+quimicoBiologo.getUrlSing()).getInputStream()));
		params.put("sello", new ClassPathResource("jrxml/sello_usalud.png").getInputStream());
		
		FormatoExportacionPruebaLaboratorio formater = new FormatoExportacionPruebaLaboratorio(params);
		params = formater.getParametrosConFormato();
		
		JasperPrint myJRprintReportObject = JasperFillManager.fillReport(report, params, new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(exportacion));
		
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=PruebaSerologica_"+expediente.getCarne().toString()+"_"+new SimpleDateFormat("dd-MM-yyyy").format(fecha)+".pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(myJRprintReportObject, outStream);
		
		return null;
	}
	
	/* Fin PruebaSerologica */
	
	/* Fin PruebasHematologicas */

	@Autowired
    	PruebasHematologicasService pruebasHematologicasService;
	
	@RequestMapping(value = { "/eliminarPRUEBAS_HEMATOLOGICAS" }, method = RequestMethod.GET)
	public String eliminarPruebasHematologicas(ModelMap model, @RequestParam("idExpediente") int idExpediente, @RequestParam("idPRUEBAS_HEMATOLOGICAS") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebasHematologicas nuevoPruebasHematologicas = pruebasHematologicasService.findById(idPerfil);
		nuevoPruebasHematologicas.setEstado(EstadoResultadoLaboratorio.ELIMINADO);
		pruebasHematologicasService.updatePruebasHematologicas(nuevoPruebasHematologicas);
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Pruebas Hematológicas Numero: " + idPerfil + " eliminado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/consultarPRUEBAS_HEMATOLOGICAS" }, method = RequestMethod.GET)
	public String consultarPruebasHematologicas(ModelMap model, @RequestParam("idPRUEBAS_HEMATOLOGICAS") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebasHematologicas pruebasHematologicas = pruebasHematologicasService.findById(idPerfil);
		model.addAttribute("pruebasHematologicas", pruebasHematologicas);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebasHematologicas.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("soloConsulta", true);
		return "addPruebasHematologicas";
	}
	
	@RequestMapping(value = { "/editarPRUEBAS_HEMATOLOGICAS" }, method = RequestMethod.GET)
	public String editarPruebasHematologicas(ModelMap model, @RequestParam("idPRUEBAS_HEMATOLOGICAS") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebasHematologicas pruebasHematologicas = pruebasHematologicasService.findById(idPerfil);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebasHematologicas.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("pruebasHematologicas", pruebasHematologicas);
		model.addAttribute("edit", true);
		return "addPruebasHematologicas";
	}
	
	@RequestMapping(value = { "/editarPRUEBAS_HEMATOLOGICAS" }, method = RequestMethod.POST)
	public String modificarPruebasHematologicas(ModelMap model, @Valid PruebasHematologicas pruebasHematologicas, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addPruebasHematologicas";
		}
		
		if(pruebasHematologicas.getHematocrito() == null && pruebasHematologicas.getVelocidadSedimentacion() == null){
			model.addAttribute("alerta", "Debe ingresar al menos uno resultado para poder guardar...");
			model.addAttribute("edit", true);
			return "addPruebasHematologicas";
		}
		
		model.addAttribute("user", getPrincipal());
		pruebasHematologicasService.updatePruebasHematologicas(pruebasHematologicas);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebasHematologicas.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Pruebas Hematológicas Numero: " + pruebasHematologicas.getId() + " editado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/agregarPRUEBAS_HEMATOLOGICAS" }, method = RequestMethod.GET)
	public String nuevoPruebasHematologicas(ModelMap model, @RequestParam("idExpediente") int idExpediente) {
		model.addAttribute("user", getPrincipal());
		//model.addAttribute("laboratoristas", userService.findAllUsersByRol("LABORATORISTA"));
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		PruebasHematologicas pruebasHematologicas = new PruebasHematologicas();
		model.addAttribute("pruebasHematologicas", pruebasHematologicas);
		model.addAttribute("idExpediente", idExpediente);
		model.addAttribute("edit", false);
		return "addPruebasHematologicas";
	}
	
	@RequestMapping(value = { "/agregarPRUEBAS_HEMATOLOGICAS" }, method = RequestMethod.POST)
	public String guardarPruebasHematologicas(@Valid PruebasHematologicas pruebasHematologicas, BindingResult result, 
			ModelMap model) {
		
		if (result.hasErrors()) {
			return "addPruebasHematologicas";
		}
		
		if(pruebasHematologicas.getHematocrito() == null && pruebasHematologicas.getVelocidadSedimentacion() == null){
			model.addAttribute("alerta", "Debe ingresar al menos uno resultado para poder guardar...");
			return "addPruebasHematologicas";
		}
		
		pruebasHematologicasService.savePruebasHematologicas(pruebasHematologicas);
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebasHematologicas.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Pruebas Hematológicas Numero: " + pruebasHematologicas.getId() + " creado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/imprimirPRUEBAS_HEMATOLOGICAS" }, method = RequestMethod.GET)
	public String imprimirPruebasHematologicas(HttpServletResponse response, ModelMap model, @RequestParam("idPRUEBAS_HEMATOLOGICAS") int idLaboratorio) throws JRException, IOException {
		model.addAttribute("user", getPrincipal());
		PruebasHematologicas pruebasHematologicas = pruebasHematologicasService.findById(idLaboratorio);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebasHematologicas.getIdExpediente());
		model.addAttribute("expediente", expediente);
		User quimicoBiologo = userService.findById(pruebasHematologicas.getIdQuimicoBiologo());
		
		List<ParametroExportacion> exportacion= new ArrayList<ParametroExportacion>();
		ParametroExportacion mpe = new ParametroExportacion();
		if(mpe.getParameters() == null){
			mpe.setParameters(new HashMap<String,Object>());
		}
		exportacion.add(mpe);
		JasperReport report = JasperCompileManager.compileReport(new ClassPathResource("jrxml/PruebasHematologicas.jrxml").getInputStream());
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("logoUSALUD", new ClassPathResource("jrxml/logo_usalud.png").getInputStream());
		params.put("logoUSAC", new ClassPathResource("jrxml/logo_usac.png").getInputStream());
		params.put("titulo", "Pruebas Hematológicas".toUpperCase());
		params.put("nombrePaciente", expediente.getNombres().toUpperCase()+" "+expediente.getApellidos().toUpperCase()+" ");
		Date fecha = pruebasHematologicas.getFechaLaboratorio().toDate();
		params.put("fecha", new SimpleDateFormat("dd/MM/yyyy").format(fecha));
		params.put("velocidadSedimentacion", (pruebasHematologicas.getVelocidadSedimentacion()==null)?"":pruebasHematologicas.getVelocidadSedimentacion().toString());
		params.put("hematocrito", (pruebasHematologicas.getHematocrito()==null)?"":pruebasHematologicas.getHematocrito().toString());
		params.put("codigoPaciente", expediente.getCarne().toString().toUpperCase()+" ");
		params.put("quimicoBiologo", quimicoBiologo.getFirstName().toUpperCase()+" "+quimicoBiologo.getLastName().toUpperCase()+" ");
		params.put("sign", (quimicoBiologo.getUrlSing()==null?null:new ClassPathResource("jrxml/signs/"+quimicoBiologo.getUrlSing()).getInputStream()));
		params.put("sello", new ClassPathResource("jrxml/sello_usalud.png").getInputStream());
		
		FormatoExportacionPruebaLaboratorio formater = new FormatoExportacionPruebaLaboratorio(params);
		params = formater.getParametrosConFormato();
		
		JasperPrint myJRprintReportObject = JasperFillManager.fillReport(report, params, new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(exportacion));
		
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=PruebasHematologicas_"+expediente.getCarne().toString()+"_"+new SimpleDateFormat("dd-MM-yyyy").format(fecha)+".pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(myJRprintReportObject, outStream);
		
		return null;
	}
	
	/* Fin PruebasHematologicas */
	
	/* Fin HematologiaCompleta */

	@Autowired
    HematologiaCompletaService hematologiaCompletaService;
	
	@RequestMapping(value = { "/eliminarHEMATOLOGIA_COMPLETA" }, method = RequestMethod.GET)
	public String eliminarHematologiaCompleta(ModelMap model, @RequestParam("idExpediente") int idExpediente, @RequestParam("idHEMATOLOGIA_COMPLETA") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		HematologiaCompleta nuevoHematologiaCompleta = hematologiaCompletaService.findById(idPerfil);
		nuevoHematologiaCompleta.setEstado(EstadoResultadoLaboratorio.ELIMINADO);
		hematologiaCompletaService.updateHematologiaCompleta(nuevoHematologiaCompleta);
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Hematología Completa Numero: " + idPerfil + " eliminado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/consultarHEMATOLOGIA_COMPLETA" }, method = RequestMethod.GET)
	public String consultarHematologiaCompleta(ModelMap model, @RequestParam("idHEMATOLOGIA_COMPLETA") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		HematologiaCompleta hematologiaCompleta = hematologiaCompletaService.findById(idPerfil);
		model.addAttribute("hematologiaCompleta", hematologiaCompleta);
		ExpedienteLaboratorio expediente = expedienteService.findById(hematologiaCompleta.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("soloConsulta", true);
		return "addHematologiaCompleta";
	}
	
	@RequestMapping(value = { "/editarHEMATOLOGIA_COMPLETA" }, method = RequestMethod.GET)
	public String editarHematologiaCompleta(ModelMap model, @RequestParam("idHEMATOLOGIA_COMPLETA") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		HematologiaCompleta hematologiaCompleta = hematologiaCompletaService.findById(idPerfil);
		ExpedienteLaboratorio expediente = expedienteService.findById(hematologiaCompleta.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("hematologiaCompleta", hematologiaCompleta);
		model.addAttribute("edit", true);
		return "addHematologiaCompleta";
	}
	
	@RequestMapping(value = { "/editarHEMATOLOGIA_COMPLETA" }, method = RequestMethod.POST)
	public String modificarHematologiaCompleta(ModelMap model, @Valid HematologiaCompleta hematologiaCompleta, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addHematologiaCompleta";
		}
		
		model.addAttribute("user", getPrincipal());
		hematologiaCompletaService.updateHematologiaCompleta(hematologiaCompleta);
		ExpedienteLaboratorio expediente = expedienteService.findById(hematologiaCompleta.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Hematología Completa Numero: " + hematologiaCompleta.getId() + " editado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/agregarHEMATOLOGIA_COMPLETA" }, method = RequestMethod.GET)
	public String nuevoHematologiaCompleta(ModelMap model, @RequestParam("idExpediente") int idExpediente) {
		model.addAttribute("user", getPrincipal());
		//model.addAttribute("laboratoristas", userService.findAllUsersByRol("LABORATORISTA"));
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		HematologiaCompleta hematologiaCompleta = new HematologiaCompleta();
		model.addAttribute("hematologiaCompleta", hematologiaCompleta);
		model.addAttribute("idExpediente", idExpediente);
		model.addAttribute("edit", false);
		return "addHematologiaCompleta";
	}
	
	@RequestMapping(value = { "/agregarHEMATOLOGIA_COMPLETA" }, method = RequestMethod.POST)
	public String guardarHematologiaCompleta(@Valid HematologiaCompleta hematologiaCompleta, BindingResult result, 
			ModelMap model) {
		
		if (result.hasErrors()) {
			return "addHematologiaCompleta";
		}
		
		hematologiaCompletaService.saveHematologiaCompleta(hematologiaCompleta);
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expediente = expedienteService.findById(hematologiaCompleta.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Hematología Completa Numero: " + hematologiaCompleta.getId() + " creado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/imprimirHEMATOLOGIA_COMPLETA" }, method = RequestMethod.GET)
	public String imprimirHematologiaCompleta(HttpServletResponse response, ModelMap model, @RequestParam("idHEMATOLOGIA_COMPLETA") int idLaboratorio) throws JRException, IOException {
		model.addAttribute("user", getPrincipal());
		HematologiaCompleta hematologiaCompleta = hematologiaCompletaService.findById(idLaboratorio);
		ExpedienteLaboratorio expediente = expedienteService.findById(hematologiaCompleta.getIdExpediente());
		model.addAttribute("expediente", expediente);
		User quimicoBiologo = userService.findById(hematologiaCompleta.getIdQuimicoBiologo());
		
		List<ParametroExportacion> exportacion= new ArrayList<ParametroExportacion>();
		ParametroExportacion mpe = new ParametroExportacion();
		if(mpe.getParameters() == null){
			mpe.setParameters(new HashMap<String,Object>());
		}
		exportacion.add(mpe);
		JasperReport report = JasperCompileManager.compileReport(new ClassPathResource("jrxml/HematologiaCompleta.jrxml").getInputStream());
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("logoUSALUD", new ClassPathResource("jrxml/logo_usalud.png").getInputStream());
		params.put("logoUSAC", new ClassPathResource("jrxml/logo_usac.png").getInputStream());
		params.put("titulo", "Hematología Completa".toUpperCase());
		params.put("nombrePaciente", expediente.getNombres().toUpperCase()+" "+expediente.getApellidos().toUpperCase()+" ");
		Date fecha = hematologiaCompleta.getFechaLaboratorio().toDate();
		params.put("fecha", new SimpleDateFormat("dd/MM/yyyy").format(fecha));
		params.put("codigoPaciente", expediente.getCarne().toString().toUpperCase()+" ");
		params.put("quimicoBiologo", quimicoBiologo.getFirstName().toUpperCase()+" "+quimicoBiologo.getLastName().toUpperCase()+" ");
		params.put("gran", (hematologiaCompleta.getGran()==null)?"":hematologiaCompleta.getGran().toString());
		params.put("hct", (hematologiaCompleta.getHct()==null)?"":hematologiaCompleta.getHct().toString());
		params.put("hgb", (hematologiaCompleta.getHgb()==null)?"":hematologiaCompleta.getHgb().toString());
		params.put("lym", (hematologiaCompleta.getLym()==null)?"":hematologiaCompleta.getLym().toString());
		params.put("mch", (hematologiaCompleta.getMch()==null)?"":hematologiaCompleta.getMch().toString());
		params.put("mchc", (hematologiaCompleta.getMchc()==null)?"":hematologiaCompleta.getMchc().toString());
		params.put("mcv", (hematologiaCompleta.getMcv()==null)?"":hematologiaCompleta.getMcv().toString());
		params.put("mid", (hematologiaCompleta.getMid()==null)?"":hematologiaCompleta.getMid().toString());
		params.put("plt", (hematologiaCompleta.getPlt()==null)?"":hematologiaCompleta.getPlt().toString());
		params.put("rbc", (hematologiaCompleta.getRbc()==null)?"":hematologiaCompleta.getRbc().toString());
		params.put("vse", (hematologiaCompleta.getVse()==null)?"":hematologiaCompleta.getVse().toString());
		params.put("wbc", (hematologiaCompleta.getWbc()==null)?"":hematologiaCompleta.getWbc().toString());
		params.put("sign", (quimicoBiologo.getUrlSing()==null?null:new ClassPathResource("jrxml/signs/"+quimicoBiologo.getUrlSing()).getInputStream()));
		params.put("sello", new ClassPathResource("jrxml/sello_usalud.png").getInputStream());
		
		FormatoExportacionPruebaLaboratorio formater = new FormatoExportacionPruebaLaboratorio(params);
		params = formater.getParametrosConFormato();
		
		JasperPrint myJRprintReportObject = JasperFillManager.fillReport(report, params, new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(exportacion));
		
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=HematologiaCompleta_"+expediente.getCarne().toString()+"_"+new SimpleDateFormat("dd-MM-yyyy").format(fecha)+".pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(myJRprintReportObject, outStream);
		
		return null;
	}
	
	/* Fin HematologiaCompleta */
	
	/* Fin ExamenOrina */

	@Autowired
    ExamenOrinaService examenOrinaService;
	
	@RequestMapping(value = { "/eliminarEXAMEN_ORINA" }, method = RequestMethod.GET)
	public String eliminarExamenOrina(ModelMap model, @RequestParam("idExpediente") int idExpediente, @RequestParam("idEXAMEN_ORINA") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		ExamenOrina nuevoExamenOrina = examenOrinaService.findById(idPerfil);
		nuevoExamenOrina.setEstado(EstadoResultadoLaboratorio.ELIMINADO);
		examenOrinaService.updateExamenOrina(nuevoExamenOrina);
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Examen Orina Numero: " + idPerfil + " eliminado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/consultarEXAMEN_ORINA" }, method = RequestMethod.GET)
	public String consultarExamenOrina(ModelMap model, @RequestParam("idEXAMEN_ORINA") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		ExamenOrina examenOrina = examenOrinaService.findById(idPerfil);
		model.addAttribute("examenOrina", examenOrina);
		ExpedienteLaboratorio expediente = expedienteService.findById(examenOrina.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("soloConsulta", true);
		return "addExamenOrina";
	}
	
	@RequestMapping(value = { "/editarEXAMEN_ORINA" }, method = RequestMethod.GET)
	public String editarExamenOrina(ModelMap model, @RequestParam("idEXAMEN_ORINA") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		ExamenOrina examenOrina = examenOrinaService.findById(idPerfil);
		ExpedienteLaboratorio expediente = expedienteService.findById(examenOrina.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("examenOrina", examenOrina);
		model.addAttribute("edit", true);
		return "addExamenOrina";
	}
	
	@RequestMapping(value = { "/editarEXAMEN_ORINA" }, method = RequestMethod.POST)
	public String modificarExamenOrina(ModelMap model, @Valid ExamenOrina examenOrina, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addExamenOrina";
		}
		
		model.addAttribute("user", getPrincipal());
		examenOrinaService.updateExamenOrina(examenOrina);
		ExpedienteLaboratorio expediente = expedienteService.findById(examenOrina.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Examen Orina Numero: " + examenOrina.getId() + " editado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/agregarEXAMEN_ORINA" }, method = RequestMethod.GET)
	public String nuevoExamenOrina(ModelMap model, @RequestParam("idExpediente") int idExpediente) {
		model.addAttribute("user", getPrincipal());
		//model.addAttribute("laboratoristas", userService.findAllUsersByRol("LABORATORISTA"));
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		ExamenOrina examenOrina = new ExamenOrina();
		model.addAttribute("examenOrina", examenOrina);
		model.addAttribute("idExpediente", idExpediente);
		List<ColorOrina> colores = new ArrayList<ColorOrina>();
		for (ColorOrina unColorOrina :ColorOrina.values()){
			colores.add(unColorOrina);
		}
		model.addAttribute("edit", false);
		return "addExamenOrina";
	}
	
	@RequestMapping(value = { "/agregarEXAMEN_ORINA" }, method = RequestMethod.POST)
	public String guardarExamenOrina(@Valid ExamenOrina examenOrina, BindingResult result, 
			ModelMap model) {
		
		if (result.hasErrors()) {
			return "addExamenOrina";
		}
		
		examenOrinaService.saveExamenOrina(examenOrina);
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expediente = expedienteService.findById(examenOrina.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Examen Orina Numero: " + examenOrina.getId() + " creado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/imprimirEXAMEN_ORINA" }, method = RequestMethod.GET)
	public String imprimirExamenOrina(HttpServletResponse response, ModelMap model, @RequestParam("idEXAMEN_ORINA") int idLaboratorio) throws JRException, IOException {
		model.addAttribute("user", getPrincipal());
		ExamenOrina examenOrina = examenOrinaService.findById(idLaboratorio);
		ExpedienteLaboratorio expediente = expedienteService.findById(examenOrina.getIdExpediente());
		model.addAttribute("expediente", expediente);
		User quimicoBiologo = userService.findById(examenOrina.getIdQuimicoBiologo());
		
		List<ParametroExportacion> exportacion= new ArrayList<ParametroExportacion>();
		ParametroExportacion mpe = new ParametroExportacion();
		if(mpe.getParameters() == null){
			mpe.setParameters(new HashMap<String,Object>());
		}
		exportacion.add(mpe);
		JasperReport report = JasperCompileManager.compileReport(new ClassPathResource("jrxml/ExamenOrina.jrxml").getInputStream());
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("logoUSALUD", new ClassPathResource("jrxml/logo_usalud.png").getInputStream());
		params.put("logoUSAC", new ClassPathResource("jrxml/logo_usac.png").getInputStream());
		params.put("titulo", "Examen de Orina".toUpperCase());
		params.put("nombrePaciente", expediente.getNombres().toUpperCase()+" "+expediente.getApellidos().toUpperCase()+" ");
		Date fecha = examenOrina.getFechaLaboratorio().toDate();
		params.put("fecha", new SimpleDateFormat("dd/MM/yyyy").format(fecha));
		params.put("codigoPaciente", expediente.getCarne().toString().toUpperCase()+" ");
		params.put("aspecto", (examenOrina.getAspecto()==null)?"":examenOrina.getAspecto().toString());
		params.put("bacterias", (examenOrina.getBacterias()==null)?"":examenOrina.getBacterias().toString());
		params.put("bioquimico", (examenOrina.getBioquimico()==null)?"":examenOrina.getBioquimico().toString());
		params.put("celulasEpiteliales", (examenOrina.getCelulasEpiteliales()==null)?"":examenOrina.getCelulasEpiteliales() .toString());
		params.put("cilindros", (examenOrina.getCilindros()==null)?"":examenOrina.getCilindros() .toString());
		params.put("color", (examenOrina.getColor()==null)?"":examenOrina.getColor() .toString());
		params.put("cristales", (examenOrina.getCristales()==null)?"":examenOrina.getCristales() .toString());
		params.put("densidad", (examenOrina.getDensidad()==null)?"":examenOrina.getDensidad() .toString());
		params.put("eritrositos", (examenOrina.getEritrositos()==null)?"":examenOrina.getEritrositos() .toString());
		params.put("leucositos", (examenOrina.getLeucositos()==null)?"":examenOrina.getLeucositos() .toString());
		params.put("moco", (examenOrina.getMoco()==null)?"":examenOrina.getMoco() .toString());
		params.put("otroColor", (examenOrina.getOtroColor()==null)?"":examenOrina.getOtroColor() .toString());
		params.put("otros", (examenOrina.getOtros()==null)?"":examenOrina.getOtros() .toString());
		params.put("ph", (examenOrina.getPh()==null)?"": UtilsSarel.darFormatoANumero(examenOrina.getPh(),2));
		params.put("textoBacterias", (examenOrina.getTextoBacterias()==null)?"":examenOrina.getTextoBacterias() .toString());
		params.put("textoCilindros", (examenOrina.getTextoCilindros()==null)?"":examenOrina.getTextoCilindros() .toString());
		params.put("textoOtros", (examenOrina.getTextoOtros()==null)?"":examenOrina.getTextoOtros() .toString());
		params.put("quimicoBiologo", quimicoBiologo.getFirstName().toUpperCase()+" "+quimicoBiologo.getLastName().toUpperCase()+" ");
		params.put("sign", (quimicoBiologo.getUrlSing()==null?null:new ClassPathResource("jrxml/signs/"+quimicoBiologo.getUrlSing()).getInputStream()));
		params.put("sello", new ClassPathResource("jrxml/sello_usalud.png").getInputStream());
		
		FormatoExportacionPruebaLaboratorio formater = new FormatoExportacionPruebaLaboratorio(params);
		params = formater.getParametrosConFormato();
		
		JasperPrint myJRprintReportObject = JasperFillManager.fillReport(report, params, new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(exportacion));
		
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=ExamenOrina_"+expediente.getCarne().toString()+"_"+new SimpleDateFormat("dd-MM-yyyy").format(fecha)+".pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(myJRprintReportObject, outStream);
		
		return null;
	}
	
	/* Fin ExamenOrina */
	
	/* Fin ExamenHeces */

	@Autowired
    ExamenHecesService examenHecesService;
	
	@RequestMapping(value = { "/eliminarEXAMEN_HECES" }, method = RequestMethod.GET)
	public String eliminarExamenHeces(ModelMap model, @RequestParam("idExpediente") int idExpediente, @RequestParam("idEXAMEN_HECES") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		ExamenHeces nuevoExamenHeces = examenHecesService.findById(idPerfil);
		nuevoExamenHeces.setEstado(EstadoResultadoLaboratorio.ELIMINADO);
		examenHecesService.updateExamenHeces(nuevoExamenHeces);
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Examen de Heces Numero: " + idPerfil + " eliminado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/consultarEXAMEN_HECES" }, method = RequestMethod.GET)
	public String consultarExamenHeces(ModelMap model, @RequestParam("idEXAMEN_HECES") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		ExamenHeces examenHeces = examenHecesService.findById(idPerfil);
		model.addAttribute("examenHeces", examenHeces);
		ExpedienteLaboratorio expediente = expedienteService.findById(examenHeces.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("soloConsulta", true);
		return "addExamenHeces";
	}
	
	@RequestMapping(value = { "/editarEXAMEN_HECES" }, method = RequestMethod.GET)
	public String editarExamenHeces(ModelMap model, @RequestParam("idEXAMEN_HECES") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		ExamenHeces examenHeces = examenHecesService.findById(idPerfil);
		ExpedienteLaboratorio expediente = expedienteService.findById(examenHeces.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("examenHeces", examenHeces);
		model.addAttribute("edit", true);
		return "addExamenHeces";
	}
	
	@RequestMapping(value = { "/editarEXAMEN_HECES" }, method = RequestMethod.POST)
	public String modificarExamenHeces(ModelMap model, @Valid ExamenHeces examenHeces, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addExamenHeces";
		}
		
		model.addAttribute("user", getPrincipal());
		examenHecesService.updateExamenHeces(examenHeces);
		ExpedienteLaboratorio expediente = expedienteService.findById(examenHeces.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Examen de Heces Numero: " + examenHeces.getId() + " editado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/agregarEXAMEN_HECES" }, method = RequestMethod.GET)
	public String nuevoExamenHeces(ModelMap model, @RequestParam("idExpediente") int idExpediente) {
		model.addAttribute("user", getPrincipal());
		//model.addAttribute("laboratoristas", userService.findAllUsersByRol("LABORATORISTA"));
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		ExamenHeces examenHeces = new ExamenHeces();
		model.addAttribute("examenHeces", examenHeces);
		model.addAttribute("idExpediente", idExpediente);
		model.addAttribute("edit", false);
		return "addExamenHeces";
	}
	
	@RequestMapping(value = { "/agregarEXAMEN_HECES" }, method = RequestMethod.POST)
	public String guardarExamenHeces(@Valid ExamenHeces examenHeces, BindingResult result, 
			ModelMap model) {
		
		if (result.hasErrors()) {
			return "addExamenHeces";
		}
		
		examenHecesService.saveExamenHeces(examenHeces);
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expediente = expedienteService.findById(examenHeces.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Examen de Heces Numero: " + examenHeces.getId() + " creado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/imprimirEXAMEN_HECES" }, method = RequestMethod.GET)
	public String imprimirExamenHeces(HttpServletResponse response, ModelMap model, @RequestParam("idEXAMEN_HECES") int idLaboratorio) throws JRException, IOException {
		model.addAttribute("user", getPrincipal());
		ExamenHeces examenHeces = examenHecesService.findById(idLaboratorio);
		ExpedienteLaboratorio expediente = expedienteService.findById(examenHeces.getIdExpediente());
		model.addAttribute("expediente", expediente);
		User quimicoBiologo = userService.findById(examenHeces.getIdQuimicoBiologo());
		
		List<ParametroExportacion> exportacion= new ArrayList<ParametroExportacion>();
		ParametroExportacion mpe = new ParametroExportacion();
		if(mpe.getParameters() == null){
			mpe.setParameters(new HashMap<String,Object>());
		}
		exportacion.add(mpe);
		JasperReport report = JasperCompileManager.compileReport(new ClassPathResource("jrxml/ExamenHeces.jrxml").getInputStream());
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("logoUSALUD", new ClassPathResource("jrxml/logo_usalud.png").getInputStream());
		params.put("logoUSAC", new ClassPathResource("jrxml/logo_usac.png").getInputStream());
		params.put("titulo", "Examen de Heces Fecales".toUpperCase());
		params.put("nombrePaciente", expediente.getNombres().toUpperCase()+" "+expediente.getApellidos().toUpperCase()+" ");
		Date fecha = examenHeces.getFechaLaboratorio().toDate();
		params.put("fecha", new SimpleDateFormat("dd/MM/yyyy").format(fecha));
		params.put("codigoPaciente", expediente.getCarne().toString().toUpperCase()+" ");
		params.put("almidones", (examenHeces.getAlmidones() ==null)?"":examenHeces.getAlmidones() .toString());
		params.put("aspecto", (examenHeces.getAspecto() ==null)?"":examenHeces.getAspecto() .toString());
		params.put("fibrasMusculares", (examenHeces.getFibrasMusculares() ==null)?"":examenHeces.getFibrasMusculares() .toString());
		params.put("grasas", (examenHeces.getGrasas() ==null)?"":examenHeces.getGrasas() .toString());
		params.put("jabones", (examenHeces.getJabones() ==null)?"":examenHeces.getJabones() .toString());
		params.put("moco", (examenHeces.getMoco() ==null)?"":examenHeces.getMoco() .toString());
		params.put("otroAspecto", (examenHeces.getOtroAspecto() ==null)?"":examenHeces.getOtroAspecto() .toString());
		params.put("otros", (examenHeces.getOtros() ==null)?"":examenHeces.getOtros() .toString());
		params.put("parasitos", (examenHeces.getParasitos() ==null)?"":examenHeces.getParasitos() .toString());
		params.put("restosAlimenticios", (examenHeces.getRestosAlimenticios() ==null)?"":examenHeces.getRestosAlimenticios() .toString());
		params.put("sangre", (examenHeces.getSangre() ==null)?"":examenHeces.getSangre() .toString());
		params.put("textoOtros", (examenHeces.getTextoOtros() ==null)?"":examenHeces.getTextoOtros() .toString());
		params.put("quimicoBiologo", quimicoBiologo.getFirstName().toUpperCase()+" "+quimicoBiologo.getLastName().toUpperCase()+" ");
		params.put("sign", (quimicoBiologo.getUrlSing()==null?null:new ClassPathResource("jrxml/signs/"+quimicoBiologo.getUrlSing()).getInputStream()));
		params.put("sello", new ClassPathResource("jrxml/sello_usalud.png").getInputStream());
		
		FormatoExportacionPruebaLaboratorio formater = new FormatoExportacionPruebaLaboratorio(params);
		params = formater.getParametrosConFormato();
		
		JasperPrint myJRprintReportObject = JasperFillManager.fillReport(report, params, new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(exportacion));
		
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=ExamenHeces_"+expediente.getCarne().toString()+"_"+new SimpleDateFormat("dd-MM-yyyy").format(fecha)+".pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(myJRprintReportObject, outStream);
		
		return null;
	}
	
	/* Fin ExamenHeces */
	
	/* Inicio PruebaVIH */

	@Autowired
    PruebaVIHService pruebaVIHService;
	
	@RequestMapping(value = { "/buscarPruebaVIH" }, method = RequestMethod.GET)
	public String listaPruebasVIH(ModelMap model, @RequestParam("orientador") String pOrientador, @RequestParam("fechaLaboratorio") String pFecha
			, @RequestParam("codigo") String pCodigo ) {
		model.addAttribute("user", getPrincipal());
		boolean parameters = false;
		List<PruebaVIH> resultados = null;
		Map<String, Object> params = new HashMap<String, Object>();
		if(pOrientador!=null && !pOrientador.isEmpty()){
			params.put("orientador", pOrientador);
			parameters = true;
		}
		if(pFecha!=null && !pFecha.isEmpty()){
			try{
				DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyy");
				LocalDate dt = formatter.parseLocalDate(pFecha);
				params.put("fechaLaboratorio", dt);
				parameters = true;
			}catch(Exception e){}
		}
		if(pCodigo!=null && !pCodigo.isEmpty()){
			params.put("codigo", pCodigo);
			parameters = true;
		}
		if(parameters){
			params.put("estado", EstadoResultadoLaboratorio.ACTIVO);
			resultados = pruebaVIHService.findByCriteria(params);
		}
		model.addAttribute("resultados", resultados);
		return "buscarPruebaVIH";
	}
	
	@RequestMapping(value = { "/eliminarPRUEBA_VIH" }, method = RequestMethod.GET)
	public String eliminarPruebaVIH(ModelMap model, @RequestParam("idPRUEBA_VIH") int idPruebaVIH) {
		model.addAttribute("user", getPrincipal());
		PruebaVIH nuevoPruebaVIH = pruebaVIHService.findById(idPruebaVIH);
		nuevoPruebaVIH.setEstado(EstadoResultadoLaboratorio.ELIMINADO);
		pruebaVIHService.updatePruebaVIH(nuevoPruebaVIH);
		model.addAttribute("message", "Prueba VIH Numero: " + idPruebaVIH + " eliminado Exitosamente...");
		return "welcome";
	}
	
	@RequestMapping(value = { "/consultarPRUEBA_VIH" }, method = RequestMethod.GET)
	public String consultarPruebaVIH(ModelMap model, @RequestParam("idPRUEBA_VIH") int idPruebaVIH) {
		model.addAttribute("user", getPrincipal());
		PruebaVIH pruebaVIH = pruebaVIHService.findById(idPruebaVIH);
		model.addAttribute("pruebaVIH", pruebaVIH);
		model.addAttribute("soloConsulta", true);
		return "addPruebaVIH";
	}
	
	@RequestMapping(value = { "/editarPRUEBA_VIH" }, method = RequestMethod.GET)
	public String editarPruebaVIH(ModelMap model, @RequestParam("idPRUEBA_VIH") int idPruebaVIH) {
		model.addAttribute("user", getPrincipal());
		PruebaVIH pruebaVIH = pruebaVIHService.findById(idPruebaVIH);
		model.addAttribute("pruebaVIH", pruebaVIH);
		model.addAttribute("edit", true);
		return "addPruebaVIH";
	}
	
	@RequestMapping(value = { "/editarPRUEBA_VIH" }, method = RequestMethod.POST)
	public String modificarPruebaVIH(ModelMap model, @Valid PruebaVIH pruebaVIH, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addPruebaVIH";
		}
		
		model.addAttribute("user", getPrincipal());
		pruebaVIHService.updatePruebaVIH(pruebaVIH);
		model.addAttribute("message", "Prueba VIH Numero: " + pruebaVIH.getId() + " editada Exitosamente...");
		return "welcome";
	}
	
	@RequestMapping(value = { "/agregarPRUEBA_VIH" }, method = RequestMethod.GET)
	public String nuevoPruebaVIH(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		PruebaVIH pruebaVIH = new PruebaVIH();
		model.addAttribute("pruebaVIH", pruebaVIH);
		model.addAttribute("edit", false);
		return "addPruebaVIH";
	}
	
	@RequestMapping(value = { "/agregarPRUEBA_VIH" }, method = RequestMethod.POST)
	public String guardarPruebaVIH(@Valid PruebaVIH pruebaVIH, BindingResult result, 
			ModelMap model) {
		
		if (result.hasErrors()) {
			return "addPruebaVIH";
		}

		pruebaVIHService.savePruebaVIH(pruebaVIH);
		model.addAttribute("user", getPrincipal());
		model.addAttribute("message", "Prueba VIH Numero: " + pruebaVIH.getId() + " creado Exitosamente...");
		return "welcome";
	}
	
	@RequestMapping(value = { "/imprimirPRUEBA_VIH" }, method = RequestMethod.GET)
	public String imprimirPruebaVIH(HttpServletResponse response, ModelMap model, @RequestParam("idPRUEBA_VIH") int idLaboratorio) throws JRException, IOException {
		model.addAttribute("user", getPrincipal());
		PruebaVIH pruebaVIH = pruebaVIHService.findById(idLaboratorio);
		User quimicoBiologo = userService.findById(pruebaVIH.getIdQuimicoBiologo());
		
		List<ParametroExportacion> exportacion= new ArrayList<ParametroExportacion>();
		ParametroExportacion mpe = new ParametroExportacion();
		if(mpe.getParameters() == null){
			mpe.setParameters(new HashMap<String,Object>());
		}
		exportacion.add(mpe);
		JasperReport report = JasperCompileManager.compileReport(new ClassPathResource("jrxml/PruebaVIH.jrxml").getInputStream());
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("logoUSALUD", new ClassPathResource("jrxml/logo_usalud.png").getInputStream());
		params.put("logoUSAC", new ClassPathResource("jrxml/logo_usac.png").getInputStream());
		params.put("titulo", "Prueba VIH".toUpperCase());
		params.put("orientador", pruebaVIH.getOrientador().toUpperCase()+" ");
		Date fecha = pruebaVIH.getFechaLaboratorio().toDate();
		params.put("fecha", new SimpleDateFormat("dd/MM/yyyy").format(fecha));
		params.put("codigo", pruebaVIH.getCodigo().toUpperCase()+" ");
		params.put("resultado", pruebaVIH.getResultado()+" ");
		params.put("quimicoBiologo", quimicoBiologo.getFirstName().toUpperCase()+" "+quimicoBiologo.getLastName().toUpperCase()+" ");
		params.put("sign", (quimicoBiologo.getUrlSing()==null?null:new ClassPathResource("jrxml/signs/"+quimicoBiologo.getUrlSing()).getInputStream()));
		params.put("sello", new ClassPathResource("jrxml/sello_usalud.png").getInputStream());
		
		FormatoExportacionPruebaLaboratorio formater = new FormatoExportacionPruebaLaboratorio(params);
		params = formater.getParametrosConFormato();
		
		JasperPrint myJRprintReportObject = JasperFillManager.fillReport(report, params, new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(exportacion));
		
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=PruebaVIH_"+pruebaVIH.getCodigo()+"_"+new SimpleDateFormat("dd-MM-yyyy").format(fecha)+".pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(myJRprintReportObject, outStream);
		
		return null;
	}
	
	/* Fin PruebaVIH */
	
	/* Inicio PruebaDengue */

	@Autowired
    PruebaDengueService pruebaDengueService;
	
	@RequestMapping(value = { "/eliminarPRUEBA_DENGE" }, method = RequestMethod.GET)
	public String eliminarPruebaDengue(ModelMap model, @RequestParam("idExpediente") int idExpediente, @RequestParam("idPRUEBA_DENGE") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebaDengue nuevoPruebaDengue = pruebaDengueService.findById(idPerfil);
		nuevoPruebaDengue.setEstado(EstadoResultadoLaboratorio.ELIMINADO);
		pruebaDengueService.updatePruebaDengue(nuevoPruebaDengue);
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Prueba de Dengue Numero: " + idPerfil + " eliminado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/consultarPRUEBA_DENGE" }, method = RequestMethod.GET)
	public String consultarPruebaDengue(ModelMap model, @RequestParam("idPRUEBA_DENGE") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebaDengue pruebaDengue = pruebaDengueService.findById(idPerfil);
		model.addAttribute("pruebaDengue", pruebaDengue);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaDengue.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("soloConsulta", true);
		return "addPruebaDengue";
	}
	
	@RequestMapping(value = { "/editarPRUEBA_DENGE" }, method = RequestMethod.GET)
	public String editarPruebaDengue(ModelMap model, @RequestParam("idPRUEBA_DENGE") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebaDengue pruebaDengue = pruebaDengueService.findById(idPerfil);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaDengue.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("pruebaDengue", pruebaDengue);
		model.addAttribute("edit", true);
		return "addPruebaDengue";
	}
	
	@RequestMapping(value = { "/editarPRUEBA_DENGE" }, method = RequestMethod.POST)
	public String modificarPruebaDengue(ModelMap model, @Valid PruebaDengue pruebaDengue, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addPruebaDengue";
		}
		
		model.addAttribute("user", getPrincipal());
		pruebaDengueService.updatePruebaDengue(pruebaDengue);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaDengue.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Prueba de Dengue Numero: " + pruebaDengue.getId() + " editado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/agregarPRUEBA_DENGE" }, method = RequestMethod.GET)
	public String nuevoPruebaDengue(ModelMap model, @RequestParam("idExpediente") int idExpediente) {
		model.addAttribute("user", getPrincipal());
		//model.addAttribute("laboratoristas", userService.findAllUsersByRol("LABORATORISTA"));
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		PruebaDengue pruebaDengue = new PruebaDengue();
		model.addAttribute("pruebaDengue", pruebaDengue);
		model.addAttribute("idExpediente", idExpediente);
		model.addAttribute("edit", false);
		return "addPruebaDengue";
	}
	
	@RequestMapping(value = { "/agregarPRUEBA_DENGE" }, method = RequestMethod.POST)
	public String guardarPruebaDengue(@Valid PruebaDengue pruebaDengue, BindingResult result, 
			ModelMap model) {
		
		if (result.hasErrors()) {
			return "addPruebaDengue";
		}
		
		pruebaDengueService.savePruebaDengue(pruebaDengue);
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaDengue.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Prueba de Dengue Numero: " + pruebaDengue.getId() + " creado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/imprimirPRUEBA_DENGE" }, method = RequestMethod.GET)
	public String imprimirPruebaDengue(HttpServletResponse response, ModelMap model, @RequestParam("idPRUEBA_DENGE") int idLaboratorio) throws JRException, IOException {
		model.addAttribute("user", getPrincipal());
		PruebaDengue pruebaDengue = pruebaDengueService.findById(idLaboratorio);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaDengue.getIdExpediente());
		model.addAttribute("expediente", expediente);
		User quimicoBiologo = userService.findById(pruebaDengue.getIdQuimicoBiologo());
		
		List<ParametroExportacion> exportacion= new ArrayList<ParametroExportacion>();
		ParametroExportacion mpe = new ParametroExportacion();
		if(mpe.getParameters() == null){
			mpe.setParameters(new HashMap<String,Object>());
		}
		exportacion.add(mpe);
		JasperReport report = JasperCompileManager.compileReport(new ClassPathResource("jrxml/PruebaDengue.jrxml").getInputStream());
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("logoUSALUD", new ClassPathResource("jrxml/logo_usalud.png").getInputStream());
		params.put("logoUSAC", new ClassPathResource("jrxml/logo_usac.png").getInputStream());
		params.put("titulo", "Prueba de Dengue".toUpperCase());
		params.put("nombrePaciente", expediente.getNombres().toUpperCase()+" "+expediente.getApellidos().toUpperCase()+" ");
		Date fecha = pruebaDengue.getFechaLaboratorio().toDate();
		params.put("fecha", new SimpleDateFormat("dd/MM/yyyy").format(fecha));
		params.put("codigoPaciente", expediente.getCarne().toString().toUpperCase()+" ");
		params.put("resultado", pruebaDengue.getResultado());
		params.put("tipoDengue", (pruebaDengue.getTipoDengue()==null)?"":pruebaDengue.getTipoDengue().toString());
		params.put("quimicoBiologo", quimicoBiologo.getFirstName().toUpperCase()+" "+quimicoBiologo.getLastName().toUpperCase()+" ");
		
		FormatoExportacionPruebaLaboratorio formater = new FormatoExportacionPruebaLaboratorio(params);
		params = formater.getParametrosConFormato();
		
		JasperPrint myJRprintReportObject = JasperFillManager.fillReport(report, params, new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(exportacion));
		
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=PruebaDengue_"+expediente.getCarne().toString()+"_"+new SimpleDateFormat("dd-MM-yyyy").format(fecha)+".pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(myJRprintReportObject, outStream);
		
		return null;
	}
	/* Fin PruebaDengue */
	
	/* Inicio HelicobacterPylori */

	@Autowired
    HelicobacterPyloriService helicobacterPyloriService;
	
	@RequestMapping(value = { "/eliminarHELICOBACTER_PYLORI" }, method = RequestMethod.GET)
	public String eliminarHelicobacterPylori(ModelMap model, @RequestParam("idExpediente") int idExpediente, @RequestParam("idHELICOBACTER_PYLORI") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		HelicobacterPylori nuevoHelicobacterPylori = helicobacterPyloriService.findById(idPerfil);
		nuevoHelicobacterPylori.setEstado(EstadoResultadoLaboratorio.ELIMINADO);
		helicobacterPyloriService.updateHelicobacterPylori(nuevoHelicobacterPylori);
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Helicobacter Pylori Numero: " + idPerfil + " eliminado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/consultarHELICOBACTER_PYLORI" }, method = RequestMethod.GET)
	public String consultarHelicobacterPylori(ModelMap model, @RequestParam("idHELICOBACTER_PYLORI") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		HelicobacterPylori helicobacterPylori = helicobacterPyloriService.findById(idPerfil);
		model.addAttribute("helicobacterPylori", helicobacterPylori);
		ExpedienteLaboratorio expediente = expedienteService.findById(helicobacterPylori.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("soloConsulta", true);
		return "addHelicobacterPylori";
	}
	
	@RequestMapping(value = { "/editarHELICOBACTER_PYLORI" }, method = RequestMethod.GET)
	public String editarHelicobacterPylori(ModelMap model, @RequestParam("idHELICOBACTER_PYLORI") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		HelicobacterPylori helicobacterPylori = helicobacterPyloriService.findById(idPerfil);
		ExpedienteLaboratorio expediente = expedienteService.findById(helicobacterPylori.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("helicobacterPylori", helicobacterPylori);
		model.addAttribute("edit", true);
		return "addHelicobacterPylori";
	}
	
	@RequestMapping(value = { "/editarHELICOBACTER_PYLORI" }, method = RequestMethod.POST)
	public String modificarHelicobacterPylori(ModelMap model, @Valid HelicobacterPylori helicobacterPylori, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addHelicobacterPylori";
		}
		
		model.addAttribute("user", getPrincipal());
		helicobacterPyloriService.updateHelicobacterPylori(helicobacterPylori);
		ExpedienteLaboratorio expediente = expedienteService.findById(helicobacterPylori.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Helicobacter Pylori Numero: " + helicobacterPylori.getId() + " editado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/agregarHELICOBACTER_PYLORI" }, method = RequestMethod.GET)
	public String nuevoHelicobacterPylori(ModelMap model, @RequestParam("idExpediente") int idExpediente) {
		model.addAttribute("user", getPrincipal());
		//model.addAttribute("laboratoristas", userService.findAllUsersByRol("LABORATORISTA"));
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		HelicobacterPylori helicobacterPylori = new HelicobacterPylori();
		model.addAttribute("helicobacterPylori", helicobacterPylori);
		model.addAttribute("idExpediente", idExpediente);
		model.addAttribute("edit", false);
		return "addHelicobacterPylori";
	}
	
	@RequestMapping(value = { "/agregarHELICOBACTER_PYLORI" }, method = RequestMethod.POST)
	public String guardarHelicobacterPylori(@Valid HelicobacterPylori helicobacterPylori, BindingResult result, 
			ModelMap model) {
		
		if (result.hasErrors()) {
			return "addHelicobacterPylori";
		}
		
		helicobacterPyloriService.saveHelicobacterPylori(helicobacterPylori);
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expediente = expedienteService.findById(helicobacterPylori.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Helicobacter Pylori Numero: " + helicobacterPylori.getId() + " creado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/imprimirHELICOBACTER_PYLORI" }, method = RequestMethod.GET)
	public String imprimirHelicobacterPylori(HttpServletResponse response, ModelMap model, @RequestParam("idHELICOBACTER_PYLORI") int idLaboratorio) throws JRException, IOException {
		model.addAttribute("user", getPrincipal());
		HelicobacterPylori helicobacterPylori = helicobacterPyloriService.findById(idLaboratorio);
		ExpedienteLaboratorio expediente = expedienteService.findById(helicobacterPylori.getIdExpediente());
		model.addAttribute("expediente", expediente);
		User quimicoBiologo = userService.findById(helicobacterPylori.getIdQuimicoBiologo());
		
		List<ParametroExportacion> exportacion= new ArrayList<ParametroExportacion>();
		ParametroExportacion mpe = new ParametroExportacion();
		if(mpe.getParameters() == null){
			mpe.setParameters(new HashMap<String,Object>());
		}
		exportacion.add(mpe);
		JasperReport report = JasperCompileManager.compileReport(new ClassPathResource("jrxml/HelicobacterPylori.jrxml").getInputStream());
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("logoUSALUD", new ClassPathResource("jrxml/logo_usalud.png").getInputStream());
		params.put("logoUSAC", new ClassPathResource("jrxml/logo_usac.png").getInputStream());
		params.put("titulo", "Helicobacter Pylori".toUpperCase());
		params.put("nombrePaciente", expediente.getNombres().toUpperCase()+" "+expediente.getApellidos().toUpperCase()+" ");
		Date fecha = helicobacterPylori.getFechaLaboratorio().toDate();
		params.put("fecha", new SimpleDateFormat("dd/MM/yyyy").format(fecha));
		params.put("resultado", helicobacterPylori.getResultado());
		params.put("codigoPaciente", expediente.getCarne().toString().toUpperCase()+" ");
		params.put("quimicoBiologo", quimicoBiologo.getFirstName().toUpperCase()+" "+quimicoBiologo.getLastName().toUpperCase()+" ");
		
		FormatoExportacionPruebaLaboratorio formater = new FormatoExportacionPruebaLaboratorio(params);
		params = formater.getParametrosConFormato();
		
		JasperPrint myJRprintReportObject = JasperFillManager.fillReport(report, params, new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(exportacion));
		
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=HelicobacterPylori_"+expediente.getCarne().toString()+"_"+new SimpleDateFormat("dd-MM-yyyy").format(fecha)+".pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(myJRprintReportObject, outStream);
		
		return null;
	}
	/* Fin HelicobacterPylori */
	
	/* Inicio HemoglobinaGlucosa */

	@Autowired
    HemoglobinaGlucosaService hemoglobinaGlucosaService;
	
	@RequestMapping(value = { "/eliminarHEMOGLOBINA_GLUCOSA" }, method = RequestMethod.GET)
	public String eliminarHemoglobinaGlucosa(ModelMap model, @RequestParam("idExpediente") int idExpediente, @RequestParam("idHEMOGLOBINA_GLUCOSA") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		HemoglobinaGlucosa nuevoHemoglobinaGlucosa = hemoglobinaGlucosaService.findById(idPerfil);
		nuevoHemoglobinaGlucosa.setEstado(EstadoResultadoLaboratorio.ELIMINADO);
		hemoglobinaGlucosaService.updateHemoglobinaGlucosa(nuevoHemoglobinaGlucosa);
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Hemoglobina y Glucosa Numero: " + idPerfil + " eliminado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/consultarHEMOGLOBINA_GLUCOSA" }, method = RequestMethod.GET)
	public String consultarHemoglobinaGlucosa(ModelMap model, @RequestParam("idHEMOGLOBINA_GLUCOSA") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		HemoglobinaGlucosa hemoglobinaGlucosa = hemoglobinaGlucosaService.findById(idPerfil);
		model.addAttribute("hemoglobinaGlucosa", hemoglobinaGlucosa);
		ExpedienteLaboratorio expediente = expedienteService.findById(hemoglobinaGlucosa.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("soloConsulta", true);
		return "addHemoglobinaGlucosa";
	}
	
	@RequestMapping(value = { "/editarHEMOGLOBINA_GLUCOSA" }, method = RequestMethod.GET)
	public String editarHemoglobinaGlucosa(ModelMap model, @RequestParam("idHEMOGLOBINA_GLUCOSA") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		HemoglobinaGlucosa hemoglobinaGlucosa = hemoglobinaGlucosaService.findById(idPerfil);
		ExpedienteLaboratorio expediente = expedienteService.findById(hemoglobinaGlucosa.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("hemoglobinaGlucosa", hemoglobinaGlucosa);
		model.addAttribute("edit", true);
		return "addHemoglobinaGlucosa";
	}
	
	@RequestMapping(value = { "/editarHEMOGLOBINA_GLUCOSA" }, method = RequestMethod.POST)
	public String modificarHemoglobinaGlucosa(ModelMap model, @Valid HemoglobinaGlucosa hemoglobinaGlucosa, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addHemoglobinaGlucosa";
		}
		
		model.addAttribute("user", getPrincipal());
		hemoglobinaGlucosaService.updateHemoglobinaGlucosa(hemoglobinaGlucosa);
		ExpedienteLaboratorio expediente = expedienteService.findById(hemoglobinaGlucosa.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Hemoglobina y Glucosa Numero: " + hemoglobinaGlucosa.getId() + " editado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/agregarHEMOGLOBINA_GLUCOSA" }, method = RequestMethod.GET)
	public String nuevoHemoglobinaGlucosa(ModelMap model, @RequestParam("idExpediente") int idExpediente) {
		model.addAttribute("user", getPrincipal());
		//model.addAttribute("laboratoristas", userService.findAllUsersByRol("LABORATORISTA"));
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		HemoglobinaGlucosa hemoglobinaGlucosa = new HemoglobinaGlucosa();
		model.addAttribute("hemoglobinaGlucosa", hemoglobinaGlucosa);
		model.addAttribute("idExpediente", idExpediente);
		model.addAttribute("edit", false);
		return "addHemoglobinaGlucosa";
	}
	
	@RequestMapping(value = { "/agregarHEMOGLOBINA_GLUCOSA" }, method = RequestMethod.POST)
	public String guardarHemoglobinaGlucosa(@Valid HemoglobinaGlucosa hemoglobinaGlucosa, BindingResult result, 
			ModelMap model) {
		
		if (result.hasErrors()) {
			return "addHemoglobinaGlucosa";
		}
		
		hemoglobinaGlucosaService.saveHemoglobinaGlucosa(hemoglobinaGlucosa);
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expediente = expedienteService.findById(hemoglobinaGlucosa.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Hemoglobina y Glucosa Numero: " + hemoglobinaGlucosa.getId() + " creado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/imprimirHEMOGLOBINA_GLUCOSA" }, method = RequestMethod.GET)
	public String imprimirHemoglobinaGlucosa(HttpServletResponse response, ModelMap model, @RequestParam("idHEMOGLOBINA_GLUCOSA") int idLaboratorio) throws JRException, IOException {
		model.addAttribute("user", getPrincipal());
		HemoglobinaGlucosa hemoglobinaGlucosa = hemoglobinaGlucosaService.findById(idLaboratorio);
		ExpedienteLaboratorio expediente = expedienteService.findById(hemoglobinaGlucosa.getIdExpediente());
		model.addAttribute("expediente", expediente);
		User quimicoBiologo = userService.findById(hemoglobinaGlucosa.getIdQuimicoBiologo());
		
		List<ParametroExportacion> exportacion= new ArrayList<ParametroExportacion>();
		ParametroExportacion mpe = new ParametroExportacion();
		if(mpe.getParameters() == null){
			mpe.setParameters(new HashMap<String,Object>());
		}
		exportacion.add(mpe);
		JasperReport report = JasperCompileManager.compileReport(new ClassPathResource("jrxml/HemoglobinaGlucosa.jrxml").getInputStream());
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("logoUSALUD", new ClassPathResource("jrxml/logo_usalud.png").getInputStream());
		params.put("logoUSAC", new ClassPathResource("jrxml/logo_usac.png").getInputStream());
		params.put("titulo", "Hemoglobina y Glucosa".toUpperCase());
		params.put("nombrePaciente", expediente.getNombres().toUpperCase()+" "+expediente.getApellidos().toUpperCase()+" ");
		Date fecha = hemoglobinaGlucosa.getFechaLaboratorio().toDate();
		params.put("fecha", new SimpleDateFormat("dd/MM/yyyy").format(fecha));
		params.put("hemoglobinaGlicosilada", (hemoglobinaGlucosa.getHemoglobinaGlicosilada() ==null)?"":hemoglobinaGlucosa.getHemoglobinaGlicosilada().toString());
		params.put("nivelPromedioGlucosa", (hemoglobinaGlucosa.getNivelPromedioGlucosa()==null)?"":hemoglobinaGlucosa.getNivelPromedioGlucosa().toString());
		params.put("codigoPaciente", expediente.getCarne().toString().toUpperCase()+" ");
		params.put("quimicoBiologo", quimicoBiologo.getFirstName().toUpperCase()+" "+quimicoBiologo.getLastName().toUpperCase()+" ");
		
		FormatoExportacionPruebaLaboratorio formater = new FormatoExportacionPruebaLaboratorio(params);
		params = formater.getParametrosConFormato();
		
		JasperPrint myJRprintReportObject = JasperFillManager.fillReport(report, params, new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(exportacion));
		
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=HemoglobinaGlucosa_"+expediente.getCarne().toString()+"_"+new SimpleDateFormat("dd-MM-yyyy").format(fecha)+".pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(myJRprintReportObject, outStream);
		
		return null;
	}
	/* Fin HemoglobinaGlucosa */
	
	
	/* Inicio PruebaTiroidea */

	@Autowired
    	PruebaTiroideaService pruebaTiroideaService;
	
	@RequestMapping(value = { "/eliminarPRUEBAS_TIROIDEAS" }, method = RequestMethod.GET)
	public String eliminarPruebaTiroidea(ModelMap model, @RequestParam("idExpediente") int idExpediente, @RequestParam("idPRUEBAS_TIROIDEAS") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebaTiroidea nuevoPruebaTiroidea = pruebaTiroideaService.findById(idPerfil);
		nuevoPruebaTiroidea.setEstado(EstadoResultadoLaboratorio.ELIMINADO);
		pruebaTiroideaService.updatePruebaTiroidea(nuevoPruebaTiroidea);
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Pruebas Tiroideas Numero: " + idPerfil + " eliminado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/consultarPRUEBAS_TIROIDEAS" }, method = RequestMethod.GET)
	public String consultarPruebaTiroidea(ModelMap model, @RequestParam("idPRUEBAS_TIROIDEAS") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebaTiroidea pruebaTiroidea = pruebaTiroideaService.findById(idPerfil);
		model.addAttribute("pruebaTiroidea", pruebaTiroidea);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaTiroidea.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("soloConsulta", true);
		return "addPruebaTiroidea";
	}
	
	@RequestMapping(value = { "/editarPRUEBAS_TIROIDEAS" }, method = RequestMethod.GET)
	public String editarPruebaTiroidea(ModelMap model, @RequestParam("idPRUEBAS_TIROIDEAS") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebaTiroidea pruebaTiroidea = pruebaTiroideaService.findById(idPerfil);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaTiroidea.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("pruebaTiroidea", pruebaTiroidea);
		model.addAttribute("edit", true);
		return "addPruebaTiroidea";
	}
	
	@RequestMapping(value = { "/editarPRUEBAS_TIROIDEAS" }, method = RequestMethod.POST)
	public String modificarPruebaTiroidea(ModelMap model, @Valid PruebaTiroidea pruebaTiroidea, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addPruebaTiroidea";
		}
		
		model.addAttribute("user", getPrincipal());
		pruebaTiroideaService.updatePruebaTiroidea(pruebaTiroidea);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaTiroidea.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Pruebas Tiroideas Numero: " + pruebaTiroidea.getId() + " editado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/agregarPRUEBAS_TIROIDEAS" }, method = RequestMethod.GET)
	public String nuevoPruebaTiroidea(ModelMap model, @RequestParam("idExpediente") int idExpediente) {
		model.addAttribute("user", getPrincipal());
		//model.addAttribute("laboratoristas", userService.findAllUsersByRol("LABORATORISTA"));
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		PruebaTiroidea pruebaTiroidea = new PruebaTiroidea();
		model.addAttribute("pruebaTiroidea", pruebaTiroidea);
		model.addAttribute("idExpediente", idExpediente);
		model.addAttribute("edit", false);
		return "addPruebaTiroidea";
	}
	
	@RequestMapping(value = { "/agregarPRUEBAS_TIROIDEAS" }, method = RequestMethod.POST)
	public String guardarPruebaTiroidea(@Valid PruebaTiroidea pruebaTiroidea, BindingResult result, 
			ModelMap model) {
		
		if (result.hasErrors()) {
			return "addPruebaTiroidea";
		}
		
		if(pruebaTiroidea.getT3() == null && pruebaTiroidea.getT4() == null){
			model.addAttribute("alerta", "Debe ingresar al menos un resultado para poder guardar...");
			return "addPruebaTiroidea";
		}
		
		pruebaTiroideaService.savePruebaTiroidea(pruebaTiroidea);
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaTiroidea.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Perfil Lipidico Numero: " + pruebaTiroidea.getId() + " creado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/imprimirPRUEBAS_TIROIDEAS" }, method = RequestMethod.GET)
	public String imprimirPruebaTiroidea(HttpServletResponse response, ModelMap model, @RequestParam("idPRUEBAS_TIROIDEAS") int idLaboratorio) throws JRException, IOException {
		model.addAttribute("user", getPrincipal());
		PruebaTiroidea pruebaTiroidea = pruebaTiroideaService.findById(idLaboratorio);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaTiroidea.getIdExpediente());
		model.addAttribute("expediente", expediente);
		User quimicoBiologo = userService.findById(pruebaTiroidea.getIdQuimicoBiologo());
		
		List<ParametroExportacion> exportacion= new ArrayList<ParametroExportacion>();
		ParametroExportacion mpe = new ParametroExportacion();
		if(mpe.getParameters() == null){
			mpe.setParameters(new HashMap<String,Object>());
		}
		exportacion.add(mpe);
		JasperReport report = JasperCompileManager.compileReport(new ClassPathResource("jrxml/PruebaTiroidea.jrxml").getInputStream());
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("logoUSALUD", new ClassPathResource("jrxml/logo_usalud.png").getInputStream());
		params.put("logoUSAC", new ClassPathResource("jrxml/logo_usac.png").getInputStream());
		params.put("titulo", "Pruebas de función Tiroidea".toUpperCase());
		params.put("nombrePaciente", expediente.getNombres().toUpperCase()+" "+expediente.getApellidos().toUpperCase()+" ");
		Date fecha = pruebaTiroidea.getFechaLaboratorio().toDate();
		params.put("fecha", new SimpleDateFormat("dd/MM/yyyy").format(fecha));
		params.put("t3", (pruebaTiroidea.getT3()==null)?"": UtilsSarel.darFormatoANumero(pruebaTiroidea.getT3(),2));
		params.put("t4", (pruebaTiroidea.getT4()==null)?"": UtilsSarel.darFormatoANumero(pruebaTiroidea.getT4(),2));
		params.put("codigoPaciente", expediente.getCarne().toString().toUpperCase()+" ");
		params.put("quimicoBiologo", quimicoBiologo.getFirstName().toUpperCase()+" "+quimicoBiologo.getLastName().toUpperCase()+" ");
		JasperPrint myJRprintReportObject = JasperFillManager.fillReport(report, params, new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(exportacion));
		
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=PruebaTiroidea_"+expediente.getCarne().toString()+"_"+new SimpleDateFormat("dd-MM-yyyy").format(fecha)+".pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(myJRprintReportObject, outStream);
		
		return null;
	}
	/* Fin PruebaTiroidea */
	
	/* Inicio AntigenoProstatico */

	@Autowired
    	AntigenoProstaticoService antigenoProstaticoService;
	
	@RequestMapping(value = { "/eliminarANTIGENO_PROSTATICO" }, method = RequestMethod.GET)
	public String eliminarAntigenoProstatico(ModelMap model, @RequestParam("idExpediente") int idExpediente, @RequestParam("idANTIGENO_PROSTATICO") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		AntigenoProstatico nuevoAntigenoProstatico = antigenoProstaticoService.findById(idPerfil);
		nuevoAntigenoProstatico.setEstado(EstadoResultadoLaboratorio.ELIMINADO);
		antigenoProstaticoService.updateAntigenoProstatico(nuevoAntigenoProstatico);
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Perfil Lipido Numero: " + idPerfil + " eliminado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/consultarANTIGENO_PROSTATICO" }, method = RequestMethod.GET)
	public String consultarAntigenoProstatico(ModelMap model, @RequestParam("idANTIGENO_PROSTATICO") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		AntigenoProstatico antigenoProstatico = antigenoProstaticoService.findById(idPerfil);
		model.addAttribute("antigenoProstatico", antigenoProstatico);
		ExpedienteLaboratorio expediente = expedienteService.findById(antigenoProstatico.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("soloConsulta", true);
		return "addAntigenoProstatico";
	}
	
	@RequestMapping(value = { "/editarANTIGENO_PROSTATICO" }, method = RequestMethod.GET)
	public String editarAntigenoProstatico(ModelMap model, @RequestParam("idANTIGENO_PROSTATICO") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		AntigenoProstatico antigenoProstatico = antigenoProstaticoService.findById(idPerfil);
		ExpedienteLaboratorio expediente = expedienteService.findById(antigenoProstatico.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("antigenoProstatico", antigenoProstatico);
		model.addAttribute("edit", true);
		return "addAntigenoProstatico";
	}
	
	@RequestMapping(value = { "/editarANTIGENO_PROSTATICO" }, method = RequestMethod.POST)
	public String modificarAntigenoProstatico(ModelMap model, @Valid AntigenoProstatico antigenoProstatico, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addAntigenoProstatico";
		}
		
		model.addAttribute("user", getPrincipal());
		antigenoProstaticoService.updateAntigenoProstatico(antigenoProstatico);
		ExpedienteLaboratorio expediente = expedienteService.findById(antigenoProstatico.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Perfil Lipido Numero: " + antigenoProstatico.getId() + " editado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/agregarANTIGENO_PROSTATICO" }, method = RequestMethod.GET)
	public String nuevoAntigenoProstatico(ModelMap model, @RequestParam("idExpediente") int idExpediente) {
		model.addAttribute("user", getPrincipal());
		//model.addAttribute("laboratoristas", userService.findAllUsersByRol("LABORATORISTA"));
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		AntigenoProstatico antigenoProstatico = new AntigenoProstatico();
		model.addAttribute("antigenoProstatico", antigenoProstatico);
		model.addAttribute("idExpediente", idExpediente);
		model.addAttribute("edit", false);
		return "addAntigenoProstatico";
	}
	
	@RequestMapping(value = { "/agregarANTIGENO_PROSTATICO" }, method = RequestMethod.POST)
	public String guardarAntigenoProstatico(@Valid AntigenoProstatico antigenoProstatico, BindingResult result, 
			ModelMap model) {
		
		if (result.hasErrors()) {
			return "addAntigenoProstatico";
		}
		
		antigenoProstaticoService.saveAntigenoProstatico(antigenoProstatico);
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expediente = expedienteService.findById(antigenoProstatico.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Antigeno Prostatico Numero: " + antigenoProstatico.getId() + " creado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/imprimirANTIGENO_PROSTATICO" }, method = RequestMethod.GET)
	public String imprimirAntigenoProstatico(HttpServletResponse response, ModelMap model, @RequestParam("idANTIGENO_PROSTATICO") int idLaboratorio) throws JRException, IOException {
		model.addAttribute("user", getPrincipal());
		AntigenoProstatico antigenoProstatico = antigenoProstaticoService.findById(idLaboratorio);
		ExpedienteLaboratorio expediente = expedienteService.findById(antigenoProstatico.getIdExpediente());
		model.addAttribute("expediente", expediente);
		User quimicoBiologo = userService.findById(antigenoProstatico.getIdQuimicoBiologo());
		
		List<ParametroExportacion> exportacion= new ArrayList<ParametroExportacion>();
		ParametroExportacion mpe = new ParametroExportacion();
		if(mpe.getParameters() == null){
			mpe.setParameters(new HashMap<String,Object>());
		}
		exportacion.add(mpe);
		JasperReport report = JasperCompileManager.compileReport(new ClassPathResource("jrxml/AntigenoProstatico.jrxml").getInputStream());
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("logoUSALUD", new ClassPathResource("jrxml/logo_usalud.png").getInputStream());
		params.put("logoUSAC", new ClassPathResource("jrxml/logo_usac.png").getInputStream());
		params.put("titulo", "PRUEBA DE ANTIGENO PROSTÁTICO ESPECÍFICO".toUpperCase());
		params.put("nombrePaciente", expediente.getNombres().toUpperCase()+" "+expediente.getApellidos().toUpperCase()+" ");
		Date fecha = antigenoProstatico.getFechaLaboratorio().toDate();
		params.put("fecha", new SimpleDateFormat("dd/MM/yyyy").format(fecha));
		params.put("resultado", (antigenoProstatico.getResultado()==null)?"": UtilsSarel.darFormatoANumero(antigenoProstatico.getResultado(),2));
		params.put("codigoPaciente", expediente.getCarne().toString().toUpperCase()+" ");
		params.put("quimicoBiologo", quimicoBiologo.getFirstName().toUpperCase()+" "+quimicoBiologo.getLastName().toUpperCase()+" ");

		FormatoExportacionPruebaLaboratorio formater = new FormatoExportacionPruebaLaboratorio(params);
		params = formater.getParametrosConFormato();

		JasperPrint myJRprintReportObject = JasperFillManager.fillReport(report, params, new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(exportacion));
		
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=AntigenoProstatico_"+expediente.getCarne().toString()+"_"+new SimpleDateFormat("dd-MM-yyyy").format(fecha)+".pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(myJRprintReportObject, outStream);
		
		return null;
	}
	/* Fin AntigenoProstatico */
	
	
	/* Inicio PruebaTSH */

	@Autowired
    	PruebaTSHService pruebaTSHService;
	
	@RequestMapping(value = { "/eliminarPRUEBA_TSH" }, method = RequestMethod.GET)
	public String eliminarPruebaTSH(ModelMap model, @RequestParam("idExpediente") int idExpediente, @RequestParam("idPRUEBA_TSH") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebaTSH nuevoPruebaTSH = pruebaTSHService.findById(idPerfil);
		nuevoPruebaTSH.setEstado(EstadoResultadoLaboratorio.ELIMINADO);
		pruebaTSHService.updatePruebaTSH(nuevoPruebaTSH);
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Perfil Lipido Numero: " + idPerfil + " eliminado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/consultarPRUEBA_TSH" }, method = RequestMethod.GET)
	public String consultarPruebaTSH(ModelMap model, @RequestParam("idPRUEBA_TSH") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebaTSH pruebaTSH = pruebaTSHService.findById(idPerfil);
		model.addAttribute("pruebaTSH", pruebaTSH);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaTSH.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("soloConsulta", true);
		return "addPruebaTSH";
	}
	
	@RequestMapping(value = { "/editarPRUEBA_TSH" }, method = RequestMethod.GET)
	public String editarPruebaTSH(ModelMap model, @RequestParam("idPRUEBA_TSH") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebaTSH pruebaTSH = pruebaTSHService.findById(idPerfil);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaTSH.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("pruebaTSH", pruebaTSH);
		model.addAttribute("edit", true);
		return "addPruebaTSH";
	}
	
	@RequestMapping(value = { "/editarPRUEBA_TSH" }, method = RequestMethod.POST)
	public String modificarPruebaTSH(ModelMap model, @Valid PruebaTSH pruebaTSH, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addPruebaTSH";
		}
		
		model.addAttribute("user", getPrincipal());
		pruebaTSHService.updatePruebaTSH(pruebaTSH);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaTSH.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Perfil Lipido Numero: " + pruebaTSH.getId() + " editado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/agregarPRUEBA_TSH" }, method = RequestMethod.GET)
	public String nuevoPruebaTSH(ModelMap model, @RequestParam("idExpediente") int idExpediente) {
		model.addAttribute("user", getPrincipal());
		//model.addAttribute("laboratoristas", userService.findAllUsersByRol("LABORATORISTA"));
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		PruebaTSH pruebaTSH = new PruebaTSH();
		model.addAttribute("pruebaTSH", pruebaTSH);
		model.addAttribute("idExpediente", idExpediente);
		model.addAttribute("edit", false);
		return "addPruebaTSH";
	}
	
	@RequestMapping(value = { "/agregarPRUEBA_TSH" }, method = RequestMethod.POST)
	public String guardarPruebaTSH(@Valid PruebaTSH pruebaTSH, BindingResult result, 
			ModelMap model) {
		
		if (result.hasErrors()) {
			return "addPruebaTSH";
		}
		
		pruebaTSHService.savePruebaTSH(pruebaTSH);
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaTSH.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Prueba TSH Numero: " + pruebaTSH.getId() + " creado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/imprimirPRUEBA_TSH" }, method = RequestMethod.GET)
	public String imprimirPruebaTSH(HttpServletResponse response, ModelMap model, @RequestParam("idPRUEBA_TSH") int idLaboratorio) throws JRException, IOException {
		model.addAttribute("user", getPrincipal());
		PruebaTSH pruebaTSH = pruebaTSHService.findById(idLaboratorio);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaTSH.getIdExpediente());
		model.addAttribute("expediente", expediente);
		User quimicoBiologo = userService.findById(pruebaTSH.getIdQuimicoBiologo());
		
		List<ParametroExportacion> exportacion= new ArrayList<ParametroExportacion>();
		ParametroExportacion mpe = new ParametroExportacion();
		if(mpe.getParameters() == null){
			mpe.setParameters(new HashMap<String,Object>());
		}
		exportacion.add(mpe);
		JasperReport report = JasperCompileManager.compileReport(new ClassPathResource("jrxml/PruebaTSH.jrxml").getInputStream());
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("logoUSALUD", new ClassPathResource("jrxml/logo_usalud.png").getInputStream());
		params.put("logoUSAC", new ClassPathResource("jrxml/logo_usac.png").getInputStream());
		params.put("titulo", "PRUEBA DE TSH".toUpperCase());
		params.put("nombrePaciente", expediente.getNombres().toUpperCase()+" "+expediente.getApellidos().toUpperCase()+" ");
		Date fecha = pruebaTSH.getFechaLaboratorio().toDate();
		params.put("fecha", new SimpleDateFormat("dd/MM/yyyy").format(fecha));
		params.put("resultado", (pruebaTSH.getResultado()==null)?"": UtilsSarel.darFormatoANumero(pruebaTSH.getResultado(),2));
		params.put("codigoPaciente", expediente.getCarne().toString().toUpperCase()+" ");
		params.put("quimicoBiologo", quimicoBiologo.getFirstName().toUpperCase()+" "+quimicoBiologo.getLastName().toUpperCase()+" ");

		FormatoExportacionPruebaLaboratorio formater = new FormatoExportacionPruebaLaboratorio(params);
		params = formater.getParametrosConFormato();

		JasperPrint myJRprintReportObject = JasperFillManager.fillReport(report, params, new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(exportacion));
		
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=PruebaTSH_"+expediente.getCarne().toString()+"_"+new SimpleDateFormat("dd-MM-yyyy").format(fecha)+".pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(myJRprintReportObject, outStream);
		
		return null;
	}
	/* Fin PruebaTSH */
	
	/* Inicio PruebasRenales */

	@Autowired
    	PruebasRenalesService pruebasRenalesService;
	
	@RequestMapping(value = { "/eliminarPRUEBAS_RENALES" }, method = RequestMethod.GET)
	public String eliminarPruebasRenales(ModelMap model, @RequestParam("idExpediente") int idExpediente, @RequestParam("idPRUEBAS_RENALES") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebasRenales nuevoPruebasRenales = pruebasRenalesService.findById(idPerfil);
		nuevoPruebasRenales.setEstado(EstadoResultadoLaboratorio.ELIMINADO);
		pruebasRenalesService.updatePruebasRenales(nuevoPruebasRenales);
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Perfil Lipido Numero: " + idPerfil + " eliminado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/consultarPRUEBAS_RENALES" }, method = RequestMethod.GET)
	public String consultarPruebasRenales(ModelMap model, @RequestParam("idPRUEBAS_RENALES") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebasRenales pruebasRenales = pruebasRenalesService.findById(idPerfil);
		model.addAttribute("pruebasRenales", pruebasRenales);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebasRenales.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("soloConsulta", true);
		return "addPruebasRenales";
	}
	
	@RequestMapping(value = { "/editarPRUEBAS_RENALES" }, method = RequestMethod.GET)
	public String editarPruebasRenales(ModelMap model, @RequestParam("idPRUEBAS_RENALES") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebasRenales pruebasRenales = pruebasRenalesService.findById(idPerfil);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebasRenales.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("pruebasRenales", pruebasRenales);
		model.addAttribute("edit", true);
		return "addPruebasRenales";
	}
	
	@RequestMapping(value = { "/editarPRUEBAS_RENALES" }, method = RequestMethod.POST)
	public String modificarPruebasRenales(ModelMap model, @Valid PruebasRenales pruebasRenales, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addPruebasRenales";
		}
		
		model.addAttribute("user", getPrincipal());
		pruebasRenalesService.updatePruebasRenales(pruebasRenales);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebasRenales.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Perfil Lipido Numero: " + pruebasRenales.getId() + " editado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/agregarPRUEBAS_RENALES" }, method = RequestMethod.GET)
	public String nuevoPruebasRenales(ModelMap model, @RequestParam("idExpediente") int idExpediente) {
		model.addAttribute("user", getPrincipal());
		//model.addAttribute("laboratoristas", userService.findAllUsersByRol("LABORATORISTA"));
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		PruebasRenales pruebasRenales = new PruebasRenales();
		model.addAttribute("pruebasRenales", pruebasRenales);
		model.addAttribute("idExpediente", idExpediente);
		model.addAttribute("edit", false);
		return "addPruebasRenales";
	}
	
	@RequestMapping(value = { "/agregarPRUEBAS_RENALES" }, method = RequestMethod.POST)
	public String guardarPruebasRenales(@Valid PruebasRenales pruebasRenales, BindingResult result, 
			ModelMap model) {
		
		if (result.hasErrors()) {
			return "addPruebasRenales";
		}
		
		pruebasRenalesService.savePruebasRenales(pruebasRenales);
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebasRenales.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Pruebas Renales Numero: " + pruebasRenales.getId() + " creado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/imprimirPRUEBAS_RENALES" }, method = RequestMethod.GET)
	public String imprimirPruebasRenales(HttpServletResponse response, ModelMap model, @RequestParam("idPRUEBAS_RENALES") int idLaboratorio) throws JRException, IOException {
		model.addAttribute("user", getPrincipal());
		PruebasRenales pruebasRenales = pruebasRenalesService.findById(idLaboratorio);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebasRenales.getIdExpediente());
		model.addAttribute("expediente", expediente);
		User quimicoBiologo = userService.findById(pruebasRenales.getIdQuimicoBiologo());
		
		List<ParametroExportacion> exportacion= new ArrayList<ParametroExportacion>();
		ParametroExportacion mpe = new ParametroExportacion();
		if(mpe.getParameters() == null){
			mpe.setParameters(new HashMap<String,Object>());
		}
		exportacion.add(mpe);
		JasperReport report = JasperCompileManager.compileReport(new ClassPathResource("jrxml/PruebasRenales.jrxml").getInputStream());
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("logoUSALUD", new ClassPathResource("jrxml/logo_usalud.png").getInputStream());
		params.put("logoUSAC", new ClassPathResource("jrxml/logo_usac.png").getInputStream());
		params.put("titulo", "Pruebas de Función Renal".toUpperCase());
		params.put("nombrePaciente", expediente.getNombres().toUpperCase()+" "+expediente.getApellidos().toUpperCase()+" ");
		Date fecha = pruebasRenales.getFechaLaboratorio().toDate();
		params.put("fecha", new SimpleDateFormat("dd/MM/yyyy").format(fecha));
		params.put("creatinina", (pruebasRenales.getCreatinina()==null)?"": UtilsSarel.darFormatoANumero(pruebasRenales.getCreatinina(),2));
		params.put("nitrogenoUrea", (pruebasRenales.getNitrogenoUrea()==null)?"": UtilsSarel.darFormatoANumero(pruebasRenales.getNitrogenoUrea(),2));
		params.put("codigoPaciente", expediente.getCarne().toString().toUpperCase()+" ");
		params.put("quimicoBiologo", quimicoBiologo.getFirstName().toUpperCase()+" "+quimicoBiologo.getLastName().toUpperCase()+" ");

		FormatoExportacionPruebaLaboratorio formater = new FormatoExportacionPruebaLaboratorio(params);
		params = formater.getParametrosConFormato();

		JasperPrint myJRprintReportObject = JasperFillManager.fillReport(report, params, new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(exportacion));
		
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=PruebasRenales_"+expediente.getCarne().toString()+"_"+new SimpleDateFormat("dd-MM-yyyy").format(fecha)+".pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(myJRprintReportObject, outStream);
		
		return null;
	}
	/* Fin PruebasRenales */
	
	/* Inicio PruebaPCR */

	@Autowired
    	PruebaPCRService pruebaPCRService;
	
	@RequestMapping(value = { "/eliminarPRUEBA_PCR" }, method = RequestMethod.GET)
	public String eliminarPruebaPCR(ModelMap model, @RequestParam("idExpediente") int idExpediente, @RequestParam("idPRUEBA_PCR") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebaPCR nuevoPruebaPCR = pruebaPCRService.findById(idPerfil);
		nuevoPruebaPCR.setEstado(EstadoResultadoLaboratorio.ELIMINADO);
		pruebaPCRService.updatePruebaPCR(nuevoPruebaPCR);
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Perfil Lipido Numero: " + idPerfil + " eliminado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/consultarPRUEBA_PCR" }, method = RequestMethod.GET)
	public String consultarPruebaPCR(ModelMap model, @RequestParam("idPRUEBA_PCR") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebaPCR pruebaPCR = pruebaPCRService.findById(idPerfil);
		model.addAttribute("pruebaPCR", pruebaPCR);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaPCR.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("soloConsulta", true);
		return "addPruebaPCR";
	}
	
	@RequestMapping(value = { "/editarPRUEBA_PCR" }, method = RequestMethod.GET)
	public String editarPruebaPCR(ModelMap model, @RequestParam("idPRUEBA_PCR") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebaPCR pruebaPCR = pruebaPCRService.findById(idPerfil);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaPCR.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("pruebaPCR", pruebaPCR);
		model.addAttribute("edit", true);
		return "addPruebaPCR";
	}
	
	@RequestMapping(value = { "/editarPRUEBA_PCR" }, method = RequestMethod.POST)
	public String modificarPruebaPCR(ModelMap model, @Valid PruebaPCR pruebaPCR, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addPruebaPCR";
		}
		
		model.addAttribute("user", getPrincipal());
		pruebaPCRService.updatePruebaPCR(pruebaPCR);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaPCR.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Perfil Lipido Numero: " + pruebaPCR.getId() + " editado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/agregarPRUEBA_PCR" }, method = RequestMethod.GET)
	public String nuevoPruebaPCR(ModelMap model, @RequestParam("idExpediente") int idExpediente) {
		model.addAttribute("user", getPrincipal());
		//model.addAttribute("laboratoristas", userService.findAllUsersByRol("LABORATORISTA"));
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		PruebaPCR pruebaPCR = new PruebaPCR();
		model.addAttribute("pruebaPCR", pruebaPCR);
		model.addAttribute("idExpediente", idExpediente);
		model.addAttribute("edit", false);
		return "addPruebaPCR";
	}
	
	@RequestMapping(value = { "/agregarPRUEBA_PCR" }, method = RequestMethod.POST)
	public String guardarPruebaPCR(@Valid PruebaPCR pruebaPCR, BindingResult result, 
			ModelMap model) {
		
		if (result.hasErrors()) {
			return "addPruebaPCR";
		}
		
		pruebaPCRService.savePruebaPCR(pruebaPCR);
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaPCR.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Prueba PCR Numero: " + pruebaPCR.getId() + " creado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/imprimirPRUEBA_PCR" }, method = RequestMethod.GET)
	public String imprimirPruebaPCR(HttpServletResponse response, ModelMap model, @RequestParam("idPRUEBA_PCR") int idLaboratorio) throws JRException, IOException {
		model.addAttribute("user", getPrincipal());
		PruebaPCR pruebaPCR = pruebaPCRService.findById(idLaboratorio);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaPCR.getIdExpediente());
		model.addAttribute("expediente", expediente);
		User quimicoBiologo = userService.findById(pruebaPCR.getIdQuimicoBiologo());
		
		List<ParametroExportacion> exportacion= new ArrayList<ParametroExportacion>();
		ParametroExportacion mpe = new ParametroExportacion();
		if(mpe.getParameters() == null){
			mpe.setParameters(new HashMap<String,Object>());
		}
		exportacion.add(mpe);
		JasperReport report = JasperCompileManager.compileReport(new ClassPathResource("jrxml/PruebaPCR.jrxml").getInputStream());
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("logoUSALUD", new ClassPathResource("jrxml/logo_usalud.png").getInputStream());
		params.put("logoUSAC", new ClassPathResource("jrxml/logo_usac.png").getInputStream());
		params.put("titulo", "Prueba PCR".toUpperCase());
		params.put("nombrePaciente", expediente.getNombres().toUpperCase()+" "+expediente.getApellidos().toUpperCase()+" ");
		Date fecha = pruebaPCR.getFechaLaboratorio().toDate();
		params.put("fecha", new SimpleDateFormat("dd/MM/yyyy").format(fecha));
		params.put("resultado", (pruebaPCR.getResultado()==null)?"": UtilsSarel.darFormatoANumero(pruebaPCR.getResultado(),2));
		params.put("codigoPaciente", expediente.getCarne().toString().toUpperCase()+" ");
		params.put("quimicoBiologo", quimicoBiologo.getFirstName().toUpperCase()+" "+quimicoBiologo.getLastName().toUpperCase()+" ");

		FormatoExportacionPruebaLaboratorio formater = new FormatoExportacionPruebaLaboratorio(params);
		params = formater.getParametrosConFormato();

		JasperPrint myJRprintReportObject = JasperFillManager.fillReport(report, params, new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(exportacion));
		
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=PruebaPCR_"+expediente.getCarne().toString()+"_"+new SimpleDateFormat("dd-MM-yyyy").format(fecha)+".pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(myJRprintReportObject, outStream);
		
		return null;
	}
	/* Fin PruebaPCR */
	
	/* Inicio PruebaAlfafetoproteina */

	@Autowired
    	PruebaAlfafetoproteinaService pruebaAlfafetoproteinaService;
	
	@RequestMapping(value = { "/eliminarPRUEBA_ALFAFETOPROTEINA" }, method = RequestMethod.GET)
	public String eliminarPruebaAlfafetoproteina(ModelMap model, @RequestParam("idExpediente") int idExpediente, @RequestParam("idPRUEBA_ALFAFETOPROTEINA") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebaAlfafetoproteina nuevoPruebaAlfafetoproteina = pruebaAlfafetoproteinaService.findById(idPerfil);
		nuevoPruebaAlfafetoproteina.setEstado(EstadoResultadoLaboratorio.ELIMINADO);
		pruebaAlfafetoproteinaService.updatePruebaAlfafetoproteina(nuevoPruebaAlfafetoproteina);
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Perfil Lipido Numero: " + idPerfil + " eliminado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/consultarPRUEBA_ALFAFETOPROTEINA" }, method = RequestMethod.GET)
	public String consultarPruebaAlfafetoproteina(ModelMap model, @RequestParam("idPRUEBA_ALFAFETOPROTEINA") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebaAlfafetoproteina pruebaAlfafetoproteina = pruebaAlfafetoproteinaService.findById(idPerfil);
		model.addAttribute("pruebaAlfafetoproteina", pruebaAlfafetoproteina);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaAlfafetoproteina.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("soloConsulta", true);
		return "addPruebaAlfafetoproteina";
	}
	
	@RequestMapping(value = { "/editarPRUEBA_ALFAFETOPROTEINA" }, method = RequestMethod.GET)
	public String editarPruebaAlfafetoproteina(ModelMap model, @RequestParam("idPRUEBA_ALFAFETOPROTEINA") int idPerfil) {
		model.addAttribute("user", getPrincipal());
		PruebaAlfafetoproteina pruebaAlfafetoproteina = pruebaAlfafetoproteinaService.findById(idPerfil);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaAlfafetoproteina.getIdExpediente());
		model.addAttribute("expediente", expediente);
		model.addAttribute("pruebaAlfafetoproteina", pruebaAlfafetoproteina);
		model.addAttribute("edit", true);
		return "addPruebaAlfafetoproteina";
	}
	
	@RequestMapping(value = { "/editarPRUEBA_ALFAFETOPROTEINA" }, method = RequestMethod.POST)
	public String modificarPruebaAlfafetoproteina(ModelMap model, @Valid PruebaAlfafetoproteina pruebaAlfafetoproteina, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addPruebaAlfafetoproteina";
		}
		
		model.addAttribute("user", getPrincipal());
		pruebaAlfafetoproteinaService.updatePruebaAlfafetoproteina(pruebaAlfafetoproteina);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaAlfafetoproteina.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Perfil Lipido Numero: " + pruebaAlfafetoproteina.getId() + " editado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/agregarPRUEBA_ALFAFETOPROTEINA" }, method = RequestMethod.GET)
	public String nuevoPruebaAlfafetoproteina(ModelMap model, @RequestParam("idExpediente") int idExpediente) {
		model.addAttribute("user", getPrincipal());
		//model.addAttribute("laboratoristas", userService.findAllUsersByRol("LABORATORISTA"));
		ExpedienteLaboratorio expediente = expedienteService.findById(idExpediente);
		model.addAttribute("expediente", expediente);
		PruebaAlfafetoproteina pruebaAlfafetoproteina = new PruebaAlfafetoproteina();
		model.addAttribute("pruebaAlfafetoproteina", pruebaAlfafetoproteina);
		model.addAttribute("idExpediente", idExpediente);
		model.addAttribute("edit", false);
		return "addPruebaAlfafetoproteina";
	}
	
	@RequestMapping(value = { "/agregarPRUEBA_ALFAFETOPROTEINA" }, method = RequestMethod.POST)
	public String guardarPruebaAlfafetoproteina(@Valid PruebaAlfafetoproteina pruebaAlfafetoproteina, BindingResult result, 
			ModelMap model) {
		
		if (result.hasErrors()) {
			return "addPruebaAlfafetoproteina";
		}
		
		pruebaAlfafetoproteinaService.savePruebaAlfafetoproteina(pruebaAlfafetoproteina);
		model.addAttribute("user", getPrincipal());
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaAlfafetoproteina.getIdExpediente());
		model.addAttribute("expediente", expediente);
		List<ResultadoLaboratorioVO> labs = obtenerTodosLosLaboratoriosPorIdExpediente(expediente.getId());
		model.addAttribute("labs", labs);
		model.addAttribute("message", "Laboratorio Prueba de Alfafetoproteina Numero: " + pruebaAlfafetoproteina.getId() + " creado Exitosamente...");
		return "verExpedienteLaboratorio";
	}
	
	@RequestMapping(value = { "/imprimirPRUEBA_ALFAFETOPROTEINA" }, method = RequestMethod.GET)
	public String imprimirPruebaAlfafetoproteina(HttpServletResponse response, ModelMap model, @RequestParam("idPRUEBA_ALFAFETOPROTEINA") int idLaboratorio) throws JRException, IOException {
		model.addAttribute("user", getPrincipal());
		PruebaAlfafetoproteina pruebaAlfafetoproteina = pruebaAlfafetoproteinaService.findById(idLaboratorio);
		ExpedienteLaboratorio expediente = expedienteService.findById(pruebaAlfafetoproteina.getIdExpediente());
		model.addAttribute("expediente", expediente);
		User quimicoBiologo = userService.findById(pruebaAlfafetoproteina.getIdQuimicoBiologo());
		
		List<ParametroExportacion> exportacion= new ArrayList<ParametroExportacion>();
		ParametroExportacion mpe = new ParametroExportacion();
		if(mpe.getParameters() == null){
			mpe.setParameters(new HashMap<String,Object>());
		}
		exportacion.add(mpe);
		JasperReport report = JasperCompileManager.compileReport(new ClassPathResource("jrxml/PruebaAlfafetoproteina.jrxml").getInputStream());
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("logoUSALUD", new ClassPathResource("jrxml/logo_usalud.png").getInputStream());
		params.put("logoUSAC", new ClassPathResource("jrxml/logo_usac.png").getInputStream());
		params.put("titulo", "Prueba de Alfafetoproteina".toUpperCase());
		params.put("nombrePaciente", expediente.getNombres().toUpperCase()+" "+expediente.getApellidos().toUpperCase()+" ");
		Date fecha = pruebaAlfafetoproteina.getFechaLaboratorio().toDate();
		params.put("fecha", new SimpleDateFormat("dd/MM/yyyy").format(fecha));
		params.put("resultado", (pruebaAlfafetoproteina.getResultado()==null)?"": UtilsSarel.darFormatoANumero(pruebaAlfafetoproteina.getResultado(),2));
		params.put("codigoPaciente", expediente.getCarne().toString().toUpperCase()+" ");
		params.put("quimicoBiologo", quimicoBiologo.getFirstName().toUpperCase()+" "+quimicoBiologo.getLastName().toUpperCase()+" ");

		FormatoExportacionPruebaLaboratorio formater = new FormatoExportacionPruebaLaboratorio(params);
		params = formater.getParametrosConFormato();

		JasperPrint myJRprintReportObject = JasperFillManager.fillReport(report, params, new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(exportacion));
		
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=PruebaAlfafetoproteina_"+expediente.getCarne().toString()+"_"+new SimpleDateFormat("dd-MM-yyyy").format(fecha)+".pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(myJRprintReportObject, outStream);
		
		return null;
	}
	/* Fin PruebaAlfafetoproteina */
	
	@RequestMapping(value = { "/descargarManualdeUsuario" }, method = RequestMethod.GET)
	public String servirManualUsuario(HttpServletResponse response, ModelMap model){		 
		response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=Manual_Usuario_SAREL.pdf");
	    try {
			OutputStream outStream = response.getOutputStream();
			InputStream in = new ClassPathResource("Manual_Usuario_SAREL.pdf").getInputStream();
			byte[] outputByte = new byte[4096];
			//copy binary contect to output stream
			while(in.read(outputByte, 0, 4096) != -1)
			{
				outStream.write(outputByte, 0, 4096);
			}
			in.close();
			outStream.flush();
			outStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return null;
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
	
	private List<ResultadoLaboratorioVO> obtenerLaboratoriosPorExpedienteYFecha(int idExpediente, Date fechaInicial, Date fechaFinal){
		List<ResultadoLaboratorioVO> resultados = new ArrayList<ResultadoLaboratorioVO>();
		for(TipoLaboratorio unTipo : TipoLaboratorio.values()){
			if(unTipo.getName().equals(TipoLaboratorio.PERFIL_LIPIDICO.getName())){
				List<PerfilLipidico> lipidicos = perfilLipidicoService.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
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
				List<PruebaEmbarazo> pruebaEmbarazo = pruebaEmbarazoService.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
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
				List<AcidoUrico> acidos = acidoUricoService.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
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
				List<PruebaVDRL> VDRLs = pruebaVDRLService.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
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
				List<ColesterolTrigliceridos> labs = colesterolTrigliceridosService.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
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
				List<GlucosaPreYPost> labs = glucosaPreYPostService.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
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
				List<PruebaSerologica> labs = pruebaSerologicaService.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
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
			}else if(unTipo.getName().equals(TipoLaboratorio.PRUEBAS_HEMATOLOGICAS.getName())){
				List<PruebasHematologicas> labs = pruebasHematologicasService.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
				for(PruebasHematologicas unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.HEMATOLOGIA_COMPLETA.getName())){
				List<HematologiaCompleta> labs = hematologiaCompletaService.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
				for(HematologiaCompleta unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.EXAMEN_ORINA.getName())){
				List<ExamenOrina> labs = examenOrinaService.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
				for(ExamenOrina unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.EXAMEN_HECES.getName())){
				List<ExamenHeces> labs = examenHecesService.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
				for(ExamenHeces unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.PRUEBA_DENGE.getName())){
				List<PruebaDengue> labs = pruebaDengueService.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
				for(PruebaDengue unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.HELICOBACTER_PYLORI.getName())){
				List<HelicobacterPylori> labs = helicobacterPyloriService.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
				for(HelicobacterPylori unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.HEMOGLOBINA_GLUCOSA.getName())){
				List<HemoglobinaGlucosa> labs = hemoglobinaGlucosaService.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
				for(HemoglobinaGlucosa unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.PRUEBAS_TIROIDEAS.getName())){
				List<PruebaTiroidea> labs = pruebaTiroideaService.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
				for(PruebaTiroidea unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.ANTIGENO_PROSTATICO.getName())){
				List<AntigenoProstatico> labs = antigenoProstaticoService.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
				for(AntigenoProstatico unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.PRUEBA_TSH.getName())){
				List<PruebaTSH> labs = pruebaTSHService.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
				for(PruebaTSH unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.PRUEBAS_RENALES.getName())){
				List<PruebasRenales> labs = pruebasRenalesService.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
				for(PruebasRenales unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.PRUEBA_PCR.getName())){
				List<PruebaPCR> labs = pruebaPCRService.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
				for(PruebaPCR unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.PRUEBA_ALFAFETOPROTEINA.getName())){
				List<PruebaAlfafetoproteina> labs = pruebaAlfafetoproteinaService.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
				for(PruebaAlfafetoproteina unLab : labs){
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
			}else if(unTipo.getName().equals(TipoLaboratorio.PRUEBAS_HEMATOLOGICAS.getName())){
				List<PruebasHematologicas> labs = pruebasHematologicasService.findByIdExpediente(idExpediente);
				for(PruebasHematologicas unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.HEMATOLOGIA_COMPLETA.getName())){
				List<HematologiaCompleta> labs = hematologiaCompletaService.findByIdExpediente(idExpediente);
				for(HematologiaCompleta unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.EXAMEN_ORINA.getName())){
				List<ExamenOrina> labs = examenOrinaService.findByIdExpediente(idExpediente);
				for(ExamenOrina unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.EXAMEN_HECES.getName())){
				List<ExamenHeces> labs = examenHecesService.findByIdExpediente(idExpediente);
				for(ExamenHeces unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.PRUEBA_DENGE.getName())){
				List<PruebaDengue> labs = pruebaDengueService.findByIdExpediente(idExpediente);
				for(PruebaDengue unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.HELICOBACTER_PYLORI.getName())){
				List<HelicobacterPylori> labs = helicobacterPyloriService.findByIdExpediente(idExpediente);
				for(HelicobacterPylori unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.HEMOGLOBINA_GLUCOSA.getName())){
				List<HemoglobinaGlucosa> labs = hemoglobinaGlucosaService.findByIdExpediente(idExpediente);
				for(HemoglobinaGlucosa unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.PRUEBAS_TIROIDEAS.getName())){
				List<PruebaTiroidea> labs = pruebaTiroideaService.findByIdExpediente(idExpediente);
				for(PruebaTiroidea unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.ANTIGENO_PROSTATICO.getName())){
				List<AntigenoProstatico> labs = antigenoProstaticoService.findByIdExpediente(idExpediente);
				for(AntigenoProstatico unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.PRUEBA_TSH.getName())){
				List<PruebaTSH> labs = pruebaTSHService.findByIdExpediente(idExpediente);
				for(PruebaTSH unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.PRUEBAS_RENALES.getName())){
				List<PruebasRenales> labs = pruebasRenalesService.findByIdExpediente(idExpediente);
				for(PruebasRenales unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.PRUEBA_PCR.getName())){
				List<PruebaPCR> labs = pruebaPCRService.findByIdExpediente(idExpediente);
				for(PruebaPCR unLab : labs){
					ResultadoLaboratorioVO unResultado = new ResultadoLaboratorioVO();
					unResultado.setId(unLab.getId());
					unResultado.setIdExpediente(idExpediente);
					unResultado.setFechaLaboratorio(unLab.getFechaLaboratorio());
					unResultado.setQuimicoBiologo(userService.findById(unLab.getIdQuimicoBiologo()).getSsoId());
					unResultado.setEstado(unLab.getEstado().getName().replaceAll("_", " "));
					unResultado.setTipoLaboratorio(unTipo);
					resultados.add(unResultado);
				}
			}else if(unTipo.getName().equals(TipoLaboratorio.PRUEBA_ALFAFETOPROTEINA.getName())){
				List<PruebaAlfafetoproteina> labs = pruebaAlfafetoproteinaService.findByIdExpediente(idExpediente);
				for(PruebaAlfafetoproteina unLab : labs){
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
