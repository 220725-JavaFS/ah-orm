package rev.orm.daos;

import java.util.List;

public interface ObjectDAO {

	public abstract void storeObject(Object obj);
	
	public abstract Object getByContentByColumnNum(String content, int columnNum, Object obj);
	
	public abstract List<Object> getAll(Object obj);

	public abstract Object updateRowByColumnNum(String setContent, int setColumn, String whereContent, int whereColumn, Object obj);
	
	public abstract Object deleteRowByColumnNum(String whereContent, int whereColumnNum, Object obj);
}
