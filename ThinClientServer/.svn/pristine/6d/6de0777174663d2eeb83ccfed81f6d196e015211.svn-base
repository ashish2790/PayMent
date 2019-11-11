package com.awl.tch.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.awl.tch.bean.Payment;
import com.awl.tch.constant.Constants;
import com.awl.tch.wallet.fc.utility.SequenceGenerator;

@Component(Constants.TCH_COMMON_DAO)
public class CommonDaoImpl extends GenericDaoImpl<Payment>{

public DataSource dataSourceForSequence;
	
    @Autowired
    public void setDataSource(DataSource dataSource) {
       this.dataSourceForSequence = dataSource;
    }
	public int getSequenceNumber(){
		return SequenceGenerator.getNextSequence(dataSourceForSequence);
	}
	
}
