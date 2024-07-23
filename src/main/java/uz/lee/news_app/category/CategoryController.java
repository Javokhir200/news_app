package uz.lee.news_app.category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }
    @PostMapping("/save-parent")
    public ResponseEntity<?> addParentCategory(@RequestBody CategoryDto dto) {
        String resp = service.addParentCategory(dto);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/save-child/{parentId}")
    public ResponseEntity<?> addChildCategory(@RequestBody CategoryDto dto,@PathVariable Integer parentId) {
        String resp = service.addChildCategory(dto,parentId);
        return ResponseEntity.ok(resp);
    }

    @GetMapping
    public ResponseEntity<?> getCategories(){
        List<Category> categories = service.getParentCategoriesOnly();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{parentId}")
    public ResponseEntity<?> getCategoryByParentId(@PathVariable Integer parentId){
        List<Category> categories = service.getCategoriesByParentId(parentId);
        return ResponseEntity.ok(categories);
    }
}
