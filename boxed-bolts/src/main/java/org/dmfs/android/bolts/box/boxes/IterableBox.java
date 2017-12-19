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
import org.dmfs.jems.iterable.decorators.Mapped;

import java.util.LinkedList;
import java.util.List;


/**
 * {@link Box} for {@link Iterable}.
 *
 * @author Gabor Keszthelyi
 */
public final class IterableBox<T> implements Box<Iterable<T>>
{
    private final Iterable<Box<T>> mBoxIterable;


    public IterableBox(Iterable<T> iterable, Function<T, Box<T>> boxFactoryFunction)
    {
        this(new Mapped<>(boxFactoryFunction, iterable));
    }


    public IterableBox(Iterable<Box<T>> boxIterable)
    {
        mBoxIterable = boxIterable;
    }


    @Override
    public Iterable<T> value()
    {
        return new Mapped<>(Box::value, mBoxIterable);
    }


    @Override
    public int describeContents()
    {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        List<Box<T>> boxList = new LinkedList<>();
        for (Box<T> box : mBoxIterable)
        {
            boxList.add(box);
        }
        dest.writeList(boxList);
    }


    public static final Parcelable.Creator<IterableBox> CREATOR = new Parcelable.Creator<IterableBox>()
    {
        @Override
        public IterableBox createFromParcel(Parcel in)
        {
            List<Box> list = new LinkedList<>();
            in.readList(list, getClass().getClassLoader());
            return new IterableBox(list);
        }


        @Override
        public IterableBox[] newArray(int size)
        {
            return new IterableBox[size];
        }
    };
}
