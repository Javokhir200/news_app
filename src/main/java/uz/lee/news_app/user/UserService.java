package uz.lee.news_app.user;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uz.lee.news_app.attachment.AttachmentRepository;
import uz.lee.news_app.attachment.Attachments;
import uz.lee.news_app.dto.ApiResponse;
import uz.lee.news_app.exceptions.SourceIsNotExistException;
import uz.lee.news_app.occupation.OccupationsRepository;

@Service
public class UserService {

    private final UsersRepository usersRepository;
    private final AttachmentRepository attachmentRepository;
    private final OccupationsRepository occupationsRepository;

    public UserService(UsersRepository usersRepository,
                       AttachmentRepository attachmentRepository,
                       OccupationsRepository occupationsRepository) {
        this.usersRepository = usersRepository;
        this.attachmentRepository = attachmentRepository;
        this.occupationsRepository = occupationsRepository;
    }

    @Transactional
    public ApiResponse followUser(Long followerId, Long ownerId) {
        Users user = usersRepository.findById(followerId).orElseThrow();
        Users followUser = usersRepository.findById(ownerId).orElseThrow();

        user.getFollowings().add(followUser);
        usersRepository.save(user);

        return new ApiResponse("Followed !",true);
    }

    @Transactional
    public ApiResponse unfollowUser(Long followerId, Long ownerId) {
        Users user = usersRepository.findById(followerId).orElseThrow();
        Users followUser = usersRepository.findById(ownerId).orElseThrow();

        user.getFollowings().remove(followUser);
        usersRepository.save(user);

        return new ApiResponse("Unfollowed !",true);
    }

    public long countFollowers(Long id) {
        return usersRepository.countFollowersById(id);
    }

    public long countFollowings(Long id) {
        return usersRepository.countFollowingsByById(id);
    }

    public ApiResponse editUser(String username, UserDto dto) {
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
}
