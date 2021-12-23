/**
 * 
 */
package ca.est.entity;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Estevam Meneses Dec. 1, 2021
 * The fundamental units of a blockchain are blocks. A single block can encapsulate several transactions or other valuable data
 * 
 */
public class Block {
	private static Logger log = LoggerFactory.getLogger(Block.class);
	private long timestamp; // creation of this block
	private String hash; // Hash of the previous block, an important part to build the chain
	private String previousHash;
	private String data; // data
	private int nonce; // arbitrary number that can be used just once in a cryptographic

	@SuppressWarnings("unused")
	private Block() {
		// TODO Auto-generated constructor stub
	}

	public Block(String data, String previousHash, long timeStamp) {
		this.data = data;
		this.previousHash = previousHash;
		this.timestamp = timeStamp;
		this.hash = calculateBlockHash();
	}

	public Block(long timestamp, String hash, String previousHash, String data, int nonce) {
		super();
		this.timestamp = timestamp;
		this.hash = hash;
		this.previousHash = previousHash;
		this.data = data;
		this.nonce = nonce;
	}
	
	/**
	 * Primarily, the hash of a block consists of the transactions it encapsulates
	 * The hash also consists of the timestamp of the block's creation
	 * Finally, the hash of the current block also includes the hash of the previous block
	 * @return
	 */
	public String calculateBlockHash() {
		String dataToHash = previousHash + Long.toString(timestamp) + Integer.toString(nonce) + data;
		MessageDigest digest = null;
		byte[] bytes = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			bytes = digest.digest(dataToHash.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
			log.error("{} {}",Level.SEVERE, ex.getMessage());
		}
		StringBuffer buffer = new StringBuffer();
		for (byte b : bytes) {
			buffer.append(String.format("%02x", b));
		}
		
		log.info("Calculate block hash result: {}", buffer.toString());
		return buffer.toString();
	}

	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * @param hash the hash to set
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

	/**
	 * @return the previousHash
	 */
	public String getPreviousHash() {
		return previousHash;
	}

	/**
	 * @param previousHash the previousHash to set
	 */
	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	/**
	 * @return the ledger
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param ledger the ledger to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @return the nonce
	 */
	public int getNonce() {
		return nonce;
	}

	/**
	 * @param nonce the nonce to set
	 */
	public void setNonce(int nonce) {
		this.nonce = nonce;
	}
}
