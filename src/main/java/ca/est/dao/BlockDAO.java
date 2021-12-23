/**
 * 
 */
package ca.est.dao;

/**
 * @author Estevam Meneses
 * Dec. 1, 2021
 */
public interface BlockDAO {
	public void setUp();
	public void givenBlockchainWhenNewBlockAddedThenSuccess();
	public void givenBlockchainWhenValidatedThenSuccess();
	public void tearDown();
}
