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

package org.dmfs.android.bolts.service.elementary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import org.dmfs.android.bolts.service.FutureServiceConnection;
import org.dmfs.android.bolts.service.exceptions.BindFailed;

import java.util.concurrent.TimeoutException;


/**
 * An abstract {@link FutureServiceConnection} which delegates all calls to another {@link FutureServiceConnection}. This is meant as a workaround for the lack
 * of support of the decorator pattern in Java.
 *
 * @author Marten Gajda
 */
public abstract class DelegatingServiceConnection<T> implements FutureServiceConnection<T>
{

    private final FutureServiceConnection<T> mDelegate;


    /**
     * Binds the service identified by the given Intent.
     *
     * @param delegate
     *         The {@link FutureServiceConnection} to delegate all calls to. A {@link Context}.
     */
    public DelegatingServiceConnection(FutureServiceConnection<T> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public final boolean isConnected()
    {
        return mDelegate.isConnected();
    }


    @WorkerThread
    @NonNull
    @Override
    public final T service(long timeout) throws TimeoutException, InterruptedException, BindFailed
    {
        return mDelegate.service(timeout);
    }


    @Override
    public final void disconnect()
    {
        mDelegate.disconnect();
    }
}