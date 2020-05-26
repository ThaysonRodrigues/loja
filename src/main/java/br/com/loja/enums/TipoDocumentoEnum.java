package br.com.loja.enums;

public enum TipoDocumentoEnum {
	
	RG("RG"), CPF("CPF");
	
	private String tipoDocumento;
	
	private TipoDocumentoEnum(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}
}
