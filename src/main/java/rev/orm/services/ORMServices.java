package rev.orm.services;

import java.util.List;

import rev.orm.daos.ObjectDAO;
import rev.orm.daos.ObjectDAOImpl;

public class ORMServices {
	//test service layer
	public ObjectDAO objDao = new ObjectDAOImpl();
	
	/**
	 * Creates and stores new Object in Database
	 * @param obj 
	 */
	public void insertNewObject(Object obj){
		objDao.storeObject(obj);
	}
	
	/**
	 * @param content that is being searched
	 * @param columnNum placement of column within the database
	 * @param obj Object which is begin accessed
	 * @return New Object
	 * 
	 * Select * from obj where columnNum = content;
	 */
	public Object retriveRowContentByColumn(String content, int columnNum, Object obj) {
		return objDao.getByContentByColumnNum(content, columnNum, obj);
	}
	
	/**
	 * @param obj
	 * @return New Object
	 * 
	 * Select * from obj;
	 */
	public List<Object> retriveAll(Object obj){
		return objDao.getAll(obj);
	}
	
	/**
	 * @param setContent
	 * @param setColumn
	 * @param whereContent
	 * @param whereColumn
	 * @param obj
	 * @return
	 * 
	 * Update obj Set setColumn = setContent Where whereColumn = whereContent; 
	 */
	public Object updateRowContentByColumn(String setContent, int setColumn, String whereContent, int whereColumn, Object obj) {
		return objDao.updateRowByColumnNum(setContent, setColumn, whereContent, whereColumn, obj);
	}
	
	/**
	 * @param whereContent
	 * @param whereColumnNum
	 * @param obj
	 * @return
	 * 
	 * Delete from obj Where whereColumnNum = whereContent;
	 */
	public Object deleteRowContentByColumn(String whereContent, int whereColumnNum, Object obj) {
		return objDao.deleteRowByColumnNum(whereContent, whereColumnNum, obj);
	}

}
