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

import org.dmfs.android.bolts.box.Box;


/**
 * {@link Box} for {@link Float}.
 *
 * @author Gabor Keszthelyi
 */
public final class FloatBox implements Box<Float>
{
    private final float mValue;


    public FloatBox(float value)
    {
        mValue = value;
    }


    @Override
    public Float value()
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
        dest.writeFloat(mValue);
    }


    public static final Creator<FloatBox> CREATOR = new Creator<FloatBox>()
    {
        @Override
        public FloatBox createFromParcel(Parcel in)
        {
            return new FloatBox(in.readFloat());
        }


        @Override
        public FloatBox[] newArray(int size)
        {
            return new FloatBox[size];
        }
    };
}
