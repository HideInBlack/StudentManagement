package utils;

import java.sql.*;
import java.util.*;
import java.io.*;
import javax.naming.*;

public final class JdbcSqlite {
	public Connection connection = null;// 连接
	public Statement statement = null;// 语句
	public ResultSet resultSet = null;// 结果集
	public int updateCount = -1;// 影响记录数
	public int moreResult=0;//更多结果，0-无，1-记录数，2-结果集

	// 构造方法（无参）
	public JdbcSqlite(){}
	
	// 构造方法（数据库文件路径）
	// 构造方法（JDBC数据源）
	public JdbcSqlite(String ds)throws Exception{
		//setConnection(ds);
		setDataSource(ds);
	}
	
	// 关闭连接
	public void close() throws SQLException {
		closeStatement();
		if (connection != null)
			connection.close();
	}
	
	// 关闭语句
	public void closeStatement() throws SQLException {
		if (resultSet != null)
			resultSet.close();
		if (statement != null)
			statement.close();
	}

	// 设置连接（数据库文件路径）
	public void setConnection(String dbPath)
			throws Exception {
		Class.forName("org.sqlite.JDBC");// 加载jdbc驱动程序
		String cs = "jdbc:sqlite:"+dbPath;// 数据库连接字符串
		close();// 关闭连接
		connection = DriverManager.getConnection(cs);// 获取数据库连接
		connection.setAutoCommit(false);// 开启事务
		statement = connection.createStatement();// 生成语句对象
	}

	// 设置数据源(JDBC数据源)
	public void setDataSource(String dataSource) throws Exception {
		Context initCtx = new InitialContext();// 初始化上下文
		Context ctx = (Context) initCtx.lookup("java:comp/env");// 上下文
		// Object
		// obj=(Object)ctx.lookup("jdbc/Db");//数据源名称，与server.xml设定一致
		Object obj = (Object) ctx.lookup(dataSource);// 数据源名称，与server.xml设定一致
		javax.sql.DataSource ds = (javax.sql.DataSource) obj;// 数据源
		connection = ds.getConnection();// 获取数据库连接
		connection.setAutoCommit(false);// 开启事务
		statement = connection.createStatement();// 生成语句对象
	}

	// 执行非查询SQL语句
	public int update(String sql) throws SQLException {
		//System.out.println(sql);
		updateCount=statement.executeUpdate(sql);
		return updateCount;
	}

	// 执行查询SQL语句
	public ResultSet query(String sql) throws SQLException {
		if (resultSet != null)resultSet.close();
		resultSet = statement.executeQuery(sql);
		return resultSet;
	}

	// 设置自动提交方式(true或false)
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		connection.setAutoCommit(autoCommit);
	}

	// 事务提交
	public void commit()throws SQLException  {
		connection.commit();// 事务提交
	}
	
	// 事务回滚
	public void rollback()throws SQLException  {
		connection.rollback();// 事务回滚
	}

	// 执行SQL命令
	public int sql(String sql) throws SQLException {
		closeStatement();		
		statement = connection.createStatement();// 生成语句对象
		boolean  b= statement.execute(sql);// 执行语句，返回多个结果
		if(b)moreResult=2;
		else moreResult=1;
		return moreResult;
	}
	
	//获取下一个结果的结果集，非结果集返回null
	public ResultSet getNextResults()throws SQLException {
		if(moreResult==2){
			resultSet = statement.getResultSet();// 获取结果集
			return resultSet;
		}
		return null;
	}
	
	//获取下一个结果的影响记录数，非影响记录数返回-2，如果返回-1则无下一个结果
	public int getNextUpdateCount()throws SQLException {
		if(moreResult==1){
			updateCount = statement.getUpdateCount();// 获取受影响记录数
			if(updateCount == -1){
				moreResult=0;//无下一个结果
				closeStatement();
			}
			return updateCount;
		}
		return -2;
	}
	
	//有更多结果，0-无，1-记录数，2-结果集
	public int getMoreResults()throws SQLException {
		if(moreResult==0)return 0;
		boolean b=statement.getMoreResults();
		if(b)moreResult=2;
		else moreResult=1;
		return moreResult;
	}
	
	//添加SQL命令到批量执行列表
	public void addBatch(String sql)throws SQLException {
		statement.addBatch(sql);		
	}
	
	//清空批量执行的SQL命令列表
	public void clearBatch()throws SQLException {
		statement.clearBatch();		
	} 

	//提交批量执行的SQL命令列表，返回更新计数组成的数组
	public int[] executeBatch()throws SQLException {
		return statement.executeBatch();		
	} 

	// 查询为对象数组
	public Object[][] queryToArray(String sql) throws SQLException {
		return toArray(query(sql));
	}

	// 查询为为JSON字符串
	public String queryToJson(String sql) throws SQLException {
		return toJson(toArray(query(sql)));
	}

	// 结果集转变为对象数组
	public static Object[][] toArray(ResultSet resultSet) throws SQLException {
		int c = resultSet.getMetaData().getColumnCount();// 获取结果集列数
		List<Object[]> list = new ArrayList<Object[]>();
		int limit=100;//转换记录数限额
		while (resultSet.next()) {
			if(--limit<0)break;//转换记录数超过限额
			Object[] a = new Object[c];
			for (int i = 0; i < c; i++) {
				a[i] = resultSet.getObject(i + 1);
				// utils[i]=resultSet.getString(i+1);//null将转换为""
			}
			list.add(a);
		}
		return (Object[][]) list.toArray(new Object[0][0]);
	}

	// 二维对象数组转换为JSON字符串
	public static String toJson(Object[][] a) {
		String s = "[";
		for (int i = 0; i < a.length; i++) {
			if (i != 0) s += ",";
			s += "[";
			for (int j = 0; j < a[i].length; j++) {
				if (j != 0) s += ",";
				if (a[i][j]==null) s +="undefined";
				else if(a[i][j].getClass().getName()=="java.lang.String")
					s += "\"" + a[i][j].toString() + "\"";
				else s += a[i][j].toString();
			}
			s += "]";
		}
		s += "]";
		return s;
	}

	// JSON字符串转换为二维字符串数组
	public static String[][] toArray(String json) {
		String[][] aa = null;
		String[] a = null;
		String s = json;
		s = s.substring(2, s.length() - 2);
		a = s.split("],[");
		aa = new String[a.length][];
		for (int i = 0; i < a.length; i++) {
			aa[i] = a[i].split(",");
		}
		return aa;
	}
	
	//从文件执行SQL命令(文件路径名，字符编码）
	//SQL命令以;结尾，可以跨越多行
	//注释为//开始至;结尾，可以跨越多行
	public int[] sqlFile(String path,String encoding)throws Exception{
		FileInputStream fis = new FileInputStream(path);   
		InputStreamReader isr = new InputStreamReader(fis, encoding);   
		BufferedReader br = new BufferedReader(isr);   
		String line = null;
		String s="";
		while ((line = br.readLine()) != null) { 
			s += line;   
			s += "\r\n"; // 补上换行符   
		}
		String[] a=s.split(";");
		for(String b:a){
			String c=b.trim();
			if(c.length()==0||c.startsWith("//"))continue;
			addBatch(c+";");
			//System.out.println(c+";");
		}
		int[] r=executeBatch();
		//commit();
		return r;
	}
}
