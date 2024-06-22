package cn.ssy.module.finance.spider.tt;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Pair;
import cn.ssy.module.biz.enums.MarketType;
import cn.ssy.module.finance.dal.dataobject.FinanceAsset;
import cn.ssy.module.finance.dal.dataobject.FinanceFundPrice;
import cn.ssy.module.finance.spider.ths.THSFundListCollector;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
public class FundListCollectorTest {

//    http://api.fund.eastmoney.com/f10/lsjz?callback=jQuery18308486279131842354_1717077670060&fundCode=002207&pageIndex=2&pageSize=20&startDate=&endDate=&_=1717077680841
//    http://api.fund.eastmoney.com/f10/lsjz?callback=jQuery18306087246797610892_1717077547243&fundCode=004244&pageIndex=4&pageSize=20&startDate=&endDate=&_=1717077824424
//    http://api.fund.eastmoney.com/f10/lsjz?callback=jQuery18306087246797610892_1717077547243&fundCode=004244&pageIndex=5&pageSize=20&startDate=&endDate=&_=1717077846816
//    http://api.fund.eastmoney.com/f10/lsjz?callback=jQuery18306087246797610892_1717077547243&fundCode=004244&pageIndex=6&pageSize=20&startDate=&endDate=&_=1717077858332
//    http://api.fund.eastmoney.com/f10/lsjz?callback=jQuery18306087246797610892_1717077547243&fundCode=004244&pageIndex=7&pageSize=20&startDate=&endDate=&_=1717077880967
//    http://api.fund.eastmoney.com/f10/lsjz?callback=jQuery183027441755388165445_1717078435486&fundCode=004244&pageIndex=8&pageSize=20&startDate=&endDate=&_=1717077937150
//    http://api.fund.eastmoney.com/f10/lsjz?callback=jQuery183027441755388165445_1717078435485&fundCode=004244&pageIndex=13&pageSize=20&startDate=&endDate=&_=1717080015758
//    http://api.fund.eastmoney.com/f10/lsjz?callback=jQuery183027441755388165445_1717078435485&fundCode=004244&pageIndex=15&pageSize=20&startDate=&endDate=&_=1717080392635
//    http://api.fund.eastmoney.com/f10/lsjz?callback=jQuery18306087246797610892_1717077547243&fundCode=004244&pageIndex=3&pageSize=20&startDate=&endDate=&_=1717077616549

    @Test
    public void collectLogic() throws Exception {

        Set<String> keys = Sets.newHashSet();

        for (int i = 0; i < 99999; i++) {
            THSFundListCollector fundListCollector = new THSFundListCollector("", MarketType.SH_SZ, i + 1, 200);
            List<FinanceAsset> financeAssets = fundListCollector.collectLogic();
            if (CollectionUtil.isEmpty(financeAssets)) break;
            List<String> collect = financeAssets.stream().map(FinanceAsset::getSymbol).distinct().collect(Collectors.toList());
            keys.addAll(collect);
        }
        System.out.println(keys.size());

//        pairs.forEach(pricePair -> {
//            log.info("{}", pricePair.getKey());
//            log.info("{}", pricePair.getValue());
//        });


    }
}