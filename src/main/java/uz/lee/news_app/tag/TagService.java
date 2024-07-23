package uz.lee.news_app.tag;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    private final TagsRepository tagsRepository;

    public TagService(TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }

    public List<Tags> getAll() {
        return tagsRepository.findAll();
    }

    public void saveTags(List<TagDto> dtos){
        List<Tags> tags = new ArrayList<>();
        dtos.forEach(d->{
            tags.add(Tags.builder().name(d.getName()).build());
        });

        tagsRepository.saveAll(tags);
    }
}
