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
import java.util.EventListener;

/**
 * TODO SERVLET3 - Add comments
 * @since Servlet 3.0
 */
public interface AsyncListener extends EventListener {
    /**
     * 异步执行完毕事件。
     */
    void onComplete(AsyncEvent event) throws IOException;

    /**
     * 异步线程执行超时。
     */
    void onTimeout(AsyncEvent event) throws IOException;

    /**
     * 异步线程出错。
     */
    void onError(AsyncEvent event) throws IOException;

    /**
     * 异步线程开始。
     */
    void onStartAsync(AsyncEvent event) throws IOException;
}
