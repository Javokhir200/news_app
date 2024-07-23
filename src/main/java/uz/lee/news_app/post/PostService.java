package uz.lee.news_app.post;

import org.springframework.stereotype.Service;
import uz.lee.news_app.attachment.AttachmentRepository;
import uz.lee.news_app.attachment.Attachments;
import uz.lee.news_app.dto.ApiResponse;
import uz.lee.news_app.exceptions.SourceIsNotExistException;
import uz.lee.news_app.tag.Tags;
import uz.lee.news_app.tag.TagsRepository;
import uz.lee.news_app.user.Users;
import uz.lee.news_app.user.UsersRepository;

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
            attachment.setAttachmentUrl(a.getFileUrl());
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

    public Posts getById(Long id) {
        return postsRepository.findById(id).orElseThrow(() -> new SourceIsNotExistException("Post is not exist id=" + id));
    }

    public List<PostProjection> getByTagName(String tag) {
        return postsRepository.getAllByTagName(tag);
    }

    public List<PostProjection> getByWriterId(Long writeId) {
        return postsRepository.getAllByWriteId(writeId);
    }
}
