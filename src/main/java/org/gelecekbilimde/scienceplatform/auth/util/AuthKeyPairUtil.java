package org.gelecekbilimde.scienceplatform.auth.util;

import lombok.experimental.UtilityClass;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.gelecekbilimde.scienceplatform.auth.exception.KeyPairConversionException;
import org.gelecekbilimde.scienceplatform.auth.exception.KeyPairGenerationException;
import org.gelecekbilimde.scienceplatform.common.util.FileUtil;

import java.io.IOException;
import java.io.StringReader;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

@UtilityClass
public class AuthKeyPairUtil {

	public static KeyPair generateKeyPair() {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			return keyPairGenerator.generateKeyPair();
		} catch (NoSuchAlgorithmException exception) {
			throw new KeyPairGenerationException(exception);
		}
	}

	public static PrivateKey findAndConvertPrivateKey(String path) {
		String formattedPrivateKeyPem = FileUtil.findContent(path);
		final StringReader keyReader = new StringReader(formattedPrivateKeyPem);
		try {
			PrivateKeyInfo privateKeyInfo = PrivateKeyInfo
				.getInstance(new PEMParser(keyReader).readObject());
			return new JcaPEMKeyConverter().getPrivateKey(privateKeyInfo);
		} catch (IOException exception) {
			throw new KeyPairConversionException(exception);
		}
	}

	public static PublicKey findAndConvertPublicKey(String path) {
		String formattedPublicKeyPem = FileUtil.findContent(path);
		StringReader keyReader = new StringReader(formattedPublicKeyPem);
		try {
			SubjectPublicKeyInfo publicKeyInfo = SubjectPublicKeyInfo
				.getInstance(new PEMParser(keyReader).readObject());
			return new JcaPEMKeyConverter().getPublicKey(publicKeyInfo);
		} catch (IOException exception) {
			throw new KeyPairConversionException(exception);
		}
	}

}
