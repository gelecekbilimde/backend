package org.gelecekbilimde.scienceplatform.auth.config;

import lombok.Getter;
import org.gelecekbilimde.scienceplatform.auth.util.AuthKeyPairUtil;
import org.gelecekbilimde.scienceplatform.common.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

@Component
public class TokenConfiguration {

	@Getter
	@Value("${application.security.jwt.expiration}")
	private long tokenExpiration;

	@Getter
	@Value("${application.security.jwt.refresh-token.expiration}")
	private long refreshExpiration;

	@Getter
	@Value("${application.security.jwt.guest-token.expiration}")
	private Long guestTokenExpiration;

	@Value("${application.security.jwt.private-key}")
	private String privateKeyPath;

	@Value("${application.security.jwt.public-key}")
	private String publicKeyPath;

	@Getter
	private final PrivateKey privateKey;

	@Getter
	private final PublicKey publicKey;

	public TokenConfiguration() {

		boolean keyPairExists = FileUtil.isExists(this.privateKeyPath) && FileUtil.isExists(this.publicKeyPath);
		if (keyPairExists) {
			this.privateKey = AuthKeyPairUtil.findAndConvertPrivateKey(this.privateKeyPath);
			this.publicKey = AuthKeyPairUtil.findAndConvertPublicKey(this.publicKeyPath);
			return;
		}

		final KeyPair keyPair = AuthKeyPairUtil.generateKeyPair();
		this.privateKey = keyPair.getPrivate();
		this.publicKey = keyPair.getPublic();

	}

}
