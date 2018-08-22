package com.tool;

import com.tool.jms.Sender;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ShareObjectQuote {

    private final static ShareObjectQuote instance = new ShareObjectQuote();


    private ShareObjectQuote() {};


    public static ShareObjectQuote getInstance() {
        return instance;
    }

    public static TelegramBot telegramBot;

    public static Sender sender;

    public static boolean isStart = true;


    public static Map<String, Long> notifyMsg = new HashMap<>();
//    public static String token;
//
//    public static long chat_id;

//    @Value("${bot.token}")
    public static String token;
//    @Value("${chat.id}")
    public static long chat_id =-295025948;

    public static long chat_id_balance =-237206539;

    public static long chat_id_hedge =-195924043;

    public static long chat_id_ugrent =-313626904;



}
