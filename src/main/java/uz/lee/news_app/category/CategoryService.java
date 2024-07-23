package uz.lee.news_app.category;

import org.springframework.stereotype.Service;
import uz.lee.news_app.attachment.AttachmentRepository;
import uz.lee.news_app.attachment.AttachmentService;
import uz.lee.news_app.exceptions.SourceAlreadyExistException;
import uz.lee.news_app.exceptions.SourceIsNotExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final AttachmentService attachmentService;

    public CategoryService(CategoryRepository categoryRepository, AttachmentService attachmentService) {
        this.categoryRepository = categoryRepository;
        this.attachmentService = attachmentService;
    }

    public String addParentCategory(CategoryDto dto) {
        Boolean b = categoryRepository.existsByNameIgnoreCase(dto.getName());
        if (b){
            throw new SourceAlreadyExistException("Category exists with name = " + dto.getName());
        }


        Category category = new Category();
        category.setName(dto.getName());
        String attachmentUrl = dto.getAttachmentUrl();
        category.setAttachment(attachmentService.getAttachmentByUrl(attachmentUrl));
        category.setParent(true);
        categoryRepository.save(category);

        return "Parent category saved with " + dto.getName() + " name";
    }

    public List<Category> getParentCategoriesOnly() {
        return categoryRepository.getCategoriesByParentIsTrue();
    }

    public String addChildCategory(CategoryDto dto, Integer parentId) {
        Optional<Category> byId = categoryRepository.findById(parentId);
        if (byId.isPresent()){

            String attachmentUrl = dto.getAttachmentUrl();
            Category child = categoryRepository.save(
                    Category.builder().name(dto.getName()).attachment(attachmentService.getAttachmentByUrl(attachmentUrl)).build());
            Category category = byId.get();
            List<Category> children = category.getChildren();
            children.add(child);
            category.setChildren(children);
            categoryRepository.save(category);

            return "Child category saved with " + child.getName() + " name";
        }
        throw new SourceIsNotExistException("Parent category is not exist id = " + parentId);
    }

    public List<Category> getCategoriesByParentId(Integer parentId) {
        return categoryRepository.getCategoryById(parentId).orElseThrow(()-> new SourceIsNotExistException("Parent category is not exist id= "+ parentId)).getChildren();
    }
}
