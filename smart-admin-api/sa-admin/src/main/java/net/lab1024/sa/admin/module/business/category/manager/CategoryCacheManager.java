package net.lab1024.sa.admin.module.business.category.manager;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import net.lab1024.sa.admin.module.business.category.dao.CategoryDao;
import net.lab1024.sa.admin.module.business.category.domain.entity.CategoryEntity;
import net.lab1024.sa.admin.module.business.category.domain.vo.CategoryTreeVO;
import net.lab1024.sa.common.common.util.SmartBeanUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Slf4j
public class CategoryCacheManager {


    @Autowired
    private CategoryDao categoryDao;


    /**
     * 根据类目id 移除缓存
     */
    public void removeCache() {
        log.info("clear CATEGORY ,CATEGORY_SUB ,CATEGORY_TREE");
    }

    /**
     * 查詢类目
     *
     * @param categoryId
     * @return
     */
    public CategoryEntity queryCategory(Long categoryId) {
        return categoryDao.selectById(categoryId);
    }

    /**
     * 查询类目 子级
     *
     * @param categoryId
     * @return
     */
    public List<CategoryEntity> querySubCategory(Long categoryId) {
        return categoryDao.queryByParentId(Lists.newArrayList(categoryId), false);
    }


    /**
     * 查询类目 层级树
     * 优先查询缓存
     *
     * @return
     */
    public List<CategoryTreeVO> queryCategoryTree(Long parentId, Integer categoryType) {
        List<CategoryEntity> allCategoryEntityList = categoryDao.queryByType(categoryType, false);

        List<CategoryEntity> categoryEntityList = allCategoryEntityList.stream().filter(e -> e.getParentId().equals(parentId)).collect(Collectors.toList());
        List<CategoryTreeVO> treeList = SmartBeanUtil.copyList(categoryEntityList, CategoryTreeVO.class);
        treeList.forEach(e -> {
            e.setLabel(e.getCategoryName());
            e.setValue(e.getCategoryId());
            e.setCategoryFullName(e.getCategoryName());
        });
        // 递归设置子类
        this.queryAndSetSubCategory(treeList, allCategoryEntityList);
        return treeList;
    }

    /**
     * 递归查询设置类目子类
     * 从缓存查询子类
     *
     * @param treeList
     */
    private void queryAndSetSubCategory(List<CategoryTreeVO> treeList, List<CategoryEntity> allCategoryEntityList) {
        if (CollectionUtils.isEmpty(treeList)) {
            return;
        }
        List<Long> parentIdList = treeList.stream().map(CategoryTreeVO::getValue).collect(Collectors.toList());
        List<CategoryEntity> categoryEntityList = allCategoryEntityList.stream().filter(e -> parentIdList.contains(e.getParentId())).collect(Collectors.toList());
        Map<Long, List<CategoryEntity>> categorySubMap = categoryEntityList.stream().collect(Collectors.groupingBy(CategoryEntity::getParentId));
        treeList.forEach(e -> {
            List<CategoryEntity> childrenEntityList = categorySubMap.getOrDefault(e.getValue(), Lists.newArrayList());
            List<CategoryTreeVO> childrenVOList = SmartBeanUtil.copyList(childrenEntityList, CategoryTreeVO.class);
            childrenVOList.forEach(item -> {
                item.setLabel(item.getCategoryName());
                item.setValue(item.getCategoryId());
                item.setCategoryFullName(e.getCategoryFullName() + "/" + item.getCategoryName());
            });
            // 递归查询
            this.queryAndSetSubCategory(childrenVOList, allCategoryEntityList);
            e.setChildren(childrenVOList);
        });
    }


}
