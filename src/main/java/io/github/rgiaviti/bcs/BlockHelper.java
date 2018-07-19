package io.github.rgiaviti.bcs;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class BlockHelper {

	private BlockHelper() {
		// DO nothing
	}

	public static List<Block> generateBlockchain(final long chainSize) {
		List<Block> blockchain = new LinkedList<>();

		Block previousBlock = null;
		Block currentBlock = null;

		for (int i = 0; i <= chainSize; i++) {
			if (i == 0) {
				currentBlock = generateBlock("0");
				blockchain.add(currentBlock);
			} else {
				currentBlock = generateBlock(previousBlock.getHash());
				blockchain.add(currentBlock);
			}

			previousBlock = currentBlock;
		}

		return blockchain;
	}

	public static List<Block> insertInvalidBlockIntoChain(List<Block> blockchain, Block invalidBlock) {
		blockchain.add(randomNumber(blockchain.size()), invalidBlock);
		return blockchain;
	}
	
	public static List<Block> removeBlock(List<Block> blockchain) {
		blockchain.remove(randomNumber(blockchain.size()));
		return blockchain;
	}
	
	public static List<Block> removeBlock(List<Block> blockchain, int index) {
		blockchain.remove(index);
		return blockchain;
	}

	public static Block generateInvalidBlock() {
		return new Block(randomData(), "INVALID_PREVIOUS_HASH");
	}

	public static Block generateBlock(String previousHash) {
		return new Block(randomData(), previousHash);
	}

	private static byte[] randomData() {
		return UUID.randomUUID().toString().getBytes();
	}

	private static int randomNumber(int max) {
		return ThreadLocalRandom.current().nextInt(0, max);
	}

}
