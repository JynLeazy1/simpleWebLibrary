package com.lib.spring.api.uploads;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/uploads")
public class ImageUploadController {

	@Value("${file.upload-dir}")
	private String uploadDir;

	@Operation(summary = "Upload image", description = "Upload an image file (JPEG or PNG)")
	@ApiResponse(responseCode = "200", description = "Image uploaded successfully")
	@ApiResponse(responseCode = "400", description = "Invalid file")
	@PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {

		if (file.isEmpty()) {
			return ResponseEntity.badRequest().body("File is empty");
		}

		try {
			String filePath = saveImage(file);
			return ResponseEntity.ok("Image uploaded successfully: " + filePath);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	private String saveImage(MultipartFile file) throws IOException {

		String contentType = file.getContentType();
		if (contentType == null || !contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
			throw new IllegalArgumentException("Only JPEG or PNG images are allowed");
		}

		Path uploadPath = Paths.get(uploadDir);
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		String fileName = file.getOriginalFilename();
		Path filePath = uploadPath.resolve(fileName);
		Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

		return filePath.toString();
	}

	@Operation(summary = "Get image", description = "Retrieve an uploaded image by filename", parameters = {
			@io.swagger.v3.oas.annotations.Parameter(name = "filename", description = "Image filename", required = true, example = "photo.png", in = io.swagger.v3.oas.annotations.enums.ParameterIn.PATH) })
	@GetMapping(value = "/image/{filename}")
	public ResponseEntity<Resource> getImage(@PathVariable("filename") String filename) {
		
		System.out.println("UPLOAD DIR = [" + uploadDir + "]");

		try {
			
			if (filename.contains("..")) {
			    return ResponseEntity.badRequest().build();
			}

			
			Path filePath = Paths.get(uploadDir).resolve(filename);

			if (!Files.exists(filePath)) {
				return ResponseEntity.notFound().build();
			}	

			
			Resource resource = new UrlResource(filePath.toUri());
			
			String contentType;
			
			try {
	            contentType = Files.probeContentType(filePath);
	        } catch (IOException e) {
	            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
	        }
			

			if (contentType == null || !contentType.contains("/")) {
				contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			}

			
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

}
