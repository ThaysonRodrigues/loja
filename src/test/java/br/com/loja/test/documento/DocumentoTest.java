package br.com.loja.test.documento;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.loja.business.DocumentoBusiness;
import br.com.loja.enums.TipoDocumentoEnum;
import br.com.loja.model.Documento;
import br.com.loja.test.AbstractTest;

@RunWith(Arquillian.class)
public class DocumentoTest extends AbstractTest {
	
	/**
	 * Classe de teste de inserção de documento,
	 * a mesma tem como objetivo realizar testes
	 * de inserção, atualização, seleção e remoção. 
	 */

	@Inject
	DocumentoBusiness dao;

	@Inject
	Logger log;
	
	/* Teste de Inserção
	 * Basicamente esse teste recebe dados brutos,
	 * realiza uma persistencia no banco de dados,
	 * e caso o id não seja nulo o teste é verdadeiro.	
	 *
	 */
	@Test
	public void salvarDocumento() throws Exception {
		Documento documento = new Documento();

		documento.setNumero("84570174035");
		documento.setTipoDocumento(TipoDocumentoEnum.CPF);
		documento.setDescricao("APENAS UM TESTE");
		documento.setDataDeEmissao(LocalDate.now());
		documento.setOrgaoEmissor("MG");

		dao.salvar(documento);

		assertNotNull(documento.getId());

		log.info("Registrado documento número: " + documento.getNumero() + " com id: " + documento.getId());
	}
	
	// Teste de listagem de todos os documentos.
	@Test
	public void listarTodosDocumentos() throws Exception {
		List<Documento> listDocumentos = dao.getDocumentos();
		assertNotNull(listDocumentos);
	}
	
	//Teste de listagem de um documento determinado pelo id.
	@Test
	public void listarDocumentoById() throws Exception {
		Documento documento = dao.findDocumentoById(new Documento(1L));

		assertNotNull(documento);
	}
	
	/* Teste de atualização dos documento
	 * Teste que atualiza um entidade, depois
	 * realiza uma listagem e compara com a entidade
	 * antiga, se o conteúdo for diferente o teste é verdadeiro.
	 */
	@Test
	public void atualizarDocumento() throws Exception {
		Documento documento = new Documento(1L);
		
		documento.setNumero("84570174033");
		documento.setOrgaoEmissor("SP");

		dao.atualizar(documento);
		
		Documento documentoComparacao = dao.getDocumentoById(new Documento(1L));
		assertTrue(documentoComparacao.getNumero() == "84570174033");
	}
	
	/* Teste de remoção de entidade
	 * Teste que realiza remoção de uma entidade pelo ID,
	 * prosseguindo com uma consulta pelo id, se o resultado 
	 * da consulta for nulo o teste é verdadeiro.
	 * */
	@Test
	public void deletarDocumento() {
		Documento documento = dao.getDocumentoById(new Documento(1L));
		dao.deletar(documento);
		
		Documento documentoExcluido = dao.findDocumentoById(new Documento(1L));
		
		assertNull(documentoExcluido);
	}
}
