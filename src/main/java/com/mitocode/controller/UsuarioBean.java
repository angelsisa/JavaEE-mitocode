package com.mitocode.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.mindrot.jbcrypt.BCrypt;

import com.mitocode.model.Persona;
import com.mitocode.model.Usuario;
import com.mitocode.service.IUsuarioService;

@Named
@ViewScoped
public class UsuarioBean implements Serializable{
	
	@Inject
	private IUsuarioService service;
	
	private List<Usuario> listaUsuarios;
	
	private String us;
	private Usuario usuario;
	
	private String pass;
	private String passNew;	
	private String tipoDialog;
	
	
	@PostConstruct
	public void init() {
//		this.listarUsuarios();
		this.usuario = new Usuario();
		this.tipoDialog = "";
		
	}
	
	public void verificarPass() {
		try {
			this.tipoDialog = service.verificarPass(this.usuario.getUsuario(), this.pass);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public UsuarioBean() {
		this.passNew = "";
	}

/*	public void btnModificar() {
		if(this.passNew != null && !this.passNew.equals("")) {
			if(this.passNew.equals(this.passNew.toString())) {
				this.tipoDialog = "Editar";
			}else {
				this.tipoDialog = "";			
			}			
		}
			
	}*/
	
	public void modificar() {
		
		String claveHash = BCrypt.hashpw(passNew, BCrypt.gensalt());
		this.usuario.setContrasena(claveHash);
		try {					
			this.service.modificar(this.usuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		limpiarControles();
	}

	public void listarUsuarios() {
//		try {
//			this.listaUsuarios = service.listar();
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public void mostrarData(Usuario us) {
		this.usuario = us;
		
	}
	public void limpiarControles() {
		this.usuario = new Usuario();	
		this.pass = "";
		this.passNew = "";
	}
	public void buscarUsuario() {
		try {
			this.listaUsuarios = service.listarUsuarios(this.us);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}
		

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public String getUs() {
		return us;
	}

	public void setUs(String us) {
		this.us = us;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getPassNew() {
		return passNew;
	}

	public void setPassNew(String passNew) {
		this.passNew = passNew;
	}


	public String getTipoDialog() {
		return tipoDialog;
	}

	public void setTipoDialog(String tipoDialog) {
		this.tipoDialog = tipoDialog;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}


	

}
