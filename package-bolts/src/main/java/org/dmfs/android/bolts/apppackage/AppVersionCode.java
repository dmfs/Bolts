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

import org.dmfs.jems.single.Single;


/**
 * A {@link Single} of the version code of an app.
 *
 * @author Marten Gajda
 */
public final class AppVersionCode implements Single<Integer>
{
    private final Single<PackageInfo> mDelegate;


    public AppVersionCode(Context context)
    {
        this(new AppPackageInfo(context, 0));
    }


    public AppVersionCode(Single<PackageInfo> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public Integer value()
    {
        return mDelegate.value().versionCode;
    }
}
