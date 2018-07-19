package io.github.rgiaviti.bcs;

import java.io.Serializable;
import java.time.Instant;

import lombok.Data;

@Data
public class Block implements Serializable {

	private static final long serialVersionUID = 5337599873814952518L;

	private String hash;
	private String previousHash;
	private byte[] data;
	private Long timestamp;
	private int nonce;

	public Block(byte[] data, String previousHash) {
		this.previousHash = previousHash;
		this.data = data;
		this.timestamp = Instant.now().toEpochMilli();
		this.hash = this.calculateHash();
	}

	public String calculateHash() {
		HashService hashService = HashService.getInstance();
		return hashService.hashSha256(blockInfo());
	}

	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); // Create a string with difficulty * "0"
		
		while (!hash.substring(0, difficulty).equals(target)) {
			nonce++;
			hash = calculateHash();
		}
		
		System.out.println("Block Mined!!! : " + hash);
	}

	private String blockInfo() {
		StringBuilder blockinfo = new StringBuilder();
		blockinfo.append(this.previousHash);
		blockinfo.append(timestamp);
		blockinfo.append(this.data);
		return blockinfo.toString();
	}

}
