package com.nrv.auth_service.service.implementation;

import com.nrv.auth_service.config.CustomUserDetails;
import com.nrv.auth_service.exception.InvalidCredentialsException;
import com.nrv.auth_service.exception.NotAuthorizedException;
import com.nrv.auth_service.exception.ResourceNotFoundException;
import com.nrv.auth_service.log.UserLogMessage;
import com.nrv.auth_service.model.User;
import com.nrv.auth_service.model.enums.Role;
import com.nrv.auth_service.repository.UserRepository;
import com.nrv.auth_service.request.UserInsertionRequest;
import com.nrv.auth_service.request.UserLoginRequest;
import com.nrv.auth_service.response.APIResponse;
import com.nrv.auth_service.response.UserResponse;
import com.nrv.auth_service.service.JwtService;
import com.nrv.auth_service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.nrv.auth_service.service.implementation.UserServiceHelper.*;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> userList = repository.findAll();
        List<UserResponse> responseList = userListMapper(userList);
        log.info(UserLogMessage.User_LIST_GET.getMessage());
        return responseList;
    }

    @Override
    public UserResponse addAnUser(UserInsertionRequest newUser) {
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        User user = getUser(newUser);
        User saved = repository.save(user);
        UserResponse response = getUserResponse(saved);
        log.info(UserLogMessage.USER_ADD.getMessage(), user.getUserId());
        return response;
    }

    @Override
    public APIResponse login(UserLoginRequest loginUser) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUserId(), loginUser.getPassword()));
        if (authenticate.isAuthenticated()) {
            // Get the authenticated user's details
            UserDetails userDetails = (UserDetails) authenticate.getPrincipal();

            String username = userDetails.getUsername();
            String role = ""; // You'll need to retrieve the role based on your UserDetails implementation

            // Assuming UserDetails contains the user's role
            if (userDetails instanceof CustomUserDetails) { // MyUserDetails should implement UserDetails
                role = ((CustomUserDetails) userDetails).getRole();
            }
            System.out.println(username + "----" + role);
            log.info(UserLogMessage.USER_LOGIN.getMessage());
            return new APIResponse(jwtService.generateToken(username, role));
        } else {
            throw new NotAuthorizedException("invalid access");
        }
    }

    @Override
    public UserResponse updateAnUser(String userId, UserInsertionRequest updateUser) {
        User user = repository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found by id: " + userId));
        checkCredentials(updateUser, user);

        repository.save(user);
        log.info(UserLogMessage.USER_UPDATE.getMessage(), user.getUserId());
        return getUserResponse(user);
    }

    private void checkCredentials(UserInsertionRequest updateUser, User user) {

        if (updateUser.getUserId().isEmpty() || updateUser.getPassword().isEmpty()) {
            throw new InvalidCredentialsException("User Id or Password cannot be empty");
        }

        if (!user.getUserId().equals(updateUser.getUserId())) {
            user.setUserId(updateUser.getUserId());
        }
        updateUser.setPassword(encoder.encode(updateUser.getPassword()));
        if (!encoder.matches(user.getPassword(), updateUser.getPassword())) {
            user.setPassword(updateUser.getPassword());
        } else {
            throw new InvalidCredentialsException("You can not use your previous password");
        }

    }

    @Override
    public APIResponse deleteAnUser(String userId) {
        User user = repository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found by id: " + userId));

        if (user.getRole().equals(Role.ROLE_ADMIN)) {
            throw new NotAuthorizedException("You Are not authorized for this operation.");
        }

        repository.delete(user);
        log.info(UserLogMessage.USER_DELETE.getMessage(), user.getUserId());
        return new APIResponse("User Delete with id: " + user.getUserId());
    }

}
