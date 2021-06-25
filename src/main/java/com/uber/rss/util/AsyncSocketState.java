/*
 * Copyright (c) 2020 Uber Technologies, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uber.rss.util;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AsyncSocketState {
    private final AsynchronousSocketChannel socket;
    private final ConcurrentLinkedQueue<ByteBuffer> byteBuffers = new ConcurrentLinkedQueue<>();

    public AsyncSocketState(AsynchronousSocketChannel socket) {
        this.socket = socket;
    }

    public AsynchronousSocketChannel getSocket() {
        return socket;
    }
    
    public void addBuffer(ByteBuffer byteBuffer) {
        byteBuffers.add(byteBuffer);
    }

    public ByteBuffer peekBuffer() {
        if (byteBuffers.isEmpty()) {
            return null;
        }
        
        return byteBuffers.peek();
    }
    
    public ByteBuffer removeBuffer() {
        return byteBuffers.poll();
    }

    @Override
    public String toString() {
        return "AsyncSocketState{" +
                "byteBuffers=" + byteBuffers.size() +
                '}';
    }
}
