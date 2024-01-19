package com.distributed.MusicAppProject.Controller;

import com.distributed.MusicAppProject.DataObject.Requests.Review_Request;
import com.distributed.MusicAppProject.DataObject.Response;
import com.distributed.MusicAppProject.Services.Interfaces.APIService;
import com.distributed.MusicAppProject.Services.Interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("app/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final APIService apiService;

    @PostMapping("/addReview")
    public Response<String>  addReview(@RequestBody Review_Request review_request) {
        String response = reviewService.saveReview(review_request.getUsername(),review_request.getTrackName(),review_request.getTrackReview());
        if (response == null) {
            return new Response<>(false, "Review Not Saved!", null);
        }
        return new Response<>(true, "Review for the Fav Track Saved", response);
    }
    // Delete track from fav list
    @DeleteMapping("/deleteReview")
    public Response<String> deleteFavorite(@RequestBody Review_Request review_request){
        String response = reviewService.deleteReview(review_request.getUsername(),review_request.getTrackName());
        if (response == null) {
            return new Response<>(false, "An unkown error occured", null);
        }
        return new Response<>(true, "success", response);
    }
}
