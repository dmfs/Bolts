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

package org.dmfs.android.bolts.box.arguments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import org.dmfs.android.bolts.box.Key;
import org.dmfs.android.bolts.box.utils.NestedBundle;
import org.dmfs.jems.single.decorators.DelegatingSingle;
import org.dmfs.optional.NullSafe;


/**
 * Represents an argument in a parameterizable container ({@link Bundle} or {@link Intent} practically).
 *
 * @author Gabor Keszthelyi
 */
public final class Argument<T> extends DelegatingSingle<T>
{

    public Argument(Key<T> key, Bundle bundle)
    {
        super(() -> new OptionalArgument<>(key, bundle).value());
    }


    public Argument(Key<T> key, Intent intent)
    {
        this(key, new NestedBundle(intent).value());
    }


    public Argument(Key<T> key, Activity activity)
    {
        this(key, activity.getIntent());
    }


    public Argument(Key<T> key, Fragment fragment)
    {
        this(key, new NullSafe<>(fragment.getArguments()).value(Bundle.EMPTY));
    }
}
