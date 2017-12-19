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

import android.content.Intent;
import android.os.Bundle;

import org.dmfs.jems.single.Single;
import org.dmfs.jems.single.decorators.DelegatingSingle;
import org.dmfs.optional.NullSafe;


/**
 * {@link Single} for accessing a {@link Intent}s nested {@link Bundle} if it's present, or to a new empty {@link Bundle} if it's not.
 * <p>
 * (Note: Nested {@link Bundle} are used inside {@link Intent}s as a practice to avoid crashes when the system would try to
 * unparcel custom classes outside of the app.)
 *
 * @author Gabor Keszthelyi
 */
public final class NestedBundle extends DelegatingSingle<Bundle>
{
    /**
     * The key for the nested Bundle Intent extra.
     */
    public static final String NESTED_BUNDLE_KEY = "intent.extra.NESTED_BUNDLE";


    public NestedBundle(Intent intent)
    {
        super(() -> new NullSafe<>(intent.getBundleExtra(NESTED_BUNDLE_KEY)).value(new Bundle()));
    }
}
