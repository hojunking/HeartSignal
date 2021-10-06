package com.hs.meetme.image.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageVO {
	private int imgId;
	private String imgUrl;
	private String imgName;
	private String uuid;
	private long imgSize;
	private Date imgDate;
	private String usage;
}
