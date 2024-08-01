package uz.lee.news_app.post;

import org.springframework.stereotype.Service;
import uz.lee.news_app.attachment.AttachmentRepository;
import uz.lee.news_app.attachment.Attachments;
import uz.lee.news_app.attachment.dto.AttachmentDto;
import uz.lee.news_app.custom_responses.ApiResponse;
import uz.lee.news_app.custom_responses.exceptions.ForbiddenException;
import uz.lee.news_app.custom_responses.exceptions.SourceIsNotExistException;
import uz.lee.news_app.tag.Tags;
import uz.lee.news_app.tag.TagsRepository;
import uz.lee.news_app.user.Users;
import uz.lee.news_app.user.UsersRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PostService {

    private final PostsRepository postsRepository;
    private final UsersRepository usersRepository;
    private final AttachmentRepository attachmentRepository;
    private final TagsRepository tagsRepository;

    public PostService(PostsRepository postsRepository,
                       UsersRepository usersRepository,
                       AttachmentRepository attachmentRepository,
                       TagsRepository tagsRepository) {
        this.postsRepository = postsRepository;
        this.usersRepository = usersRepository;
        this.attachmentRepository = attachmentRepository;
        this.tagsRepository = tagsRepository;
    }

    public ApiResponse createPost(PostDto dto) {
        Users user = usersRepository.findById(dto.getWriterId()).orElseThrow(() -> new SourceIsNotExistException("User is not exist id=" + dto.getWriterId()));

        List<Attachments> attachments = new ArrayList<>();
        dto.getAttachments().forEach(a->{
            Attachments attachment = new Attachments();
            attachment.setAttachmentUrl(a.getAttachmentUrl());
            attachment.setFileName(a.getFileName());
            attachmentRepository.save(attachment);
            attachments.add(attachment);
        });

        Set<Tags> tags = new HashSet<>();
        dto.getTags().forEach(t->{
            Tags tag = new Tags();
            tag.setName(t.getName());
            tagsRepository.save(tag);
            tags.add(tag);
        });

        Posts post = new Posts();
        post.setContent(dto.getContent());
        post.setTags(tags);
        post.setAttachments(attachments);
        post.setTitle(dto.getTitle());
        post.setWriter(user);

        postsRepository.save(post);

        return new ApiResponse("Post saved!",true);
    }

    public ShortPostsInfo getById(Long id) {
        return postsRepository.findPostsById(id).orElseThrow(() -> new SourceIsNotExistException("Post is not exist id=" + id));
    }

    public List<ShortPostsInfo> getByTagName(String tagName) {
        return postsRepository.findAllByTagsNameContains(tagName);
    }

    public List<ShortPostsInfo> getByWriterUsername(String username) {
        return postsRepository.findAllByWriterUsername(username);
    }

    public Object editByPostId(Long postId, PostEditDto postDto, Principal principal) {

        if (!principal.getName().equals(postsRepository.getWriterUsernameByPostId(postId))){
            throw new ForbiddenException("asdasdasd");
        }

        Posts post = postsRepository.findById(postId).orElseThrow(() -> new SourceIsNotExistException("Post is not exist id=" + postId));
        Set<Long> tagIds = postDto.getTagIds();
        Set<Tags> tags = new HashSet<>();

        if (tagIds !=null){
            tagIds.forEach(id->{
                Tags tag = tagsRepository.findById(id).orElse(null);
                tags.add(tag);
            });
            post.setTags(tags);
        }

        List<AttachmentDto> attachmentDtos = postDto.getAttachments();
        List<Attachments> attachments = new ArrayList<>();
        if (!attachmentDtos.isEmpty()){
            postDto.getAttachments().forEach(a->{
                Attachments attachment = new Attachments();
                attachment.setAttachmentUrl(a.getAttachmentUrl());
                attachment.setFileName(a.getFileName());
                attachmentRepository.save(attachment);
                attachments.add(attachment);
            });
            post.setAttachments( attachments);
        }

        if (post.getContent()!=null){
            post.setContent(postDto.getContent());
        }

        if (post.getTitle()!=null){
            post.setTitle(postDto.getTitle());
        }

        postsRepository.save(post);

        return new ApiResponse("Post edited successfully!",true);
    }

    public ApiResponse deletePostById(Long postId) {
     postsRepository.deleteById(postId);
     return new ApiResponse("Post deleted successfully id="+postId,true);
    }
}
