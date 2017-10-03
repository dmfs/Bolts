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
import android.content.Intent;

import org.dmfs.android.bolts.service.FutureServiceConnection;
import org.dmfs.android.bolts.service.StubProxy;


/**
 * An implementation of {@link FutureServiceConnection} to connect aidl based services.
 *
 * @author Marten Gajda
 */
public final class FutureAidlServiceConnection<T extends android.os.IInterface> extends DelegatingServiceConnection<T>
{

    /**
     * Binds the service identified by the given Intent.
     *
     * @param context
     *         A {@link Context}.
     * @param intent
     *         The {@link Intent} to bind the service.
     */
    public FutureAidlServiceConnection(Context context, Intent intent, StubProxy<T> stubProxy)
    {
        super(new BasicServiceConnection<>(context, intent, stubProxy));
    }
}