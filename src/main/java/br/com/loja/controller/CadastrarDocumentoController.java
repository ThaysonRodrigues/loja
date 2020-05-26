package br.com.loja.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.loja.business.DocumentoBusiness;
import br.com.loja.enums.TipoDocumentoEnum;
import br.com.loja.model.Documento;

@Named("documentos")
@ViewScoped
public class CadastrarDocumentoController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//Classe DAO
	@Inject
	private DocumentoBusiness business;
	
	@Inject
	private Documento documento;
	
	//lista que guarda todos os tipo de documentos (ENUM)
	private List<TipoDocumentoEnum> listTipoDocumento = new ArrayList<TipoDocumentoEnum>();
	
	private Long id = (long) 5;
	
	//Método que ilustra a inserção dos dados do formulário
	public String salvar() {
		System.out.println("salvando documento");
		documento.setId(id++);
		ListarDocumentosController.listDocumento.add(documento);
		limparFormulario();
		
		return "/listarDocumentos.xhtml";
	}
	
	public CadastrarDocumentoController() {
		popularTipoDocumento();
	}
	
	private void popularTipoDocumento() {
		for (TipoDocumentoEnum documento : TipoDocumentoEnum.values()) {
			listTipoDocumento.add(documento);
		}
	}
	
	public void limparFormulario() {
		this.documento = new Documento();
	}
	
	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public List<TipoDocumentoEnum> getListTipoSituacao() {
		return listTipoDocumento;
	}

	public void setListTipoSituacao(List<TipoDocumentoEnum> listTipoSituacao) {
		this.listTipoDocumento = listTipoSituacao;
	}
}
