package net.lab1024.sa.admin.module.business.category.controller;

import net.lab1024.sa.admin.common.AdminBaseController;
import net.lab1024.sa.admin.module.business.category.domain.form.CategoryAddForm;
import net.lab1024.sa.admin.module.business.category.domain.form.CategoryTreeQueryForm;
import net.lab1024.sa.admin.module.business.category.domain.form.CategoryUpdateForm;
import net.lab1024.sa.admin.module.business.category.domain.vo.CategoryTreeVO;
import net.lab1024.sa.admin.module.business.category.domain.vo.CategoryVO;
import net.lab1024.sa.admin.module.business.category.service.CategoryService;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class CategoryController extends AdminBaseController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category/add")
    public ResponseDTO<String> add(@RequestBody @Valid CategoryAddForm addForm) {
        return categoryService.add(addForm);
    }

    @PostMapping("/category/update")
    public ResponseDTO<String> update(@RequestBody @Valid CategoryUpdateForm updateForm) {
        return categoryService.update(updateForm);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseDTO<CategoryVO> queryDetail(@PathVariable Long categoryId) {
        return categoryService.queryDetail(categoryId);
    }

    @PostMapping("/category/tree")
    public ResponseDTO<List<CategoryTreeVO>> queryTree(@RequestBody @Valid CategoryTreeQueryForm queryForm) {
        return categoryService.queryTree(queryForm);
    }

    @GetMapping("/category/delete/{categoryId}")
    public ResponseDTO<String> delete(@PathVariable Long categoryId) {
        return categoryService.delete(categoryId);
    }
}
