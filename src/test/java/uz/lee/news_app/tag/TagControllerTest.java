package uz.lee.news_app.tag;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = {"/tags-schema.sql", "/import-tags.sql"})

class TagControllerTest {

    @MockBean
    private final TagsRepository tagsRepository;

    TagControllerTest(TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }


    @Test
    void getAll() {
        System.out.println(tagsRepository.findAll());
    }
}