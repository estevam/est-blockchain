/**
 * 
 */
package ca.est.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.est.dao.BlockDAO;
import ca.est.entity.Block;
import ca.est.util.BlockUtil;

/**
 * @author Estevam Meneses Dec. 1, 2021
 */
public class BlockService implements BlockDAO {

	private static Logger log = LoggerFactory.getLogger(BlockService.class);
	public static List<Block> blockchain = new ArrayList<Block>();
	public static int prefix = 4;
	public static String prefixString = new String(new char[prefix]).replace('\0', '0');
	private BlockUtil blockUtil = new BlockUtil();

	/**
	 * 
	 */
	public BlockService() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 		
	 */
	@Override
	public void setUp() {
		log.info("setUp");
		Block genesisBlock = new Block("The is the Genesis Block.", "0", new Date().getTime());

		genesisBlock = blockUtil.mineBlock(prefix, genesisBlock);
		blockchain.add(genesisBlock);
		Block firstBlock = new Block("The is the First Block.", genesisBlock.getHash(), new Date().getTime());
		firstBlock = blockUtil.mineBlock(prefix, firstBlock);
		log.info("{}", firstBlock.toString());
		blockchain.add(firstBlock);
	}

	/**
	 * 
	 */
	@Override
	public void givenBlockchainWhenNewBlockAddedThenSuccess() {
		log.info("givenBlockchain_whenNewBlockAdded_then_Success");
		Block newBlock = new Block("The is a New Block.", blockchain.get(blockchain.size() - 1).getHash(),
				new Date().getTime());
		// newBlock.mineBlock(prefix);
		newBlock = blockUtil.mineBlock(prefix, newBlock);
		// assertTrue(newBlock.getHash().substring(0, prefix).equals(prefixString));
		log.info("{}", newBlock.toString());
		blockchain.add(newBlock);
	}

	/**
	 * 
	 */
	@Override
	public void givenBlockchainWhenValidatedThenSuccess() {
		log.info("givenBlockchain_whenValidated_then_Success");
		boolean flag = true;
		for (int i = 0; i < blockchain.size(); i++) {
			String previousHash = i == 0 ? "0" : blockchain.get(i - 1).getHash();
			flag = blockchain.get(i).getHash().equals(blockchain.get(i).calculateBlockHash())
					&& previousHash.equals(blockchain.get(i).getPreviousHash())
					&& blockchain.get(i).getHash().substring(0, prefix).equals(prefixString);
			if (!flag)
				break;
		}
		// log.info("{}",newBlock.toString());
		// assertTrue(flag);
	}

	@Override
	public void tearDown() {
		blockchain.clear();
	}

}
