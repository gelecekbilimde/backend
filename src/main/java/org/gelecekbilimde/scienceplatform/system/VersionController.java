package org.gelecekbilimde.scienceplatform.system;

import org.gelecekbilimde.scienceplatform.exception.ClientException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class VersionController {
	@RequestMapping("/version")
	public ResponseEntity<String> version() {
		//throw new ClientException("Versiyon Alınamadı");
		return ResponseEntity.ok("API version: 0.0.1");
	}
}
