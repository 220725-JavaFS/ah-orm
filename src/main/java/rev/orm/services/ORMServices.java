package rev.orm.services;

import java.util.List;

import rev.orm.daos.ObjectDAO;
import rev.orm.daos.ObjectDAOImpl;

public class ORMServices {
	//test service layer
	public ObjectDAO objDao = new ObjectDAOImpl();
	
	public void insertNewObject(Object obj){
		objDao.storeObject(obj);
	}
	
	public Object retriveRowContentByColumn(String content, int columnNum, Object obj) {
		return objDao.getByContentByColumnNum(content, columnNum, obj);
	}
	
	public List<Object> retriveAll(Object obj){
		return objDao.getAll(obj);
	}
	
	public Object updateRowContentByColumn(String setContent, int setColumn, String whereContent, int whereColumn, Object obj) {
		return objDao.updateRowByColumnNum(setContent, setColumn, whereContent, whereColumn, obj);
	}
	
	public Object deleteRowContentByColumn(String whereContent, int whereColumnNum, Object obj) {
		return objDao.deleteRowByColumnNum(whereContent, whereColumnNum, obj);
	}
	
	
	
}
