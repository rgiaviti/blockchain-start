package io.github.rgiaviti.bcs;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import lombok.extern.java.Log;

@Log
public class NoobChain {

	public static List<Block> blockchain = new ArrayList<>();
	public static int difficulty = 1;

	public static void main(String[] args) {
		blockchain = BlockHelper.generateBlockchain(50);

		for (Block block : blockchain) {
			//log.log(Level.INFO, "Trying to mine block: " + new String(block.getData()));
			//block.mineBlock(difficulty);
		}
		
		blockchain = BlockHelper.insertInvalidBlockIntoChain(blockchain, BlockHelper.generateInvalidBlock());

		log.log(Level.INFO, "Blockchain is Valid: " + isChainValid().toString());
	}

	public static Boolean isChainValid() {
		Block currentBlock;
		Block previousBlock;

		for (int i = 1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i - 1);

			if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
				return false;
			}
			// compare previous hash and registered previous hash
			if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
				return false;
			}
		}
		return true;
	}

}
