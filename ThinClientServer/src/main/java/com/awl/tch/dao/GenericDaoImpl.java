package com.awl.tch.dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.awl.tch.annotation.Column;
import com.awl.tch.annotation.ICurrentTimestamp;
import com.awl.tch.annotation.Id;
import com.awl.tch.annotation.Sequence;
import com.awl.tch.annotation.Table;
import com.awl.tch.annotation.Transient;
import com.awl.tch.annotation.UCurrentTimestamp;
import com.awl.tch.util.Property;

public abstract class GenericDaoImpl<T> extends AbstractGenericDao<T>  {

	private Class<T> type;

	private String tableName;

	private String idColumnName;

	private List<String> columnNames = new ArrayList<String>();

	private List<String> variableNames = new ArrayList<String>();

	private ConcurrentHashMap<String,String> fieldVarMap = new ConcurrentHashMap<String,String>();

	private static final Logger logger = LoggerFactory
			.getLogger(GenericDaoImpl.class);

	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<T>) pt.getActualTypeArguments()[0];
		getTableDetails();

	}

	private void getTableDetails() {
		if (type.isAnnotationPresent(Table.class)) {

			Annotation annotation = type.getAnnotation(Table.class);
			Table table = (Table) annotation;
			tableName = table.name();
		} else {
			tableName = type.getName();
		}

		for (Field field : type.getDeclaredFields()) {
			if (field.isAnnotationPresent(Transient.class)) {
				continue;
			}

			variableNames.add(field.getName());

			if (field.isAnnotationPresent(Column.class)) {
				Annotation annotation = field.getAnnotation(Column.class);
				Column column = (Column) annotation;
				columnNames.add(column.name());
				fieldVarMap.put(field.getName(), column.name().toUpperCase());

				if (field.isAnnotationPresent(Id.class)) {
					idColumnName = column.name();
				}
			} else {
				fieldVarMap.put(field.getName(),field.getName());
				columnNames.add(field.getName());
				if (field.isAnnotationPresent(Id.class)) {
					idColumnName = field.getName();
				}
			}
		}
	}

	@Override
	public int save(T t) {
		int returnValue = -1;
		try {

			returnValue = fireInsertQuery(t);

			if (returnValue != 0) {
				logger.debug(t.toString()+" inserted into Table name [" + tableName + "]");
			} else
			{
				logger.debug(t.toString()+" failed to insert into Table name [" + tableName + "]");
			}
		}
		catch(DuplicateKeyException e){
			logger.debug("Updating record as already exists");
			update(t);
		}
		catch (SQLIntegrityConstraintViolationException e) {
			logger.debug("Updating record as already exists");
			update(t);
		}
		catch (DataAccessException e) {
			e.printStackTrace();
			logger.error("DataAccessException while insert into Table name [" + tableName + "] :"+e,e);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception while insert into Table name [" + tableName + "] :"+e,e);
		} 
		
		return returnValue ;
	}

	private int fireInsertQuery(T t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException,SQLIntegrityConstraintViolationException,DuplicateKeyException
	{
		String query = "insert into " + tableName + " ( ";
		String values = "";
		LinkedList<Object> valueList = new LinkedList<Object>();
		for (Field field : type.getDeclaredFields()) {
			PropertyDescriptor pd = new PropertyDescriptor(field.getName(), type);
			Method getter = pd.getReadMethod();
			Object fieldValue = getter.invoke(t);
			
			if(fieldValue!=null)
			{
				query = query + fieldVarMap.get(field.getName()) + ", ";
				values = values + "?, ";
				valueList.add(fieldValue);
			}
			else
			{
				if(fieldValue==null && field.isAnnotationPresent(ICurrentTimestamp.class) )
				{
					query = query + fieldVarMap.get(field.getName()) + ", ";
					values = values + "CURRENT_TIMESTAMP" + ", ";
				}else if(fieldValue==null && field.isAnnotationPresent(Id.class) && field.isAnnotationPresent(Sequence.class) )
				{
					query = query + fieldVarMap.get(field.getName()) + ", ";
					Annotation annotation = field.getAnnotation(Sequence.class);
					Sequence seq = (Sequence) annotation;
					values = values + seq.name()+ ".NEXTVAL" + ", ";
				}
			}
			
		}
		query = query.substring(0, query.length() - 2) + " ) values ( "+values.substring(0, values.length() - 2) + " )";
		if(Property.isShowSql())
		{
			logger.debug("Save Query for table name [" + tableName + "] :"
					+ query);
		}
		int ret = getJdbcTemplate().update(query, valueList.toArray());

		return ret;
	}

	@Override
	public T getById(final Object id) {
		// TODO Auto-generated method stub
	T obj = null;
		try{

			obj = fireSelectQuery(null,id);
			if(obj !=null){
				logger.debug("Data found in table name [" + tableName + "] for id :"+id);
			}else{
				logger.debug("No Data found in table name [" + tableName + "] for id :"+id);
			}
		}catch(DataAccessException e){
			logger.error("DataAccessException while selecting record from Table name [" + tableName + "] :"+e,e);
			e.printStackTrace();
		}
		catch(Exception e){
			logger.error("Exception while selecting record from Table name [" + tableName + "] :"+e,e);
			e.printStackTrace();
		}
		return obj;
	}
	@Override
	public T getById(String columnNames ,final Object... id) {
		T obj = null;
		try{

			obj = fireSelectQuery(columnNames ,id);
			if(obj !=null){
				logger.debug("Data found in table name [" + tableName + "] for id :"+id);
			}else{
				logger.debug("No Data found in table name [" + tableName + "] for id :"+id);
			}
		}catch(DataAccessException e){
			logger.error("DataAccessException while selecting record from Table name [" + tableName + "] :"+e,e);
			e.printStackTrace();
		}
		catch(Exception e){
			logger.error("Exception while selecting record from Table name [" + tableName + "] :"+e,e);
			e.printStackTrace();
		}
		return obj;
	}
	private T fireSelectQuery(String columnNames ,final Object... id)
	{
		//StringBuilder query = new StringBuilder();
		String query = "";
		if(columnNames == null){
			query = "select * from "+tableName+" where "+idColumnName+" = ?";
		}else{
			String[] colNames = columnNames.split(",");
			query = "select * from "+ tableName + " where  ";
			for(String column : colNames){
				query = query + column + " = ? and ";
			}
			query = query.substring(0, query.length() - 4);
			
		}
		
		if(Property.isShowSql() && columnNames == null)
		{
			logger.debug("Select Query for table name [" + tableName + "] with id:"
					+id +" is :"+ query);
		}else if(Property.isShowSql() && columnNames != null){
			logger.debug("Select Query for table name [" + tableName +"] is " +query);
			
		}
		
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement arg0) throws SQLException {
				// TODO Auto-generated method stub
				int i = 1;
				for(Object idValue : id){
				arg0.setObject(i, idValue);
				i++;
				}
			}
		};

		T obj =	getJdbcTemplate().query(query.toString(),pss , new ResultSetExtractor<T>() {
			public T extractData(ResultSet rs) throws SQLException, DataAccessException {
				T obj = null;
				if (rs.next()) {

					try{
						Constructor<T> ctor = type.getConstructor();
						obj = ctor.newInstance();
						logger.debug("creating instance of "+ctor.getName());

						ResultSetMetaData metaData = rs.getMetaData();
						int count = metaData.getColumnCount(); //number of column
						String columnName[] = new String[count];

						for (int i = 1; i <= count; i++)
						{
							columnName[i-1] = metaData.getColumnLabel(i);
							String fieldName = getKey(columnName[i-1]);
							PropertyDescriptor pd = new PropertyDescriptor(fieldName, type);
							Method setter = pd.getWriteMethod();
							Class<?> fieldClassType = type.getDeclaredField(fieldName).getType();
							setter.invoke(obj,rs.getObject(columnName[i-1], fieldClassType));
						}
					} catch (NoSuchMethodException e) {
						
						e.printStackTrace();
						logger.error("Exception while selecting record from Table name [" + tableName + "] :"+e,e);
					} catch (SecurityException e) {
						
						e.printStackTrace();
						logger.error("Exception while selecting record from Table name [" + tableName + "] :"+e,e);
					} catch (InstantiationException e) {
						
						e.printStackTrace();
						logger.error("Exception while selecting record from Table name [" + tableName + "] :"+e,e);
					} catch (IllegalAccessException e) {
						
						e.printStackTrace();
						logger.error("Exception while selecting record from Table name [" + tableName + "] :"+e,e);
					} catch (IllegalArgumentException e) {
						
						e.printStackTrace();
						logger.error("Exception while selecting record from Table name [" + tableName + "] :"+e,e);
					} catch (InvocationTargetException e) {
						
						e.printStackTrace();
						logger.error("Exception while selecting record from Table name [" + tableName + "] :"+e,e);
					} catch (IntrospectionException e) {
						
						e.printStackTrace();
						logger.error("Exception while selecting record from Table name [" + tableName + "] :"+e,e);
					} catch (Exception e) {
						
						e.printStackTrace();
						logger.error("Exception while selecting record from Table name [" + tableName + "] :"+e,e);
					}
				}
				return obj;
			}
		});

		return obj;
	}



	@Override
	public int update(T t) {
		int returnValue = -1;
		try {
			returnValue = fireUpdateQuery(t);
			if (returnValue != 0) {
				logger.debug(t.toString()+" updated into Table name [" + tableName + "]");
			} else
			{
				logger.debug(t.toString()+" failed to update into Table name [" + tableName + "]");
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			logger.error("DataAccessException while update into Table name [" + tableName + "] :"+e,e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception while update into Table name [" + tableName + "] :"+e,e);
		} 
		return returnValue;
	}
	
	private int fireUpdateQuery(T t) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		
		String query = "update " + tableName + " set ";
		Object idColumnValue = null;
		LinkedList<Object> valueList = new LinkedList<Object>();
		for (Field field : type.getDeclaredFields()) {
			PropertyDescriptor pd = new PropertyDescriptor(field.getName(), type);
			Method getter = pd.getReadMethod();
			Object fieldValue = getter.invoke(t);

			if(fieldValue!=null)
			{
				if(idColumnName.equals(fieldVarMap.get(field.getName())))
				{
					idColumnValue = fieldValue;
					continue;
				}
				query = query + fieldVarMap.get(field.getName()) + " = ? , ";
				valueList.add(fieldValue);
			}
			else
			{
				if(fieldValue==null && field.isAnnotationPresent(UCurrentTimestamp.class) )
				{
					query = query + fieldVarMap.get(field.getName()) + " = CURRENT_TIMESTAMP , ";
				}
			}
		}
		if(idColumnValue!=null)
			valueList.add(idColumnValue);
		
		query = query.substring(0, query.length() - 2) + " where " + idColumnName + " = ?";
		
		
		if(Property.isShowSql())
		{
			logger.debug("Update Query for table name [" + tableName + "] :"
					+ query);
		}
		
		int ret = getJdbcTemplate().update(query,valueList.toArray());

		return ret;

	}

	@Override
	public int deleteById(Object id) {
		// TODO Auto-generated method stub
		int returnValue = -1;
		try {
			
			returnValue = fireDeleteQuery(id);
			if (returnValue != 0) {
				logger.debug("Record with id :"+id+" deleted into Table name [" + tableName + "]");
			} else
			{
				logger.debug("Record with id :"+id+" failed to delete into Table name [" + tableName + "]");
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			logger.error("DataAccessException while delete from Table name [" + tableName + "] :"+e,e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception while delete from Table name [" + tableName + "] :"+e,e);
		} 
		return returnValue;
	}
	
	private int fireDeleteQuery(Object id)
	{
		
		String query = "delete from " + tableName + " where " + idColumnName + " = ?";
		if(Property.isShowSql())
		{
			logger.debug("Delete Query for table name [" + tableName + "] :"
					+ query);
		}
		int ret = getJdbcTemplate().update(query,id);

		return ret;

	}

	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		List<T> objList = null;
		try{

			objList = fireSelectAllQuery();
			if(objList !=null && objList.size() > 0){
				logger.debug("Data found in table name [" + tableName + "] with size :"+objList.size());
			}else{
				logger.debug("No Data found in table name [" + tableName + "]");
			}
		}catch(DataAccessException e){
			logger.error("DataAccessException while selecting record from Table name [" + tableName + "] :"+e,e);
			e.printStackTrace();
		}
		catch(Exception e){
			logger.error("Exception while selecting record from Table name [" + tableName + "] :"+e,e);
			e.printStackTrace();
		}
		return objList;
	}
	
	private List<T> fireSelectAllQuery()
	{
		String query = "select * from "+tableName;
		if(Property.isShowSql())
		{
			logger.debug("Select All Query for table name [" + tableName + "] is :"+ query);
		}

		List<T> obj =	getJdbcTemplate().query(query, new RowMapper<T>(){

			@Override
			public T mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				T obj = null;

				try{
					Constructor<T> ctor = type.getConstructor();
					obj = ctor.newInstance();
					logger.debug("creating instance of "+ctor.getName());

					ResultSetMetaData metaData = rs.getMetaData();
					int count = metaData.getColumnCount(); //number of column
					String columnName[] = new String[count];

					for (int i = 1; i <= count; i++)
					{
						columnName[i-1] = metaData.getColumnLabel(i); 
						String fieldName = getKey(columnName[i-1]);
						PropertyDescriptor pd = new PropertyDescriptor(fieldName, type);
						Method setter = pd.getWriteMethod();
						Class<?> fieldClassType = type.getDeclaredField(fieldName).getType();
						setter.invoke(obj,rs.getObject(columnName[i-1], fieldClassType));
					}
				} catch (NoSuchMethodException e) {
					
					e.printStackTrace();
					logger.error("Exception while selecting record from Table name [" + tableName + "] :"+e,e);
				} catch (SecurityException e) {
					
					e.printStackTrace();
					logger.error("Exception while selecting record from Table name [" + tableName + "] :"+e,e);
				} catch (InstantiationException e) {
					
					e.printStackTrace();
					logger.error("Exception while selecting record from Table name [" + tableName + "] :"+e,e);
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
					logger.error("Exception while selecting record from Table name [" + tableName + "] :"+e,e);
				} catch (IllegalArgumentException e) {
					
					e.printStackTrace();
					logger.error("Exception while selecting record from Table name [" + tableName + "] :"+e,e);
				} catch (InvocationTargetException e) {
					
					e.printStackTrace();
					logger.error("Exception while selecting record from Table name [" + tableName + "] :"+e,e);
				} catch (IntrospectionException e) {
					
					e.printStackTrace();
					logger.error("Exception while selecting record from Table name [" + tableName + "] :"+e,e);
				} catch (Exception e) {
					
					e.printStackTrace();
					logger.error("Exception while selecting record from Table name [" + tableName + "] :"+e,e);
				}
			return obj;
			}
			
		});

		return obj;
	}
	
	@Override
	public long getSequence(String sequenceName) {
		// TODO Auto-generated method stub
		long seq = -1;
		try{
			String query = "select " + sequenceName + ".NEXTVAL from dual";
			
			seq = getJdbcTemplate().queryForObject(query, Long.class);
			
			if(seq !=-1){
				logger.debug("Sequence generated for Sequence name [" + sequenceName + "] :"+seq);
			}
		}catch(DataAccessException e){
			logger.error("DataAccessException while generating sequence from Sequence [" + sequenceName + "] :"+e,e);
			e.printStackTrace();
		}
		catch(Exception e){
			logger.error("Exception while generating sequence from Sequence [" + sequenceName + "] :"+e,e);
			e.printStackTrace();
		}
		return seq;
	}

	private String getKey(String value)
	{
		Set<Entry<String, String>> entrySet =  fieldVarMap.entrySet();
		for(Entry<String, String> entry : entrySet)
		{
			if(value.equals(entry.getValue()))
			{
				return entry.getKey();
			}
		}
		return null;
	}
	
	@Override
	public int save(T t, JdbcTemplate jdbcTemplate) {
		int returnValue = -1;
		try {

			returnValue = fireInsertQuery(t, jdbcTemplate);

			if (returnValue != 0) {
				logger.debug(t.toString()+" inserted into Table name [" + tableName + "]");
			} else
			{
				logger.debug(t.toString()+" failed to insert into Table name [" + tableName + "]");
			}
		}
		catch (DataAccessException e) {
			e.printStackTrace();
			logger.error("DataAccessException while insert into Table name [" + tableName + "] :"+e,e);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception while insert into Table name [" + tableName + "] :"+e,e);
		} 
		
		return returnValue ;
	}
	
	private int fireInsertQuery(T t, JdbcTemplate jdbcTemplate) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException
	{
		String query = "insert into " + tableName + " ( ";
		String values = "";
		LinkedList<Object> valueList = new LinkedList<Object>();
		for (Field field : type.getDeclaredFields()) {
			PropertyDescriptor pd = new PropertyDescriptor(field.getName(), type);
			Method getter = pd.getReadMethod();
			Object fieldValue = getter.invoke(t);
			
			if(fieldValue!=null)
			{
				query = query + fieldVarMap.get(field.getName()) + ", ";
				values = values + "?, ";
				valueList.add(fieldValue);
			}
			else
			{
				if(fieldValue==null && field.isAnnotationPresent(ICurrentTimestamp.class) )
				{
					query = query + fieldVarMap.get(field.getName()) + ", ";
					values = values + "CURRENT_TIMESTAMP" + ", ";
				}else if(fieldValue==null && field.isAnnotationPresent(Id.class) && field.isAnnotationPresent(Sequence.class) )
				{
					query = query + fieldVarMap.get(field.getName()) + ", ";
					Annotation annotation = field.getAnnotation(Sequence.class);
					Sequence seq = (Sequence) annotation;
					values = values + seq.name()+ ".NEXTVAL" + ", ";
				}
			}
			
		}
		query = query.substring(0, query.length() - 2) + " ) values ( "+values.substring(0, values.length() - 2) + " )";
		if(Property.isShowSql())
		{
			logger.debug("Save Query for table name [" + tableName + "] :"
					+ query);
		}
		int ret = jdbcTemplate.update(query, valueList.toArray());

		return ret;
	}

}
