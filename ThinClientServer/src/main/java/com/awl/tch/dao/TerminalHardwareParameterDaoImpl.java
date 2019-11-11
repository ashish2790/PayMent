package com.awl.tch.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.model.TerminalHardwareParameterDTO;
import com.awl.tch.util.Property;

@Repository("terminalhardwareParameterDao")
public class TerminalHardwareParameterDaoImpl extends GenericDaoImpl<TerminalHardwareParameterDTO> implements TerminalHardwareParameterDao{

	private static final Logger logger = LoggerFactory.getLogger(TerminalHardwareParameterDaoImpl.class);
	
	@Override
		
	public TerminalHardwareParameterDTO getMidTid(String terminalSerialNumber) throws TCHQueryException{
		//String sql = "select mid,tid from TC_TID_HWSR_MAPPING where hwsrno='676121421111'";//+676121421111+"'";
		String sql = "select mid,tid from TC_TID_HWSR_MAPPING where hwsrno='"+terminalSerialNumber+"'";
			
		if(Property.isShowSql()){
			logger.debug("Query for fetch MID and TID [" + sql + "]");
		}
		
		TerminalHardwareParameterDTO result = null;
		result = getJdbcTemplate().query(sql, new ResultSetExtractor<TerminalHardwareParameterDTO>(){
		    @Override
		    public TerminalHardwareParameterDTO extractData(ResultSet rs) throws SQLException ,DataAccessException {
		    	TerminalHardwareParameterDTO thpDto= new TerminalHardwareParameterDTO();
		        while(rs.next()){
		        	thpDto.setMid(rs.getString("mid"));
		        	thpDto.setTid(rs.getString("tid"));
		        }
		        return thpDto;
		    }
		});
		if(result == null){
			throw new TCHQueryException(ErrorConstants.TCH_Q001 + "Exception occured in geting MID And TID for serial number : " + terminalSerialNumber);
		}
		return result;
	}

	
}
