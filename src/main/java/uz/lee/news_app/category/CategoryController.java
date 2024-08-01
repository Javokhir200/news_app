package uz.lee.news_app.category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lee.news_app.aop.CheckPermissions;
import uz.lee.news_app.custom_responses.ApiResponse;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @CheckPermissions(permission = "CAN_CHANGE_CATEGORIES")
    @PostMapping("/save-parent")
    public ResponseEntity<?> addParentCategory(@RequestBody CategoryDto dto) {
        return ResponseEntity.ok(service.addParentCategory(dto));
    }

    @CheckPermissions(permission = "CAN_CHANGE_CATEGORIES")
    @PostMapping("/save-child/{parentId}")
    public ResponseEntity<?> addChildCategory(@RequestBody CategoryDto dto,@PathVariable Integer parentId) {
        return ResponseEntity.ok(service.addChildCategory(dto,parentId));
    }

    @GetMapping
    public ResponseEntity<?> getCategories(){
        return ResponseEntity.ok(service.getParentCategoriesOnly());
    }

    @GetMapping("/{parentId}")
    public ResponseEntity<?> getCategoryByParentId(@PathVariable Integer parentId){
        return ResponseEntity.ok(service.getCategoriesByParentId(parentId));
    }

    @CheckPermissions(permission = "CAN_CHANGE_CATEGORIES")
    @DeleteMapping("/delete-child/{childId}")
    public ResponseEntity<?> deleteChildById(@PathVariable Integer childId){
        return ResponseEntity.ok(service.deleteChildById(childId));
    }
    @CheckPermissions(permission = "CAN_CHANGE_CATEGORIES")
    @DeleteMapping("/delete-parent/{parentId}")
    public ResponseEntity<?> deleteParentById(@PathVariable Integer parentId){
        ApiResponse resp = service.deleteParentById(parentId);
        return ResponseEntity.ok(resp);
    }
    @CheckPermissions(permission = "CAN_CHANGE_CATEGORIES")
    @PutMapping("/edit-child/{categoryId}")
    public ResponseEntity<?> editChildCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){
        return ResponseEntity.ok(service.editChildCategoryById(categoryId,categoryDto));
    }
    @CheckPermissions(permission = "CAN_CHANGE_CATEGORIES")
    @PutMapping("/edit-parent/{categoryId}")
    public ResponseEntity<?> editParentCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){
        return ResponseEntity.ok(service.editParentCategoryById(categoryId,categoryDto));
    }
}
