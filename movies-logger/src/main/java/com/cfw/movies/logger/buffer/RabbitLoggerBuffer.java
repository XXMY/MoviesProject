package com.cfw.movies.logger.buffer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Duskrain on 2017/8/15.
 */
public class RabbitLoggerBuffer {

    private static volatile int bufferSize = 1000;

    private static volatile int currentSize;

    private static ConcurrentLinkedQueue<byte []> messageQueue;

    public boolean offerMessage(byte [] message){
        if(RabbitLoggerBuffer.currentSize > RabbitLoggerBuffer.bufferSize )
            return false;

        RabbitLoggerBuffer.currentSize ++;
        return RabbitLoggerBuffer.messageQueue.offer(message);
    }

    /**
     * As ConcurrentLinkedQueue can not guarantee the queue size, it will not
     * actually empty the queue while invoking this method.
     * @return Buffered messages
     * @author CaiFangwei
     *
     * @since 2017-8-15 09:08:44
     */
    public List<byte []> pollAll(){
        int bufferSize = RabbitLoggerBuffer.messageQueue.size();
        List<byte []> messageList = new ArrayList<>(bufferSize);
        for(int i=0;i<bufferSize;i++){
            messageList.add(RabbitLoggerBuffer.messageQueue.poll());
        }

        RabbitLoggerBuffer.currentSize -= bufferSize;
        return messageList;
    }

    public void setBufferSize(int size){
        RabbitLoggerBuffer.bufferSize = size;
    }

    public void getCurrentBufferSize(){
    }

}
