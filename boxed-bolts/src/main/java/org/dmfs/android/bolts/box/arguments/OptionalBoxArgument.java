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

import android.content.Intent;
import android.os.Bundle;

import org.dmfs.android.bolts.box.Box;
import org.dmfs.android.bolts.box.Key;
import org.dmfs.android.bolts.box.utils.NestedBundle;
import org.dmfs.optional.Optional;

import java.util.NoSuchElementException;


/**
 * Represent an optionally present {@link Box} argument in a {@link Bundle}.
 *
 * @author Gabor Keszthelyi
 */
public final class OptionalBoxArgument<T> implements Optional<Box<T>>
{
    private final Key<T> mKey;
    private final Bundle mBundle;


    public OptionalBoxArgument(Key<T> key, Bundle bundle)
    {
        mKey = key;
        mBundle = bundle;
    }


    public OptionalBoxArgument(Key<T> key, Intent intent)
    {
        this(key, new NestedBundle(intent).value());
    }


    @Override
    public boolean isPresent()
    {
        return mBundle.getParcelable(mKey.name()) != null;
    }


    @Override
    public Box<T> value(Box<T> defaultValue)
    {
        Box<T> box = mBundle.getParcelable(mKey.name());
        return box != null ? box : defaultValue;
    }


    @Override
    public Box<T> value() throws NoSuchElementException
    {
        Box<T> box = mBundle.getParcelable(mKey.name());
        if (box == null)
        {
            throw new NoSuchElementException(String.format("No Box found with key '%s' in Bundle", mKey.name()));
        }
        return box;
    }
}
