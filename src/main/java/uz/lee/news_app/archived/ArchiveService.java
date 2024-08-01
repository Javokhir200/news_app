package uz.lee.news_app.archived;

import org.springframework.stereotype.Service;
import uz.lee.news_app.custom_responses.ApiResponse;
import uz.lee.news_app.custom_responses.exceptions.ForbiddenException;
import uz.lee.news_app.custom_responses.exceptions.SourceIsNotExistException;
import uz.lee.news_app.post.PostsRepository;
import uz.lee.news_app.user.UsersRepository;

import java.util.List;

@Service
public class ArchiveService {

    private final ArchivedRepository archivedRepository;
    private final UsersRepository usersRepository;
    private final PostsRepository postsRepository;

    public ArchiveService(ArchivedRepository archivedRepository,
                          UsersRepository usersRepository,
                          PostsRepository postsRepository) {
        this.archivedRepository = archivedRepository;
        this.usersRepository = usersRepository;
        this.postsRepository = postsRepository;
    }

    public ApiResponse archive(ArchivedDto dto) {
        Archived archived = new Archived();
        archived.setUser(usersRepository.findById(dto.getUserId()).orElseThrow(()->new SourceIsNotExistException("User is not exists by id="+dto.getUserId())));
        archived.setPost(postsRepository.findById(dto.getPostId()).orElseThrow(()->new SourceIsNotExistException("Post is not exists id="+dto.getPostId())));
        archivedRepository.save(archived);

        return new ApiResponse("Post saved successfully!",true);
    }

    public List<ArchivedInfo> getUserArchives(String username) {
        return archivedRepository.findAllArchivesByUserUsername(username);
    }

    public ApiResponse remove(Long archivedId) {
        archivedRepository.deleteById(archivedId);
        return new ApiResponse("Post removed!",true);
    }

    public void checkPermission(String username, String ownerUsername){
        if (!ownerUsername.equals(username)){
            throw new ForbiddenException("You don't have access to this source !!!");
        }
    }
}
