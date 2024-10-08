package com.nrv.auth_service.service.implementation;

import com.nrv.auth_service.exception.NotAuthorizedException;
import com.nrv.auth_service.exception.ResourceNotFoundException;
import com.nrv.auth_service.log.UserLogMessage;
import com.nrv.auth_service.model.User;
import com.nrv.auth_service.model.enums.Role;
import com.nrv.auth_service.repository.UserRepository;
import com.nrv.auth_service.request.UserInsertionRequest;
import com.nrv.auth_service.response.APIResponse;
import com.nrv.auth_service.response.UserResponse;
import com.nrv.auth_service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.nrv.auth_service.service.implementation.UserServiceHelper.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository repository;

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> userList = repository.findAll();
        List<UserResponse> responseList = userListMapper(userList);
        logger.info(UserLogMessage.User_LIST_GET.getMessage());
        return responseList;
    }

    @Override
    public UserResponse addAnUser(UserInsertionRequest newUser) {
        User user = getUser(newUser);
        UserResponse response = getUserResponse(user);
        logger.info(UserLogMessage.USER_ADD.getMessage(), user.getUserId());
        return response;
    }

    @Override
    public UserResponse updateAnUser(String userId, UserInsertionRequest updateUser) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found by id: " + userId));
        if (!user.getUserId().equals(updateUser.getUserId())) {
            user.setUserId(updateUser.getUserId());
        }

        if (!user.getPassword().equals(updateUser.getPassword())) {
            user.setPassword(updateUser.getPassword());
        }

        repository.save(user);
        logger.info(UserLogMessage.USER_UPDATE.getMessage(), user.getUserId());
        return getUserResponse(user);
    }

    @Override
    public APIResponse deleteAnUser(String userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found by id: " + userId));

        if (user.getRole().equals(Role.ROLE_ADMIN)) {
            throw new NotAuthorizedException("You Are not authorized for this operation.");
        }

        repository.delete(user);
        logger.info(UserLogMessage.USER_DELETE.getMessage(), user.getUserId());
        return new APIResponse("User Delete with id: " + user.getUserId());
    }

}
