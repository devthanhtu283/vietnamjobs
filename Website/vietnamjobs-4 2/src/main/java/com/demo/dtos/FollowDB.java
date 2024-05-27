package com.demo.dtos;

public class FollowDB {
    private int seekerID;
    private int employerID;
    private String employerName;
    private int postingID;
    private String postingTitle;

    // Constructors
    public FollowDB(Integer seekerID, Integer employerID, String employerName, Integer postingID, String postingTitle) {
        this.seekerID = seekerID;
        this.employerID = employerID;
        this.employerName = employerName;
        this.postingID = postingID;
        this.postingTitle = postingTitle;
    }

    // Getters and setters
    public Integer getSeekerID() {
        return seekerID;
    }

    public void setSeekerID(Integer seekerID) {
        this.seekerID = seekerID;
    }

    public Integer getEmployerID() {
        return employerID;
    }

    public void setEmployerID(Integer employerID) {
        this.employerID = employerID;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public Integer getPostingID() {
        return postingID;
    }

    public void setPostingID(Integer postingID) {
        this.postingID = postingID;
    }

    public String getPostingTitle() {
        return postingTitle;
    }

    public void setPostingTitle(String postingTitle) {
        this.postingTitle = postingTitle;
    }

	@Override
	public String toString() {
		return "FollowDB [seekerID=" + seekerID + ", employerID=" + employerID + ", employerName=" + employerName
				+ ", postingID=" + postingID + ", postingTitle=" + postingTitle + "]";
	}
    
}
