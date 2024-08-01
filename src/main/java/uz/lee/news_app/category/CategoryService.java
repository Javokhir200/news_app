package uz.lee.news_app.category;

import org.springframework.stereotype.Service;
import uz.lee.news_app.attachment.AttachmentService;
import uz.lee.news_app.custom_responses.ApiResponse;
import uz.lee.news_app.custom_responses.exceptions.ForbiddenException;
import uz.lee.news_app.custom_responses.exceptions.SourceAlreadyExistException;
import uz.lee.news_app.custom_responses.exceptions.SourceIsNotExistException;

import java.util.*;

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
            Set<Category> children = category.getChildren();
            children.add(child);
            category.setChildren(children);
            categoryRepository.save(category);

            return "Child category saved with " + child.getName() + " name";
        }
        throw new SourceIsNotExistException("Parent category is not exist id = " + parentId);
    }

    public Set<Category> getCategoriesByParentId(Integer parentId) {
        return categoryRepository.getCategoryById(parentId).orElseThrow(()-> new SourceIsNotExistException("Parent category is not exist id= "+ parentId)).getChildren();
    }

    public ApiResponse deleteChildById(Integer childId) {
        if (categoryRepository.isParent(childId)||!categoryRepository.existsById(childId)){
            throw new ForbiddenException("Child category is not exist id="+childId);
        }
        categoryRepository.deleteById(childId);
        return new ApiResponse("Child category deleted!",true);
    }

    public ApiResponse deleteParentById(Integer parentId) {
        if (!categoryRepository.isParent(parentId)||!categoryRepository.existsById(parentId)){
            throw new ForbiddenException("Parent category is not exist id="+parentId);
        }
        try{
            categoryRepository.deleteById(parentId);
        }catch (Exception e){
            throw new ForbiddenException("You can't delete this category because of relationships!");
        }
        return new ApiResponse("Parent category deleted!",true);
    }

    public ApiResponse editChildCategoryById(Integer categoryId, CategoryDto categoryDto) {
        if (categoryRepository.isParent(categoryId)||!categoryRepository.existsById(categoryId)){
            throw new ForbiddenException("Child category is not exist id="+categoryId);
        }
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new SourceIsNotExistException("Category is not exist by id = " + categoryId));
        if (categoryDto.getName()!=null) {
            category.setName(categoryDto.getName());
        }

        categoryRepository.save(category);
        return new ApiResponse("Category updated!",true);
    }

    public ApiResponse editParentCategoryById(Integer categoryId, CategoryDto categoryDto) {
        if (!categoryRepository.isParent(categoryId)||!categoryRepository.existsById(categoryId)){
            throw new ForbiddenException("Parent category is not exist id="+categoryId);
        }
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new SourceIsNotExistException("Category is not exist by id = " + categoryId));
        if (categoryDto.getName()!=null|| !Objects.equals(category.getName(), "")) {
            category.setName(categoryDto.getName());
        }
        Set<Integer> setOfId = categoryDto.getChildrenId();
        Set<Category> children = new HashSet<>();
        setOfId.forEach(id->{
            Optional<Category> opt = categoryRepository.getCategoryById(id);
            if (categoryRepository.isParent(id)|| opt.isEmpty()){
                throw new ForbiddenException("Child category is not exist id="+categoryId);
            }
            children.add(opt.get());
        });
        category.setChildren(children);
        categoryRepository.save(category);
        return new ApiResponse("Category updated!",true);
    }
}
