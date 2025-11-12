package org.tsimcsheertest.test.Services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.tsimcsheertest.test.Models.User;
import org.tsimcsheertest.test.Models.UserLearning;
import org.tsimcsheertest.test.Templates.AssignedDates;
import org.tsimcsheertest.test.Templates.CustomPage;
import org.tsimcsheertest.test.Templates.LearningComponentDetails;
import org.tsimcsheertest.test.Templates.MyLearningResponse;

import java.util.*;

@Service
public class ResponseFormattingResource {

    /**
     *
     * Returns Custom Page response with the list of MyLearningResponse resources that the requested User is subscribed to
     *
     * @param user - User for which the subscribed list of eLearningComponents will be filtered
     * @return - CustomPage<MyLearningResponse> List of MyLearningResponse objects under "data"
     */
    public static CustomPage<MyLearningResponse> getListOfMyLearningResponses(User user){
        Iterator<UserLearning> iterator = user.getMyLearning().stream().iterator();

        List<MyLearningResponse> data = new ArrayList<MyLearningResponse>();

        while(iterator.hasNext()){
            UserLearning myComponent = iterator.next();
            AssignedDates userLearningDates = new AssignedDates(myComponent.getStartDate().orElse(null), myComponent.getEndDate().orElse(null));
            MyLearningResponse responseObject = new MyLearningResponse(myComponent, userLearningDates);
            data.add(responseObject);
        }

        CustomPage<MyLearningResponse> response = new CustomPage<MyLearningResponse>(data);

        return response;
    }

    /**
     *
     * Returns Custom Page response with the list of MyLearningResponse resources that the requested User is subscribed to
     * Also accepts pagination parameters and outputs them in response accordingly
     *
     * @param user - User for which the subscribed list of eLearningComponents will be filtered
     * @param pageNum - Defines the page number
     * @param pageSize - Defines the size of the page, and by proxy, maximum number elements in the list under "data"
     * @return - CustomPage<MyLearningResponse> List of MyLearningResponse objects under "data" and pagination data unser "pagination"
     */
    public static CustomPage<MyLearningResponse> getListOfMyLearningResponses(User user,
                                                                        Integer pageNum,
                                                                        Integer pageSize){
        if(pageNum <= 0){
            pageNum = 1;
        }
        if(pageSize <= 0){
            pageSize = 10;
        }

        Integer total = user.getMyLearning().size();

        Integer offset = 0;
        if(pageNum != 1){
            if(pageSize != 1){
                offset = (pageNum-1)*pageSize;
            }
            else{
                offset = (pageNum*pageSize)-1;
            }
        }

        List<MyLearningResponse> data = new ArrayList<MyLearningResponse>();

        if(offset >= user.getMyLearning().size()){
            return new CustomPage<MyLearningResponse>(data, pageNum, pageSize, total);
        }

        Integer endIndex = offset + pageSize -1;
        if(endIndex > user.getMyLearning().size()-1){
            endIndex = user.getMyLearning().size()-1;
        }

        for(int i = offset; i<= endIndex; i++){
            AssignedDates userLearningDates = new AssignedDates(user.getMyLearning().get(i).getStartDate().orElse(null), user.getMyLearning().get(i).getEndDate().orElse(null));
            MyLearningResponse responseObject = new MyLearningResponse(user.getMyLearning().get(i), userLearningDates);
            data.add(responseObject);
        }

        CustomPage<MyLearningResponse> response = new CustomPage<MyLearningResponse>(data, pageNum, pageSize, total);

        return response;
    }

    /**
     *
     * Returns formatted object used for CustomPage ResponseEntity
     * Returns details of a single eLearningComponent resource subscribed to the specified user (from UserLearning)
     *
     * @param userLearning - Defines user specific properties for specific eLearning component and user
     * @return - LearningComponentDetails - Format for CustomPage for details regarding User subscribed eLearningComponent filtered by user and component ID
     */
    public static LearningComponentDetails getUserLearningDetails(UserLearning userLearning){
        AssignedDates availableDates = new AssignedDates(userLearning.getStartDate().orElse(null), userLearning.getEndDate().orElse(null));

        LearningComponentDetails response = new LearningComponentDetails(userLearning, availableDates);
        return response;
    }

    /**
     *
     * Returns ResponseEntity with the selected HTTPStatus and error message in body
     * Accepts generic class element to match the return condition of called method
     *
     * @param tClass - Generic class to help match with the caller method
     * @param status - HTTPStatus to assign to ResponseEntity
     * @param errorMessage - Message inside the body of ResponseEntity
     * @return - ResponseEntity of CustomPage of generic class
     * @param <T> - Generic class identifier
     */
    public static <T> ResponseEntity<CustomPage<T>> generateCustomErrorPageResponse(Class<T> tClass, HttpStatus status, String errorMessage){
        CustomPage<T> response = new CustomPage<T>(errorMessage);
        return new ResponseEntity<>(response, status);
    }
}
