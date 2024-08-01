package uz.lee.news_app.user;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uz.lee.news_app.attachment.AttachmentRepository;
import uz.lee.news_app.attachment.Attachments;
import uz.lee.news_app.custom_responses.ApiResponse;
import uz.lee.news_app.custom_responses.exceptions.SourceIsNotExistException;
import uz.lee.news_app.occupation.OccupationsRepository;
import uz.lee.news_app.user.dto.UserRequestDto;
import uz.lee.news_app.user.dto.UserResponseDto;
import uz.lee.news_app.user.follow.Follows;
import uz.lee.news_app.user.follow.FollowsRepository;

@Service
public class UserService {

    private final UsersRepository usersRepository;
    private final AttachmentRepository attachmentRepository;
    private final OccupationsRepository occupationsRepository;
    private final FollowsRepository followsRepository;

    public UserService(UsersRepository usersRepository,
                       AttachmentRepository attachmentRepository,
                       OccupationsRepository occupationsRepository,
                       FollowsRepository followsRepository) {
        this.usersRepository = usersRepository;
        this.attachmentRepository = attachmentRepository;
        this.occupationsRepository = occupationsRepository;
        this.followsRepository = followsRepository;
    }

    @Transactional
    public ApiResponse followToUser(String username, String followingUsername) {
        Users user = usersRepository.findByUsername(username).orElseThrow();
        Users followUser = usersRepository.findByUsername(followingUsername).orElseThrow();

        Follows follows = new Follows();
        follows.setFollower(user);
        follows.setFollowing(followUser);
        followsRepository.save(follows);

        return new ApiResponse("Followed !",true);
    }

    @Transactional
    public ApiResponse unfollowUser(String username, String ownerUsername) {
        Users user = usersRepository.findByUsername(username).orElseThrow();
        Users followUser = usersRepository.findByUsername(ownerUsername).orElseThrow();

        Boolean b = followsRepository.deleteByFollowerUsernameAndFollowingUsername(username, ownerUsername);
        usersRepository.save(user);

        return b? new ApiResponse("Unfollowed !",true):new ApiResponse("Failed",false);
    }

    public long countFollowers(String username) {
        return followsRepository.countAllByFollowingUsername(username);
    }

    public long countFollowings(String username) {
        return followsRepository.countAllByFollowerUsername(username);
    }

    public ApiResponse editUser(String username, UserRequestDto dto) {
        Users users = usersRepository.findByUsername(username).orElseThrow(() -> new SourceIsNotExistException("User is not exist username= " + username));
        if (dto.getUsername()!=null){
            users.setUsername(dto.getUsername());
        }
        if (dto.getEmail()!=null){
            users.setEmail(dto.getEmail());
        }
        if (dto.getFullName()!=null){
            users.setFullName(dto.getFullName());
        }
        if (dto.getPassword()!=null){
            users.setPassword(dto.getPassword());
        }
        if (dto.getAttachmentUrl()!=null){
            users.setAttachments(attachmentRepository.findByAttachmentUrl(dto.getAttachmentUrl()).orElseGet(()->Attachments.builder().fileName(dto.getAttachmentUrl()).build()));
        }
        if (dto.getOccupationId()!=null){
            users.setOccupations(occupationsRepository.findById(dto.getOccupationId()).orElseThrow(()->new SourceIsNotExistException("Occupation is not exist by id= " + dto.getOccupationId())));
        }
        usersRepository.save(users);

        return new ApiResponse("Your data updated successfully",true);
    }

    public UserResponseDto getDataByOwner(String username) {
        return getUserResponseDto(username);
    }

    public UserResponseDto getDataByOther(String username) {
        return getUserResponseDto(username);
    }

    private UserResponseDto getUserResponseDto(String username) {
        Users users = usersRepository.findByUsername(username).orElseThrow(() -> new SourceIsNotExistException("User is not exist with this username!"));

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setFullName(users.getFullName());
        userResponseDto.setUsername(users.getUsername());
        if (users.getAttachments()!=null) {
            Attachments attachments = users.getAttachments();
            userResponseDto.setAttachments(attachments);
        }
        userResponseDto.setOccupation(occupationsRepository.findById(users.getOccupations().getId()).orElseThrow().getName());
        return userResponseDto;
    }

    public ApiResponse deleteByUsernameAndPassword(String username, String password) {
        Boolean b = usersRepository.deleteByUsernameAndPassword(username, password);
        if (b){
            return new ApiResponse("Your account has been deleted !!!",true);
        }
        return new ApiResponse("Your account has not been deleted !!!",false);
    }
}
