package com.cfw.movies.recommend.r;

import org.apache.commons.lang.StringUtils;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

/**
 * @author Fangwei_Cai
 * @time since 2016年5月14日 下午9:44:38
 */
public class R {
	
	private RConnection connnection;
	
	private static String DB_Connection = "dbConnection";
	
	private boolean dbConnected = false;
	
	public R(){
		this.connnection = RConnectionFactory.getConnection();
		this.loadLibraries();
	}
	
	public R(RConnection connection){
		this.connnection = connection;
		this.loadLibraries();
	}
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月14日 下午11:16:57
	 * @return
	 */
	private boolean loadLibraries(){
		
		try{
			this.voidEval("library(plyr)");
			this.voidEval("library(rmr2)");
			this.voidEval("library(rhdfs)");
			this.voidEval("hdfs.init()");
			this.voidEval("library(RMySQL)");
		}catch(RserveException e){
			e.printStackTrace();
			this.destroy();
			return false;
		}
		
		return true;
	}
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月14日 下午10:36:05
	 * @return
	 */
	public boolean connectToMysql(){
		
		String connStr = this.DB_Connection 
				+ " = " 
				+ "dbConnect("
				+ "MySQL(),"
				+ "dbname='" + RMysql.DB_NAME + "',"
				+ "username='" + RMysql.USER_NAME + "',"
				+ "password='" + RMysql.PASS_WORD + "',"
				+ "host='" + RMysql.HOST + "',"
				+ "port=" + RMysql.PORT 
				+ ")";
		
		try {
			
			this.voidEval(connStr);
			
			String setDBNames = "dbSendQuery(" + this.DB_Connection + ",'SET NAMES UTF8')";
			this.voidEval(setDBNames);
			
		} catch (RserveException e) {
			this.destroy();
			e.printStackTrace();
			return false;
		}
		
		this.dbConnected = true;
		return true;
	}
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月14日 下午11:26:05
	 * @return
	 */
	public boolean putRatingDateIntoHDFS(){
		
		String getRatingDataSql = "SELECT uid,mid,score FROM comments LIMIT 50"; 
		String queryResult = "queryResult";
		String ratingData = "ratingData";
		
		boolean result = this.dbSendQuery(queryResult, getRatingDataSql);
		if(!result){
			return false;
		}
		
		String fetchDataCMD = ratingData + " = fetch(" + queryResult + ")";
		
		String ifRatingDataExistsInHDFS_CMD = "hdfs.exists('/rating_data')";
		String deleteRatingDataFromHDFS_CMD = "hdfs.delete('/rating_data')";
		
		String toDFS_CMD = "to.dfs(" + ratingData + ",'/rating_data')";
		
		
		
		try{
			this.voidEval(fetchDataCMD);
			
			// If rating_data file exists in hdfs, delete it first before write.
			REXP exists = this.eval(ifRatingDataExistsInHDFS_CMD);
			if(exists.asString().equals("TRUE")){
				this.voidEval(deleteRatingDataFromHDFS_CMD);
			}
			
			this.voidEval(toDFS_CMD);
		}catch(RserveException | REXPMismatchException e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Do map-reduce job and save recommend result into MySQL.
	 * @author Fangwei_Cai
	 * @time since 2016年5月14日 下午11:27:15
	 * @return
	 */
	public boolean executeRecommendMR(){
		
		try{
			// Do map-reduce job.
			this.voidEval("source('/home/grid/r_workspace/mapreduce.r')");
		}catch(RserveException e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/** 
	 * @author Fangwei_Cai
	 * @time since 2016年5月15日 上午10:12:30
	 * @return
	 */
	public boolean putRecommendDataToMysql(){
		String removeTempRecommends = "DELETE FROM temp_recommends";
		String removeRecommends = "DELETE FROM recommends";
		String cmd = "dbWriteTable("+ this.DB_Connection +",'temp_recommends',recommends,append=TRUE)";
		
		try {
			this.dbSendQuery(null,removeRecommends);
			this.dbSendQuery(null,removeTempRecommends);
			REXP result = this.eval(cmd);
			if(result.asString().equals("TRUE")){
				return true;
			}
		} catch (RserveException | REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return false;
	}
	
	/**
	 * For debug.
	 * @author Fangwei_Cai
	 * @time since 2016年5月15日 上午8:47:27
	 * @param cmd
	 * @return
	 * @throws RserveException
	 */
	private REXP eval(String cmd) throws RserveException{
		System.out.println("CMD:> " + cmd);
		REXP  rexp = this.connnection.eval(cmd);
		
		return rexp;
	}
	
	/**
	 * For debug.
	 * @author Fangwei_Cai
	 * @time since 2016年5月15日 上午8:47:46
	 * @param cmd
	 * @throws RserveException
	 */
	private void voidEval(String cmd) throws RserveException{
		System.out.println("CMD:> " + cmd);
		this.connnection.voidEval(cmd);
	}
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月14日 下午11:12:01
	 * @param queryResult
	 * @param sql
	 * @return
	 */
	private boolean dbSendQuery(String queryResult,String sql){
		String evalString = "dbSendQuery(" + this.DB_Connection + ",'" + sql+ "')";
		
		if(StringUtils.isNotEmpty(queryResult)){
			evalString = queryResult + "=" +evalString;
		}
		
		try {
			this.voidEval(evalString);
		} catch (RserveException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Destroy R.
	 * @author Fangwei_Cai
	 * @time since 2016年5月14日 下午9:47:41
	 * @return
	 */
	public boolean destroy(){
		
		try {
			if(this.dbConnected){
				//this.connnection.eval("dbClearResult(dbListResults(conn)[[1]])");
				this.connnection.eval("dbDisconnect("+this.DB_Connection+")");
			}
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean destroyResult = RConnectionFactory.closeConnection(this.connnection);
		
		return destroyResult;
	}
	
}
