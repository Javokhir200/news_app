package uz.lee.news_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.lee.news_app.dto.Sub_CategoryDto;
import uz.lee.news_app.service.SubCategoryService;

@RestController
@RequestMapping("/api/subcategories")
public class SubCategoryController {
    private final SubCategoryService service;

    public SubCategoryController(SubCategoryService service) {
        this.service = service;
    }
    @PostMapping("/save")
    public ResponseEntity<?> create(@RequestBody Sub_CategoryDto dto) {
        return ResponseEntity.ok().build();
    }
}
