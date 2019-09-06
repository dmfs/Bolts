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

import androidx.annotation.NonNull;


/**
 * A {@link FutureServiceConnection} to connect to local services (which means services in the same process which do not use aidl interfaces).
 *
 * @author Marten Gajda
 */
public final class FutureLocalServiceConnection<T> extends DelegatingServiceConnection<T>
{
    /**
     * Binds the local service (i.e. a service running in the same process) identified by the given Intent.
     *
     * @param context
     *         A {@link Context}.
     * @param intent
     *         The {@link Intent} to bind the service.
     */
    public FutureLocalServiceConnection(@NonNull Context context, @NonNull Intent intent)
    {
        super(new BasicServiceConnection<>(context, intent, new LocalServiceStubProxy<T>()));
    }
}