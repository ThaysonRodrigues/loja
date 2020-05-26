package br.com.loja.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.loja.business.DocumentoBusiness;
import br.com.loja.model.Documento;

@Named("listarDocumentos")
public class ListarDocumentosController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//Classe DAO
	@Inject
	private DocumentoBusiness business;
	
	//Lista que controla o datatable
	public static List<Documento> listDocumento;
	
	@PostConstruct
	public void init() {
		listDocumento = business.listaDocumentoStatico();
	}

	public List<Documento> getListDocumento() {
		return listDocumento;
	}

	public void setListDocumento(List<Documento> listDocumento) {
		ListarDocumentosController.listDocumento = listDocumento;
	}
}
