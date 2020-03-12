package ru.eda.bot.feedchecker

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException


class FeedCheckerBot() : TelegramLongPollingBot() {
    /**
     * Return bot username of this bot
     */
    override fun getBotUsername() = "FeedCheckerBot"

    /**
     * Returns the token of the bot to be able to perform Telegram Api Requests
     * @return Token of the bot
     */
    override fun getBotToken() = "746138982:AAE7wM4fVNzZq6_3gpyyBOjnRoB8vNkFN-0"

    /**
     * This method is called when receiving updates via GetUpdates method
     * @param update Update received
     */
    override fun onUpdateReceived(update: Update?) {
        println("Get update=$update")
        update.let {
            if (it?.hasMessage()!! && it.message?.hasText()!!) {
                val message = SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update!!.message.chatId)
                    .setText(update.message.text)
                try {
                    execute(message) // Call method to send the message
                } catch (e: TelegramApiException) {
                    e.printStackTrace()
                }
            }
        }
    }
}