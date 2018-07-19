package io.github.rgiaviti.bcs;

import org.apache.commons.codec.digest.DigestUtils;

import lombok.NonNull;

public class HashService {

	private static HashService instance;

	private HashService() {
		// Do nothing
	}

	public static HashService getInstance() {
		if (instance == null) {
			instance = new HashService();
		}

		return instance;
	}

	public String hashSha256(@NonNull String data) {
		return DigestUtils.sha256Hex(data);
	}

	public String hashSha512(@NonNull String data) {
		return DigestUtils.sha512Hex(data);
	}
}
