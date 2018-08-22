package com.tool;

import com.google.gson.Gson;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.binance.BinanceExchange;
import org.knowm.xchange.bitbox.BitboxExchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrade;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.kucoin.service.KucoinCancelOrderParams;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.service.trade.params.CancelOrderParams;
import org.knowm.xchange.service.trade.params.TradeHistoryParamsAll;
import org.knowm.xchange.service.trade.params.orders.DefaultOpenOrdersParamCurrencyPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class BinanceQuote implements Runnable{

    private static final Logger log = LoggerFactory.getLogger(BinanceQuote.class);

    public BinanceQuote() {

    }

    public void SendQuote()
    {
        try
        {
            String SELL = "{\n" +
                    "\"msgType\":\"REQUESTNEW\",\n" +
                    "\"msg\":{\n" +
                    "\"orderId\":\"0001\",\n" +
                    "\"symbol\":\"%s\",\n" +
                    "\"baseCC\":\"btc\",\n" +
                    "\"counterCC\":\"eth\",\n" +
                    "\"orderType\":\"LIMIT\",\n" +
                    "\"orderSide\":\"SELL\",\n" +
                    "\"rate\":%6f,\n" +
                    "\"quantity\":1.000000,\n" +
                    "\"convertAmount\":7.0090739471,\n" +
                    "\"remainAmount\":null,\n" +
                    "\"fee\":null,\n" +
                    "\"orderTime\":1530866673422,\n" +
                    "\"userId\":\"1\"\n" +
                    "}}";
            String BUY = "{\n" +
                    "\"msgType\":\"REQUESTNEW\",\n" +
                    "\"msg\":{\n" +
                    "\"orderId\":\"0001\",\n" +
                    "\"symbol\":\"%s\",\n" +
                    "\"baseCC\":\"btc\",\n" +
                    "\"counterCC\":\"eth\",\n" +
                    "\"orderType\":\"LIMIT\",\n" +
                    "\"orderSide\":\"BUY\",\n" +
                    "\"rate\":%6f,\n" +
                    "\"quantity\":1.000000,\n" +
                    "\"convertAmount\":7.0090739471,\n" +
                    "\"remainAmount\":null,\n" +
                    "\"fee\":null,\n" +
                    "\"orderTime\":1530866673422,\n" +
                    "\"userId\":\"1\"\n" +
                    "}}";

            log.info("Get public data from binance");
            Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BinanceExchange.class.getName());
            MarketDataService marketDataService = exchange.getMarketDataService();

            Ticker ticker = marketDataService.getTicker(new CurrencyPair("ETH", "BTC"));
            System.out.println(ticker.toString());

            if(ShareObjectQuote.sender != null && ShareObjectQuote.isStart == true)
            {
                String pair = ""+ ticker.getCurrencyPair().base + ticker.getCurrencyPair().counter + "_T";

                BUY = String.format(BUY, pair, ticker.getLast());
                SELL = String.format(SELL, pair, ticker.getLast());
                ShareObjectQuote.sender.send(BUY);
                ShareObjectQuote.sender.send(SELL);
                log.info(BUY);
                log.info(SELL);
            }

        }
        catch (Exception e)
        {
            log.info(e.getMessage());
        }

    }


    @Override
    public void run() {
        while (true)
        {
            try
            {
                SendQuote();
                Thread.sleep(2000);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }

    }
}
