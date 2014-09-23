package com.gtotek.kidquiz.dao;

import android.annotation.SuppressLint;
import java.io.Serializable; 

public class QuestionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private String image;

	private String dapAnCoDau;

	private String dapAnKoDau;
 

	private String content;

	public QuestionEntity() {
	}

	public QuestionEntity(int id, String image, String dapAnCoDau,
			String dapAnKoDau) {
		super();
		this.id = id;
		this.image = image;
		this.dapAnCoDau = dapAnCoDau;
		this.dapAnKoDau = dapAnKoDau;
	}

	public String getContent() {
		return content != null ? content : "Đang cập nhật";
	}

	public void setContent(String content) {
		this.content = content;
	}
  
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() { 
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDapAnCoDau() {
		return dapAnCoDau;
	}

	@SuppressLint("DefaultLocale")
	public void setDapAnCoDau(String dapAnCoDau) {
		this.dapAnCoDau = dapAnCoDau.toUpperCase();
	}

	public String getDapAnKoDau() {
		return dapAnKoDau;
	}

	@SuppressLint("DefaultLocale")
	public void setDapAnKoDau(String dapAnKoDau) {
		this.dapAnKoDau = dapAnKoDau.toUpperCase();
	}

	@Override
	public String toString() {
		return "QuestionEntity [id=" + id + ", image=" + image
				+ ", dapAnCoDau=" + dapAnCoDau + ", dapAnKoDau=" + dapAnKoDau
				+ "]";
	}

}
