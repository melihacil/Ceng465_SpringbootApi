package com.distributed.MusicAppProject.Services;

import com.distributed.MusicAppProject.MusicUsers.AppUsers.AppUser;
import com.distributed.MusicAppProject.MusicUsers.MusicObjects.FavoriteRepo;
import com.distributed.MusicAppProject.MusicUsers.MusicObjects.Review;
import com.distributed.MusicAppProject.MusicUsers.MusicObjects.ReviewRepo;
import com.distributed.MusicAppProject.MusicUsers.UserRepo;
import com.distributed.MusicAppProject.Services.Interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {




    private final UserRepo userRepository;
    private final FavoriteRepo favoriteRepository;
    private final ReviewRepo reviewRepository;


    @Override
    public String saveReview(String username, String trackName, String review) {
        Optional<AppUser> user = userRepository.findByUsername(username);
        if (user.isPresent() && trackName != null) {
            if (favoriteRepository.existsByUsernameAndTrackName(username, trackName) && !reviewRepository.existsByUsernameAndTrackName(username, trackName)) {
                Review trackReview = Review
                        .builder()
                        .trackName(trackName)
                        .username(username)
                        .trackReview(review)
                        .build();
                reviewRepository.save(trackReview);
                return "Favorite track review saved";
            } else {
                return "Favorite Track not found or there is already a review";
            }
        }
        return "User or Track can not found";
    }
    @Override
    public String deleteReview(String username, String trackName) {
        Optional<AppUser> user = userRepository.findByUsername(username);
        if (user.isPresent() && trackName != null) {
            if (reviewRepository.existsByUsernameAndTrackName(username, trackName)) {
                reviewRepository.deleteByUsernameAndTrackName(username,trackName);
                return "Fav track deleted";
            } else {
                return "No review for this fav track found";
            }
        }
        return "User or Track can not found";
    }


}
