package org.gelecekbilimde.scienceplatform.auth.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.gelecekbilimde.scienceplatform.auth.util.AuthKeyPairUtil;
import org.gelecekbilimde.scienceplatform.common.util.FileUtil;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Objects;

@Slf4j
@Getter
@Component
public class TokenConfiguration {

    private final long tokenExpiration;
    private final long refreshExpiration;
    private final long guestTokenExpiration;
    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    public TokenConfiguration(Environment environment) {

        this.tokenExpiration = Long.parseLong(
                Objects.requireNonNull(environment.getProperty("application.security.jwt.expiration"))
        );
        this.refreshExpiration = Long.parseLong(
                Objects.requireNonNull(environment.getProperty("application.security.jwt.refresh-token.expiration"))
        );
        this.guestTokenExpiration = Long.parseLong(
                Objects.requireNonNull(environment.getProperty("application.security.jwt.guest-token.expiration"))
        );

        final String privateKeyPath = environment.getProperty("application.security.jwt.private-key");
        final String publicKeyPath = environment.getProperty("application.security.jwt.public-key");

        boolean keyPairExists = FileUtil.isExists(privateKeyPath) && FileUtil.isExists(publicKeyPath);
        if (keyPairExists) {

			log.info("Key pair files exist");

			this.privateKey = AuthKeyPairUtil.findAndConvertPrivateKey(privateKeyPath);
            this.publicKey = AuthKeyPairUtil.findAndConvertPublicKey(publicKeyPath);

			log.info("Key pair converted");

            return;
        }

		log.warn("Key pair files do not exist");

		log.info("Key pair files are generating...");

		final KeyPair keyPair = AuthKeyPairUtil.generateKeyPair();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();

		log.info("Key pair generated");
    }

}
