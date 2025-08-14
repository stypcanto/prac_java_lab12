package dto;

import java.sql.Date;

public class ClienteDTO
{
	private Long    idcliente;
	private String  appaterno;
	private String  apmaterno;
	private String  nombres;
	private Date    nacimiento;
	private String  direccion;
	private String  referencia;
	private String  genero;
	private Integer estado;
	
	public ClienteDTO()
	{
	}
	
	public ClienteDTO(Long idcliente, String appaterno, String apmaterno, String nombres, Date nacimiento, String direccion, String referencia, String genero, Integer estado)
	{
		this.idcliente = idcliente;
		this.appaterno = appaterno;
		this.apmaterno = apmaterno;
		this.nombres = nombres;
		this.nacimiento = nacimiento;
		this.direccion = direccion;
		this.referencia = referencia;
		this.genero = genero;
		this.estado = estado;
	}
	
	
	public Long getIdcliente()
	{
		return idcliente;
	}
	
	public void setIdcliente(Long idcliente)
	{
		this.idcliente = idcliente;
	}
	
	public String getAppaterno()
	{
		return appaterno;
	}
	
	public void setAppaterno(String appaterno)
	{
		this.appaterno = appaterno;
	}
	
	public String getApmaterno()
	{
		return apmaterno;
	}
	
	public void setApmaterno(String apmaterno)
	{
		this.apmaterno = apmaterno;
	}
	
	public String getNombres()
	{
		return nombres;
	}
	
	public void setNombres(String nombres)
	{
		this.nombres = nombres;
	}
	
	public Date getNacimiento()
	{
		return nacimiento;
	}
	
	public void setNacimiento(Date nacimiento)
	{
		this.nacimiento = nacimiento;
	}
	
	public String getDireccion()
	{
		return direccion;
	}
	
	public void setDireccion(String direccion)
	{
		this.direccion = direccion;
	}
	
	public String getReferencia()
	{
		return referencia;
	}
	
	public void setReferencia(String referencia)
	{
		this.referencia = referencia;
	}
	
	public String getGenero()
	{
		return genero;
	}
	
	public void setGenero(String genero)
	{
		this.genero = genero;
	}
	
	public Integer getEstado()
	{
		return estado;
	}
	
	public void setEstado(Integer estado)
	{
		this.estado = estado;
	}
	
	
	@Override
	public String toString()
	{
		return "ClienteDTO{" +
			"idcliente=" + idcliente +
			", appaterno='" + appaterno + '\'' +
			", apmaterno='" + apmaterno + '\'' +
			", nombres='" + nombres + '\'' +
			", nacimiento=" + nacimiento +
			", direccion='" + direccion + '\'' +
			", referencia='" + referencia + '\'' +
			", genero='" + genero + '\'' +
			", estado=" + estado +
			'}';
	}
}
