package com.contact_manager.services.servicesImplementation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.contact_manager.Exception.ResourceNotFoundException;
import com.contact_manager.entities.User;
import com.contact_manager.helper.WebAppConstants;
import com.contact_manager.repositories.UserRepository;
import com.contact_manager.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(List.of(WebAppConstants.ROLE_USER));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User dbUser = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        dbUser.setName(user.getName());
        dbUser.setEmail(user.getEmail());
        dbUser.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(List.of(WebAppConstants.ROLE_USER));
        dbUser.setAbout(user.getAbout());
        dbUser.setPhoneNumber(user.getPhoneNumber());
        dbUser.setProfilePic(user.getProfilePic());
        dbUser.setEnabled(user.isEnabled());
        dbUser.setEmailVerified(user.isEmailVerified());
        dbUser.setPhoneVerified(user.isPhoneVerified());
        dbUser.setProvider(user.getProvider());
        dbUser.setProviderUserId(user.getProviderUserId());
        return Optional.ofNullable(userRepository.save(dbUser));
    }

    @Override
    public void deleteUser(String id) {
        User dbUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        userRepository.delete(dbUser);
    }

    @Override
    public boolean isUserExist(String userId) {
        User dbUser = userRepository.findById(userId).orElse(null);
        return dbUser != null;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User dbUser = userRepository.findByEmail(email).orElse(null);
        return dbUser != null;
    }

    @Override
    public List<User> getAllUsers() {
        throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
