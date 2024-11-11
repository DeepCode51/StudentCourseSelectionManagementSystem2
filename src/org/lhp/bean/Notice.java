/**
 * 
 */
package org.lhp.bean;

/**
 * @author rcx
 * @date   2020年4月9日  下午3:08:31
 * @class  org.lhp.bean.Notice
 * 
 * 
 */
public class Notice {
	private int noticeid;
	private int tid;
	private String noticetype;
	private String noticeinfo;
	private int check;
	public int getNoticeid() {
		return noticeid;
	}
	public void setNoticeid(int noticeid) {
		this.noticeid = noticeid;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getNoticetype() {
		return noticetype;
	}
	public void setNoticetype(String noticetype) {
		this.noticetype = noticetype;
	}
	public String getNoticeinfo() {
		return noticeinfo;
	}
	public void setNoticeinfo(String noticeinfo) {
		this.noticeinfo = noticeinfo;
	}
	public int getCheck() {
		return check;
	}
	public void setCheck(int check) {
		this.check = check;
	}
	
}
