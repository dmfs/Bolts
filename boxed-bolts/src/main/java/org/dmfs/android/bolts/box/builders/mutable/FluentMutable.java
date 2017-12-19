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

package org.dmfs.android.bolts.box.builders.mutable;

import org.dmfs.android.bolts.box.Box;
import org.dmfs.android.bolts.box.FluentBuilder;
import org.dmfs.android.bolts.box.Key;


/**
 * Interface for objects holding a mutable container to which parameters can be add using {@link Key} and {@link Box}.
 * <p>
 * Note: use the immutable {@link FluentBuilder} instead whenever possible. This is for cases when the framework gives an instance of the
 * container for manipulation and uses the same instance further.
 *
 * @author Gabor Keszthelyi
 */
public interface FluentMutable
{
    /**
     * Adds the given parameter to the underlying parametrized object.
     *
     * @param key
     *         the key for this parameter
     * @param box
     *         the box 'containing' the value
     * @param <T>
     *         type of the value
     *
     * @return this object to allow chaining
     */
    <T> FluentMutable put(Key<T> key, Box<T> box);

}
