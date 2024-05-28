package cn.ssy.module.finance.dal.dataobject;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @TableName finance_asset
 */
@TableName(value ="finance_asset")
@Data
@Builder
@AllArgsConstructor
public class FinanceAsset implements Serializable {


    public FinanceAsset() {
    }

    /**
     * 代码
     */
    @TableId(value = "symbol")
    private String symbol;

    /**
     * 数据来源 XQ THS TTJS
     */
    @TableField(value = "source")
    private String source;

    /**
     * 删除标记
     */
    @TableField(value = "deleted")
    @TableLogic
    private Integer deleted;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 资产类型 STOCK FUND ..
     */
    @TableField(value = "finance_type")
    private String financeType;

    /**
     * 资产类型
     */
    @TableField(value = "market_type")
    private String marketType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        FinanceAsset other = (FinanceAsset) that;
        return (this.getSymbol() == null ? other.getSymbol() == null : this.getSymbol().equals(other.getSymbol()))
                && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
                && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getFinanceType() == null ? other.getFinanceType() == null : this.getFinanceType().equals(other.getFinanceType()))
                && (this.getMarketType() == null ? other.getMarketType() == null : this.getMarketType().equals(other.getMarketType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSymbol() == null) ? 0 : getSymbol().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getFinanceType() == null) ? 0 : getFinanceType().hashCode());
        result = prime * result + ((getMarketType() == null) ? 0 : getMarketType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", symbol=").append(symbol);
        sb.append(", source=").append(source);
        sb.append(", deleted=").append(deleted);
        sb.append(", name=").append(name);
        sb.append(", financeType=").append(financeType);
        sb.append(", marketType=").append(marketType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}