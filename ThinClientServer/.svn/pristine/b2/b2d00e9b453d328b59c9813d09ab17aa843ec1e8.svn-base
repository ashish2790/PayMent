package com.awl.tch.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.awl.tch.bean.CapKey;
import com.awl.tch.bean.CapKey.CapKeyData;
import com.awl.tch.constant.ErrorConstants;
import com.awl.tch.exceptions.TCHQueryException;
import com.awl.tch.model.CapKeyDTO;

@Repository("capKeyDao")
public class CapKeyDaoImpl extends GenericDaoImpl<CapKeyDTO> implements CapkeyDao{

	private static final Logger logger = LoggerFactory.getLogger(CapKeyDaoImpl.class);
	
	@Override
	public int getCapKey(final CapKey capKey) throws TCHQueryException {
		
		logger.debug("Entering in getCpayKey");
		
		String query = "Select TC.C_COMPONENT, TC.C_INDEX, TC.C_EXPIRY, TC.C_CAPKEY, TSM.S_RID, TC.C_CAPKEY_VERSION_NUMBER from TCH_CAPKEY TC JOIN TCH_SCHEMA_MASTER TSM ON TC.C_SCHEMAID = TSM.S_SCHEMAID";
		
		List<CapKeyData> listCapyKey = new ArrayList<CapKeyData>();	
		
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
		
		CapKeyData capKeyData = capKey.new CapKeyData();
		
		Properties ep = new Properties();
		//InputStream in = Property.class.getResourceAsStream("/application.properties");
		File f= new File("application.properties");
		FileInputStream fis;
		int count = 0;
		int countCap = 0;
		try {
			fis = new FileInputStream(f);
			ep.load(fis);
			count = Integer.parseInt(ep.getProperty("count_cap"));
		} catch (IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(rows != null){
			for(Map<String,Object> row : rows){
				if(countCap >= count){
					break;
				}
				capKeyData.setPubExponent((String) row.get("C_COMPONENT"));
				capKeyData.setPubIndex((String) row.get("C_INDEX"));
				capKeyData.setPubModulos((String) row.get("C_CAPKEY"));
				capKeyData.setPubRid((String) row.get("S_RID"));
				capKeyData.setCapkeyVersionNumber(((BigDecimal) row.get("C_CAPKEY_VERSION_NUMBER")).toPlainString());
				listCapyKey.add(capKeyData);
				countCap++;
			}
		}else{
			throw new TCHQueryException(ErrorConstants.TCH_C001,"Capkey not present exception");
		}
		CapKeyData[] capKeydataArr  = new CapKeyData[listCapyKey.size()];
		capKey.setCapKeyData(listCapyKey.toArray(capKeydataArr));
		
		logger.debug("Exiting cap key");
		
		return 0;
	}


}
