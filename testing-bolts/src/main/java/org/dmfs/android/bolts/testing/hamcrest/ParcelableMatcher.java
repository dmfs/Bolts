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

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import static org.hamcrest.CoreMatchers.equalTo;


/**
 * A Matcher to test {@link Parcelable}s.
 * <p>
 * Example usage:
 *
 * <pre>{@code
 * assertThat(new Account("x", "y"), is(parcelable(equalTo(new Account("x", "y")))));
 * }</pre>
 *
 * @author Marten Gajda
 */
public final class ParcelableMatcher<T extends Parcelable> extends TypeSafeDiagnosingMatcher<T>
{
    private final Matcher<? super T> mDelegate;


    public static <T extends Parcelable> Matcher<T> parcelable(T delegate)
    {
        return parcelable(equalTo(delegate));
    }


    public static <T extends Parcelable> Matcher<T> parcelable(Matcher<? super T> delegate)
    {
        return new ParcelableMatcher<>(delegate);
    }


    public ParcelableMatcher(Matcher<? super T> delegate)
    {
        this.mDelegate = delegate;
    }


    @Override
    protected boolean matchesSafely(T item, Description mismatchDescription)
    {
        Parcel parcel = Parcel.obtain();
        try
        {
            parcel.writeParcelable(item, 0);
            int pos = parcel.dataPosition();
            parcel.setDataPosition(0);

            T result = parcel.readParcelable(item.getClass().getClassLoader());
            if (pos != parcel.dataPosition())
            {
                mismatchDescription.appendText("Parcelable ");
                mismatchDescription.appendText(item.getClass().getSimpleName());
                mismatchDescription.appendText(" wrote ").appendText(String.valueOf(pos)).appendText(" bytes");
                mismatchDescription.appendText(" but read ").appendText(String.valueOf(parcel.dataPosition())).appendText(" bytes");
                return false;
            }
            if (!mDelegate.matches(result))
            {
                mismatchDescription.appendText("unparcelled to ");
                mDelegate.describeMismatch(item, mismatchDescription);
                return false;
            }
            return true;
        }
        finally
        {
            parcel.recycle();
        }
    }


    @Override
    public void describeTo(Description description)
    {
        description.appendText("parcels and unparcels to ").appendDescriptionOf(mDelegate);
    }

}
