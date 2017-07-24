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
@Table(name = "PRODUTO")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "NOME", nullable = false)
	private String nome;
	
	@Column(name = "DESCRICAO", nullable = true)
	private String descricao;
	
	@Column(name = "ID_PRODUTO_PAI", nullable = true)
	private Long idProdutoPai;
	
	@ManyToOne(optional=true, fetch=FetchType.LAZY)
	@JoinColumn(name="ID_PRODUTO_PAI", referencedColumnName="id", nullable=true, updatable=false, insertable=false)
	private Produto produtoPai;
	
	public Produto() {
		super();
	}

	/**
	 * @param id
	 * @param nome
	 * @param descricao
	 * @param idProdutoPai
	 */
	public Produto(Long id, String nome, String descricao, Long idProdutoPai) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.idProdutoPai = idProdutoPai;
	}

	/**
	 * @param id
	 */
	public Produto(Long id) {
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
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @return the idProdutoPai
	 */
	public Long getIdProdutoPai() {
		return idProdutoPai;
	}

	/**
	 * @return the produtoPai
	 */
	public Produto getProdutoPai() {
		return produtoPai;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @param idProdutoPai the idProdutoPai to set
	 */
	public void setIdProdutoPai(Long idProdutoPai) {
		this.idProdutoPai = idProdutoPai;
	}

	/**
	 * @param produtoPai the produtoPai to set
	 */
	public void setProdutoPai(Produto produtoPai) {
		this.produtoPai = produtoPai;
	}

}
