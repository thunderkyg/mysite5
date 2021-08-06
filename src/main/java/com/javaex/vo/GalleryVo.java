package com.javaex.vo;

public class GalleryVo {

	// Field
	private int no;
	private int user_no;
	private String name;
	private String content;
	private String filePath;
	private String orgName;
	private String saveName;
	private long fileSize;

	// Constructor
	
	public GalleryVo() {
		
	}
	
	public GalleryVo(int user_no, String content, String saveName) {
		this.user_no = user_no;
		this.content = content;
		this.saveName = saveName;
	}

	public GalleryVo(int no, int user_no, String name, String content, String saveName) {
		this.no = no;
		this.user_no = user_no;
		this.name = name;
		this.content = content;
		this.saveName = saveName;
	}

	public GalleryVo(int user_no, String content, String filePath, String orgName, String saveName, long fileSize) {
		this.user_no = user_no;
		this.content = content;
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
	}

	public GalleryVo(int no, int user_no, String content, String filePath, String orgName, String saveName,
			long fileSize) {
		this.no = no;
		this.user_no = user_no;
		this.content = content;
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
	}
	
	public GalleryVo(int no, int user_no, String name, String content, String filePath, String orgName, String saveName,
			long fileSize) {
		this.no = no;
		this.user_no = user_no;
		this.name = name;
		this.content = content;
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
	}

	// Method G/S
	
	
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	// Method
	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", user_no=" + user_no + ", name=" + name + ", content=" + content
				+ ", filePath=" + filePath + ", orgName=" + orgName + ", saveName=" + saveName + ", fileSize="
				+ fileSize + "]";
	}
	
	
	
}
