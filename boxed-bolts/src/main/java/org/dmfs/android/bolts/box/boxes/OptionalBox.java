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

package org.dmfs.android.bolts.box.boxes;

import android.os.Parcel;
import android.os.Parcelable;

import org.dmfs.android.bolts.box.Box;
import org.dmfs.jems.function.Function;
import org.dmfs.optional.Optional;


/**
 * {@link Box} for an {@link Optional}.
 *
 * @author Gabor Keszthelyi
 */
public final class OptionalBox<T> implements Box<Optional<T>>
{
    private final Box<Optional<T>> mDelegate;


    public OptionalBox(Optional<T> optValue, Function<T, Box<T>> boxFactoryFunction)
    {
        this(optValue.isPresent() ? new PresentBox<>(boxFactoryFunction.value(optValue.value())) : new AbsentBox<T>());
    }


    private OptionalBox(Box<Optional<T>> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public Optional<T> value()
    {
        return mDelegate.value();
    }


    @Override
    public int describeContents()
    {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeParcelable(mDelegate, flags);
    }


    public static final Parcelable.Creator<Box<Optional>> CREATOR = new Parcelable.Creator<Box<Optional>>()
    {
        @Override
        public Box<Optional> createFromParcel(Parcel in)
        {
            return new OptionalBox(in.readParcelable(getClass().getClassLoader()));
        }


        @Override
        public Box<Optional>[] newArray(int size)
        {
            return new OptionalBox[size];
        }
    };
}
