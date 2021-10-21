package com.hs.meetme.coupleLog.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hs.meetme.coupleLog.domain.CoupleLogVO;
import com.hs.meetme.coupleLog.service.CoupleLogService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/coupleLog/*")
public class RestCoupleLogController {
	@Autowired
	CoupleLogService service;

	File fileDir = new File("src/main/resources/static/img/");

	@PostMapping("/coupleLogImg")
	public CoupleLogVO updateCoupleImage(MultipartFile file, CoupleLogVO vo, HttpServletRequest request) throws IllegalStateException, IOException {
//		String path = fileDir.getAbsolutePath() + "/coupleLog/";
		String path = request.getSession().getServletContext().getRealPath("/img/coupleLog");
		File filePath = new File(path);
		if(!filePath.exists()) {
			filePath.mkdirs();
		}
		
		System.out.println(path);
		MultipartFile ufile = file;
		if (!ufile.isEmpty() && ufile.getSize() > 0) {
			String filename = ufile.getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			String imgUrl = uuid + filename;
			File uufile = new File(path, imgUrl);
			System.out.println(uufile.getPath());
			ufile.transferTo(uufile); // 파일 옮기기
			vo.setImgUrl(imgUrl);
			System.out.println("이미지URL=" + vo.getImgUrl());
			service.logInsertImg(vo);
			System.out.println(vo.toString());

		}

		return vo;
	}

	/*
	 * private boolean checkImageType(File file) { String contentType; try {
	 * contentType = Files.probeContentType(file.toPath()); return
	 * contentType.startsWith("image"); } catch (IOException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * return false; }
	 */
	
	
	/*
	 * @GetMapping("/readImage") public ResponseEntity<byte[]>
	 * readImage(@PathVariable("imagename") String imagename) throws IOException {
	 * String path = fileDir.getAbsolutePath() + "/coupleLog/"; InputStream
	 * imageStream = new FileInputStream(path + imagename); byte[] imageByteArray =
	 * IOUtils.toByteArray(imageStream); imageStream.close(); return new
	 * ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK); }
	 */

}
