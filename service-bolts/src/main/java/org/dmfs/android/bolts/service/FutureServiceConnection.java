/*
 * Copyright (c) 2017 dmfs GmbH
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

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import org.dmfs.android.bolts.service.exceptions.BindFailed;

import java.util.concurrent.TimeoutException;


/**
 * Interface of an object that holds a {@link android.content.ServiceConnection}.
 *
 * @param <T>
 *         The type of the service (i.e. it's interface).
 *
 * @author Marten Gajda
 */
public interface FutureServiceConnection<T>
{
    /**
     * Returns whether the service is currently connected.
     *
     * @return {@code true} if the service is connected.
     */
    boolean isConnected();

    /**
     * Returns the service instance.
     * <p>
     * Note always call {@link #disconnect()} when you're done with this, even in case of an Exception.
     *
     * @param timeout
     *         The maximum time to wait for the connection to be established
     *
     * @return The service instance.
     *
     * @throws TimeoutException
     * @throws InterruptedException
     * @throws BindFailed
     */
    @NonNull
    @WorkerThread
    T service(long timeout) throws TimeoutException, InterruptedException, BindFailed;

    /**
     * Closes the service connection if there was any. Note that the instance returned by {@link #service(long)} must no longer be used after calling this.
     * <p>
     * Make sure you always call this when you're done with the service returned by {@link #service(long)}.
     */
    void disconnect();
}
