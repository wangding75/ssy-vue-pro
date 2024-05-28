package cn.ssy.module.finance.dal.mysql.mapper;

import cn.iocoder.ssy.framework.mybatis.core.mapper.BaseMapperX;
import cn.ssy.module.finance.dal.dataobject.FinanceAsset;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

/**
 * @author wangding
 * @description 针对表【finance_asset】的数据库操作Mapper
 * @createDate 2024-05-26 15:51:52
 * @Entity cn.ssy.module.finance.dal.dataobject.FinanceAsset
 */
@Mapper
public interface FinanceAssetMapper extends BaseMapperX<FinanceAsset> {

}




