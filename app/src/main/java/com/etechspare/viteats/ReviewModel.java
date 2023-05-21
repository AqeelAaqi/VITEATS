package com.etechspare.viteats;

public class ReviewModel {
    String id;
    String person_name;
    String review;
    String hotel_id;
    String feedback;

    public ReviewModel(String id, String person_name, String review, String hotel_id, String feedback) {
        this.id = id;
        this.person_name = person_name;
        this.review = review;
        this.hotel_id = hotel_id;
        this.feedback = feedback;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
