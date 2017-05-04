package com.cfw.movies.commons.dto;

/**
 * Class UploadResult, used in file upload method, as returned data.
 * @author CaiFangwei
 * @time since 2015年11月17日 下午5:22:04
 */
public class UploadResult {
	private short status;
	private String message;
	private String tempFileName; // Temparory file path 
	private String link;
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public short getStatus() {
		return status;
	}
	public void setStatus(short status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTempFileName() {
		return tempFileName;
	}
	public void setTempFileName(String tempFileName) {
		this.tempFileName = tempFileName;
	}
	
}
