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

package org.dmfs.android.bolts.box.utils;

import org.dmfs.android.bolts.box.Key;

import java.util.Date;


/**
 * {@link Key} that takes a {@link String} to use for the name.
 *
 * @author Gabor Keszthelyi
 */
public final class StringKey<V> implements Key<V>
{
    private final String mName;

    private static Key<Date> d = () -> "hehe";


    public StringKey(String name)
    {
        mName = name;
    }


    @Override
    public String name()
    {
        return mName;
    }

}
