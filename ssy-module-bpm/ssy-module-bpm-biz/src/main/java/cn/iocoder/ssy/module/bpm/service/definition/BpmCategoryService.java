package cn.iocoder.ssy.module.bpm.service.definition;

import cn.iocoder.ssy.framework.common.pojo.PageResult;
import cn.iocoder.ssy.module.bpm.controller.admin.definition.vo.category.BpmCategoryPageReqVO;
import cn.iocoder.ssy.module.bpm.controller.admin.definition.vo.category.BpmCategorySaveReqVO;
import cn.iocoder.ssy.module.bpm.dal.dataobject.definition.BpmCategoryDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static cn.iocoder.ssy.framework.common.util.collection.CollectionUtils.convertMap;

/**
 * BPM 流程分类 Service 接口
 *
 * @author  Ssy
 */
public interface BpmCategoryService {

    /**
     * 创建流程分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCategory(@Valid BpmCategorySaveReqVO createReqVO);

    /**
     * 更新流程分类
     *
     * @param updateReqVO 更新信息
     */
    void updateCategory(@Valid BpmCategorySaveReqVO updateReqVO);

    /**
     * 删除流程分类
     *
     * @param id 编号
     */
    void deleteCategory(Long id);

    /**
     * 获得流程分类
     *
     * @param id 编号
     * @return BPM 流程分类
     */
    BpmCategoryDO getCategory(Long id);

    /**
     * 获得流程分类分页
     *
     * @param pageReqVO 分页查询
     * @return 流程分类分页
     */
    PageResult<BpmCategoryDO> getCategoryPage(BpmCategoryPageReqVO pageReqVO);

    /**
     * 获得流程分类 Map，基于指定编码
     *
     * @param codes 编号数组
     * @return 流程分类 Map
     */
    default Map<String, BpmCategoryDO> getCategoryMap(Collection<String> codes) {
        return convertMap(getCategoryListByCode(codes), BpmCategoryDO::getCode);
    }

    /**
     * 获得流程分类列表，基于指定编码
     *
     * @return 流程分类列表
     */
    List<BpmCategoryDO> getCategoryListByCode(Collection<String> codes);

    /**
     * 获得流程分类列表，基于指定状态
     *
     * @param status 状态
     * @return 流程分类列表
     */
    List<BpmCategoryDO> getCategoryListByStatus(Integer status);

}