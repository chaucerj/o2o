package com.chaucer.o2o.dto;

import java.io.InputStream;

public class ImageHolder {
	private String imgName;
	private InputStream image;

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public InputStream getImage() {
		return image;
	}

	public void setImage(InputStream image) {
		this.image = image;
	}

	public ImageHolder(String imgName, InputStream image) {
		super();
		this.imgName = imgName;
		this.image = image;
	}

}
