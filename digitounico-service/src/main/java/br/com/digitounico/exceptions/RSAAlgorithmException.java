package br.com.digitounico.exceptions;

@SuppressWarnings("serial")
public class RSAAlgorithmException extends RuntimeException {

	public RSAAlgorithmException() {
		super("Erro ao criptografar/descriptografar usuário");
	}

}
