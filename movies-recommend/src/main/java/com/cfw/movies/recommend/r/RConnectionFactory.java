package com.cfw.movies.recommend.r;

import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

/**
 * @author Fangwei_Cai
 * @time since 2016年5月14日 下午9:32:08
 */
public class RConnectionFactory {

	private final static String RSERVE_ADDR = "192.168.56.102";
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月14日 下午9:42:44
	 * @return
	 */
	public static RConnection getConnection(){
		RConnection connection = null;
		try {
			connection = new RConnection(RSERVE_ADDR);
		} catch (RserveException e) {
			e.printStackTrace();
			return null;
		}
		
		return connection;
	}
	
	/**
	 * Every connection should use this to destroy.
	 * @author Fangwei_Cai
	 * @time since 2016年5月14日 下午9:42:49
	 * @param connection
	 * @return
	 */
	public static boolean closeConnection(RConnection connection){
		connection.close();
		connection = null ;
		return true;
	}
	
}
