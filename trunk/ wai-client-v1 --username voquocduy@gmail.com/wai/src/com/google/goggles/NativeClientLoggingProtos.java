package com.google.goggles;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import com.google.protobuf.Internal;
import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;

public final class NativeClientLoggingProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class NativeClientClick extends GeneratedMessageLite
    implements NativeClientLoggingProtos.NativeClientClickOrBuilder
  {
    public static final int ACTION_POSITION_FIELD_NUMBER = 7;
    public static final int CLICK_TARGET_FIELD_NUMBER = 1;
    public static final int DISPLAY_POSITION_FIELD_NUMBER = 5;
    public static final int NOTIFICATION_RESULTS_COUNT_FIELD_NUMBER = 4;
    public static final int RESULTS_SHOWN_ABOVE_FOLD_FIELD_NUMBER = 3;
    public static final int RESULT_ID_FIELD_NUMBER = 6;
    public static final int RESULT_POSITION_FIELD_NUMBER = 2;
    private static final NativeClientClick defaultInstance = new NativeClientClick(true);
    private static final long serialVersionUID;
    private int actionPosition_;
    private int bitField0_;
    private CLICK_TARGET clickTarget_;
    private int displayPosition_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private int notificationResultsCount_;
    private Object resultId_;
    private int resultPosition_;
    private int resultsShownAboveFold_;

    static
    {
      defaultInstance.initFields();
    }

    private NativeClientClick(Builder paramBuilder)
    {
      super();
    }

    private NativeClientClick(boolean paramBoolean)
    {
    }

    public static NativeClientClick getDefaultInstance()
    {
      return defaultInstance;
    }

    private ByteString getResultIdBytes()
    {
      Object localObject = this.resultId_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.resultId_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private void initFields()
    {
      this.clickTarget_ = CLICK_TARGET.RESULT_CLICK_IN_RESULTS_LIST;
      this.resultPosition_ = 0;
      this.resultsShownAboveFold_ = 0;
      this.notificationResultsCount_ = 0;
      this.displayPosition_ = 0;
      this.resultId_ = "";
      this.actionPosition_ = 0;
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(NativeClientClick paramNativeClientClick)
    {
      return newBuilder().mergeFrom(paramNativeClientClick);
    }

    public static NativeClientClick parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static NativeClientClick parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static NativeClientClick parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static NativeClientClick parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static NativeClientClick parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static NativeClientClick parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static NativeClientClick parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static NativeClientClick parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static NativeClientClick parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static NativeClientClick parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public int getActionPosition()
    {
      return this.actionPosition_;
    }

    public CLICK_TARGET getClickTarget()
    {
      return this.clickTarget_;
    }

    public NativeClientClick getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public int getDisplayPosition()
    {
      return this.displayPosition_;
    }

    public int getNotificationResultsCount()
    {
      return this.notificationResultsCount_;
    }

    public String getResultId()
    {
      Object localObject = this.resultId_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.resultId_ = str;
      return str;
    }

    public int getResultPosition()
    {
      return this.resultPosition_;
    }

    public int getResultsShownAboveFold()
    {
      return this.resultsShownAboveFold_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeEnumSize(1, this.clickTarget_.getNumber());
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeInt32Size(2, this.resultPosition_);
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeInt32Size(3, this.resultsShownAboveFold_);
      if ((0x8 & this.bitField0_) == 8)
        k += CodedOutputStream.computeInt32Size(4, this.notificationResultsCount_);
      if ((0x10 & this.bitField0_) == 16)
        k += CodedOutputStream.computeInt32Size(5, this.displayPosition_);
      if ((0x20 & this.bitField0_) == 32)
        k += CodedOutputStream.computeBytesSize(6, getResultIdBytes());
      if ((0x40 & this.bitField0_) == 64)
        k += CodedOutputStream.computeInt32Size(7, this.actionPosition_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public boolean hasActionPosition()
    {
      return (0x40 & this.bitField0_) == 64;
    }

    public boolean hasClickTarget()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public boolean hasDisplayPosition()
    {
      return (0x10 & this.bitField0_) == 16;
    }

    public boolean hasNotificationResultsCount()
    {
      return (0x8 & this.bitField0_) == 8;
    }

    public boolean hasResultId()
    {
      return (0x20 & this.bitField0_) == 32;
    }

    public boolean hasResultPosition()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasResultsShownAboveFold()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      if (!hasClickTarget())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      this.memoizedIsInitialized = 1;
      return true;
    }

    public Builder newBuilderForType()
    {
      return newBuilder();
    }

    public Builder toBuilder()
    {
      return newBuilder(this);
    }

    protected Object writeReplace()
      throws ObjectStreamException
    {
      return super.writeReplace();
    }

    public void writeTo(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      getSerializedSize();
      if ((0x1 & this.bitField0_) == 1)
        paramCodedOutputStream.writeEnum(1, this.clickTarget_.getNumber());
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeInt32(2, this.resultPosition_);
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeInt32(3, this.resultsShownAboveFold_);
      if ((0x8 & this.bitField0_) == 8)
        paramCodedOutputStream.writeInt32(4, this.notificationResultsCount_);
      if ((0x10 & this.bitField0_) == 16)
        paramCodedOutputStream.writeInt32(5, this.displayPosition_);
      if ((0x20 & this.bitField0_) == 32)
        paramCodedOutputStream.writeBytes(6, getResultIdBytes());
      if ((0x40 & this.bitField0_) == 64)
        paramCodedOutputStream.writeInt32(7, this.actionPosition_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NativeClientLoggingProtos.NativeClientClick, Builder>
      implements NativeClientLoggingProtos.NativeClientClickOrBuilder
    {
      private int actionPosition_;
      private int bitField0_;
      private NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET clickTarget_ = NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.RESULT_CLICK_IN_RESULTS_LIST;
      private int displayPosition_;
      private int notificationResultsCount_;
      private Object resultId_ = "";
      private int resultPosition_;
      private int resultsShownAboveFold_;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private NativeClientLoggingProtos.NativeClientClick buildParsed()
        throws InvalidProtocolBufferException
      {
        NativeClientLoggingProtos.NativeClientClick localNativeClientClick = buildPartial();
        if (!localNativeClientClick.isInitialized())
          throw newUninitializedMessageException(localNativeClientClick).asInvalidProtocolBufferException();
        return localNativeClientClick;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public NativeClientLoggingProtos.NativeClientClick build()
      {
        NativeClientLoggingProtos.NativeClientClick localNativeClientClick = buildPartial();
        if (!localNativeClientClick.isInitialized())
          throw newUninitializedMessageException(localNativeClientClick);
        return localNativeClientClick;
      }

      public NativeClientLoggingProtos.NativeClientClick buildPartial()
      {
        int i = 1;
        NativeClientLoggingProtos.NativeClientClick localNativeClientClick = new NativeClientLoggingProtos.NativeClientClick(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          NativeClientLoggingProtos.NativeClientClick.access$302(localNativeClientClick, this.clickTarget_);
          if ((j & 0x2) == 2)
            i |= 2;
          NativeClientLoggingProtos.NativeClientClick.access$402(localNativeClientClick, this.resultPosition_);
          if ((j & 0x4) == 4)
            i |= 4;
          NativeClientLoggingProtos.NativeClientClick.access$502(localNativeClientClick, this.resultsShownAboveFold_);
          if ((j & 0x8) == 8)
            i |= 8;
          NativeClientLoggingProtos.NativeClientClick.access$602(localNativeClientClick, this.notificationResultsCount_);
          if ((j & 0x10) == 16)
            i |= 16;
          NativeClientLoggingProtos.NativeClientClick.access$702(localNativeClientClick, this.displayPosition_);
          if ((j & 0x20) == 32)
            i |= 32;
          NativeClientLoggingProtos.NativeClientClick.access$802(localNativeClientClick, this.resultId_);
          if ((j & 0x40) == 64)
            i |= 64;
          NativeClientLoggingProtos.NativeClientClick.access$902(localNativeClientClick, this.actionPosition_);
          NativeClientLoggingProtos.NativeClientClick.access$1002(localNativeClientClick, i);
          return localNativeClientClick;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.clickTarget_ = NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.RESULT_CLICK_IN_RESULTS_LIST;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.resultPosition_ = 0;
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.resultsShownAboveFold_ = 0;
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.notificationResultsCount_ = 0;
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.displayPosition_ = 0;
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        this.resultId_ = "";
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        this.actionPosition_ = 0;
        this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
        return this;
      }

      public Builder clearActionPosition()
      {
        this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
        this.actionPosition_ = 0;
        return this;
      }

      public Builder clearClickTarget()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.clickTarget_ = NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.RESULT_CLICK_IN_RESULTS_LIST;
        return this;
      }

      public Builder clearDisplayPosition()
      {
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        this.displayPosition_ = 0;
        return this;
      }

      public Builder clearNotificationResultsCount()
      {
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.notificationResultsCount_ = 0;
        return this;
      }

      public Builder clearResultId()
      {
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        this.resultId_ = NativeClientLoggingProtos.NativeClientClick.getDefaultInstance().getResultId();
        return this;
      }

      public Builder clearResultPosition()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.resultPosition_ = 0;
        return this;
      }

      public Builder clearResultsShownAboveFold()
      {
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.resultsShownAboveFold_ = 0;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public int getActionPosition()
      {
        return this.actionPosition_;
      }

      public NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET getClickTarget()
      {
        return this.clickTarget_;
      }

      public NativeClientLoggingProtos.NativeClientClick getDefaultInstanceForType()
      {
        return NativeClientLoggingProtos.NativeClientClick.getDefaultInstance();
      }

      public int getDisplayPosition()
      {
        return this.displayPosition_;
      }

      public int getNotificationResultsCount()
      {
        return this.notificationResultsCount_;
      }

      public String getResultId()
      {
        Object localObject = this.resultId_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.resultId_ = str;
          return str;
        }
        return (String)localObject;
      }

      public int getResultPosition()
      {
        return this.resultPosition_;
      }

      public int getResultsShownAboveFold()
      {
        return this.resultsShownAboveFold_;
      }

      public boolean hasActionPosition()
      {
        return (0x40 & this.bitField0_) == 64;
      }

      public boolean hasClickTarget()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public boolean hasDisplayPosition()
      {
        return (0x10 & this.bitField0_) == 16;
      }

      public boolean hasNotificationResultsCount()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public boolean hasResultId()
      {
        return (0x20 & this.bitField0_) == 32;
      }

      public boolean hasResultPosition()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasResultsShownAboveFold()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public final boolean isInitialized()
      {
        return hasClickTarget();
      }

      public Builder mergeFrom(NativeClientLoggingProtos.NativeClientClick paramNativeClientClick)
      {
        if (paramNativeClientClick == NativeClientLoggingProtos.NativeClientClick.getDefaultInstance());
        do
        {
          return this;
          if (paramNativeClientClick.hasClickTarget())
            setClickTarget(paramNativeClientClick.getClickTarget());
          if (paramNativeClientClick.hasResultPosition())
            setResultPosition(paramNativeClientClick.getResultPosition());
          if (paramNativeClientClick.hasResultsShownAboveFold())
            setResultsShownAboveFold(paramNativeClientClick.getResultsShownAboveFold());
          if (paramNativeClientClick.hasNotificationResultsCount())
            setNotificationResultsCount(paramNativeClientClick.getNotificationResultsCount());
          if (paramNativeClientClick.hasDisplayPosition())
            setDisplayPosition(paramNativeClientClick.getDisplayPosition());
          if (paramNativeClientClick.hasResultId())
            setResultId(paramNativeClientClick.getResultId());
        }
        while (!paramNativeClientClick.hasActionPosition());
        setActionPosition(paramNativeClientClick.getActionPosition());
        return this;
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        while (true)
        {
          int i = paramCodedInputStream.readTag();
          switch (i)
          {
          default:
            if (parseUnknownField(paramCodedInputStream, paramExtensionRegistryLite, i))
              continue;
          case 0:
            return this;
          case 8:
            NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET localCLICK_TARGET = NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.valueOf(paramCodedInputStream.readEnum());
            if (localCLICK_TARGET == null)
              continue;
            this.bitField0_ = (0x1 | this.bitField0_);
            this.clickTarget_ = localCLICK_TARGET;
            break;
          case 16:
            this.bitField0_ = (0x2 | this.bitField0_);
            this.resultPosition_ = paramCodedInputStream.readInt32();
            break;
          case 24:
            this.bitField0_ = (0x4 | this.bitField0_);
            this.resultsShownAboveFold_ = paramCodedInputStream.readInt32();
            break;
          case 32:
            this.bitField0_ = (0x8 | this.bitField0_);
            this.notificationResultsCount_ = paramCodedInputStream.readInt32();
            break;
          case 40:
            this.bitField0_ = (0x10 | this.bitField0_);
            this.displayPosition_ = paramCodedInputStream.readInt32();
            break;
          case 50:
            this.bitField0_ = (0x20 | this.bitField0_);
            this.resultId_ = paramCodedInputStream.readBytes();
            break;
          case 56:
          }
          this.bitField0_ = (0x40 | this.bitField0_);
          this.actionPosition_ = paramCodedInputStream.readInt32();
        }
      }

      public Builder setActionPosition(int paramInt)
      {
        this.bitField0_ = (0x40 | this.bitField0_);
        this.actionPosition_ = paramInt;
        return this;
      }

      public Builder setClickTarget(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET paramCLICK_TARGET)
      {
        if (paramCLICK_TARGET == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.clickTarget_ = paramCLICK_TARGET;
        return this;
      }

      public Builder setDisplayPosition(int paramInt)
      {
        this.bitField0_ = (0x10 | this.bitField0_);
        this.displayPosition_ = paramInt;
        return this;
      }

      public Builder setNotificationResultsCount(int paramInt)
      {
        this.bitField0_ = (0x8 | this.bitField0_);
        this.notificationResultsCount_ = paramInt;
        return this;
      }

      public Builder setResultId(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x20 | this.bitField0_);
        this.resultId_ = paramString;
        return this;
      }

      void setResultId(ByteString paramByteString)
      {
        this.bitField0_ = (0x20 | this.bitField0_);
        this.resultId_ = paramByteString;
      }

      public Builder setResultPosition(int paramInt)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.resultPosition_ = paramInt;
        return this;
      }

      public Builder setResultsShownAboveFold(int paramInt)
      {
        this.bitField0_ = (0x4 | this.bitField0_);
        this.resultsShownAboveFold_ = paramInt;
        return this;
      }
    }

    public static enum CLICK_TARGET
      implements Internal.EnumLite
    {
      public static final int ADDED_PROMPTED_TEXT_RESTRICT_SKIP_VALUE = 36;
      public static final int ADDED_PROMPTED_TEXT_RESTRICT_SPEECH_VALUE = 32;
      public static final int ADDED_PROMPTED_TEXT_RESTRICT_SUGGESTION_VALUE = 35;
      public static final int ADDED_PROMPTED_TEXT_RESTRICT_TYPE_AUTOCOMPLETED_VALUE = 34;
      public static final int ADDED_PROMPTED_TEXT_RESTRICT_TYPE_VALUE = 33;
      public static final int ADDED_TEXT_RESTRICT_SPEECH_VALUE = 30;
      public static final int ADDED_TEXT_RESTRICT_TYPE_VALUE = 31;
      public static final int ADJUST_PUGGLE_CROP_VALUE = 29;
      public static final int BACK_BUTTON_FROM_CAMERA_PREVIEW_VALUE = 10;
      public static final int BACK_BUTTON_FROM_HISTORY_PAGE_VALUE = 17;
      public static final int BACK_BUTTON_FROM_PUGGLE_RESULT_VALUE = 43;
      public static final int BACK_BUTTON_FROM_RESULTS_LIST_PAGE_VALUE = 3;
      public static final int BACK_BUTTON_FROM_RESULT_PAGE_VALUE = 4;
      public static final int BOUNDING_BOX_CLICK_ON_RESULTS_PAGE_VALUE = 1;
      public static final int CAMERA_PREVIEW_SHOWN_VALUE = 14;
      public static final int CANCEL_FROM_SEARCH_ERROR_VALUE = 12;
      public static final int CONTINUOUS_ALL_RESULTS_RESULT_ITEM_TAP_VALUE = 82;
      public static final int CONTINUOUS_AUTOMATED_PAUSE_VALUE = 87;
      public static final int CONTINUOUS_CLICK_TO_PAUSE_VALUE = 86;
      public static final int CONTINUOUS_TIMELINE_SWIPE_VALUE = 80;
      public static final int CONTINUOUS_TIMELINE_THUMBNAIL_TAP_VALUE = 79;
      public static final int CONTINUOUS_VIEW_ALL_RESULTS_VALUE = 81;
      public static final int DESCRIBE_BACK_VALUE = 70;
      public static final int DESCRIBE_CANCEL_VALUE = 69;
      public static final int DESCRIBE_SUBMIT_VALUE = 68;
      public static final int DRAWER_CLOSED_VALUE = 26;
      public static final int DRAWER_OPENED_VALUE = 24;
      public static final int DRAWER_SEMI_VALUE = 25;
      public static final int ENTER_PUGGLE_MODE_VALUE = 28;
      public static final int EXECUTE_SAVED_QUERY_VALUE = 20;
      public static final int EYECANDY_RESULT_CLICK_VALUE = 7;
      public static final int FLASH_TOGGLE_VALUE = 22;
      public static final int HARDWARE_BACK_BUTTON_VALUE = 6;
      public static final int HISTORY_LIST_MODE_CLICK_VALUE = 74;
      public static final int HISTORY_MAP_MODE_CLICK_VALUE = 73;
      public static final int HISTORY_SEARCH_BUTTON_CLICK_VALUE = 75;
      public static final int HISTORY_SWIPE_TO_ALL_RESULTS_VALUE = 72;
      public static final int HISTORY_SWIPE_TO_SUBMITTED_RESULTS_VALUE = 71;
      public static final int HOME_SCREEN_CONTINUOUS_VALUE = 56;
      public static final int HOME_SCREEN_HISTORY_VALUE = 58;
      public static final int HOME_SCREEN_LOAD_IMAGE_VALUE = 57;
      public static final int HOME_SCREEN_SNAPSHOT_VALUE = 55;
      public static final int MORE_LIKE_THIS_FROM_EXPANDED_RESULTS_VALUE = 97;
      public static final int MORE_LIKE_THIS_LONG_PRESS_VALUE = 96;
      public static final int NEW_SEARCH_BUTTON_FROM_RESULT_PAGE_VALUE = 5;
      public static final int PROMOTION_PENDING_SEARCHES_VALUE = 61;
      public static final int PROMOTION_SEARCH_FROM_CAMERA_VALUE = 62;
      public static final int PROMOTION_TIP_VALUE = 63;
      public static final int PUGGLE_CATEGORY_CLICK_IN_FAIL_PAGE_VALUE = 88;
      public static final int PUGGLE_CATEGORY_CLICK_IN_RESULTS_PAGE_VALUE = 89;
      public static final int PUGGLE_CLICK_GRID_VIEW_VALUE = 38;
      public static final int PUGGLE_CLICK_LIST_VIEW_VALUE = 37;
      public static final int PUGGLE_CLICK_NEW_SEARCH_VALUE = 95;
      public static final int PUGGLE_EXTERNAL_CLICK_IN_RESULT_VIEW_VALUE = 42;
      public static final int PUGGLE_REFINE_BY_BRAND_VALUE = 91;
      public static final int PUGGLE_REFINE_BY_CATEGORY_VALUE = 90;
      public static final int PUGGLE_REFINE_BY_GENDER_VALUE = 92;
      public static final int PUGGLE_REFINE_QUERY_SPEECH_VALUE = 39;
      public static final int PUGGLE_REFINE_QUERY_TYPE_VALUE = 40;
      public static final int PUGGLE_RESULT_CLICK_IN_RESULT_LIST_VALUE = 41;
      public static final int PUGGLE_SORT_BY_HIGH_PRICE_VALUE = 94;
      public static final int PUGGLE_SORT_BY_LOW_PRICE_VALUE = 93;
      public static final int PUGGLE_SORT_BY_RELEVANCE_VALUE = 100;
      public static final int RATINGS_CLOSED_ON_RESULTS_LIST_PAGE_VALUE = 9;
      public static final int RATINGS_OPENED_ON_RESULTS_LIST_PAGE_VALUE = 8;
      public static final int RESULT_CLICK_IN_RESULTS_LIST_VALUE = 0;
      public static final int RESULT_CLICK_VIA_SCROLL_BALL_ON_RESULTS_PAGE_VALUE = 2;
      public static final int RESULT_DISMBIGUATION_ACTION_VALUE = 84;
      public static final int RESULT_EXPANDED_RESULT_ACTION_VALUE = 85;
      public static final int RESULT_HTML_CACHED_VALUE = 18;
      public static final int RESULT_SHOW_EXPANDED_RESULT_VALUE = 83;
      public static final int RETRY_FROM_SEARCH_ERROR_VALUE = 13;
      public static final int RETURN_TO_HOME_SCREEN_BACK_BUTTON_VALUE = 59;
      public static final int RETURN_TO_HOME_SCREEN_MODE_ICON_VALUE = 60;
      public static final int ROI_TOGGLE_VALUE = 23;
      public static final int SAVE_FOR_LATER_FROM_SEARCH_ERROR_VALUE = 21;
      public static final int SAVE_TO_GALLERY_DISABLED_VALUE = 48;
      public static final int SAVE_TO_GALLERY_ENABLED_VALUE = 47;
      public static final int SEARCH_ERROR_SHOWN_VALUE = 11;
      public static final int SEARCH_FROM_CAMERA_DISABLED_VALUE = 50;
      public static final int SEARCH_FROM_CAMERA_ENABLED_VALUE = 49;
      public static final int SEARCH_FROM_CAMERA_GOGGLES_NOTIFICATION_VALUE = 44;
      public static final int SEARCH_FROM_CAMERA_LEARN_MORE_VALUE = 104;
      public static final int SEARCH_FROM_CAMERA_MOBILE_NETWORKS_DISABLED_VALUE = 52;
      public static final int SEARCH_FROM_CAMERA_MOBILE_NETWORKS_ENABLED_VALUE = 51;
      public static final int SEARCH_FROM_CAMERA_MOBILE_WIFI_VALUE = 105;
      public static final int SEARCH_FROM_CAMERA_NEXT_VALUE = 107;
      public static final int SEARCH_FROM_CAMERA_OFF_VALUE = 109;
      public static final int SEARCH_FROM_CAMERA_ON_VALUE = 108;
      public static final int SEARCH_FROM_CAMERA_ROAMING_DISABLED_VALUE = 54;
      public static final int SEARCH_FROM_CAMERA_ROAMING_ENABLED_VALUE = 53;
      public static final int SEARCH_FROM_CAMERA_WIFI_ONLY_VALUE = 106;
      public static final int SEARCH_HISTORY_DISABLED_VALUE = 46;
      public static final int SEARCH_HISTORY_ENABLED_VALUE = 45;
      public static final int SEARCH_HISTORY_LEARN_MORE_VALUE = 113;
      public static final int SEARCH_HISTORY_NEXT_VALUE = 112;
      public static final int SEARCH_HISTORY_OFF_VALUE = 111;
      public static final int SEARCH_HISTORY_ON_VALUE = 110;
      public static final int SHARE_RESULT_VALUE = 98;
      public static final int SHOW_FULL_SIMILAR_IMAGE_VALUE = 27;
      public static final int SIMILAR_ITEM_FROM_EXPANDED_RESULTS_VALUE = 101;
      public static final int SUGGEST_A_RESULT_BUTTON_CLICK_VALUE = 64;
      public static final int TAG_IMAGE_BACK_VALUE = 67;
      public static final int TAG_IMAGE_CANCEL_VALUE = 66;
      public static final int TAG_IMAGE_CONTINUE_VALUE = 65;
      public static final int TAP_OUTSIDE_BOUNDING_BOX_HIDE_RESULTS_LIST_VALUE = 15;
      public static final int TAP_OUTSIDE_BOUNDING_BOX_SHOW_RESULTS_LIST_VALUE = 16;
      public static final int TAP_SAVE_FOR_LATER_VALUE = 19;
      public static final int TIPS_LETS_START_CLICK_VALUE = 76;
      public static final int TIPS_PUGGLE_CLICK_VALUE = 99;
      public static final int TIPS_SEARCH_FROM_CAMERA_CLICK_VALUE = 78;
      public static final int TIPS_TRANSLATE_CLICK_VALUE = 77;
      public static final int WELCOME_LEARN_MORE_VALUE = 102;
      public static final int WELCOME_NEXT_VALUE = 103;
      private static Internal.EnumLiteMap<CLICK_TARGET> internalValueMap = new Internal.EnumLiteMap()
      {
        public NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET findValueByNumber(int paramAnonymousInt)
        {
          return NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.valueOf(paramAnonymousInt);
        }
      };
      private final int value;

      static
      {
        BOUNDING_BOX_CLICK_ON_RESULTS_PAGE = new CLICK_TARGET("BOUNDING_BOX_CLICK_ON_RESULTS_PAGE", 1, 1, 1);
        RESULT_CLICK_VIA_SCROLL_BALL_ON_RESULTS_PAGE = new CLICK_TARGET("RESULT_CLICK_VIA_SCROLL_BALL_ON_RESULTS_PAGE", 2, 2, 2);
        BACK_BUTTON_FROM_RESULTS_LIST_PAGE = new CLICK_TARGET("BACK_BUTTON_FROM_RESULTS_LIST_PAGE", 3, 3, 3);
        BACK_BUTTON_FROM_RESULT_PAGE = new CLICK_TARGET("BACK_BUTTON_FROM_RESULT_PAGE", 4, 4, 4);
        NEW_SEARCH_BUTTON_FROM_RESULT_PAGE = new CLICK_TARGET("NEW_SEARCH_BUTTON_FROM_RESULT_PAGE", 5, 5, 5);
        HARDWARE_BACK_BUTTON = new CLICK_TARGET("HARDWARE_BACK_BUTTON", 6, 6, 6);
        EYECANDY_RESULT_CLICK = new CLICK_TARGET("EYECANDY_RESULT_CLICK", 7, 7, 7);
        RATINGS_OPENED_ON_RESULTS_LIST_PAGE = new CLICK_TARGET("RATINGS_OPENED_ON_RESULTS_LIST_PAGE", 8, 8, 8);
        RATINGS_CLOSED_ON_RESULTS_LIST_PAGE = new CLICK_TARGET("RATINGS_CLOSED_ON_RESULTS_LIST_PAGE", 9, 9, 9);
        BACK_BUTTON_FROM_CAMERA_PREVIEW = new CLICK_TARGET("BACK_BUTTON_FROM_CAMERA_PREVIEW", 10, 10, 10);
        SEARCH_ERROR_SHOWN = new CLICK_TARGET("SEARCH_ERROR_SHOWN", 11, 11, 11);
        CANCEL_FROM_SEARCH_ERROR = new CLICK_TARGET("CANCEL_FROM_SEARCH_ERROR", 12, 12, 12);
        RETRY_FROM_SEARCH_ERROR = new CLICK_TARGET("RETRY_FROM_SEARCH_ERROR", 13, 13, 13);
        SAVE_FOR_LATER_FROM_SEARCH_ERROR = new CLICK_TARGET("SAVE_FOR_LATER_FROM_SEARCH_ERROR", 14, 14, 21);
        CAMERA_PREVIEW_SHOWN = new CLICK_TARGET("CAMERA_PREVIEW_SHOWN", 15, 15, 14);
        TAP_OUTSIDE_BOUNDING_BOX_HIDE_RESULTS_LIST = new CLICK_TARGET("TAP_OUTSIDE_BOUNDING_BOX_HIDE_RESULTS_LIST", 16, 16, 15);
        TAP_OUTSIDE_BOUNDING_BOX_SHOW_RESULTS_LIST = new CLICK_TARGET("TAP_OUTSIDE_BOUNDING_BOX_SHOW_RESULTS_LIST", 17, 17, 16);
        BACK_BUTTON_FROM_HISTORY_PAGE = new CLICK_TARGET("BACK_BUTTON_FROM_HISTORY_PAGE", 18, 18, 17);
        RESULT_HTML_CACHED = new CLICK_TARGET("RESULT_HTML_CACHED", 19, 19, 18);
        TAP_SAVE_FOR_LATER = new CLICK_TARGET("TAP_SAVE_FOR_LATER", 20, 20, 19);
        EXECUTE_SAVED_QUERY = new CLICK_TARGET("EXECUTE_SAVED_QUERY", 21, 21, 20);
        FLASH_TOGGLE = new CLICK_TARGET("FLASH_TOGGLE", 22, 22, 22);
        ROI_TOGGLE = new CLICK_TARGET("ROI_TOGGLE", 23, 23, 23);
        DRAWER_OPENED = new CLICK_TARGET("DRAWER_OPENED", 24, 24, 24);
        DRAWER_SEMI = new CLICK_TARGET("DRAWER_SEMI", 25, 25, 25);
        DRAWER_CLOSED = new CLICK_TARGET("DRAWER_CLOSED", 26, 26, 26);
        SHOW_FULL_SIMILAR_IMAGE = new CLICK_TARGET("SHOW_FULL_SIMILAR_IMAGE", 27, 27, 27);
        ENTER_PUGGLE_MODE = new CLICK_TARGET("ENTER_PUGGLE_MODE", 28, 28, 28);
        ADJUST_PUGGLE_CROP = new CLICK_TARGET("ADJUST_PUGGLE_CROP", 29, 29, 29);
        ADDED_TEXT_RESTRICT_SPEECH = new CLICK_TARGET("ADDED_TEXT_RESTRICT_SPEECH", 30, 30, 30);
        ADDED_TEXT_RESTRICT_TYPE = new CLICK_TARGET("ADDED_TEXT_RESTRICT_TYPE", 31, 31, 31);
        ADDED_PROMPTED_TEXT_RESTRICT_SPEECH = new CLICK_TARGET("ADDED_PROMPTED_TEXT_RESTRICT_SPEECH", 32, 32, 32);
        ADDED_PROMPTED_TEXT_RESTRICT_TYPE = new CLICK_TARGET("ADDED_PROMPTED_TEXT_RESTRICT_TYPE", 33, 33, 33);
        ADDED_PROMPTED_TEXT_RESTRICT_TYPE_AUTOCOMPLETED = new CLICK_TARGET("ADDED_PROMPTED_TEXT_RESTRICT_TYPE_AUTOCOMPLETED", 34, 34, 34);
        ADDED_PROMPTED_TEXT_RESTRICT_SUGGESTION = new CLICK_TARGET("ADDED_PROMPTED_TEXT_RESTRICT_SUGGESTION", 35, 35, 35);
        ADDED_PROMPTED_TEXT_RESTRICT_SKIP = new CLICK_TARGET("ADDED_PROMPTED_TEXT_RESTRICT_SKIP", 36, 36, 36);
        PUGGLE_CLICK_LIST_VIEW = new CLICK_TARGET("PUGGLE_CLICK_LIST_VIEW", 37, 37, 37);
        PUGGLE_CLICK_GRID_VIEW = new CLICK_TARGET("PUGGLE_CLICK_GRID_VIEW", 38, 38, 38);
        PUGGLE_REFINE_QUERY_SPEECH = new CLICK_TARGET("PUGGLE_REFINE_QUERY_SPEECH", 39, 39, 39);
        PUGGLE_REFINE_QUERY_TYPE = new CLICK_TARGET("PUGGLE_REFINE_QUERY_TYPE", 40, 40, 40);
        PUGGLE_RESULT_CLICK_IN_RESULT_LIST = new CLICK_TARGET("PUGGLE_RESULT_CLICK_IN_RESULT_LIST", 41, 41, 41);
        PUGGLE_EXTERNAL_CLICK_IN_RESULT_VIEW = new CLICK_TARGET("PUGGLE_EXTERNAL_CLICK_IN_RESULT_VIEW", 42, 42, 42);
        BACK_BUTTON_FROM_PUGGLE_RESULT = new CLICK_TARGET("BACK_BUTTON_FROM_PUGGLE_RESULT", 43, 43, 43);
        SEARCH_FROM_CAMERA_GOGGLES_NOTIFICATION = new CLICK_TARGET("SEARCH_FROM_CAMERA_GOGGLES_NOTIFICATION", 44, 44, 44);
        SEARCH_HISTORY_ENABLED = new CLICK_TARGET("SEARCH_HISTORY_ENABLED", 45, 45, 45);
        SEARCH_HISTORY_DISABLED = new CLICK_TARGET("SEARCH_HISTORY_DISABLED", 46, 46, 46);
        SAVE_TO_GALLERY_ENABLED = new CLICK_TARGET("SAVE_TO_GALLERY_ENABLED", 47, 47, 47);
        SAVE_TO_GALLERY_DISABLED = new CLICK_TARGET("SAVE_TO_GALLERY_DISABLED", 48, 48, 48);
        SEARCH_FROM_CAMERA_ENABLED = new CLICK_TARGET("SEARCH_FROM_CAMERA_ENABLED", 49, 49, 49);
        SEARCH_FROM_CAMERA_DISABLED = new CLICK_TARGET("SEARCH_FROM_CAMERA_DISABLED", 50, 50, 50);
        SEARCH_FROM_CAMERA_MOBILE_NETWORKS_ENABLED = new CLICK_TARGET("SEARCH_FROM_CAMERA_MOBILE_NETWORKS_ENABLED", 51, 51, 51);
        SEARCH_FROM_CAMERA_MOBILE_NETWORKS_DISABLED = new CLICK_TARGET("SEARCH_FROM_CAMERA_MOBILE_NETWORKS_DISABLED", 52, 52, 52);
        SEARCH_FROM_CAMERA_ROAMING_ENABLED = new CLICK_TARGET("SEARCH_FROM_CAMERA_ROAMING_ENABLED", 53, 53, 53);
        SEARCH_FROM_CAMERA_ROAMING_DISABLED = new CLICK_TARGET("SEARCH_FROM_CAMERA_ROAMING_DISABLED", 54, 54, 54);
        HOME_SCREEN_SNAPSHOT = new CLICK_TARGET("HOME_SCREEN_SNAPSHOT", 55, 55, 55);
        HOME_SCREEN_CONTINUOUS = new CLICK_TARGET("HOME_SCREEN_CONTINUOUS", 56, 56, 56);
        HOME_SCREEN_LOAD_IMAGE = new CLICK_TARGET("HOME_SCREEN_LOAD_IMAGE", 57, 57, 57);
        HOME_SCREEN_HISTORY = new CLICK_TARGET("HOME_SCREEN_HISTORY", 58, 58, 58);
        RETURN_TO_HOME_SCREEN_BACK_BUTTON = new CLICK_TARGET("RETURN_TO_HOME_SCREEN_BACK_BUTTON", 59, 59, 59);
        RETURN_TO_HOME_SCREEN_MODE_ICON = new CLICK_TARGET("RETURN_TO_HOME_SCREEN_MODE_ICON", 60, 60, 60);
        PROMOTION_PENDING_SEARCHES = new CLICK_TARGET("PROMOTION_PENDING_SEARCHES", 61, 61, 61);
        PROMOTION_SEARCH_FROM_CAMERA = new CLICK_TARGET("PROMOTION_SEARCH_FROM_CAMERA", 62, 62, 62);
        PROMOTION_TIP = new CLICK_TARGET("PROMOTION_TIP", 63, 63, 63);
        SUGGEST_A_RESULT_BUTTON_CLICK = new CLICK_TARGET("SUGGEST_A_RESULT_BUTTON_CLICK", 64, 64, 64);
        TAG_IMAGE_CONTINUE = new CLICK_TARGET("TAG_IMAGE_CONTINUE", 65, 65, 65);
        TAG_IMAGE_CANCEL = new CLICK_TARGET("TAG_IMAGE_CANCEL", 66, 66, 66);
        TAG_IMAGE_BACK = new CLICK_TARGET("TAG_IMAGE_BACK", 67, 67, 67);
        DESCRIBE_SUBMIT = new CLICK_TARGET("DESCRIBE_SUBMIT", 68, 68, 68);
        DESCRIBE_CANCEL = new CLICK_TARGET("DESCRIBE_CANCEL", 69, 69, 69);
        DESCRIBE_BACK = new CLICK_TARGET("DESCRIBE_BACK", 70, 70, 70);
        HISTORY_SWIPE_TO_SUBMITTED_RESULTS = new CLICK_TARGET("HISTORY_SWIPE_TO_SUBMITTED_RESULTS", 71, 71, 71);
        HISTORY_SWIPE_TO_ALL_RESULTS = new CLICK_TARGET("HISTORY_SWIPE_TO_ALL_RESULTS", 72, 72, 72);
        HISTORY_MAP_MODE_CLICK = new CLICK_TARGET("HISTORY_MAP_MODE_CLICK", 73, 73, 73);
        HISTORY_LIST_MODE_CLICK = new CLICK_TARGET("HISTORY_LIST_MODE_CLICK", 74, 74, 74);
        HISTORY_SEARCH_BUTTON_CLICK = new CLICK_TARGET("HISTORY_SEARCH_BUTTON_CLICK", 75, 75, 75);
        TIPS_LETS_START_CLICK = new CLICK_TARGET("TIPS_LETS_START_CLICK", 76, 76, 76);
        TIPS_TRANSLATE_CLICK = new CLICK_TARGET("TIPS_TRANSLATE_CLICK", 77, 77, 77);
        TIPS_SEARCH_FROM_CAMERA_CLICK = new CLICK_TARGET("TIPS_SEARCH_FROM_CAMERA_CLICK", 78, 78, 78);
        TIPS_PUGGLE_CLICK = new CLICK_TARGET("TIPS_PUGGLE_CLICK", 79, 79, 99);
        CONTINUOUS_TIMELINE_THUMBNAIL_TAP = new CLICK_TARGET("CONTINUOUS_TIMELINE_THUMBNAIL_TAP", 80, 80, 79);
        CONTINUOUS_TIMELINE_SWIPE = new CLICK_TARGET("CONTINUOUS_TIMELINE_SWIPE", 81, 81, 80);
        CONTINUOUS_VIEW_ALL_RESULTS = new CLICK_TARGET("CONTINUOUS_VIEW_ALL_RESULTS", 82, 82, 81);
        CONTINUOUS_ALL_RESULTS_RESULT_ITEM_TAP = new CLICK_TARGET("CONTINUOUS_ALL_RESULTS_RESULT_ITEM_TAP", 83, 83, 82);
        RESULT_SHOW_EXPANDED_RESULT = new CLICK_TARGET("RESULT_SHOW_EXPANDED_RESULT", 84, 84, 83);
        RESULT_DISMBIGUATION_ACTION = new CLICK_TARGET("RESULT_DISMBIGUATION_ACTION", 85, 85, 84);
        RESULT_EXPANDED_RESULT_ACTION = new CLICK_TARGET("RESULT_EXPANDED_RESULT_ACTION", 86, 86, 85);
        CONTINUOUS_CLICK_TO_PAUSE = new CLICK_TARGET("CONTINUOUS_CLICK_TO_PAUSE", 87, 87, 86);
        CONTINUOUS_AUTOMATED_PAUSE = new CLICK_TARGET("CONTINUOUS_AUTOMATED_PAUSE", 88, 88, 87);
        PUGGLE_CATEGORY_CLICK_IN_FAIL_PAGE = new CLICK_TARGET("PUGGLE_CATEGORY_CLICK_IN_FAIL_PAGE", 89, 89, 88);
        PUGGLE_CATEGORY_CLICK_IN_RESULTS_PAGE = new CLICK_TARGET("PUGGLE_CATEGORY_CLICK_IN_RESULTS_PAGE", 90, 90, 89);
        PUGGLE_REFINE_BY_CATEGORY = new CLICK_TARGET("PUGGLE_REFINE_BY_CATEGORY", 91, 91, 90);
        PUGGLE_REFINE_BY_BRAND = new CLICK_TARGET("PUGGLE_REFINE_BY_BRAND", 92, 92, 91);
        PUGGLE_REFINE_BY_GENDER = new CLICK_TARGET("PUGGLE_REFINE_BY_GENDER", 93, 93, 92);
        PUGGLE_SORT_BY_LOW_PRICE = new CLICK_TARGET("PUGGLE_SORT_BY_LOW_PRICE", 94, 94, 93);
        PUGGLE_SORT_BY_HIGH_PRICE = new CLICK_TARGET("PUGGLE_SORT_BY_HIGH_PRICE", 95, 95, 94);
        PUGGLE_SORT_BY_RELEVANCE = new CLICK_TARGET("PUGGLE_SORT_BY_RELEVANCE", 96, 96, 100);
        PUGGLE_CLICK_NEW_SEARCH = new CLICK_TARGET("PUGGLE_CLICK_NEW_SEARCH", 97, 97, 95);
        MORE_LIKE_THIS_LONG_PRESS = new CLICK_TARGET("MORE_LIKE_THIS_LONG_PRESS", 98, 98, 96);
        MORE_LIKE_THIS_FROM_EXPANDED_RESULTS = new CLICK_TARGET("MORE_LIKE_THIS_FROM_EXPANDED_RESULTS", 99, 99, 97);
        SHARE_RESULT = new CLICK_TARGET("SHARE_RESULT", 100, 100, 98);
        SIMILAR_ITEM_FROM_EXPANDED_RESULTS = new CLICK_TARGET("SIMILAR_ITEM_FROM_EXPANDED_RESULTS", 101, 101, 101);
        WELCOME_LEARN_MORE = new CLICK_TARGET("WELCOME_LEARN_MORE", 102, 102, 102);
        WELCOME_NEXT = new CLICK_TARGET("WELCOME_NEXT", 103, 103, 103);
        SEARCH_FROM_CAMERA_LEARN_MORE = new CLICK_TARGET("SEARCH_FROM_CAMERA_LEARN_MORE", 104, 104, 104);
        SEARCH_FROM_CAMERA_MOBILE_WIFI = new CLICK_TARGET("SEARCH_FROM_CAMERA_MOBILE_WIFI", 105, 105, 105);
        SEARCH_FROM_CAMERA_WIFI_ONLY = new CLICK_TARGET("SEARCH_FROM_CAMERA_WIFI_ONLY", 106, 106, 106);
        SEARCH_FROM_CAMERA_NEXT = new CLICK_TARGET("SEARCH_FROM_CAMERA_NEXT", 107, 107, 107);
        SEARCH_FROM_CAMERA_ON = new CLICK_TARGET("SEARCH_FROM_CAMERA_ON", 108, 108, 108);
        SEARCH_FROM_CAMERA_OFF = new CLICK_TARGET("SEARCH_FROM_CAMERA_OFF", 109, 109, 109);
        SEARCH_HISTORY_ON = new CLICK_TARGET("SEARCH_HISTORY_ON", 110, 110, 110);
        SEARCH_HISTORY_OFF = new CLICK_TARGET("SEARCH_HISTORY_OFF", 111, 111, 111);
        SEARCH_HISTORY_NEXT = new CLICK_TARGET("SEARCH_HISTORY_NEXT", 112, 112, 112);
        SEARCH_HISTORY_LEARN_MORE = new CLICK_TARGET("SEARCH_HISTORY_LEARN_MORE", 113, 113, 113);
        CLICK_TARGET[] arrayOfCLICK_TARGET = new CLICK_TARGET[114];
        arrayOfCLICK_TARGET[0] = RESULT_CLICK_IN_RESULTS_LIST;
        arrayOfCLICK_TARGET[1] = BOUNDING_BOX_CLICK_ON_RESULTS_PAGE;
        arrayOfCLICK_TARGET[2] = RESULT_CLICK_VIA_SCROLL_BALL_ON_RESULTS_PAGE;
        arrayOfCLICK_TARGET[3] = BACK_BUTTON_FROM_RESULTS_LIST_PAGE;
        arrayOfCLICK_TARGET[4] = BACK_BUTTON_FROM_RESULT_PAGE;
        arrayOfCLICK_TARGET[5] = NEW_SEARCH_BUTTON_FROM_RESULT_PAGE;
        arrayOfCLICK_TARGET[6] = HARDWARE_BACK_BUTTON;
        arrayOfCLICK_TARGET[7] = EYECANDY_RESULT_CLICK;
        arrayOfCLICK_TARGET[8] = RATINGS_OPENED_ON_RESULTS_LIST_PAGE;
        arrayOfCLICK_TARGET[9] = RATINGS_CLOSED_ON_RESULTS_LIST_PAGE;
        arrayOfCLICK_TARGET[10] = BACK_BUTTON_FROM_CAMERA_PREVIEW;
        arrayOfCLICK_TARGET[11] = SEARCH_ERROR_SHOWN;
        arrayOfCLICK_TARGET[12] = CANCEL_FROM_SEARCH_ERROR;
        arrayOfCLICK_TARGET[13] = RETRY_FROM_SEARCH_ERROR;
        arrayOfCLICK_TARGET[14] = SAVE_FOR_LATER_FROM_SEARCH_ERROR;
        arrayOfCLICK_TARGET[15] = CAMERA_PREVIEW_SHOWN;
        arrayOfCLICK_TARGET[16] = TAP_OUTSIDE_BOUNDING_BOX_HIDE_RESULTS_LIST;
        arrayOfCLICK_TARGET[17] = TAP_OUTSIDE_BOUNDING_BOX_SHOW_RESULTS_LIST;
        arrayOfCLICK_TARGET[18] = BACK_BUTTON_FROM_HISTORY_PAGE;
        arrayOfCLICK_TARGET[19] = RESULT_HTML_CACHED;
        arrayOfCLICK_TARGET[20] = TAP_SAVE_FOR_LATER;
        arrayOfCLICK_TARGET[21] = EXECUTE_SAVED_QUERY;
        arrayOfCLICK_TARGET[22] = FLASH_TOGGLE;
        arrayOfCLICK_TARGET[23] = ROI_TOGGLE;
        arrayOfCLICK_TARGET[24] = DRAWER_OPENED;
        arrayOfCLICK_TARGET[25] = DRAWER_SEMI;
        arrayOfCLICK_TARGET[26] = DRAWER_CLOSED;
        arrayOfCLICK_TARGET[27] = SHOW_FULL_SIMILAR_IMAGE;
        arrayOfCLICK_TARGET[28] = ENTER_PUGGLE_MODE;
        arrayOfCLICK_TARGET[29] = ADJUST_PUGGLE_CROP;
        arrayOfCLICK_TARGET[30] = ADDED_TEXT_RESTRICT_SPEECH;
        arrayOfCLICK_TARGET[31] = ADDED_TEXT_RESTRICT_TYPE;
        arrayOfCLICK_TARGET[32] = ADDED_PROMPTED_TEXT_RESTRICT_SPEECH;
        arrayOfCLICK_TARGET[33] = ADDED_PROMPTED_TEXT_RESTRICT_TYPE;
        arrayOfCLICK_TARGET[34] = ADDED_PROMPTED_TEXT_RESTRICT_TYPE_AUTOCOMPLETED;
        arrayOfCLICK_TARGET[35] = ADDED_PROMPTED_TEXT_RESTRICT_SUGGESTION;
        arrayOfCLICK_TARGET[36] = ADDED_PROMPTED_TEXT_RESTRICT_SKIP;
        arrayOfCLICK_TARGET[37] = PUGGLE_CLICK_LIST_VIEW;
        arrayOfCLICK_TARGET[38] = PUGGLE_CLICK_GRID_VIEW;
        arrayOfCLICK_TARGET[39] = PUGGLE_REFINE_QUERY_SPEECH;
        arrayOfCLICK_TARGET[40] = PUGGLE_REFINE_QUERY_TYPE;
        arrayOfCLICK_TARGET[41] = PUGGLE_RESULT_CLICK_IN_RESULT_LIST;
        arrayOfCLICK_TARGET[42] = PUGGLE_EXTERNAL_CLICK_IN_RESULT_VIEW;
        arrayOfCLICK_TARGET[43] = BACK_BUTTON_FROM_PUGGLE_RESULT;
        arrayOfCLICK_TARGET[44] = SEARCH_FROM_CAMERA_GOGGLES_NOTIFICATION;
        arrayOfCLICK_TARGET[45] = SEARCH_HISTORY_ENABLED;
        arrayOfCLICK_TARGET[46] = SEARCH_HISTORY_DISABLED;
        arrayOfCLICK_TARGET[47] = SAVE_TO_GALLERY_ENABLED;
        arrayOfCLICK_TARGET[48] = SAVE_TO_GALLERY_DISABLED;
        arrayOfCLICK_TARGET[49] = SEARCH_FROM_CAMERA_ENABLED;
        arrayOfCLICK_TARGET[50] = SEARCH_FROM_CAMERA_DISABLED;
        arrayOfCLICK_TARGET[51] = SEARCH_FROM_CAMERA_MOBILE_NETWORKS_ENABLED;
        arrayOfCLICK_TARGET[52] = SEARCH_FROM_CAMERA_MOBILE_NETWORKS_DISABLED;
        arrayOfCLICK_TARGET[53] = SEARCH_FROM_CAMERA_ROAMING_ENABLED;
        arrayOfCLICK_TARGET[54] = SEARCH_FROM_CAMERA_ROAMING_DISABLED;
        arrayOfCLICK_TARGET[55] = HOME_SCREEN_SNAPSHOT;
        arrayOfCLICK_TARGET[56] = HOME_SCREEN_CONTINUOUS;
        arrayOfCLICK_TARGET[57] = HOME_SCREEN_LOAD_IMAGE;
        arrayOfCLICK_TARGET[58] = HOME_SCREEN_HISTORY;
        arrayOfCLICK_TARGET[59] = RETURN_TO_HOME_SCREEN_BACK_BUTTON;
        arrayOfCLICK_TARGET[60] = RETURN_TO_HOME_SCREEN_MODE_ICON;
        arrayOfCLICK_TARGET[61] = PROMOTION_PENDING_SEARCHES;
        arrayOfCLICK_TARGET[62] = PROMOTION_SEARCH_FROM_CAMERA;
        arrayOfCLICK_TARGET[63] = PROMOTION_TIP;
        arrayOfCLICK_TARGET[64] = SUGGEST_A_RESULT_BUTTON_CLICK;
        arrayOfCLICK_TARGET[65] = TAG_IMAGE_CONTINUE;
        arrayOfCLICK_TARGET[66] = TAG_IMAGE_CANCEL;
        arrayOfCLICK_TARGET[67] = TAG_IMAGE_BACK;
        arrayOfCLICK_TARGET[68] = DESCRIBE_SUBMIT;
        arrayOfCLICK_TARGET[69] = DESCRIBE_CANCEL;
        arrayOfCLICK_TARGET[70] = DESCRIBE_BACK;
        arrayOfCLICK_TARGET[71] = HISTORY_SWIPE_TO_SUBMITTED_RESULTS;
        arrayOfCLICK_TARGET[72] = HISTORY_SWIPE_TO_ALL_RESULTS;
        arrayOfCLICK_TARGET[73] = HISTORY_MAP_MODE_CLICK;
        arrayOfCLICK_TARGET[74] = HISTORY_LIST_MODE_CLICK;
        arrayOfCLICK_TARGET[75] = HISTORY_SEARCH_BUTTON_CLICK;
        arrayOfCLICK_TARGET[76] = TIPS_LETS_START_CLICK;
        arrayOfCLICK_TARGET[77] = TIPS_TRANSLATE_CLICK;
        arrayOfCLICK_TARGET[78] = TIPS_SEARCH_FROM_CAMERA_CLICK;
        arrayOfCLICK_TARGET[79] = TIPS_PUGGLE_CLICK;
        arrayOfCLICK_TARGET[80] = CONTINUOUS_TIMELINE_THUMBNAIL_TAP;
        arrayOfCLICK_TARGET[81] = CONTINUOUS_TIMELINE_SWIPE;
        arrayOfCLICK_TARGET[82] = CONTINUOUS_VIEW_ALL_RESULTS;
        arrayOfCLICK_TARGET[83] = CONTINUOUS_ALL_RESULTS_RESULT_ITEM_TAP;
        arrayOfCLICK_TARGET[84] = RESULT_SHOW_EXPANDED_RESULT;
        arrayOfCLICK_TARGET[85] = RESULT_DISMBIGUATION_ACTION;
        arrayOfCLICK_TARGET[86] = RESULT_EXPANDED_RESULT_ACTION;
        arrayOfCLICK_TARGET[87] = CONTINUOUS_CLICK_TO_PAUSE;
        arrayOfCLICK_TARGET[88] = CONTINUOUS_AUTOMATED_PAUSE;
        arrayOfCLICK_TARGET[89] = PUGGLE_CATEGORY_CLICK_IN_FAIL_PAGE;
        arrayOfCLICK_TARGET[90] = PUGGLE_CATEGORY_CLICK_IN_RESULTS_PAGE;
        arrayOfCLICK_TARGET[91] = PUGGLE_REFINE_BY_CATEGORY;
        arrayOfCLICK_TARGET[92] = PUGGLE_REFINE_BY_BRAND;
        arrayOfCLICK_TARGET[93] = PUGGLE_REFINE_BY_GENDER;
        arrayOfCLICK_TARGET[94] = PUGGLE_SORT_BY_LOW_PRICE;
        arrayOfCLICK_TARGET[95] = PUGGLE_SORT_BY_HIGH_PRICE;
        arrayOfCLICK_TARGET[96] = PUGGLE_SORT_BY_RELEVANCE;
        arrayOfCLICK_TARGET[97] = PUGGLE_CLICK_NEW_SEARCH;
        arrayOfCLICK_TARGET[98] = MORE_LIKE_THIS_LONG_PRESS;
        arrayOfCLICK_TARGET[99] = MORE_LIKE_THIS_FROM_EXPANDED_RESULTS;
        arrayOfCLICK_TARGET[100] = SHARE_RESULT;
        arrayOfCLICK_TARGET[101] = SIMILAR_ITEM_FROM_EXPANDED_RESULTS;
        arrayOfCLICK_TARGET[102] = WELCOME_LEARN_MORE;
        arrayOfCLICK_TARGET[103] = WELCOME_NEXT;
        arrayOfCLICK_TARGET[104] = SEARCH_FROM_CAMERA_LEARN_MORE;
        arrayOfCLICK_TARGET[105] = SEARCH_FROM_CAMERA_MOBILE_WIFI;
        arrayOfCLICK_TARGET[106] = SEARCH_FROM_CAMERA_WIFI_ONLY;
        arrayOfCLICK_TARGET[107] = SEARCH_FROM_CAMERA_NEXT;
        arrayOfCLICK_TARGET[108] = SEARCH_FROM_CAMERA_ON;
        arrayOfCLICK_TARGET[109] = SEARCH_FROM_CAMERA_OFF;
        arrayOfCLICK_TARGET[110] = SEARCH_HISTORY_ON;
        arrayOfCLICK_TARGET[111] = SEARCH_HISTORY_OFF;
        arrayOfCLICK_TARGET[112] = SEARCH_HISTORY_NEXT;
        arrayOfCLICK_TARGET[113] = SEARCH_HISTORY_LEARN_MORE;
      }

      private CLICK_TARGET(int paramInt1, int paramInt2)
      {
        this.value = paramInt2;
      }

      public static Internal.EnumLiteMap<CLICK_TARGET> internalGetValueMap()
      {
        return internalValueMap;
      }

      public static CLICK_TARGET valueOf(int paramInt)
      {
        switch (paramInt)
        {
        default:
          return null;
        case 0:
          return RESULT_CLICK_IN_RESULTS_LIST;
        case 1:
          return BOUNDING_BOX_CLICK_ON_RESULTS_PAGE;
        case 2:
          return RESULT_CLICK_VIA_SCROLL_BALL_ON_RESULTS_PAGE;
        case 3:
          return BACK_BUTTON_FROM_RESULTS_LIST_PAGE;
        case 4:
          return BACK_BUTTON_FROM_RESULT_PAGE;
        case 5:
          return NEW_SEARCH_BUTTON_FROM_RESULT_PAGE;
        case 6:
          return HARDWARE_BACK_BUTTON;
        case 7:
          return EYECANDY_RESULT_CLICK;
        case 8:
          return RATINGS_OPENED_ON_RESULTS_LIST_PAGE;
        case 9:
          return RATINGS_CLOSED_ON_RESULTS_LIST_PAGE;
        case 10:
          return BACK_BUTTON_FROM_CAMERA_PREVIEW;
        case 11:
          return SEARCH_ERROR_SHOWN;
        case 12:
          return CANCEL_FROM_SEARCH_ERROR;
        case 13:
          return RETRY_FROM_SEARCH_ERROR;
        case 21:
          return SAVE_FOR_LATER_FROM_SEARCH_ERROR;
        case 14:
          return CAMERA_PREVIEW_SHOWN;
        case 15:
          return TAP_OUTSIDE_BOUNDING_BOX_HIDE_RESULTS_LIST;
        case 16:
          return TAP_OUTSIDE_BOUNDING_BOX_SHOW_RESULTS_LIST;
        case 17:
          return BACK_BUTTON_FROM_HISTORY_PAGE;
        case 18:
          return RESULT_HTML_CACHED;
        case 19:
          return TAP_SAVE_FOR_LATER;
        case 20:
          return EXECUTE_SAVED_QUERY;
        case 22:
          return FLASH_TOGGLE;
        case 23:
          return ROI_TOGGLE;
        case 24:
          return DRAWER_OPENED;
        case 25:
          return DRAWER_SEMI;
        case 26:
          return DRAWER_CLOSED;
        case 27:
          return SHOW_FULL_SIMILAR_IMAGE;
        case 28:
          return ENTER_PUGGLE_MODE;
        case 29:
          return ADJUST_PUGGLE_CROP;
        case 30:
          return ADDED_TEXT_RESTRICT_SPEECH;
        case 31:
          return ADDED_TEXT_RESTRICT_TYPE;
        case 32:
          return ADDED_PROMPTED_TEXT_RESTRICT_SPEECH;
        case 33:
          return ADDED_PROMPTED_TEXT_RESTRICT_TYPE;
        case 34:
          return ADDED_PROMPTED_TEXT_RESTRICT_TYPE_AUTOCOMPLETED;
        case 35:
          return ADDED_PROMPTED_TEXT_RESTRICT_SUGGESTION;
        case 36:
          return ADDED_PROMPTED_TEXT_RESTRICT_SKIP;
        case 37:
          return PUGGLE_CLICK_LIST_VIEW;
        case 38:
          return PUGGLE_CLICK_GRID_VIEW;
        case 39:
          return PUGGLE_REFINE_QUERY_SPEECH;
        case 40:
          return PUGGLE_REFINE_QUERY_TYPE;
        case 41:
          return PUGGLE_RESULT_CLICK_IN_RESULT_LIST;
        case 42:
          return PUGGLE_EXTERNAL_CLICK_IN_RESULT_VIEW;
        case 43:
          return BACK_BUTTON_FROM_PUGGLE_RESULT;
        case 44:
          return SEARCH_FROM_CAMERA_GOGGLES_NOTIFICATION;
        case 45:
          return SEARCH_HISTORY_ENABLED;
        case 46:
          return SEARCH_HISTORY_DISABLED;
        case 47:
          return SAVE_TO_GALLERY_ENABLED;
        case 48:
          return SAVE_TO_GALLERY_DISABLED;
        case 49:
          return SEARCH_FROM_CAMERA_ENABLED;
        case 50:
          return SEARCH_FROM_CAMERA_DISABLED;
        case 51:
          return SEARCH_FROM_CAMERA_MOBILE_NETWORKS_ENABLED;
        case 52:
          return SEARCH_FROM_CAMERA_MOBILE_NETWORKS_DISABLED;
        case 53:
          return SEARCH_FROM_CAMERA_ROAMING_ENABLED;
        case 54:
          return SEARCH_FROM_CAMERA_ROAMING_DISABLED;
        case 55:
          return HOME_SCREEN_SNAPSHOT;
        case 56:
          return HOME_SCREEN_CONTINUOUS;
        case 57:
          return HOME_SCREEN_LOAD_IMAGE;
        case 58:
          return HOME_SCREEN_HISTORY;
        case 59:
          return RETURN_TO_HOME_SCREEN_BACK_BUTTON;
        case 60:
          return RETURN_TO_HOME_SCREEN_MODE_ICON;
        case 61:
          return PROMOTION_PENDING_SEARCHES;
        case 62:
          return PROMOTION_SEARCH_FROM_CAMERA;
        case 63:
          return PROMOTION_TIP;
        case 64:
          return SUGGEST_A_RESULT_BUTTON_CLICK;
        case 65:
          return TAG_IMAGE_CONTINUE;
        case 66:
          return TAG_IMAGE_CANCEL;
        case 67:
          return TAG_IMAGE_BACK;
        case 68:
          return DESCRIBE_SUBMIT;
        case 69:
          return DESCRIBE_CANCEL;
        case 70:
          return DESCRIBE_BACK;
        case 71:
          return HISTORY_SWIPE_TO_SUBMITTED_RESULTS;
        case 72:
          return HISTORY_SWIPE_TO_ALL_RESULTS;
        case 73:
          return HISTORY_MAP_MODE_CLICK;
        case 74:
          return HISTORY_LIST_MODE_CLICK;
        case 75:
          return HISTORY_SEARCH_BUTTON_CLICK;
        case 76:
          return TIPS_LETS_START_CLICK;
        case 77:
          return TIPS_TRANSLATE_CLICK;
        case 78:
          return TIPS_SEARCH_FROM_CAMERA_CLICK;
        case 99:
          return TIPS_PUGGLE_CLICK;
        case 79:
          return CONTINUOUS_TIMELINE_THUMBNAIL_TAP;
        case 80:
          return CONTINUOUS_TIMELINE_SWIPE;
        case 81:
          return CONTINUOUS_VIEW_ALL_RESULTS;
        case 82:
          return CONTINUOUS_ALL_RESULTS_RESULT_ITEM_TAP;
        case 83:
          return RESULT_SHOW_EXPANDED_RESULT;
        case 84:
          return RESULT_DISMBIGUATION_ACTION;
        case 85:
          return RESULT_EXPANDED_RESULT_ACTION;
        case 86:
          return CONTINUOUS_CLICK_TO_PAUSE;
        case 87:
          return CONTINUOUS_AUTOMATED_PAUSE;
        case 88:
          return PUGGLE_CATEGORY_CLICK_IN_FAIL_PAGE;
        case 89:
          return PUGGLE_CATEGORY_CLICK_IN_RESULTS_PAGE;
        case 90:
          return PUGGLE_REFINE_BY_CATEGORY;
        case 91:
          return PUGGLE_REFINE_BY_BRAND;
        case 92:
          return PUGGLE_REFINE_BY_GENDER;
        case 93:
          return PUGGLE_SORT_BY_LOW_PRICE;
        case 94:
          return PUGGLE_SORT_BY_HIGH_PRICE;
        case 100:
          return PUGGLE_SORT_BY_RELEVANCE;
        case 95:
          return PUGGLE_CLICK_NEW_SEARCH;
        case 96:
          return MORE_LIKE_THIS_LONG_PRESS;
        case 97:
          return MORE_LIKE_THIS_FROM_EXPANDED_RESULTS;
        case 98:
          return SHARE_RESULT;
        case 101:
          return SIMILAR_ITEM_FROM_EXPANDED_RESULTS;
        case 102:
          return WELCOME_LEARN_MORE;
        case 103:
          return WELCOME_NEXT;
        case 104:
          return SEARCH_FROM_CAMERA_LEARN_MORE;
        case 105:
          return SEARCH_FROM_CAMERA_MOBILE_WIFI;
        case 106:
          return SEARCH_FROM_CAMERA_WIFI_ONLY;
        case 107:
          return SEARCH_FROM_CAMERA_NEXT;
        case 108:
          return SEARCH_FROM_CAMERA_ON;
        case 109:
          return SEARCH_FROM_CAMERA_OFF;
        case 110:
          return SEARCH_HISTORY_ON;
        case 111:
          return SEARCH_HISTORY_OFF;
        case 112:
          return SEARCH_HISTORY_NEXT;
        case 113:
        }
        return SEARCH_HISTORY_LEARN_MORE;
      }

      public final int getNumber()
      {
        return this.value;
      }
    }
  }

  public static abstract interface NativeClientClickOrBuilder extends MessageLiteOrBuilder
  {
    public abstract int getActionPosition();

    public abstract NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET getClickTarget();

    public abstract int getDisplayPosition();

    public abstract int getNotificationResultsCount();

    public abstract String getResultId();

    public abstract int getResultPosition();

    public abstract int getResultsShownAboveFold();

    public abstract boolean hasActionPosition();

    public abstract boolean hasClickTarget();

    public abstract boolean hasDisplayPosition();

    public abstract boolean hasNotificationResultsCount();

    public abstract boolean hasResultId();

    public abstract boolean hasResultPosition();

    public abstract boolean hasResultsShownAboveFold();
  }

  public static final class NativeClientInstall extends GeneratedMessageLite
    implements NativeClientLoggingProtos.NativeClientInstallOrBuilder
  {
    public static final int INSTALLED_VERSION_FIELD_NUMBER = 2;
    public static final int UPGRADE_FROM_FIELD_NUMBER = 1;
    private static final NativeClientInstall defaultInstance = new NativeClientInstall(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private Object installedVersion_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private Object upgradeFrom_;

    static
    {
      defaultInstance.initFields();
    }

    private NativeClientInstall(Builder paramBuilder)
    {
      super();
    }

    private NativeClientInstall(boolean paramBoolean)
    {
    }

    public static NativeClientInstall getDefaultInstance()
    {
      return defaultInstance;
    }

    private ByteString getInstalledVersionBytes()
    {
      Object localObject = this.installedVersion_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.installedVersion_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getUpgradeFromBytes()
    {
      Object localObject = this.upgradeFrom_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.upgradeFrom_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private void initFields()
    {
      this.upgradeFrom_ = "";
      this.installedVersion_ = "";
    }

    public static Builder newBuilder()
    {
      return Builder.access$1700();
    }

    public static Builder newBuilder(NativeClientInstall paramNativeClientInstall)
    {
      return newBuilder().mergeFrom(paramNativeClientInstall);
    }

    public static NativeClientInstall parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static NativeClientInstall parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static NativeClientInstall parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static NativeClientInstall parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static NativeClientInstall parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static NativeClientInstall parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static NativeClientInstall parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static NativeClientInstall parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static NativeClientInstall parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static NativeClientInstall parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public NativeClientInstall getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public String getInstalledVersion()
    {
      Object localObject = this.installedVersion_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.installedVersion_ = str;
      return str;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeBytesSize(1, getUpgradeFromBytes());
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeBytesSize(2, getInstalledVersionBytes());
      this.memoizedSerializedSize = k;
      return k;
    }

    public String getUpgradeFrom()
    {
      Object localObject = this.upgradeFrom_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.upgradeFrom_ = str;
      return str;
    }

    public boolean hasInstalledVersion()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasUpgradeFrom()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      this.memoizedIsInitialized = 1;
      return true;
    }

    public Builder newBuilderForType()
    {
      return newBuilder();
    }

    public Builder toBuilder()
    {
      return newBuilder(this);
    }

    protected Object writeReplace()
      throws ObjectStreamException
    {
      return super.writeReplace();
    }

    public void writeTo(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      getSerializedSize();
      if ((0x1 & this.bitField0_) == 1)
        paramCodedOutputStream.writeBytes(1, getUpgradeFromBytes());
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeBytes(2, getInstalledVersionBytes());
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NativeClientLoggingProtos.NativeClientInstall, Builder>
      implements NativeClientLoggingProtos.NativeClientInstallOrBuilder
    {
      private int bitField0_;
      private Object installedVersion_ = "";
      private Object upgradeFrom_ = "";

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private NativeClientLoggingProtos.NativeClientInstall buildParsed()
        throws InvalidProtocolBufferException
      {
        NativeClientLoggingProtos.NativeClientInstall localNativeClientInstall = buildPartial();
        if (!localNativeClientInstall.isInitialized())
          throw newUninitializedMessageException(localNativeClientInstall).asInvalidProtocolBufferException();
        return localNativeClientInstall;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public NativeClientLoggingProtos.NativeClientInstall build()
      {
        NativeClientLoggingProtos.NativeClientInstall localNativeClientInstall = buildPartial();
        if (!localNativeClientInstall.isInitialized())
          throw newUninitializedMessageException(localNativeClientInstall);
        return localNativeClientInstall;
      }

      public NativeClientLoggingProtos.NativeClientInstall buildPartial()
      {
        int i = 1;
        NativeClientLoggingProtos.NativeClientInstall localNativeClientInstall = new NativeClientLoggingProtos.NativeClientInstall(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          NativeClientLoggingProtos.NativeClientInstall.access$1902(localNativeClientInstall, this.upgradeFrom_);
          if ((j & 0x2) == 2)
            i |= 2;
          NativeClientLoggingProtos.NativeClientInstall.access$2002(localNativeClientInstall, this.installedVersion_);
          NativeClientLoggingProtos.NativeClientInstall.access$2102(localNativeClientInstall, i);
          return localNativeClientInstall;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.upgradeFrom_ = "";
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.installedVersion_ = "";
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      public Builder clearInstalledVersion()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.installedVersion_ = NativeClientLoggingProtos.NativeClientInstall.getDefaultInstance().getInstalledVersion();
        return this;
      }

      public Builder clearUpgradeFrom()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.upgradeFrom_ = NativeClientLoggingProtos.NativeClientInstall.getDefaultInstance().getUpgradeFrom();
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public NativeClientLoggingProtos.NativeClientInstall getDefaultInstanceForType()
      {
        return NativeClientLoggingProtos.NativeClientInstall.getDefaultInstance();
      }

      public String getInstalledVersion()
      {
        Object localObject = this.installedVersion_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.installedVersion_ = str;
          return str;
        }
        return (String)localObject;
      }

      public String getUpgradeFrom()
      {
        Object localObject = this.upgradeFrom_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.upgradeFrom_ = str;
          return str;
        }
        return (String)localObject;
      }

      public boolean hasInstalledVersion()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasUpgradeFrom()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(NativeClientLoggingProtos.NativeClientInstall paramNativeClientInstall)
      {
        if (paramNativeClientInstall == NativeClientLoggingProtos.NativeClientInstall.getDefaultInstance());
        do
        {
          return this;
          if (paramNativeClientInstall.hasUpgradeFrom())
            setUpgradeFrom(paramNativeClientInstall.getUpgradeFrom());
        }
        while (!paramNativeClientInstall.hasInstalledVersion());
        setInstalledVersion(paramNativeClientInstall.getInstalledVersion());
        return this;
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        while (true)
        {
          int i = paramCodedInputStream.readTag();
          switch (i)
          {
          default:
            if (parseUnknownField(paramCodedInputStream, paramExtensionRegistryLite, i))
              continue;
          case 0:
            return this;
          case 10:
            this.bitField0_ = (0x1 | this.bitField0_);
            this.upgradeFrom_ = paramCodedInputStream.readBytes();
            break;
          case 18:
          }
          this.bitField0_ = (0x2 | this.bitField0_);
          this.installedVersion_ = paramCodedInputStream.readBytes();
        }
      }

      public Builder setInstalledVersion(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x2 | this.bitField0_);
        this.installedVersion_ = paramString;
        return this;
      }

      void setInstalledVersion(ByteString paramByteString)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.installedVersion_ = paramByteString;
      }

      public Builder setUpgradeFrom(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.upgradeFrom_ = paramString;
        return this;
      }

      void setUpgradeFrom(ByteString paramByteString)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.upgradeFrom_ = paramByteString;
      }
    }
  }

  public static abstract interface NativeClientInstallOrBuilder extends MessageLiteOrBuilder
  {
    public abstract String getInstalledVersion();

    public abstract String getUpgradeFrom();

    public abstract boolean hasInstalledVersion();

    public abstract boolean hasUpgradeFrom();
  }

  public static final class NativeClientLogEventRequest extends GeneratedMessageLite
    implements NativeClientLoggingProtos.NativeClientLogEventRequestOrBuilder
  {
    public static final int CLIENT_CLICK_FIELD_NUMBER = 2;
    public static final int CLIENT_INSTALL_FIELD_NUMBER = 5;
    public static final int CLIENT_RATING_FIELD_NUMBER = 3;
    public static final int GOGGLES_ID_FIELD_NUMBER = 9;
    public static final int MS_SINCE_EPOCH_FIELD_NUMBER = 4;
    public static final int SESSION_ID_FIELD_NUMBER = 11;
    public static final int TRACKING_ID_FIELD_NUMBER = 10;
    private static final NativeClientLogEventRequest defaultInstance = new NativeClientLogEventRequest(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private NativeClientLoggingProtos.NativeClientClick clientClick_;
    private NativeClientLoggingProtos.NativeClientInstall clientInstall_;
    private NativeClientLoggingProtos.NativeClientRating clientRating_;
    private long gogglesId_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private long msSinceEpoch_;
    private Object sessionId_;
    private Object trackingId_;

    static
    {
      defaultInstance.initFields();
    }

    private NativeClientLogEventRequest(Builder paramBuilder)
    {
      super();
    }

    private NativeClientLogEventRequest(boolean paramBoolean)
    {
    }

    public static NativeClientLogEventRequest getDefaultInstance()
    {
      return defaultInstance;
    }

    private ByteString getSessionIdBytes()
    {
      Object localObject = this.sessionId_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.sessionId_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getTrackingIdBytes()
    {
      Object localObject = this.trackingId_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.trackingId_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private void initFields()
    {
      this.msSinceEpoch_ = 0L;
      this.clientClick_ = NativeClientLoggingProtos.NativeClientClick.getDefaultInstance();
      this.clientRating_ = NativeClientLoggingProtos.NativeClientRating.getDefaultInstance();
      this.clientInstall_ = NativeClientLoggingProtos.NativeClientInstall.getDefaultInstance();
      this.gogglesId_ = 0L;
      this.trackingId_ = "";
      this.sessionId_ = "";
    }

    public static Builder newBuilder()
    {
      return Builder.access$2300();
    }

    public static Builder newBuilder(NativeClientLogEventRequest paramNativeClientLogEventRequest)
    {
      return newBuilder().mergeFrom(paramNativeClientLogEventRequest);
    }

    public static NativeClientLogEventRequest parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static NativeClientLogEventRequest parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static NativeClientLogEventRequest parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static NativeClientLogEventRequest parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static NativeClientLogEventRequest parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static NativeClientLogEventRequest parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static NativeClientLogEventRequest parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static NativeClientLogEventRequest parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static NativeClientLogEventRequest parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static NativeClientLogEventRequest parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public NativeClientLoggingProtos.NativeClientClick getClientClick()
    {
      return this.clientClick_;
    }

    public NativeClientLoggingProtos.NativeClientInstall getClientInstall()
    {
      return this.clientInstall_;
    }

    public NativeClientLoggingProtos.NativeClientRating getClientRating()
    {
      return this.clientRating_;
    }

    public NativeClientLogEventRequest getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    @Deprecated
    public long getGogglesId()
    {
      return this.gogglesId_;
    }

    public long getMsSinceEpoch()
    {
      return this.msSinceEpoch_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x2 & this.bitField0_;
      int k = 0;
      if (j == 2)
        k = 0 + CodedOutputStream.computeMessageSize(2, this.clientClick_);
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeMessageSize(3, this.clientRating_);
      if ((0x1 & this.bitField0_) == 1)
        k += CodedOutputStream.computeInt64Size(4, this.msSinceEpoch_);
      if ((0x8 & this.bitField0_) == 8)
        k += CodedOutputStream.computeMessageSize(5, this.clientInstall_);
      if ((0x10 & this.bitField0_) == 16)
        k += CodedOutputStream.computeFixed64Size(9, this.gogglesId_);
      if ((0x20 & this.bitField0_) == 32)
        k += CodedOutputStream.computeBytesSize(10, getTrackingIdBytes());
      if ((0x40 & this.bitField0_) == 64)
        k += CodedOutputStream.computeBytesSize(11, getSessionIdBytes());
      this.memoizedSerializedSize = k;
      return k;
    }

    public String getSessionId()
    {
      Object localObject = this.sessionId_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.sessionId_ = str;
      return str;
    }

    public String getTrackingId()
    {
      Object localObject = this.trackingId_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.trackingId_ = str;
      return str;
    }

    public boolean hasClientClick()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasClientInstall()
    {
      return (0x8 & this.bitField0_) == 8;
    }

    public boolean hasClientRating()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    @Deprecated
    public boolean hasGogglesId()
    {
      return (0x10 & this.bitField0_) == 16;
    }

    public boolean hasMsSinceEpoch()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public boolean hasSessionId()
    {
      return (0x40 & this.bitField0_) == 64;
    }

    public boolean hasTrackingId()
    {
      return (0x20 & this.bitField0_) == 32;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      if ((hasClientClick()) && (!getClientClick().isInitialized()))
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      this.memoizedIsInitialized = 1;
      return true;
    }

    public Builder newBuilderForType()
    {
      return newBuilder();
    }

    public Builder toBuilder()
    {
      return newBuilder(this);
    }

    protected Object writeReplace()
      throws ObjectStreamException
    {
      return super.writeReplace();
    }

    public void writeTo(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      getSerializedSize();
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeMessage(2, this.clientClick_);
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeMessage(3, this.clientRating_);
      if ((0x1 & this.bitField0_) == 1)
        paramCodedOutputStream.writeInt64(4, this.msSinceEpoch_);
      if ((0x8 & this.bitField0_) == 8)
        paramCodedOutputStream.writeMessage(5, this.clientInstall_);
      if ((0x10 & this.bitField0_) == 16)
        paramCodedOutputStream.writeFixed64(9, this.gogglesId_);
      if ((0x20 & this.bitField0_) == 32)
        paramCodedOutputStream.writeBytes(10, getTrackingIdBytes());
      if ((0x40 & this.bitField0_) == 64)
        paramCodedOutputStream.writeBytes(11, getSessionIdBytes());
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NativeClientLoggingProtos.NativeClientLogEventRequest, Builder>
      implements NativeClientLoggingProtos.NativeClientLogEventRequestOrBuilder
    {
      private int bitField0_;
      private NativeClientLoggingProtos.NativeClientClick clientClick_ = NativeClientLoggingProtos.NativeClientClick.getDefaultInstance();
      private NativeClientLoggingProtos.NativeClientInstall clientInstall_ = NativeClientLoggingProtos.NativeClientInstall.getDefaultInstance();
      private NativeClientLoggingProtos.NativeClientRating clientRating_ = NativeClientLoggingProtos.NativeClientRating.getDefaultInstance();
      private long gogglesId_;
      private long msSinceEpoch_;
      private Object sessionId_ = "";
      private Object trackingId_ = "";

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private NativeClientLoggingProtos.NativeClientLogEventRequest buildParsed()
        throws InvalidProtocolBufferException
      {
        NativeClientLoggingProtos.NativeClientLogEventRequest localNativeClientLogEventRequest = buildPartial();
        if (!localNativeClientLogEventRequest.isInitialized())
          throw newUninitializedMessageException(localNativeClientLogEventRequest).asInvalidProtocolBufferException();
        return localNativeClientLogEventRequest;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public NativeClientLoggingProtos.NativeClientLogEventRequest build()
      {
        NativeClientLoggingProtos.NativeClientLogEventRequest localNativeClientLogEventRequest = buildPartial();
        if (!localNativeClientLogEventRequest.isInitialized())
          throw newUninitializedMessageException(localNativeClientLogEventRequest);
        return localNativeClientLogEventRequest;
      }

      public NativeClientLoggingProtos.NativeClientLogEventRequest buildPartial()
      {
        int i = 1;
        NativeClientLoggingProtos.NativeClientLogEventRequest localNativeClientLogEventRequest = new NativeClientLoggingProtos.NativeClientLogEventRequest(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          NativeClientLoggingProtos.NativeClientLogEventRequest.access$2502(localNativeClientLogEventRequest, this.msSinceEpoch_);
          if ((j & 0x2) == 2)
            i |= 2;
          NativeClientLoggingProtos.NativeClientLogEventRequest.access$2602(localNativeClientLogEventRequest, this.clientClick_);
          if ((j & 0x4) == 4)
            i |= 4;
          NativeClientLoggingProtos.NativeClientLogEventRequest.access$2702(localNativeClientLogEventRequest, this.clientRating_);
          if ((j & 0x8) == 8)
            i |= 8;
          NativeClientLoggingProtos.NativeClientLogEventRequest.access$2802(localNativeClientLogEventRequest, this.clientInstall_);
          if ((j & 0x10) == 16)
            i |= 16;
          NativeClientLoggingProtos.NativeClientLogEventRequest.access$2902(localNativeClientLogEventRequest, this.gogglesId_);
          if ((j & 0x20) == 32)
            i |= 32;
          NativeClientLoggingProtos.NativeClientLogEventRequest.access$3002(localNativeClientLogEventRequest, this.trackingId_);
          if ((j & 0x40) == 64)
            i |= 64;
          NativeClientLoggingProtos.NativeClientLogEventRequest.access$3102(localNativeClientLogEventRequest, this.sessionId_);
          NativeClientLoggingProtos.NativeClientLogEventRequest.access$3202(localNativeClientLogEventRequest, i);
          return localNativeClientLogEventRequest;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.msSinceEpoch_ = 0L;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.clientClick_ = NativeClientLoggingProtos.NativeClientClick.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.clientRating_ = NativeClientLoggingProtos.NativeClientRating.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.clientInstall_ = NativeClientLoggingProtos.NativeClientInstall.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.gogglesId_ = 0L;
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        this.trackingId_ = "";
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        this.sessionId_ = "";
        this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
        return this;
      }

      public Builder clearClientClick()
      {
        this.clientClick_ = NativeClientLoggingProtos.NativeClientClick.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      public Builder clearClientInstall()
      {
        this.clientInstall_ = NativeClientLoggingProtos.NativeClientInstall.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        return this;
      }

      public Builder clearClientRating()
      {
        this.clientRating_ = NativeClientLoggingProtos.NativeClientRating.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearGogglesId()
      {
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        this.gogglesId_ = 0L;
        return this;
      }

      public Builder clearMsSinceEpoch()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.msSinceEpoch_ = 0L;
        return this;
      }

      public Builder clearSessionId()
      {
        this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
        this.sessionId_ = NativeClientLoggingProtos.NativeClientLogEventRequest.getDefaultInstance().getSessionId();
        return this;
      }

      public Builder clearTrackingId()
      {
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        this.trackingId_ = NativeClientLoggingProtos.NativeClientLogEventRequest.getDefaultInstance().getTrackingId();
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public NativeClientLoggingProtos.NativeClientClick getClientClick()
      {
        return this.clientClick_;
      }

      public NativeClientLoggingProtos.NativeClientInstall getClientInstall()
      {
        return this.clientInstall_;
      }

      public NativeClientLoggingProtos.NativeClientRating getClientRating()
      {
        return this.clientRating_;
      }

      public NativeClientLoggingProtos.NativeClientLogEventRequest getDefaultInstanceForType()
      {
        return NativeClientLoggingProtos.NativeClientLogEventRequest.getDefaultInstance();
      }

      @Deprecated
      public long getGogglesId()
      {
        return this.gogglesId_;
      }

      public long getMsSinceEpoch()
      {
        return this.msSinceEpoch_;
      }

      public String getSessionId()
      {
        Object localObject = this.sessionId_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.sessionId_ = str;
          return str;
        }
        return (String)localObject;
      }

      public String getTrackingId()
      {
        Object localObject = this.trackingId_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.trackingId_ = str;
          return str;
        }
        return (String)localObject;
      }

      public boolean hasClientClick()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasClientInstall()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public boolean hasClientRating()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      @Deprecated
      public boolean hasGogglesId()
      {
        return (0x10 & this.bitField0_) == 16;
      }

      public boolean hasMsSinceEpoch()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public boolean hasSessionId()
      {
        return (0x40 & this.bitField0_) == 64;
      }

      public boolean hasTrackingId()
      {
        return (0x20 & this.bitField0_) == 32;
      }

      public final boolean isInitialized()
      {
        return (!hasClientClick()) || (getClientClick().isInitialized());
      }

      public Builder mergeClientClick(NativeClientLoggingProtos.NativeClientClick paramNativeClientClick)
      {
        if (((0x2 & this.bitField0_) == 2) && (this.clientClick_ != NativeClientLoggingProtos.NativeClientClick.getDefaultInstance()));
        for (this.clientClick_ = NativeClientLoggingProtos.NativeClientClick.newBuilder(this.clientClick_).mergeFrom(paramNativeClientClick).buildPartial(); ; this.clientClick_ = paramNativeClientClick)
        {
          this.bitField0_ = (0x2 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeClientInstall(NativeClientLoggingProtos.NativeClientInstall paramNativeClientInstall)
      {
        if (((0x8 & this.bitField0_) == 8) && (this.clientInstall_ != NativeClientLoggingProtos.NativeClientInstall.getDefaultInstance()));
        for (this.clientInstall_ = NativeClientLoggingProtos.NativeClientInstall.newBuilder(this.clientInstall_).mergeFrom(paramNativeClientInstall).buildPartial(); ; this.clientInstall_ = paramNativeClientInstall)
        {
          this.bitField0_ = (0x8 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeClientRating(NativeClientLoggingProtos.NativeClientRating paramNativeClientRating)
      {
        if (((0x4 & this.bitField0_) == 4) && (this.clientRating_ != NativeClientLoggingProtos.NativeClientRating.getDefaultInstance()));
        for (this.clientRating_ = NativeClientLoggingProtos.NativeClientRating.newBuilder(this.clientRating_).mergeFrom(paramNativeClientRating).buildPartial(); ; this.clientRating_ = paramNativeClientRating)
        {
          this.bitField0_ = (0x4 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeFrom(NativeClientLoggingProtos.NativeClientLogEventRequest paramNativeClientLogEventRequest)
      {
        if (paramNativeClientLogEventRequest == NativeClientLoggingProtos.NativeClientLogEventRequest.getDefaultInstance());
        do
        {
          return this;
          if (paramNativeClientLogEventRequest.hasMsSinceEpoch())
            setMsSinceEpoch(paramNativeClientLogEventRequest.getMsSinceEpoch());
          if (paramNativeClientLogEventRequest.hasClientClick())
            mergeClientClick(paramNativeClientLogEventRequest.getClientClick());
          if (paramNativeClientLogEventRequest.hasClientRating())
            mergeClientRating(paramNativeClientLogEventRequest.getClientRating());
          if (paramNativeClientLogEventRequest.hasClientInstall())
            mergeClientInstall(paramNativeClientLogEventRequest.getClientInstall());
          if (paramNativeClientLogEventRequest.hasGogglesId())
            setGogglesId(paramNativeClientLogEventRequest.getGogglesId());
          if (paramNativeClientLogEventRequest.hasTrackingId())
            setTrackingId(paramNativeClientLogEventRequest.getTrackingId());
        }
        while (!paramNativeClientLogEventRequest.hasSessionId());
        setSessionId(paramNativeClientLogEventRequest.getSessionId());
        return this;
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        while (true)
        {
          int i = paramCodedInputStream.readTag();
          switch (i)
          {
          default:
            if (parseUnknownField(paramCodedInputStream, paramExtensionRegistryLite, i))
              continue;
          case 0:
            return this;
          case 18:
            NativeClientLoggingProtos.NativeClientClick.Builder localBuilder2 = NativeClientLoggingProtos.NativeClientClick.newBuilder();
            if (hasClientClick())
              localBuilder2.mergeFrom(getClientClick());
            paramCodedInputStream.readMessage(localBuilder2, paramExtensionRegistryLite);
            setClientClick(localBuilder2.buildPartial());
            break;
          case 26:
            NativeClientLoggingProtos.NativeClientRating.Builder localBuilder1 = NativeClientLoggingProtos.NativeClientRating.newBuilder();
            if (hasClientRating())
              localBuilder1.mergeFrom(getClientRating());
            paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
            setClientRating(localBuilder1.buildPartial());
            break;
          case 32:
            this.bitField0_ = (0x1 | this.bitField0_);
            this.msSinceEpoch_ = paramCodedInputStream.readInt64();
            break;
          case 42:
            NativeClientLoggingProtos.NativeClientInstall.Builder localBuilder = NativeClientLoggingProtos.NativeClientInstall.newBuilder();
            if (hasClientInstall())
              localBuilder.mergeFrom(getClientInstall());
            paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
            setClientInstall(localBuilder.buildPartial());
            break;
          case 73:
            this.bitField0_ = (0x10 | this.bitField0_);
            this.gogglesId_ = paramCodedInputStream.readFixed64();
            break;
          case 82:
            this.bitField0_ = (0x20 | this.bitField0_);
            this.trackingId_ = paramCodedInputStream.readBytes();
            break;
          case 90:
          }
          this.bitField0_ = (0x40 | this.bitField0_);
          this.sessionId_ = paramCodedInputStream.readBytes();
        }
      }

      public Builder setClientClick(NativeClientLoggingProtos.NativeClientClick.Builder paramBuilder)
      {
        this.clientClick_ = paramBuilder.build();
        this.bitField0_ = (0x2 | this.bitField0_);
        return this;
      }

      public Builder setClientClick(NativeClientLoggingProtos.NativeClientClick paramNativeClientClick)
      {
        if (paramNativeClientClick == null)
          throw new NullPointerException();
        this.clientClick_ = paramNativeClientClick;
        this.bitField0_ = (0x2 | this.bitField0_);
        return this;
      }

      public Builder setClientInstall(NativeClientLoggingProtos.NativeClientInstall.Builder paramBuilder)
      {
        this.clientInstall_ = paramBuilder.build();
        this.bitField0_ = (0x8 | this.bitField0_);
        return this;
      }

      public Builder setClientInstall(NativeClientLoggingProtos.NativeClientInstall paramNativeClientInstall)
      {
        if (paramNativeClientInstall == null)
          throw new NullPointerException();
        this.clientInstall_ = paramNativeClientInstall;
        this.bitField0_ = (0x8 | this.bitField0_);
        return this;
      }

      public Builder setClientRating(NativeClientLoggingProtos.NativeClientRating.Builder paramBuilder)
      {
        this.clientRating_ = paramBuilder.build();
        this.bitField0_ = (0x4 | this.bitField0_);
        return this;
      }

      public Builder setClientRating(NativeClientLoggingProtos.NativeClientRating paramNativeClientRating)
      {
        if (paramNativeClientRating == null)
          throw new NullPointerException();
        this.clientRating_ = paramNativeClientRating;
        this.bitField0_ = (0x4 | this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder setGogglesId(long paramLong)
      {
        this.bitField0_ = (0x10 | this.bitField0_);
        this.gogglesId_ = paramLong;
        return this;
      }

      public Builder setMsSinceEpoch(long paramLong)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.msSinceEpoch_ = paramLong;
        return this;
      }

      public Builder setSessionId(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x40 | this.bitField0_);
        this.sessionId_ = paramString;
        return this;
      }

      void setSessionId(ByteString paramByteString)
      {
        this.bitField0_ = (0x40 | this.bitField0_);
        this.sessionId_ = paramByteString;
      }

      public Builder setTrackingId(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x20 | this.bitField0_);
        this.trackingId_ = paramString;
        return this;
      }

      void setTrackingId(ByteString paramByteString)
      {
        this.bitField0_ = (0x20 | this.bitField0_);
        this.trackingId_ = paramByteString;
      }
    }
  }

  public static abstract interface NativeClientLogEventRequestOrBuilder extends MessageLiteOrBuilder
  {
    public abstract NativeClientLoggingProtos.NativeClientClick getClientClick();

    public abstract NativeClientLoggingProtos.NativeClientInstall getClientInstall();

    public abstract NativeClientLoggingProtos.NativeClientRating getClientRating();

    @Deprecated
    public abstract long getGogglesId();

    public abstract long getMsSinceEpoch();

    public abstract String getSessionId();

    public abstract String getTrackingId();

    public abstract boolean hasClientClick();

    public abstract boolean hasClientInstall();

    public abstract boolean hasClientRating();

    @Deprecated
    public abstract boolean hasGogglesId();

    public abstract boolean hasMsSinceEpoch();

    public abstract boolean hasSessionId();

    public abstract boolean hasTrackingId();
  }

  public static final class NativeClientLogEventResponse extends GeneratedMessageLite
    implements NativeClientLoggingProtos.NativeClientLogEventResponseOrBuilder
  {
    private static final NativeClientLogEventResponse defaultInstance = new NativeClientLogEventResponse(true);
    private static final long serialVersionUID;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;

    static
    {
      defaultInstance.initFields();
    }

    private NativeClientLogEventResponse(Builder paramBuilder)
    {
      super();
    }

    private NativeClientLogEventResponse(boolean paramBoolean)
    {
    }

    public static NativeClientLogEventResponse getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
    }

    public static Builder newBuilder()
    {
      return Builder.access$3400();
    }

    public static Builder newBuilder(NativeClientLogEventResponse paramNativeClientLogEventResponse)
    {
      return newBuilder().mergeFrom(paramNativeClientLogEventResponse);
    }

    public static NativeClientLogEventResponse parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static NativeClientLogEventResponse parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static NativeClientLogEventResponse parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static NativeClientLogEventResponse parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static NativeClientLogEventResponse parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static NativeClientLogEventResponse parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static NativeClientLogEventResponse parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static NativeClientLogEventResponse parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static NativeClientLogEventResponse parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static NativeClientLogEventResponse parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public NativeClientLogEventResponse getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      this.memoizedSerializedSize = 0;
      return 0;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      this.memoizedIsInitialized = 1;
      return true;
    }

    public Builder newBuilderForType()
    {
      return newBuilder();
    }

    public Builder toBuilder()
    {
      return newBuilder(this);
    }

    protected Object writeReplace()
      throws ObjectStreamException
    {
      return super.writeReplace();
    }

    public void writeTo(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      getSerializedSize();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NativeClientLoggingProtos.NativeClientLogEventResponse, Builder>
      implements NativeClientLoggingProtos.NativeClientLogEventResponseOrBuilder
    {
      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private NativeClientLoggingProtos.NativeClientLogEventResponse buildParsed()
        throws InvalidProtocolBufferException
      {
        NativeClientLoggingProtos.NativeClientLogEventResponse localNativeClientLogEventResponse = buildPartial();
        if (!localNativeClientLogEventResponse.isInitialized())
          throw newUninitializedMessageException(localNativeClientLogEventResponse).asInvalidProtocolBufferException();
        return localNativeClientLogEventResponse;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public NativeClientLoggingProtos.NativeClientLogEventResponse build()
      {
        NativeClientLoggingProtos.NativeClientLogEventResponse localNativeClientLogEventResponse = buildPartial();
        if (!localNativeClientLogEventResponse.isInitialized())
          throw newUninitializedMessageException(localNativeClientLogEventResponse);
        return localNativeClientLogEventResponse;
      }

      public NativeClientLoggingProtos.NativeClientLogEventResponse buildPartial()
      {
        return new NativeClientLoggingProtos.NativeClientLogEventResponse(this, null);
      }

      public Builder clear()
      {
        super.clear();
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public NativeClientLoggingProtos.NativeClientLogEventResponse getDefaultInstanceForType()
      {
        return NativeClientLoggingProtos.NativeClientLogEventResponse.getDefaultInstance();
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(NativeClientLoggingProtos.NativeClientLogEventResponse paramNativeClientLogEventResponse)
      {
        if (paramNativeClientLogEventResponse == NativeClientLoggingProtos.NativeClientLogEventResponse.getDefaultInstance());
        return this;
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        int i;
        do
        {
          i = paramCodedInputStream.readTag();
          switch (i)
          {
          default:
          case 0:
          }
        }
        while (parseUnknownField(paramCodedInputStream, paramExtensionRegistryLite, i));
        return this;
      }
    }
  }

  public static abstract interface NativeClientLogEventResponseOrBuilder extends MessageLiteOrBuilder
  {
  }

  public static final class NativeClientRating extends GeneratedMessageLite
    implements NativeClientLoggingProtos.NativeClientRatingOrBuilder
  {
    public static final int FIVE_STAR_RATING_FIELD_NUMBER = 1;
    private static final NativeClientRating defaultInstance = new NativeClientRating(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private int fiveStarRating_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;

    static
    {
      defaultInstance.initFields();
    }

    private NativeClientRating(Builder paramBuilder)
    {
      super();
    }

    private NativeClientRating(boolean paramBoolean)
    {
    }

    public static NativeClientRating getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.fiveStarRating_ = 0;
    }

    public static Builder newBuilder()
    {
      return Builder.access$1200();
    }

    public static Builder newBuilder(NativeClientRating paramNativeClientRating)
    {
      return newBuilder().mergeFrom(paramNativeClientRating);
    }

    public static NativeClientRating parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static NativeClientRating parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static NativeClientRating parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static NativeClientRating parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static NativeClientRating parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static NativeClientRating parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static NativeClientRating parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static NativeClientRating parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static NativeClientRating parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static NativeClientRating parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public NativeClientRating getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public int getFiveStarRating()
    {
      return this.fiveStarRating_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeInt32Size(1, this.fiveStarRating_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public boolean hasFiveStarRating()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      this.memoizedIsInitialized = 1;
      return true;
    }

    public Builder newBuilderForType()
    {
      return newBuilder();
    }

    public Builder toBuilder()
    {
      return newBuilder(this);
    }

    protected Object writeReplace()
      throws ObjectStreamException
    {
      return super.writeReplace();
    }

    public void writeTo(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      getSerializedSize();
      if ((0x1 & this.bitField0_) == 1)
        paramCodedOutputStream.writeInt32(1, this.fiveStarRating_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NativeClientLoggingProtos.NativeClientRating, Builder>
      implements NativeClientLoggingProtos.NativeClientRatingOrBuilder
    {
      private int bitField0_;
      private int fiveStarRating_;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private NativeClientLoggingProtos.NativeClientRating buildParsed()
        throws InvalidProtocolBufferException
      {
        NativeClientLoggingProtos.NativeClientRating localNativeClientRating = buildPartial();
        if (!localNativeClientRating.isInitialized())
          throw newUninitializedMessageException(localNativeClientRating).asInvalidProtocolBufferException();
        return localNativeClientRating;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public NativeClientLoggingProtos.NativeClientRating build()
      {
        NativeClientLoggingProtos.NativeClientRating localNativeClientRating = buildPartial();
        if (!localNativeClientRating.isInitialized())
          throw newUninitializedMessageException(localNativeClientRating);
        return localNativeClientRating;
      }

      public NativeClientLoggingProtos.NativeClientRating buildPartial()
      {
        int i = 1;
        NativeClientLoggingProtos.NativeClientRating localNativeClientRating = new NativeClientLoggingProtos.NativeClientRating(this, null);
        if ((0x1 & this.bitField0_) == i);
        while (true)
        {
          NativeClientLoggingProtos.NativeClientRating.access$1402(localNativeClientRating, this.fiveStarRating_);
          NativeClientLoggingProtos.NativeClientRating.access$1502(localNativeClientRating, i);
          return localNativeClientRating;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.fiveStarRating_ = 0;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clearFiveStarRating()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.fiveStarRating_ = 0;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public NativeClientLoggingProtos.NativeClientRating getDefaultInstanceForType()
      {
        return NativeClientLoggingProtos.NativeClientRating.getDefaultInstance();
      }

      public int getFiveStarRating()
      {
        return this.fiveStarRating_;
      }

      public boolean hasFiveStarRating()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(NativeClientLoggingProtos.NativeClientRating paramNativeClientRating)
      {
        if (paramNativeClientRating == NativeClientLoggingProtos.NativeClientRating.getDefaultInstance());
        while (!paramNativeClientRating.hasFiveStarRating())
          return this;
        setFiveStarRating(paramNativeClientRating.getFiveStarRating());
        return this;
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        while (true)
        {
          int i = paramCodedInputStream.readTag();
          switch (i)
          {
          default:
            if (parseUnknownField(paramCodedInputStream, paramExtensionRegistryLite, i))
              continue;
          case 0:
            return this;
          case 8:
          }
          this.bitField0_ = (0x1 | this.bitField0_);
          this.fiveStarRating_ = paramCodedInputStream.readInt32();
        }
      }

      public Builder setFiveStarRating(int paramInt)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.fiveStarRating_ = paramInt;
        return this;
      }
    }
  }

  public static abstract interface NativeClientRatingOrBuilder extends MessageLiteOrBuilder
  {
    public abstract int getFiveStarRating();

    public abstract boolean hasFiveStarRating();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.NativeClientLoggingProtos
 * JD-Core Version:    0.6.2
 */