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


/**
 * {@link Box} for {@link Integer}.
 *
 * @author Gabor Keszthelyi
 */
public final class IntBox implements Box<Integer>
{
    private final int mValue;


    public IntBox(int value)
    {
        mValue = value;
    }


    @Override
    public Integer value()
    {
        return mValue;
    }


    @Override
    public int describeContents()
    {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(mValue);
    }


    public static final Parcelable.Creator<IntBox> CREATOR = new Parcelable.Creator<IntBox>()
    {
        @Override
        public IntBox createFromParcel(Parcel in)
        {
            return new IntBox(in.readInt());
        }


        @Override
        public IntBox[] newArray(int size)
        {
            return new IntBox[size];
        }
    };
}
