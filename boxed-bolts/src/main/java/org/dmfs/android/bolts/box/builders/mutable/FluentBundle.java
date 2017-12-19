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

package org.dmfs.android.bolts.box.builders.mutable;

import android.app.Activity;
import android.os.Bundle;

import org.dmfs.android.bolts.box.Box;
import org.dmfs.android.bolts.box.Key;
import org.dmfs.android.bolts.box.builders.BundleBuilder;


/**
 * {@link FluentMutable} for updating a {@link Bundle}.
 * <p>
 * Note: use {@link BundleBuilder} whenever possible. This class if for the case, for example in {@link Activity#onSaveInstanceState(Bundle)},
 * where we are to update an existing instance of {@link Bundle} which is then used further by the framework.
 *
 * @author Gabor Keszthelyi
 */
public final class FluentBundle implements FluentMutable
{
    private final Bundle mBundle;


    public FluentBundle(Bundle bundle)
    {
        mBundle = bundle;
    }


    @Override
    public <T> FluentMutable put(Key<T> key, Box<T> box)
    {
        mBundle.putParcelable(key.name(), box);
        return this;
    }
}
