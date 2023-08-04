package com.midominio.Evaluable02.app.Utils.Paginator;

public class PageItem {
	private int numero;//numero de paginas
	private boolean actual;//true si es pagina actual
	
	
	public int getNumero() {
		return numero;
	}

	public boolean isActual() {
		return actual;
	}

	public PageItem(int numero, boolean actual) {
		super();
		this.numero = numero;
		this.actual = actual;
	}

	
	
}
