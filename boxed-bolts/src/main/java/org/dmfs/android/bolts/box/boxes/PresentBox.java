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
import org.dmfs.optional.Optional;
import org.dmfs.optional.Present;


/**
 * {@link Box} for an present {@link Optional} value.
 *
 * @author Gabor Keszthelyi
 */
public final class PresentBox<T> implements Box<Optional<T>>
{
    private final Box<T> mBox;


    public PresentBox(Box<T> box)
    {
        mBox = box;
    }


    @Override
    public Optional<T> value()
    {
        return new Present<>(mBox.value());
    }


    @Override
    public int describeContents()
    {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeParcelable(mBox, flags);
    }


    public static final Parcelable.Creator<PresentBox> CREATOR = new Parcelable.Creator<PresentBox>()
    {
        @Override
        public PresentBox createFromParcel(Parcel in)
        {
            return new PresentBox(in.readParcelable(getClass().getClassLoader()));
        }


        @Override
        public PresentBox[] newArray(int size)
        {
            return new PresentBox[size];
        }
    };
}
