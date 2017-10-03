/*
 * Copyright 2017 dmfs GmbH
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dmfs.android.bolts.service;

import android.os.IBinder;


/**
 * TODO: find a better name
 *
 * @author Marten Gajda
 */
public interface StubProxy<T>
{
    /**
     * Returns a stub object that connects to the service.
     * <p>
     * This method needs to contain only one line like:
     * <p>
     * <pre>
     * <code>
     * return &lt;T>.Stub.asInterface(service);
     * </code>
     * </pre>
     * <p>
     * Where &lt;T> is the interface of the service.
     *
     * @param service
     *
     * @return
     */
    T asInterface(IBinder service);
}
