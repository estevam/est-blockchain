package ca.est;

import ca.est.service.BlockService;

/**
 * @author Estevam Meneses
 * Dec. 1, 2021
 */
public class ApplitionStart {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BlockService blockService = new BlockService();
		blockService.setUp();
		blockService.givenBlockchainWhenNewBlockAddedThenSuccess();
		blockService.givenBlockchainWhenValidatedThenSuccess();
	}

}
