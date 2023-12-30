package com.distributed.MusicAppProject.Services.Interfaces;

public interface ReviewService {


    String saveReview(String username, String trackName, String review);
    String deleteReview(String username, String trackName);
}
