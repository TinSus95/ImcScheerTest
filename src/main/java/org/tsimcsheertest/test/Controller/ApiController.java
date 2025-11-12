package org.tsimcsheertest.test.Controller;

import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tsimcsheertest.test.Models.User;
import org.tsimcsheertest.test.Models.UserLearning;
import org.tsimcsheertest.test.Repo.UserLearningRepo;
import org.tsimcsheertest.test.Repo.UserRepo;
import org.tsimcsheertest.test.Templates.CustomPage;
import org.tsimcsheertest.test.Templates.LearningComponentDetails;
import org.tsimcsheertest.test.Templates.MyLearningResponse;

import java.util.*;

import static org.tsimcsheertest.test.Services.ResponseFormattingResource.*;

@RestController
@RequestMapping(value = "/project/restapi/")
public class ApiController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserLearningRepo userLearningRepo;

    @GetMapping(value = "/")
    public String DefaultPage(){
        return "Home page";
    }

    @GetMapping(value = "lms/my-elearings")
    public ResponseEntity<CustomPage<MyLearningResponse>> getMyELearning(@RequestHeader Map<String, String> headers,
                                                     @RequestParam(required = false, name="pageNum") Optional<Integer> pageNum,
                                                     @RequestParam(required = false, name="pageSize") Optional<Integer> pageSize){

        if(!headers.containsKey("authorization")){
            return generateCustomErrorPageResponse(MyLearningResponse.class, HttpStatus.UNAUTHORIZED, "Please provide username and password in the auth.");
        }

        Optional<User> user = this.findUserByHeaderBasicAuth(headers);

        if(user.isEmpty()){
            return generateCustomErrorPageResponse(MyLearningResponse.class, HttpStatus.UNAUTHORIZED, "Invalid user data.");
        }

        CustomPage<MyLearningResponse> response = null;

        if(pageNum.isPresent() && pageSize.isPresent()){
            response = getListOfMyLearningResponses(user.get(), pageNum.get(), pageSize.get());
        }
        else{
            response = getListOfMyLearningResponses(user.get());
        }

        if(response.data.size() == 0){
            return generateCustomErrorPageResponse(MyLearningResponse.class, HttpStatus.NOT_FOUND, "Data not found");
        }

        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping(value = "lms/my-elearings/{component_id}")
    public ResponseEntity<CustomPage<LearningComponentDetails>> getELearningComponentDetails(@RequestHeader Map<String, String> headers,
                                                                                 @PathVariable String component_id){
        if(!headers.containsKey("authorization")){
            return generateCustomErrorPageResponse(LearningComponentDetails.class, HttpStatus.UNAUTHORIZED, "Please provide username and password in the auth.");
        }

        Optional<User> user = this.findUserByHeaderBasicAuth(headers);

        if(user.isEmpty()){
            return generateCustomErrorPageResponse(LearningComponentDetails.class, HttpStatus.UNAUTHORIZED, "Invalid user data.");
        }

        Optional<UserLearning> userLearning = this.getUserLearning(user.get(), component_id);
        if(userLearning.isEmpty()){
            return generateCustomErrorPageResponse(LearningComponentDetails.class, HttpStatus.NOT_FOUND, "Resource not found");
        }

        LearningComponentDetails details = getUserLearningDetails(userLearning.get());

        CustomPage<LearningComponentDetails> response = new CustomPage<LearningComponentDetails>(details);

        return ResponseEntity.ok()
                .body(response);
    }

    /**
     *
     * Receives the map of headers, and gets basic auth from headers, decodes it and splits username and pass and passes them for querying
     *
     * @param headers - Map of headers passed from route method
     * @return - Returns response of UserRepo query for selected username and password
     */
    private Optional<User> findUserByHeaderBasicAuth(@RequestHeader Map<String, String> headers){
        String response = headers.get("authorization").replaceAll("Basic ", "");
        byte[] decoded = Base64.getDecoder().decode(response);
        response = new String(decoded);
        return this.getUser(response);
    }

    /**
     *
     * Passes decoded Basic auth string to UserRepo for query and returns the result
     *
     * @param authData - Decoded Basic auth string
     * @return - Optional<User> from UserRepo
     */
    private Optional<User> getUser(String authData){
        return userRepo.findByUsernameAndPassword(authData.split(":")[0], authData.split(":")[1]);
    }

    /**
     *
     * Passes User instance and String representing eLearningComponents ID to userLearningRepo for query and returns the result
     *
     * @param user - User instance that will serve as one filter for the query (User ID will be used)
     * @param learningId - String representing eLearningComponent ID to be used as a filter for the query
     * @return - Optional<UserLearning> from UserLearningRepo
     */
    private Optional<UserLearning> getUserLearning(User user, String learningId){
        return userLearningRepo.findByUserIdAndComponentId(user.getId(), learningId);
    }

}
