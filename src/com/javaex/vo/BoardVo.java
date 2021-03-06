package com.javaex.vo;

public class BoardVo {
	
	//Field
	private int no;
	private int board_no;
	private String title;
	private String  content;
	private int hit;
	private String reg_date;
	private int user_no;
	private String name;
	
	//Constructor

	public BoardVo() {

	}
	
	public BoardVo(int no, int board_no) {
		this.no = no;
		this.board_no = board_no;
	}
	
	public BoardVo(String title, String content, int user_no) {
		this.title = title;
		this.content = content;
		this.user_no = user_no;
	}

	public BoardVo(int board_no, String title, String content) {
		this.board_no = board_no;
		this.title = title;
		this.content = content;
	}
	
	public BoardVo(int board_no, String title, String content, int hit, String reg_date, int user_no) {
		this.board_no = board_no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.reg_date = reg_date;
		this.user_no = user_no;
	}
	
	public BoardVo(int no, int board_no, String title, String content, int hit, String reg_date, int user_no,
			String name) {
		this.no = no;
		this.board_no = board_no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.reg_date = reg_date;
		this.user_no = user_no;
		this.name = name;
	}

	public BoardVo(int board_no, String title, String content, int hit, String reg_date, int user_no, String name) {
		this.board_no = board_no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.reg_date = reg_date;
		this.user_no = user_no;
		this.name = name;
	}

	//Method - G/S
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getHit() {
		return hit;
	}
	
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	public String getReg_date() {
		return reg_date;
	}
	
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	public int getUser_no() {
		return user_no;
	}
	
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public int getBoard_no() {
		return board_no;
	}
	
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	//Method - Ordinary
	
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", board_no=" + board_no + ", title=" + title + ", content=" + content + ", hit="
				+ hit + ", reg_date=" + reg_date + ", user_no=" + user_no + ", name=" + name + "]";
	}
	

}
