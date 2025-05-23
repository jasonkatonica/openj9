/*[INCLUDE-IF SharedClasses]*/
package com.ibm.oti.shared;

import java.util.WeakHashMap;

/*
 * Copyright IBM Corp. and others 1998
 *
 * This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License 2.0 which accompanies this
 * distribution and is available at https://www.eclipse.org/legal/epl-2.0/
 * or the Apache License, Version 2.0 which accompanies this distribution and
 * is available at https://www.apache.org/licenses/LICENSE-2.0.
 *
 * This Source Code may also be made available under the following
 * Secondary Licenses when the conditions for such availability set
 * forth in the Eclipse Public License, v. 2.0 are satisfied: GNU
 * General Public License, version 2 with the GNU Classpath
 * Exception [1] and GNU General Public License, version 2 with the
 * OpenJDK Assembly Exception [2].
 *
 * [1] https://www.gnu.org/software/classpath/license.html
 * [2] https://openjdk.org/legal/assembly-exception.html
 *
 * SPDX-License-Identifier: EPL-2.0 OR Apache-2.0 OR GPL-2.0-only WITH Classpath-exception-2.0 OR GPL-2.0-only WITH OpenJDK-assembly-exception-1.0
 */

/**
 * Implementation of SharedDataHelperFactory.
 * <p>
 * @see SharedClassHelperFactory
 * @see SharedAbstractHelperFactory
 */
final class SharedDataHelperFactoryImpl extends SharedAbstractHelperFactory implements SharedDataHelperFactory {

	private static final WeakHashMap<ClassLoader, SharedHelper> helpers;

	static {
		helpers = new WeakHashMap<>();
	}

	@Override
	public SharedDataHelper getDataHelper(ClassLoader owner) {
		if (owner==null) {
			return null;
		}
		synchronized(helpers) {
			SharedHelper helper = helpers.get(owner);

			if (helper != null) {
				if (helper instanceof SharedDataHelper) {
					return (SharedDataHelper)helper;
				}
			} else {
				/*[IF JAVA_SPEC_VERSION >= 24]*/
				SharedDataHelper result = new SharedDataHelperImpl(owner, getNewID());
				helpers.put(owner, result);
				return result;
				/*[ELSE] JAVA_SPEC_VERSION >= 24 */
				boolean canFind = canFind(owner);
				boolean canStore = canStore(owner);

				if (canFind || canStore) {
					SharedDataHelper result = new SharedDataHelperImpl(owner, getNewID(), canFind, canStore);
					helpers.put(owner, result);
					return result;
				}
				/*[ENDIF] JAVA_SPEC_VERSION >= 24 */
			}
		}
		return null;
	}
}
