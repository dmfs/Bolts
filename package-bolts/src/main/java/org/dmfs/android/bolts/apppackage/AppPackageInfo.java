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

import org.dmfs.jems.single.Single;


/**
 * A {@link Single} of the {@link PackageInfo} of the own package.
 *
 * @author Marten Gajda
 */
public final class AppPackageInfo implements Single<PackageInfo>
{
    private final Context mContext;
    private final int mFlags;


    public AppPackageInfo(Context context, int flags)
    {
        mContext = context;
        mFlags = flags;
    }


    @Override
    public PackageInfo value()
    {
        try
        {
            return mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), mFlags);
        }
        catch (PackageManager.NameNotFoundException e)
        {
            throw new RuntimeException("Can't get PackageInfo of own package", e);
        }
    }
}
