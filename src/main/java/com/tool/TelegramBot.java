package com.tool;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.math.BigDecimal;
import java.util.*;

@Component
public class TelegramBot extends TelegramLongPollingBot  {


    @Override
    public void onUpdateReceived(Update update) {

        long chat_id = update.getMessage().getChatId();
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables

            String message_text = update.getMessage().getText();
            if(message_text.equals("/start"))
            {
               ShareObjectQuote.isStart = true;
                String msg = "Start push order";
                SendMessage message_ugrent = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText(msg);
                try {
                    execute(message_ugrent); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }

            if(message_text.equals("/stop"))
            {
               ShareObjectQuote.isStart = false;

                String msg = "Stop push order";
                SendMessage message_ugrent = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText(msg);
                try {
                    execute(message_ugrent); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

//            if(message_text.equals("/balances"))
//            {
//                querryBalance(chat_id);
//            }

        }
    }



    //query stop/start


    @Override
    public String getBotUsername() {
        // Return bot username
        // If bot username is @TelegramBot, it must return 'TelegramBot'
        return "mtbit_bot";
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
//        return ShareObjectQuote.token;
        return "680035266:AAG-JL0Hfm-s5klJvM6YEFGXoCr2xky4f8s";

    }


}
