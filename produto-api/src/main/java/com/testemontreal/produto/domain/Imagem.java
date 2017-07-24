package com.testemontreal.produto.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "IMAGEM")
public class Imagem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "TIPO", nullable = false)
	private String tipo;
	
	@Column(name = "ID_PRODUTO", nullable = false)
	private Long idProduto;
	
	@ManyToOne(optional=true, fetch=FetchType.LAZY)
	@JoinColumn(name="ID_PRODUTO", referencedColumnName="id", nullable=true, updatable=false, insertable=false)
	private Produto produto;
	
	public Imagem() {
		super();
	}

	/**
	 * @param id
	 * @param tipo
	 * @param idProduto
	 */
	public Imagem(Long id, String tipo, Long idProduto) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.idProduto = idProduto;
	}

	/**
	 * @param id
	 */
	public Imagem(Long id) {
		super();
		this.id = id;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @return the idProduto
	 */
	public Long getIdProduto() {
		return idProduto;
	}

	/**
	 * @return the produto
	 */
	public Produto getProduto() {
		return produto;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @param idProduto the idProduto to set
	 */
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	/**
	 * @param produto the produto to set
	 */
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
