/**
 * 
 */
package ca.est;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.est.entity.Block;
import ca.est.util.BlockUtil;

/**
 * @author Estevam Meneses
 * Dec. 3, 2021
 */
public class BlockchainUnitTest {
	private static Logger log = LoggerFactory.getLogger(BlockchainUnitTest.class);
    public static List<Block> blockchain = new ArrayList<Block>();
    public static int prefix = 4;
    public static String prefixString = new String(new char[prefix]).replace('\0', '0');
    private static BlockUtil blockUtil = new BlockUtil();
    @BeforeClass
    public static void setUp() {
        Block genesisBlock = new Block("The is the Genesis Block.", "0", new Date().getTime());
        genesisBlock = blockUtil.mineBlock(prefix, genesisBlock);
        blockchain.add(genesisBlock);
        Block firstBlock = new Block("The is the First Block.", genesisBlock.getHash(), new Date().getTime());
        firstBlock =  blockUtil.mineBlock(prefix,firstBlock);
        log.info("{}",firstBlock.toString());
        blockchain.add(firstBlock);
    }

    @Test
    public void givenBlockchainWhenNewBlockAddedThenSuccess() {
        Block newBlock = new Block("The is a New Block.", blockchain.get(blockchain.size() - 1)
            .getHash(), new Date().getTime());
        newBlock = blockUtil.mineBlock(prefix, newBlock);
        assertTrue(newBlock.getHash()
            .substring(0, prefix)
            .equals(prefixString));
        log.info("{}",newBlock.toString());
        blockchain.add(newBlock);
    }

    @Test
    public void givenBlockchainWhenValidatedThenSuccess() {
        boolean flag = true;
        for (int i = 0; i < blockchain.size(); i++) {
            String previousHash = i == 0 ? "0"
                : blockchain.get(i - 1)
                    .getHash();
            flag = blockchain.get(i)
                .getHash()
                .equals(blockchain.get(i)
                    .calculateBlockHash())
                && previousHash.equals(blockchain.get(i)
                    .getPreviousHash())
                && blockchain.get(i)
                    .getHash()
                    .substring(0, prefix)
                    .equals(prefixString);
            if (!flag)
                break;
        }
        //log.info("{}",newBlock.toString());
        assertTrue(flag);
    }

    @AfterClass
    public static void tearDown() {
        blockchain.clear();
    }

}
