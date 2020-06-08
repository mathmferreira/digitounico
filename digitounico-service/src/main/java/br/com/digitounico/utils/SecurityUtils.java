package br.com.digitounico.utils;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import br.com.digitounico.dto.UsuarioDTO;
import br.com.digitounico.exceptions.RSAAlgorithmException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityUtils {

	private static final String ALGORITMO = "RSA";
	private static final Map<Long, KeyPair> keyPairs = new HashMap<>();

	private SecurityUtils() {
		throw new IllegalAccessError("Classe utiliária");
	}

	public static UsuarioDTO criptografar(UsuarioDTO dto) {
		log.debug(">>> SecurityUtils.criptografar [dto={}]", dto);
		gerarChaves(dto.getId());
		PublicKey key = keyPairs.get(dto.getId()).getPublic();
		dto.setNome(executarAlgoritmoRSA(dto.getNome(), key));
		dto.setEmail(executarAlgoritmoRSA(dto.getEmail(), key));
		log.debug("<<< SecurityUtils.criptografar [dto={}]", dto);
		return dto;
	}

	public static UsuarioDTO descriptografar(UsuarioDTO dto) {
		log.debug(">>> SecurityUtils.descriptografar [dto={}]", dto);
		gerarChaves(dto.getId());
		PrivateKey key = keyPairs.get(dto.getId()).getPrivate();
		dto.setNome(executarAlgoritmoRSA(dto.getNome(), key));
		dto.setEmail(executarAlgoritmoRSA(dto.getEmail(), key));
		log.debug("<<< SecurityUtils.descriptografar [dto={}]", dto);
		return dto;
	}

	private static void gerarChaves(Long idUsuario) {
		log.debug(">>> SecurityUtils.gerarChaves [idUsuario={}]", idUsuario);
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITMO);
			keyGen.initialize(2048);
			keyPairs.put(idUsuario, keyGen.generateKeyPair());
			log.debug("<<< SecurityUtils.gerarChaves [idUsuario={}]", idUsuario);
		} catch (NoSuchAlgorithmException ex) {
			log.error("<<< SecurityUtils.gerarChaves [error={}]", ex.getMessage());
		}
	}

	private static String executarAlgoritmoRSA(String texto, PublicKey key) {
		try {
			Cipher cipher = Cipher.getInstance(ALGORITMO);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return Base64.getEncoder().encodeToString(cipher.doFinal(texto.getBytes(StandardCharsets.UTF_8)));
		} catch (Exception ex) {
			log.error("<<< SecurityUtils.executarAlgoritmoRSA [error={}]", ex.getMessage());
			throw new RSAAlgorithmException();
		}
	}

	private static String executarAlgoritmoRSA(String texto, PrivateKey key) {
		try {
			Cipher cipher = Cipher.getInstance(ALGORITMO);
			cipher.init(Cipher.DECRYPT_MODE, key);
			return new String(cipher.doFinal(Base64.getDecoder().decode(texto)), StandardCharsets.UTF_8.name());
		} catch (Exception ex) {
			log.error("<<< SecurityUtils.executarAlgoritmoRSA [error={}]", ex.getMessage());
			throw new RSAAlgorithmException();
		}
	}

}
