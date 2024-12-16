package be.ecotravel.back.service;

import be.ecotravel.back.entity.User;
import be.ecotravel.back.repository.UserRepository;
import be.ecotravel.back.user.dto.UserDto;
import be.ecotravel.back.user.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    public CloudinaryService cloudinaryService;

    public UserResponse getUserById(String id) throws Exception {
        User user = findUserById(id);
        return new UserResponse(user.getFirstname(), user.getLastName(), user.getUsername(), user.getProfilePicturePath());
    }

    private User findUserById(String id) throws Exception {
        UUID uuid = UUID.fromString(id);
        Optional<User> user = userRepository.findUserById(uuid);

        if(user.isPresent()){
            return user.get();
        }else{
            throw new Exception("User not found");
        }
    }

    public UserResponse putUserById(String id, UserDto registerUserDto) throws Exception {
        User user = findUserById(id);
        if(registerUserDto.email() != null && !registerUserDto.email().equals(user.getEmail())){
            user.setEmail(registerUserDto.email());
        }
        if(registerUserDto.firstname() != null && !registerUserDto.firstname().equals(user.getFirstname())){
            user.setFirstname(registerUserDto.firstname());
        }
        if(registerUserDto.lastname() != null && !registerUserDto.lastname().equals(user.getLastName())){
            user.setLastName(registerUserDto.lastname());
        }
        userRepository.save(user);
        return getUserById(id);
    }

    public UserResponse addProfilePicture(String id, MultipartFile file) throws Exception {
        String imageUrl = cloudinaryService.uploadImageToFolder(file, "userPicture", id);
        User user = findUserById(id);
        if(!user.getProfilePicturePath().equals(imageUrl)){
            user.setProfilePicturePath(imageUrl);
        }
        userRepository.save(user);
        return getUserById(id);
    }
}
