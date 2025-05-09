package com.tekri.grunts.repository

import com.tekri.grunts.data.MessageDao
import com.tekri.grunts.model.Message
import kotlinx.coroutines.flow.Flow

class ChatRepository(private val messageDao: MessageDao) {
    fun getAllMessages(): Flow<List<Message>> = messageDao.getAllMessages()
    suspend fun insertMessage(message: Message) = messageDao.insertMessage(message)
    suspend fun clearMessages() = messageDao.clearMessages() // Added for completeness
}