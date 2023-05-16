package com.example.demo.dto;

import lombok.Data;

@Data
public class LibroDto {
	
	private Long idlibro;
	private String titulo;
	private String descripcion;
	private int paginas;
	private String edicion;
	private Long idautor;
	private Long ideditorial;

}
