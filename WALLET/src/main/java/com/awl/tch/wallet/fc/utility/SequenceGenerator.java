package com.awl.tch.wallet.fc.utility;

import javax.sql.DataSource;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;

public class SequenceGenerator {
	
		/**
		 * Generate unique sequence from sequence in database to generate sequence number 
		 * @param dataSource
		 * @return sequence number
		 */
		public static int getNextSequence(DataSource dataSource) {
			return new OracleSequenceMaxValueIncrementer(dataSource, "SEQ_TCH_RRN").nextIntValue();
		}
		
}
