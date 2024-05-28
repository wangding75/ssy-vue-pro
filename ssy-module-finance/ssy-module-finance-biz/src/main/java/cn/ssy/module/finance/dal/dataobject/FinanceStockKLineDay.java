package cn.ssy.module.finance.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName finance_stock_k_line_day
 */
@TableName(value = "finance_stock_k_line_day")
@Data
@Builder
@AllArgsConstructor
public class FinanceStockKLineDay implements Serializable {


    public FinanceStockKLineDay() {
    }

    /**
     * 代码
     */
    @TableField(value = "symbol")
    private String symbol;

    /**
     * 时间
     */
    @TableField(value = "timestamp")
    private Long timestamp;

    /**
     * 时间
     */
    @TableField(value = "date_time")
    private Date dateTime;

    /**
     * 成交量
     */
    @TableField(value = "volume")
    private Long volume;

    /**
     * 开盘价
     */
    @TableField(value = "open")
    private Integer open;

    /**
     * 最高价
     */
    @TableField(value = "high")
    private Integer high;

    /**
     * 最低价
     */
    @TableField(value = "low")
    private Integer low;

    /**
     * 收盘价
     */
    @TableField(value = "close")
    private Integer close;

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

    /**
     * 换手率
     */
    @TableField(value = "turnoverrate")
    private Integer turnoverrate;

    /**
     * 成交额
     */
    @TableField(value = "amount")
    private Long amount;

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

    /**
     * 数据来源 XQ THS TTJS
     */
    @TableField(value = "source")
    private String source;

    /**
     * 删除标记
     */
    @TableField(value = "deleted")
    private Integer deleted;

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
        FinanceStockKLineDay other = (FinanceStockKLineDay) that;
        return (this.getSymbol() == null ? other.getSymbol() == null : this.getSymbol().equals(other.getSymbol()))
                && (this.getTimestamp() == null ? other.getTimestamp() == null : this.getTimestamp().equals(other.getTimestamp()))
                && (this.getDateTime() == null ? other.getDateTime() == null : this.getDateTime().equals(other.getDateTime()))
                && (this.getVolume() == null ? other.getVolume() == null : this.getVolume().equals(other.getVolume()))
                && (this.getOpen() == null ? other.getOpen() == null : this.getOpen().equals(other.getOpen()))
                && (this.getHigh() == null ? other.getHigh() == null : this.getHigh().equals(other.getHigh()))
                && (this.getLow() == null ? other.getLow() == null : this.getLow().equals(other.getLow()))
                && (this.getClose() == null ? other.getClose() == null : this.getClose().equals(other.getClose()))
                && (this.getChg() == null ? other.getChg() == null : this.getChg().equals(other.getChg()))
                && (this.getPercent() == null ? other.getPercent() == null : this.getPercent().equals(other.getPercent()))
                && (this.getTurnoverrate() == null ? other.getTurnoverrate() == null : this.getTurnoverrate().equals(other.getTurnoverrate()))
                && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
                && (this.getFinanceType() == null ? other.getFinanceType() == null : this.getFinanceType().equals(other.getFinanceType()))
                && (this.getMarketType() == null ? other.getMarketType() == null : this.getMarketType().equals(other.getMarketType()))
                && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
                && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSymbol() == null) ? 0 : getSymbol().hashCode());
        result = prime * result + ((getTimestamp() == null) ? 0 : getTimestamp().hashCode());
        result = prime * result + ((getDateTime() == null) ? 0 : getDateTime().hashCode());
        result = prime * result + ((getVolume() == null) ? 0 : getVolume().hashCode());
        result = prime * result + ((getOpen() == null) ? 0 : getOpen().hashCode());
        result = prime * result + ((getHigh() == null) ? 0 : getHigh().hashCode());
        result = prime * result + ((getLow() == null) ? 0 : getLow().hashCode());
        result = prime * result + ((getClose() == null) ? 0 : getClose().hashCode());
        result = prime * result + ((getChg() == null) ? 0 : getChg().hashCode());
        result = prime * result + ((getPercent() == null) ? 0 : getPercent().hashCode());
        result = prime * result + ((getTurnoverrate() == null) ? 0 : getTurnoverrate().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getFinanceType() == null) ? 0 : getFinanceType().hashCode());
        result = prime * result + ((getMarketType() == null) ? 0 : getMarketType().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", symbol=").append(symbol);
        sb.append(", timestamp=").append(timestamp);
        sb.append(", dateTime=").append(dateTime);
        sb.append(", volume=").append(volume);
        sb.append(", open=").append(open);
        sb.append(", high=").append(high);
        sb.append(", low=").append(low);
        sb.append(", close=").append(close);
        sb.append(", chg=").append(chg);
        sb.append(", percent=").append(percent);
        sb.append(", turnoverrate=").append(turnoverrate);
        sb.append(", amount=").append(amount);
        sb.append(", financeType=").append(financeType);
        sb.append(", marketType=").append(marketType);
        sb.append(", source=").append(source);
        sb.append(", deleted=").append(deleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}