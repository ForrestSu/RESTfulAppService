/**
 * creator:谭锟芥栋
 * Date: 2014-11-14 
 * Descritiom:
 * Department: 
 *
 *
 *
 */
package com.computer.db;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.FieldFilter;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.dao.sql.OrderBy;

import com.computer.entity.AState;
import com.computer.entity.Absence;
import com.computer.entity.User;
import com.computer.util.Log;
import com.computer.util.PropertyUtils;

/**
 * @author tanqidong
 * 
 */
public class DBTools<T> {

	private static Log log = Log.getLog(PropertyUtils.class);

	private static final String tag = "MyDataSource";
	private static final String MYSQL_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	private static final String mysqlUrl = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8";

	private static NutDao mNutDao;

	private static DataSource getSimpleDataSource() {
		SimpleDataSource sds = new SimpleDataSource();

		try {
			sds.setDriverClassName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			log.i(e.getMessage());
			e.printStackTrace();
		}

		sds.setJdbcUrl(mysqlUrl);
		sds.setPassword("123456");
		sds.setUsername("root");
		return sds;
	}

	private static void createTable(Class<?> cls, boolean dropIfexist) {
		log.i("create table " + cls.getSimpleName());
		mNutDao.create(cls, dropIfexist);
	}

	public static void main(String[] args) {

	}

	public List<T> findAll(Class<T> cls) {

		log.i("find all in table " + cls.getSimpleName());
		OrderBy con=Cnd.orderBy().asc("id");//默认按照id 排序
		List list = mNutDao.query(cls, con);
		return list;
	}

	/**
	 * 多条件查询，
	 * 
	 * @param o 传入的对象不为NULL的字段都是查询条件 ，为NULL的字段就会忽略,
	 * @param operations  查询条件的比较形式 主要有 =, <, <,LIKE等，
	 * LIKE模糊查询的话，自己在字段的值赋值时注意哦，不给条件，默认为=
	 * Map<String,String>  key 为字段
	 * @return 
	 */
	public List<T> findObjects(T o, Map<String, String> operations) {
		Field[] fields = o.getClass().getDeclaredFields();
		Cnd con = Cnd.where("1", "=", "1");
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			String fieldName = fields[i].getName();
			Object fieldValue = null;
			try {
				fieldValue = fields[i].get(o);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if (fieldValue != null) {
				con.and(fieldName, (null==operations || operations.get(fieldName) == null)? "=": operations.get(fieldName), fieldValue);
			}
		}
		con.asc("id");//默认按照id 排序
		return (List<T>) mNutDao.query(o.getClass(), con);
	}
	

	public T findById(Class<T> cls, Integer id) {
		log.i("findById in table " + cls.getSimpleName() + " with id " + id);
		T object = mNutDao.fetch(cls, id);
		return object;
	}

	public T findByName(Class<T> cls, String name) {
		log.i("findById in table " + cls.getSimpleName() + " with name " + name);

		T object = mNutDao.fetch(cls, name);

		return object;
	}

	public int updateById(T object) {
		log.i("updateById in table " + object.getClass().getSimpleName());
		int result = mNutDao.updateIgnoreNull(object);
		return result;
	}
	public int updateByState(Class<T> cls,Integer tid,String oldstate,String nowstate) {
		log.i("updateById in table " + cls.getSimpleName());
		int result = mNutDao.update(cls, Chain.make("state",nowstate), Cnd.where("state","=",oldstate).and("tid","=",tid));
		return result;
	}

	public int deleteById(Class<T> cls, Integer id) {
		log.i("deleteById in table " + cls.getSimpleName() + " with id " + id);

		int result = mNutDao.delete(cls, id);
		return result;

	}

	public T insert(T o) {
		log.i("insert in table " + o.getClass().getSimpleName());
		o = mNutDao.insert(o);
		return o;
	}

	// 这里是指定建哪几张表
	static Class<?>[] tables = new Class<?>[] { Absence.class, User.class,
			AState.class };
	static {

		mNutDao = new NutDao(getSimpleDataSource());
		Properties p = PropertyUtils.loadProperties();

		for (int i = 0; i < tables.length; i++) {
			String tableName = tables[i].getSimpleName();
			if (p.containsKey(tableName)) {
				String isTableCreated = p.getProperty(tableName);

				if (isTableCreated.equals("true"))
					log.i("table " + tableName + " is alrealy created");
				else {
					log.i("create table " + tableName);
					Entity e = mNutDao.create(tables[i], true);

					if (e != null) {
						p.put(tableName, "true");
					} else {
						log.i("create table " + tableName + " fail");
					}
				}
			} else {
				log.i("create table " + tableName);
				Entity e = mNutDao.create(tables[i], true);

				if (e != null) {
					p.put(tableName, "true");
				} else {
					log.i("create table " + tableName + " fail");
				}
			}

		}

		PropertyUtils.save(p);
	}

	private static final String isTableCreated = "true";

}
