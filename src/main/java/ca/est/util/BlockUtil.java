/**
 * 
 */
package ca.est.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.est.entity.Block;

/**
 * @author Estevam Meneses
 * Dec. 22, 2021
 */
public class BlockUtil {
	
	private static Logger log = LoggerFactory.getLogger(BlockUtil.class);
	/**
	 * 
	 */
	public BlockUtil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * We represent a block by a hash value. Generating the hash value of a block is called “mining” the block. 
	 * Mining a block is typically computationally expensive to do as it serves as the “proof of work”.
	 *
	 * 
	 * @param prefix
	 * @param block
	 * @return
	 */
	public Block mineBlock(int prefix, Block block) {
		log.info("Mine block {}", prefix);
		String prefixString = new String(new char[prefix]).replace('\0', '0');
		while (!block.getHash().substring(0, prefix).equals(prefixString)) {
			int nonce = block.getNonce();
			block.setNonce(nonce++);
			block.setHash(block.calculateBlockHash());
		}
		log.info("Mine hash {}", block.getHash());
		return  block;
	}
}
