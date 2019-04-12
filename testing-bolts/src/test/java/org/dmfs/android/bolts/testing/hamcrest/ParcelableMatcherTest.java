/*
 * Copyright 2019 dmfs GmbH
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

package org.dmfs.android.bolts.testing.hamcrest;

import android.os.Parcel;
import android.os.Parcelable;

import org.dmfs.jems.hamcrest.matchers.matcher.MatcherMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.dmfs.android.bolts.testing.hamcrest.ParcelableMatcher.parcelable;
import static org.dmfs.jems.hamcrest.matchers.matcher.MatcherMatcher.matches;
import static org.dmfs.jems.hamcrest.matchers.matcher.MatcherMatcher.mismatches;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;


/**
 * Test {@link ParcelableMatcher}.
 *
 * @author Marten Gajda
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class ParcelableMatcherTest
{
    @Test
    public void test()
    {
        assertThat(parcelable(new TestParcelable("abc")),
                allOf(
                        matches(new TestParcelable("abc")),
                        mismatches(new TestParcelable("xyz"), "unparcelled to was <TestParcelable(xyz)>"),
                        MatcherMatcher.<TestParcelable>describesAs("parcels and unparcels to <TestParcelable(abc)>")));

        assertThat(parcelable(equalTo(new TestParcelable("abc"))),
                allOf(
                        matches(new TestParcelable("abc")),
                        mismatches(new TestParcelable("xyz"), "unparcelled to was <TestParcelable(xyz)>"),
                        MatcherMatcher.<TestParcelable>describesAs("parcels and unparcels to <TestParcelable(abc)>")));

        assertThat(new ParcelableMatcher<>(equalTo(new TestParcelable("abc"))),
                allOf(
                        matches(new TestParcelable("abc")),
                        mismatches(new TestParcelable("xyz"), "unparcelled to was <TestParcelable(xyz)>"),
                        MatcherMatcher.<TestParcelable>describesAs("parcels and unparcels to <TestParcelable(abc)>")));
    }


    public final static class TestParcelable implements Parcelable
    {

        private final String mValue;


        private TestParcelable(String value)
        {
            this.mValue = value;
        }


        @Override
        public int describeContents()
        {
            return 0;
        }


        @Override
        public boolean equals(Object obj)
        {
            return (obj instanceof TestParcelable) && mValue.equals(((TestParcelable) obj).mValue);
        }


        @Override
        public void writeToParcel(Parcel dest, int flags)
        {
            dest.writeString(mValue);
        }


        @Override
        public String toString()
        {
            return "TestParcelable(" + mValue + ")";
        }


        public final static Creator<TestParcelable> CREATOR = new Creator<TestParcelable>()
        {
            @Override
            public TestParcelable createFromParcel(Parcel source)
            {
                return new TestParcelable(source.readString());
            }


            @Override
            public TestParcelable[] newArray(int size)
            {
                return new TestParcelable[size];
            }
        };
    }
}