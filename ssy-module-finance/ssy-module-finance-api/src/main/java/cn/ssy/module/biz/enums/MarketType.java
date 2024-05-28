package cn.ssy.module.biz.enums;

import lombok.Getter;

/**
 * 市场类型
 */
@Getter
public enum MarketType {
    SH_SZ("CN", "sh_sz"), // 沪深
//    KCB("CN", "kcb"), // 科创
    HK("HK", "hk"),   //港股
    US("US", "us");   //美股

    private final String market;

    private final String type;

    MarketType(String market, String type) {
        this.market = market;
        this.type = type;
    }

    public static MarketType getMarketType(String market) {
        for (MarketType type : MarketType.values()) {
            if (type.getMarket().equals(market)) {
                return type;
            }
        }
        return null;
    }
}
