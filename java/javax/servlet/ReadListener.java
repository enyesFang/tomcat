/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package javax.servlet;

import java.io.IOException;

/**
 * Receives notification of read events when using non-blocking IO.
 * Servlet 3.0对请求的处理虽然是异步的，但是对InputStream和OutputStream的IO操作却依然是阻塞的，
 * 对于数据量大的请求体或者返回体，阻塞IO也将导致不必要的等待。
 * 因此在Servlet 3.1中引入了非阻塞IO（参考下图红框内容），
 * 通过在HttpServletRequest和HttpServletResponse中分别添加ReadListener和WriterListener方式，
 * 只有在IO数据满足一定条件时（比如数据准备好时），才进行后续的操作。
 * @since Servlet 3.1
 * @see ServletInputStream#setReadListener(ReadListener)
 */
public interface ReadListener extends java.util.EventListener{

    /**
     * Invoked when data is available to read. The container will invoke this
     * method the first time for a request as soon as there is data to read.
     * Subsequent invocations will only occur if a call to
     * {@link ServletInputStream#isReady()} has returned false and data has
     * subsequently become available to read.
     *
     * @throws IOException id an I/O error occurs while processing the event
     */
    public abstract void onDataAvailable() throws IOException;

    /**
     * Invoked when the request body has been fully read.
     *
     * @throws IOException id an I/O error occurs while processing the event
     */
    public abstract void onAllDataRead() throws IOException;

    /**
     * Invoked if an error occurs while reading the request body.
     *
     * @param throwable The exception that occurred
     */
    public abstract void onError(java.lang.Throwable throwable);
}
