package com.gtotek.kidquiz.dao;

import android.annotation.SuppressLint; 

public class Letter implements Cloneable{ 

	private int id;
	
	private String letter;
	
	private boolean isVisible;
	
	private boolean isHepl;
	
	public Letter(String letter ) {
		super();
		this.letter = letter;
		this.isVisible = true;
	}
	
	public Letter(int id,String letter ) {
		super();
		this.letter = letter;
		this.isVisible = true;
		this.id = id;
	}
	
	 
	
	public boolean isHepl() {
		return isHepl;
	}

	public void setHepl(boolean isHepl) {
		this.isHepl = isHepl;
	}

	public Letter(String letter, boolean isVisible) {
		super();
		this.letter = letter;
		this.isVisible = isVisible;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@SuppressLint("DefaultLocale") public String getLetter() {
		return letter.toUpperCase();
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	@Override
	public String toString() {
		return "Letter [id=" + id + ", letter=" + letter + ", isVisible="
				+ isVisible + "]";
	}
	
	@Override
	public Object clone()  {
		// TODO Auto-generated method stub
		try {
			return super.clone();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

 
}
