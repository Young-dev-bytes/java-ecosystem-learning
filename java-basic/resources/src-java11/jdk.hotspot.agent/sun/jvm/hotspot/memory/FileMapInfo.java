/*
 * Copyright (c) 2018, 2022, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package sun.jvm.hotspot.memory;

import java.util.*;
import sun.jvm.hotspot.debugger.Address;
import sun.jvm.hotspot.runtime.VM;
import sun.jvm.hotspot.runtime.VMObject;
import sun.jvm.hotspot.runtime.VMObjectFactory;
import sun.jvm.hotspot.types.*;

public class FileMapInfo {
  private static FileMapHeader header;
  private static Address headerValue;

  // Fields for class FileMapHeader
  private static Address mdSpaceValue;
  private static Address mdRegionBaseAddress;
  private static Address mdRegionEndAddress;

  // HashMap created by mapping the vTable addresses in the md region with
  // the corresponding metadata type.
  private static Map<Address, Type> vTableTypeMap;

  private static Type metadataTypeArray[];

  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static void initialize(TypeDataBase db) {
    // FileMapInfo
    Type type = db.lookupType("FileMapInfo");
    AddressField currentInfoField = type.getAddressField("_current_info");
    long headerFieldOffset = type.getField("_header").getOffset();
    Address headerAddress = currentInfoField.getValue().addOffsetTo(headerFieldOffset);
    headerValue = headerAddress.getAddressAt(0);

    // FileMapHeader
    type = db.lookupType("FileMapInfo::FileMapHeader");
    AddressField spaceField = type.getAddressField("_space[0]");
    Address spaceValue = headerValue.addOffsetTo(type.getField("_space[0]").getOffset());
    mdSpaceValue = spaceValue.addOffsetTo(3 * spaceField.getSize());

    // SpaceInfo
    type = db.lookupType("FileMapInfo::FileMapHeader::space_info");
    long mdRegionBaseAddressOffset = type.getField("_mapped_base").getOffset();
    mdRegionBaseAddress = (mdSpaceValue.addOffsetTo(mdRegionBaseAddressOffset)).getAddressAt(0);
    long mdRegionSizeOffset = type.getField("_used").getOffset();
    long mdRegionSize = (mdSpaceValue.addOffsetTo(mdRegionSizeOffset)).getAddressAt(0).asLongValue();
    mdRegionEndAddress = mdRegionBaseAddress.addOffsetTo(mdRegionSize);

    populateMetadataTypeArray(db);
  }

  private static void populateMetadataTypeArray(TypeDataBase db) {
    metadataTypeArray = new Type[8];

    metadataTypeArray[0] = db.lookupType("ConstantPool");
    metadataTypeArray[1] = db.lookupType("InstanceKlass");
    metadataTypeArray[2] = db.lookupType("InstanceClassLoaderKlass");
    metadataTypeArray[3] = db.lookupType("InstanceMirrorKlass");
    metadataTypeArray[4] = db.lookupType("InstanceRefKlass");
    metadataTypeArray[5] = db.lookupType("Method");
    metadataTypeArray[6] = db.lookupType("ObjArrayKlass");
    metadataTypeArray[7] = db.lookupType("TypeArrayKlass");
  }

  public FileMapHeader getHeader() {
    if (header == null) {
      header = (FileMapHeader) VMObjectFactory.newObject(FileMapInfo.FileMapHeader.class, headerValue);
    }
    return header;
  }

  public boolean inCopiedVtableSpace(Address vptrAddress) {
    FileMapHeader fmHeader = getHeader();
    return fmHeader.inCopiedVtableSpace(vptrAddress);
  }

  public Type getTypeForVptrAddress(Address vptrAddress) {
    if (vTableTypeMap == null) {
      getHeader().createVtableTypeMapping();
    }
    return vTableTypeMap.get(vptrAddress);
  }


  //------------------------------------------------------------------------------------------

  public static class FileMapHeader extends VMObject {

    public FileMapHeader(Address addr) {
      super(addr);
    }

    public boolean inCopiedVtableSpace(Address vptrAddress) {
      if (vptrAddress.greaterThan(mdRegionBaseAddress) &&
          vptrAddress.lessThanOrEqual(mdRegionEndAddress)) {
        return true;
      }
      return false;
    }

    public void createVtableTypeMapping() {
      vTableTypeMap = new HashMap<Address, Type>();
      long metadataVTableSize = 0;
      long addressSize = VM.getVM().getAddressSize();

      Address copiedVtableAddress = mdRegionBaseAddress;
      for (int i=0; i < metadataTypeArray.length; i++) {
        // The first entry denotes the vtable size.
        metadataVTableSize = copiedVtableAddress.getAddressAt(0).asLongValue();
        vTableTypeMap.put(copiedVtableAddress.addOffsetTo(addressSize), metadataTypeArray[i]);

        // The '+ 1' below is to skip the entry containing the size of this metadata's vtable.
        copiedVtableAddress =
          copiedVtableAddress.addOffsetTo((metadataVTableSize + 1) * addressSize);
      }
    }
  }
}
