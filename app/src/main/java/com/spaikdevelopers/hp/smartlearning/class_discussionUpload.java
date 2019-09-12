package com.spaikdevelopers.hp.smartlearning;

public class class_discussionUpload {
    String discussionID;
    String discussionTitle;
    String discussionView;

    public class_discussionUpload(){

    }

    public class_discussionUpload(String discussionID, String discussionTitle, String discussionView) {
        this.discussionID = discussionID;
        this.discussionTitle = discussionTitle;
        this.discussionView = discussionView;
    }

    public String getDiscussionID() {
        return discussionID;
    }

    public String getDiscussionTitle() {
        return discussionTitle;
    }

    public String getDiscussionView() {
        return discussionView;
    }
}
