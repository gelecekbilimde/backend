package org.gelecekbilimde.scienceplatform.auth.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.gelecekbilimde.scienceplatform.common.exception.ServerException;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;


@UtilityClass
public class AuthKeyPairUtil {
	private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048);
		return generator.generateKeyPair();
	}

	public static String getContentOfPrivateKey(String privateKeyPath) {
		try {
			if(!Files.exists(Path.of(privateKeyPath))){
				KeyPair pair = AuthKeyPairUtil.generateKeyPair();
				return pair.getPrivate().toString();
			}
			byte[] keyBytes = Files.readAllBytes(Paths.get(privateKeyPath));
			String keyContent = new String(keyBytes);
			keyContent = keyContent.replaceAll("\\s+|-----BEGIN PRIVATE KEY-----|-----END PRIVATE KEY-----", "");
			return keyContent;
		} catch (Exception e) {
			throw new ServerException("Private Key read error: " + e.getMessage());
		}

	}

	public static String getContentOfPublicKey(String publicKeyPath) {
		try {
			if(!Files.exists(Path.of(publicKeyPath))){
				KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
				generator.initialize(2048);
				KeyPair pair = generator.generateKeyPair();
				return pair.getPrivate().toString();
			}
			byte[] keyBytes = Files.readAllBytes(Paths.get(publicKeyPath));
			String keyContent = new String(keyBytes);
			keyContent = keyContent.replaceAll("\\s+|-----BEGIN PRIVATE KEY-----|-----END PRIVATE KEY-----", "");
			return keyContent;
		} catch (Exception e) {
			throw new ServerException("Public Key read error: " + e.getMessage());
		}
	}
}
