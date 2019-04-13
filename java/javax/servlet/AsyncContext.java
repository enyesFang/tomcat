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

/**
 * Servlet 3异步处理上下文。
 * AsyncContext可以从当前线程传给另外的线程，并在新的线程中完成对请求的处理并返回结果给客户端，初始线程便可以还回给容器线程池以处理更多的请求。
 * TODO SERVLET3 - Add comments
 * @since Servlet 3.0
 */
public interface AsyncContext {
    public static final String ASYNC_REQUEST_URI =
        "javax.servlet.async.request_uri";
    public static final String ASYNC_CONTEXT_PATH  =
        "javax.servlet.async.context_path";
    public static final String ASYNC_MAPPING =
            "javax.servlet.async.mapping";
    public static final String ASYNC_PATH_INFO =
        "javax.servlet.async.path_info";
    public static final String ASYNC_SERVLET_PATH =
        "javax.servlet.async.servlet_path";
    public static final String ASYNC_QUERY_STRING =
        "javax.servlet.async.query_string";

    ServletRequest getRequest();

    ServletResponse getResponse();

    boolean hasOriginalRequestAndResponse();

    /**
     * @throws IllegalStateException if this method is called when the request
     * is not in asynchronous mode. The request is in asynchronous mode after
     * {@link javax.servlet.http.HttpServletRequest#startAsync()} or
     * {@link javax.servlet.http.HttpServletRequest#startAsync(ServletRequest,
     * ServletResponse)} has been called and before {@link #complete()} or any
     * other dispatch() method has been called.
     */
    void dispatch();

    /**
     * @param path The path to which the request/response should be dispatched
     *             relative to the {@link ServletContext} from which this async
     *             request was started.
     *
     * @throws IllegalStateException if this method is called when the request
     * is not in asynchronous mode. The request is in asynchronous mode after
     * {@link javax.servlet.http.HttpServletRequest#startAsync()} or
     * {@link javax.servlet.http.HttpServletRequest#startAsync(ServletRequest,
     * ServletResponse)} has been called and before {@link #complete()} or any
     * other dispatch() method has been called.
     */
    void dispatch(String path);

    /**
     * @param path The path to which the request/response should be dispatched
     *             relative to the specified {@link ServletContext}.
     * @param context The {@link ServletContext} to which the request/response
     *                should be dispatched.
     *
     * @throws IllegalStateException if this method is called when the request
     * is not in asynchronous mode. The request is in asynchronous mode after
     * {@link javax.servlet.http.HttpServletRequest#startAsync()} or
     * {@link javax.servlet.http.HttpServletRequest#startAsync(ServletRequest,
     * ServletResponse)} has been called and before {@link #complete()} or any
     * other dispatch() method has been called.
     */
    void dispatch(ServletContext context, String path);

    /**
     * 异步任务处理完成，需要调用该方法告知Servlet容器。
     */
    void complete();

    /**
     * 开始一个异步task。
     * 1. 执行的线程可以是从Servlet容器中已有的主线程池获取，也可以另外维护一个线程池，不同容器实现可能不一样。
     * 该新线程中继续处理请求，而原先的线程将被回收到主线程池中。
     * 事实上，这种方式对性能的改进不大，因为如果新的线程和初始线程共享同一个线程池的话，相当于闲置下了一个线程，但同时又占用了另一个线程。
     * 2. 除了调用AsyncContext的start()方法，我们还可以通过自定义线程池的方式来实现异步处理。
     * @param run
     */
    void start(Runnable run);

    /**
     * 增加异步事件监听器。
     * 如监听是否完成，否则不能获取Request、Response流，防止在业务代码处理之前读取了流，影响业务代码逻辑。
     * @param listener
     */
    void addListener(AsyncListener listener);

    void addListener(AsyncListener listener, ServletRequest request,
            ServletResponse response);

    <T extends AsyncListener> T createListener(Class<T> clazz)
    throws ServletException;

    /**
     * Set the timeout.
     *
     * @param timeout The timeout in milliseconds. 0 or less indicates no
     *                timeout.
     */
    void setTimeout(long timeout);

    /**
     * Get the current.
     *
     * @return The timeout in milliseconds. 0 or less indicates no timeout.
     */
    long getTimeout();
}
