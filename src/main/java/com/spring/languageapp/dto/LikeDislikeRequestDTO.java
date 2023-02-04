package com.spring.languageapp.dto;

public class LikeDislikeRequestDTO {

    private Integer numberOfLikes;
    private Integer numberOfDislikes;

    public LikeDislikeRequestDTO(){
    }

    public LikeDislikeRequestDTO(Integer numberOfLikes, Integer numberOfDislikes) {
        this.numberOfLikes = numberOfLikes;
        this.numberOfDislikes = numberOfDislikes;
    }

    public Integer getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(Integer numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public Integer getNumberOfDislikes() {
        return numberOfDislikes;
    }

    public void setNumberOfDislikes(Integer numberOfDislikes) {
        this.numberOfDislikes = numberOfDislikes;
    }
}
