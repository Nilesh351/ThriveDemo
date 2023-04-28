package com.thrive.common.testdata.pojos.book_session;

public class BookSessionDetails {
	
	private String credit;
	private String date;
	private String time;
	private String clientname;
	private String expertise;
	private String sessionhelp;
	private String background;
	private String share;
	private String option;
	private String choice;
	private String followOnSession;
	
	
	public BookSessionDetails(String credit, String date, String time, String clientname, String expertise,
			String sessionhelp, String background, String share, String option, String choice, String followOnSession) {
		super();
		this.credit = credit;
		this.date = date;
		this.time = time;
		this.clientname = clientname;
		this.expertise = expertise;
		this.sessionhelp = sessionhelp;
		this.background = background;
		this.share = share;
		this.option = option;
		this.choice = choice;
		this.followOnSession = followOnSession;
	}
	
	public BookSessionDetails(String credit, String expertise, String followOnSession) {
		this.credit = credit;
		this.followOnSession = followOnSession;
		this.expertise = expertise;
		
	}
	
	
	public String getCredit() {
		return credit;
	}


	public void setCredit(String credit) {
		this.credit = credit;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getClientname() {
		return clientname;
	}


	public void setClientname(String clientname) {
		this.clientname = clientname;
	}


	public String getExpertise() {
		return expertise;
	}


	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}


	public String getSessionhelp() {
		return sessionhelp;
	}


	public void setSessionhelp(String sessionhelp) {
		this.sessionhelp = sessionhelp;
	}


	public String getBackground() {
		return background;
	}


	public void setBackground(String background) {
		this.background = background;
	}


	public String getShare() {
		return share;
	}


	public void setShare(String share) {
		this.share = share;
	}


	public String getOption() {
		return option;
	}


	public void setOption(String option) {
		this.option = option;
	}


	public String getChoice() {
		return choice;
	}


	public void setChoice(String choice) {
		this.choice = choice;
	}
	
	public String getFollowOnSession() {
		return followOnSession;
	}


	public void setFollowOnSession(String followOnSession) {
		this.followOnSession = followOnSession;
	}


}
