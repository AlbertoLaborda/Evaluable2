package com.midominio.Evaluable02.app.Utils.Paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {
	private String url;
	private Page<T> page;
	
	private int totalPaginas;
	private int numPaginasAMostrar;
	private int pagActual;
	
	private List<PageItem> paginas;

	public PageRender(String url, Page<T> page) {
		super();
		this.url = url;
		this.page = page;
		this.paginas=new ArrayList<PageItem>();
		numPaginasAMostrar=5;
		totalPaginas=page.getTotalPages();
		pagActual=page.getNumber()+1;
		
		int desde,hasta;
		
		if (totalPaginas <= numPaginasAMostrar) {
			desde = 1;
			hasta = totalPaginas;					
		} else {
			if(pagActual <= numPaginasAMostrar / 2) {
				desde = 1;
				hasta = numPaginasAMostrar;
			} else if(pagActual >= totalPaginas - numPaginasAMostrar / 2) {
				desde = totalPaginas - numPaginasAMostrar + 1;
				hasta = numPaginasAMostrar;
			} else {
				desde = pagActual - numPaginasAMostrar / 2;
				hasta = numPaginasAMostrar;
			}
		}
		for(int i=0; i < hasta; i++) {
			paginas.add(new PageItem(desde + i, pagActual == desde + i));
		}	
	}
	public boolean isFirst() {
		return page.isFirst();
	}
	
	public boolean isLast() {
		return page.isLast();
	}
	
	public boolean isHasNext() {
		
		return page.hasNext();
	}
	
	public boolean isHasPrevious() {
		return page.hasPrevious();
		
	}
	public String getUrl() {
		return url;
	}

	public Page<T> getPage() {
		return page;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public int getNumPaginasAMostrar() {
		return numPaginasAMostrar;
	}

	public int getPagActual() {
		return pagActual;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}
	
	
	
	
}
