################################################################################
# Copyright IBM Corp. and others 2017
#
# This program and the accompanying materials are made available under
# the terms of the Eclipse Public License 2.0 which accompanies this
# distribution and is available at https://www.eclipse.org/legal/epl-2.0/
# or the Apache License, Version 2.0 which accompanies this distribution and
# is available at https://www.apache.org/licenses/LICENSE-2.0.
#
# This Source Code may also be made available under the following
# Secondary Licenses when the conditions for such availability set
# forth in the Eclipse Public License, v. 2.0 are satisfied: GNU
# General Public License, version 2 with the GNU Classpath
# Exception [1] and GNU General Public License, version 2 with the
# OpenJDK Assembly Exception [2].
#
# [1] https://www.gnu.org/software/classpath/license.html
# [2] https://openjdk.org/legal/assembly-exception.html
#
# SPDX-License-Identifier: EPL-2.0 OR Apache-2.0 OR GPL-2.0-only WITH Classpath-exception-2.0 OR GPL-2.0-only WITH OpenJDK-assembly-exception-1.0
################################################################################

set(gc_structs_sources
	ArrayletLeafIterator.cpp
	CallSitesIterator.cpp
	ClassArrayClassSlotIterator.cpp
	ClassHeapIterator.cpp
	ClassIterator.cpp
	ClassIteratorClassSlots.cpp
	ClassFCCSlotIterator.cpp
	ClassIteratorDeclarationOrder.cpp
	ClassLoaderClassesIterator.cpp
	ClassLoaderSegmentIterator.cpp
	ClassLocalInterfaceIterator.cpp
	ClassStaticsDeclarationOrderIterator.cpp
	ClassStaticsIterator.cpp
	ClassSuperclassesIterator.cpp
	ConstantDynamicSlotIterator.cpp
	ConstantPoolClassSlotIterator.cpp
	ConstantPoolObjectSlotIterator.cpp
	ContinuationSlotIterator.cpp
	JVMTIObjectTagTableIterator.cpp
	MethodTypesIterator.cpp
	MixedObjectDeclarationOrderIterator.cpp
	MixedObjectIterator.cpp
	FlattenedContiguousArrayIterator.cpp
	PointerArrayIterator.cpp
	SegmentIterator.cpp
	StringTableIncrementalIterator.cpp
	VMClassSlotIterator.cpp
	VMThreadIterator.cpp
	VMThreadJNISlotIterator.cpp
	VMThreadListIterator.cpp
	VMThreadMonitorRecordSlotIterator.cpp
	VMThreadSlotIterator.cpp
	VMThreadStackSlotIterator.cpp
)

j9vm_add_library(j9gcstructs STATIC
	${gc_structs_sources}
)

target_link_libraries(j9gcstructs
	PRIVATE
		j9vm_interface
		j9vm_gc_includes

		omrgc
)

if(OMR_MIXED_REFERENCES_MODE_STATIC)
	j9vm_add_library(j9gcstructs_full STATIC
		${gc_structs_sources}
	)

	target_link_libraries(j9gcstructs_full
		PRIVATE
			j9vm_interface
			j9vm_gc_includes

			omrgc_full
	)
endif()
