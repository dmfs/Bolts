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

import android.os.IBinder;

import org.dmfs.android.bolts.service.StubProxy;

import androidx.annotation.NonNull;


/**
 * A {@link StubProxy} for local services.
 *
 * @author Marten Gajda
 */
public final class LocalServiceStubProxy<T> implements StubProxy<T>
{
    @Override
    public T asInterface(@NonNull IBinder service)
    {
        // for a local service we just need to cast the IBinder
        return (T) service;
    }
}
