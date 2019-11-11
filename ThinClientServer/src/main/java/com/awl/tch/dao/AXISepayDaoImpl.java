package com.awl.tch.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.awl.tch.constant.Constants;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.model.AxisEpayDTO;
import com.awl.tch.util.Property;

@Repository(Constants.TCH_AXISEPAY_DAO)
public class AXISepayDaoImpl extends GenericDaoImpl<AxisEpayDTO> {

	private static final Logger logger = LoggerFactory.getLogger(AXISepayDaoImpl.class);
	
	public AxisEpayDTO getExistingTransactionDetails(String urn) throws TCHQueryException {
		
		//String sql = "select mid,tid from TC_TID_HWSR_MAPPING where hwsrno='676121421111'";//+676121421111+"'";
		String sql = "select A_URN, A_CID, A_RID, A_RRN, A_AMOUNT from TCH_AXISEPAY_REPORT where A_URN='"+urn+"'";
			
		if(Property.isShowSql()){
			logger.debug("Query for fetch CID and RID [" + sql + "]");
		}
		
		AxisEpayDTO result = null;
		result = getJdbcTemplate().query(sql, new ResultSetExtractor<AxisEpayDTO>(){
		    @Override
		    public AxisEpayDTO extractData(ResultSet rs) throws SQLException ,DataAccessException {
		    	AxisEpayDTO epayDTO = new AxisEpayDTO();
		        while(rs.next()){
		        	epayDTO.setCid(rs.getString("A_CID"));
		        	epayDTO.setRid(rs.getString("A_RID"));
		        	epayDTO.setRrn(rs.getString("A_RRN"));
		        	epayDTO.setAmount(rs.getString("A_AMOUNT"));
		        	epayDTO.setUrn(rs.getString("A_URN"));
		        }
		        return epayDTO;
		    }
		});
		if(result == null){
			throw new TCHQueryException(ErrorConstants.TCH_Q001 + "Exception occured in geting MID And TID for serial number : " + urn);
		}
		return result;
		
		
	}

}
