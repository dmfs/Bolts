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

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import org.dmfs.android.bolts.service.FutureServiceConnection;
import org.dmfs.android.bolts.service.StubProxy;
import org.dmfs.android.bolts.service.exceptions.BindFailed;
import org.dmfs.jems.optional.Optional;
import org.dmfs.jems.optional.elementary.Present;

import java.util.Locale;
import java.util.concurrent.TimeoutException;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;

import static org.dmfs.jems.optional.elementary.Absent.absent;


/**
 * A generic implementation of {@link FutureServiceConnection} to connect to any service.
 * <p>
 * For internal purposes only.
 *
 * @author Marten Gajda
 */
final class BasicServiceConnection<T> implements FutureServiceConnection<T>
{
    private final Context mContext;
    private final Intent mIntent;
    private final StubProxy<T> mStubProxy;
    private boolean mBound;
    private Optional<T> mService = absent();

    private final ServiceConnection mConnection = new ServiceConnection()
    {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            synchronized (this)
            {
                mService = new Present<>(mStubProxy.asInterface(service));
                notifyAll();
            }
        }


        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            synchronized (this)
            {
                mService = absent();
                notifyAll();
            }
        }
    };


    /**
     * Binds the service identified by the given Intent.
     *
     * @param context
     *         A {@link Context}.
     * @param intent
     *         The {@link Intent} to bind the service.
     * @param stubProxy
     *         A StubProxy to convert the IBinder to the service interface.
     */
    public BasicServiceConnection(@NonNull Context context, @NonNull Intent intent, @NonNull StubProxy<T> stubProxy)
    {
        mContext = context.getApplicationContext();
        mIntent = intent;
        mStubProxy = stubProxy;
    }


    @Override
    public boolean isConnected()
    {
        synchronized (mConnection)
        {
            return mService.isPresent();
        }
    }


    @WorkerThread
    @NonNull
    @Override
    public T service(long timeout) throws TimeoutException, InterruptedException, BindFailed
    {
        synchronized (mConnection)
        {
            if (!mBound)
            {
                // not bound yet
                mBound = mContext.bindService(mIntent, mConnection, Context.BIND_AUTO_CREATE);
                if (!mBound)
                {
                    // according to the docs we need to unbind explicitly in this case
                    mContext.unbindService(mConnection);
                    throw new BindFailed("Could not bind the service");
                }
            }

            long now = System.currentTimeMillis();
            long end = now + timeout;
            try
            {
                while (now < end)
                {
                    if (mService.isPresent())
                    {
                        return mService.value();
                    }
                    mConnection.wait(end - now);
                    now = System.currentTimeMillis();
                }
                throw new TimeoutException(String.format(Locale.ENGLISH, "Could not connect within %d milliseconds", timeout));
            }
            catch (Exception e)
            {
                // automatically disconnect in case of an Exception
                disconnect();
                throw e;
            }
        }
    }


    @Override
    public void disconnect()
    {
        synchronized (mConnection)
        {
            if (mBound)
            {
                mContext.unbindService(mConnection);
                mBound = false;
            }
        }
    }
}