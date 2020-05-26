package br.com.loja.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.loja.enums.TipoDocumentoEnum;

@Entity
@Table

@NamedQueries({
	@NamedQuery(name="Documento.getDocumentos", query = "select d from Documento d"),
	@NamedQuery(name="Documento.getDocumentoById", query = "select d from Documento d where d.id = :id")
})
public class Documento implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private TipoDocumentoEnum tipoDocumento;
	
	private String descricao;
	
	@NotNull
	private String numero;
	
	private LocalDate dataDeEmissao;
	
	@NotNull
	private String orgaoEmissor;
	
	public Documento() {
		
	}
	
	public Documento(Long id, TipoDocumentoEnum tipoDocumento, String descricao, @NotNull String numero,
			LocalDate dataEmissao, @NotNull String orgaoEmissor) {
		this.id = id;
		this.tipoDocumento = tipoDocumento;
		this.descricao = descricao;
		this.numero = numero;
		this.dataDeEmissao = dataEmissao;
		this.orgaoEmissor = orgaoEmissor;
	}

	@Override
	public String toString() {
		return "Documento [id=" + id + ", tipoDocumento=" + tipoDocumento + ", descricao=" + descricao + ", numero="
				+ numero + ", dataDeEmissao=" + dataDeEmissao + ", orgaoEmissor=" + orgaoEmissor + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataDeEmissao == null) ? 0 : dataDeEmissao.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((orgaoEmissor == null) ? 0 : orgaoEmissor.hashCode());
		result = prime * result + ((tipoDocumento == null) ? 0 : tipoDocumento.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Documento other = (Documento) obj;
		if (dataDeEmissao == null) {
			if (other.dataDeEmissao != null)
				return false;
		} else if (!dataDeEmissao.equals(other.dataDeEmissao))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (orgaoEmissor == null) {
			if (other.orgaoEmissor != null)
				return false;
		} else if (!orgaoEmissor.equals(other.orgaoEmissor))
			return false;
		if (tipoDocumento != other.tipoDocumento)
			return false;
		return true;
	}

	public Documento(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoDocumentoEnum getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumentoEnum tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}

	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
	}

	public LocalDate getDataDeEmissao() {
		return dataDeEmissao;
	}

	public void setDataDeEmissao(LocalDate dataDeEmissao) {
		this.dataDeEmissao = dataDeEmissao;
	}
	
}
