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

import java.util.EventListener;

/**
 * Implementations of this interface receive notifications about changes to the
 * servlet context of the web application they are part of. To receive
 * notification events, the implementation class must be configured in the
 * deployment descriptor for the web application.
 * 在Context容器启动之后不能再添加新的该listener。
 * @see ServletContextEvent
 * @since v 2.3
 */

public interface ServletContextListener extends EventListener {

    /**
     ** Notification that the web application initialization process is starting.
     * All ServletContextListeners are notified of context initialization before
     * any filter or servlet in the web application is initialized.
     * The default implementation is a NO-OP.
     * Context容器初始化时触发，所有的Filter和Servlet的init方法调用之前触发。
     * @param sce Information about the ServletContext that was initialized
     */
    public default void contextInitialized(ServletContextEvent sce) {
    }

    /**
     ** Notification that the servlet context is about to be shut down. All
     * servlets and filters have been destroy()ed before any
     * ServletContextListeners are notified of context destruction.
     * The default implementation is a NO-OP.
     * Context容器销毁，在所有的Filter和Servlet的destroy方法调用之后触发。
     * @param sce Information about the ServletContext that was destroyed
     */
    public default void contextDestroyed(ServletContextEvent sce) {
    }
}
