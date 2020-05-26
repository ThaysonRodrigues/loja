package br.com.loja.business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.loja.enums.TipoDocumentoEnum;
import br.com.loja.model.Documento;

@Stateless
public class DocumentoBusiness implements Serializable {
	
	/*
	 * Classe DAO com os principais métodos de 
	 * maniputação de uma entidade (CRUD). 
	*/

	private static final long serialVersionUID = 1L;
	
	@Inject
	EntityManager em;
	
	@Inject
	Logger log;
	
	@Inject
    private Event<Documento> documentoEvent;
	
	//insere no banco um documento
	public void salvar(Documento documento) {
		log.info("Registrando o documento: " + documento.getTipoDocumento() + " Descrição: " + documento.getDescricao());
		em.persist(documento);
		documentoEvent.fire(documento);
	}
	
	//Lista todos os documentos
	public List<Documento> getDocumentos() {
		log.info("Coletando todas as informações dos documentos");
		return em.createNamedQuery("Documento.getDocumentos", Documento.class).getResultList();
	}
	
	//retorna um documento de um determinado id
	public Documento getDocumentoById(Documento documento) {
		log.info("Coletando as informações do documentos: " + documento.getNumero());
		return em.createNamedQuery("Documento.getDocumentoById", Documento.class).setParameter("id", documento.getId()).getSingleResult();	
	}
	
	//atualiza a entidade documento
	public void atualizar(Documento documento) {
		log.info("Atualizando documento ID:" + documento.getId());
		em.merge(documento);
	}
	
	//remove a entidade documento do banco de dados
	public void deletar(Documento documento) {
		log.info("apagando documento ID:" + documento.getId());
		em.remove(em.getReference(Documento.class, documento.getId()));
		documentoEvent.fire(documento);
		
	}
	
	//retorna um documento de um determinado id
	public Documento findDocumentoById(Documento documento) {
		log.info("Coletando as informações do documentos: " + documento.getNumero());
		return em.find(Documento.class, documento.getId());
	}
	
	//Retorna lista de documentos para preencher xhtml de teste
	public List<Documento> listaDocumentoStatico(){
		List<Documento> listDocumentos = new ArrayList<Documento>();
		
		LocalDate data = LocalDate.now();
		
		Documento cpf = new Documento(1L, TipoDocumentoEnum.CPF, "CPF de teste", "344.765.930-03", data, "SP");
		Documento rg = new Documento(2L, TipoDocumentoEnum.RG, "RG de teste", "47.137.479-9", data, "MG");
		
		Documento cpf2 = new Documento(3L, TipoDocumentoEnum.CPF, "CPF de teste 2", "469.406.640-41", data, "SP");
		Documento rg2 = new Documento(4L, TipoDocumentoEnum.RG, "RG de teste 2", "48.195.981-6", data, "MG");
		
		listDocumentos.add(cpf);
		listDocumentos.add(rg);
		listDocumentos.add(cpf2);
		listDocumentos.add(rg2);
		
		return listDocumentos;
	}
}
