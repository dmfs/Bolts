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

package org.dmfs.android.bolts.apppackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import org.dmfs.iterators.EmptyIterator;
import org.dmfs.iterators.Function;
import org.dmfs.iterators.elementary.Seq;
import org.dmfs.jems.single.Single;
import org.dmfs.optional.NullSafe;
import org.dmfs.optional.decorators.Mapped;

import java.util.Iterator;

import androidx.annotation.NonNull;


/**
 * An {@link Iterable} of all permissions declared in the app manifest using a {@code <uses-permission>} element. This includes permissions which have not been
 * granted or were unknown at installation time.
 *
 * @author Marten Gajda
 */
public final class RequestedAppPermissions implements Iterable<String>
{
    private final Single<PackageInfo> mPackageInfo;


    public RequestedAppPermissions(@Deprecated Context context)
    {
        this(new AppPackageInfo(context, PackageManager.GET_PERMISSIONS));
    }


    /**
     * Creates an Iterable of permission names from the given {@link PackageInfo} {@link Single}.
     * <p>
     * Note, the {@link PackageInfo} must have been retrieved with the {@link PackageManager#GET_PERMISSIONS} flag.
     *
     * @param packageInfo
     *         A {@link Single} {@link PackageInfo} of the app.
     */
    public RequestedAppPermissions(@NonNull Single<PackageInfo> packageInfo)
    {
        mPackageInfo = packageInfo;
    }


    @NonNull
    @Override
    public Iterator<String> iterator()
    {
        // requestedPermissions is null if none have been requested (big fail), so we need to use an Optional and map it
        return new Mapped<>(
                new Function<String[], Iterator<String>>()
                {
                    @Override
                    public Iterator<String> apply(String[] argument)
                    {
                        return new Seq<>(argument);
                    }
                },
                new NullSafe<>(mPackageInfo.value().requestedPermissions)).value(EmptyIterator.<String>instance());
    }
}
