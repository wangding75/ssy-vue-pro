package cn.ssy.module.finance.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName finance_fund_price
 */
@TableName(value = "finance_fund_price")
@Data
@Builder
@AllArgsConstructor
public class FinanceFundPrice implements Serializable {

    /**
     * 代码
     */
    @TableField(value = "symbol")
    private String symbol;

    /**
     * 日期
     */
    @TableField(value = "date_time")
    private String dateTime;

    /**
     * 单位净值
     */
    @TableField(value = "per_price")
    private Integer perPrice;

    /**
     * 累计净值
     */
    @TableField(value = "fq_price")
    private Integer fqPrice;

    /**
     * 累计净值
     */
    @TableField(value = "price")
    private Integer price;

    /**
     * 涨跌额
     */
    @TableField(value = "chg")
    private Integer chg;

    /**
     * 涨跌幅
     */
    @TableField(value = "percent")
    private Integer percent;

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
        FinanceFundPrice other = (FinanceFundPrice) that;
        return (this.getSymbol() == null ? other.getSymbol() == null : this.getSymbol().equals(other.getSymbol()))
                && (this.getDateTime() == null ? other.getDateTime() == null : this.getDateTime().equals(other.getDateTime()))
                && (this.getPerPrice() == null ? other.getPerPrice() == null : this.getPerPrice().equals(other.getPerPrice()))
                && (this.getFqPrice() == null ? other.getFqPrice() == null : this.getFqPrice().equals(other.getFqPrice()))
                && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
                && (this.getChg() == null ? other.getChg() == null : this.getChg().equals(other.getChg()))
                && (this.getPercent() == null ? other.getPercent() == null : this.getPercent().equals(other.getPercent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSymbol() == null) ? 0 : getSymbol().hashCode());
        result = prime * result + ((getDateTime() == null) ? 0 : getDateTime().hashCode());
        result = prime * result + ((getPerPrice() == null) ? 0 : getPerPrice().hashCode());
        result = prime * result + ((getFqPrice() == null) ? 0 : getFqPrice().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getChg() == null) ? 0 : getChg().hashCode());
        result = prime * result + ((getPercent() == null) ? 0 : getPercent().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", symbol=").append(symbol);
        sb.append(", dateTime=").append(dateTime);
        sb.append(", perPrice=").append(perPrice);
        sb.append(", fqPrice=").append(fqPrice);
        sb.append(", price=").append(price);
        sb.append(", chg=").append(chg);
        sb.append(", percent=").append(percent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}