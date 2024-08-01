package uz.lee.news_app.comment;

import org.springframework.stereotype.Service;
import uz.lee.news_app.custom_responses.ApiResponse;
import uz.lee.news_app.post.PostsRepository;
import uz.lee.news_app.user.UsersRepository;

import java.util.List;

@Service
public class CommentService {

    private final CommentsRepository commentsRepository;
    private final PostsRepository postsRepository;
    private final UsersRepository usersRepository;

    public CommentService(CommentsRepository commentsRepository,
                          PostsRepository postsRepository,
                          UsersRepository usersRepository) {
        this.commentsRepository = commentsRepository;
        this.postsRepository = postsRepository;
        this.usersRepository = usersRepository;
    }

    public ApiResponse saveComment(CommentDto dto,Long postId,Long toUserId,String fromUsername) {
        Comments comment = new Comments();
        comment.setPost(postsRepository.getReferenceById(postId));
        comment.setRate(dto.getRate());
        comment.setText(dto.getText());
        comment.setFromUser(usersRepository.findByUsername(fromUsername).orElseThrow());
        comment.setToUser(usersRepository.findById(toUserId).orElseThrow());
        commentsRepository.save(comment);
        return new ApiResponse("Comment saved",true);
    }

    public List<CommentsInfo> getCommentsByPostId(Long postId) {
        List<CommentsInfo> comments = commentsRepository.getCommentsByPostId(postId);
        comments.forEach(c->{
            System.out.println(c.toString());
        });
        return comments;
    }
}
