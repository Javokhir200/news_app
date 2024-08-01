package uz.lee.news_app.tag;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TagService {
    private final TagsRepository tagsRepository;
    public TagService(TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }
    public List<Tags> getAll() {
        return tagsRepository.findAll();
    }
}
