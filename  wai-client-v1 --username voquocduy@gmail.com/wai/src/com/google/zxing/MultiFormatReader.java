package com.google.zxing;

import com.google.zxing.oned.MultiFormatOneDReader;
import com.google.zxing.oned.MultiFormatUPCEANReader;
import com.google.zxing.qrcode.QRCodeReader;
import java.util.Hashtable;
import java.util.Vector;

public final class MultiFormatReader
  implements Reader
{
  private Hashtable hints;
  private Vector readers;

  private Result decodeInternal(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException
  {
    int i = this.readers.size();
    int j = 0;
    while (j < i)
    {
      Reader localReader = (Reader)this.readers.elementAt(j);
      try
      {
        Result localResult = localReader.decode(paramBinaryBitmap, this.hints);
        return localResult;
      }
      catch (ReaderException localReaderException)
      {
        j++;
      }
    }
    throw NotFoundException.getNotFoundInstance();
  }

  public Result decode(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException
  {
    setHints(null);
    return decodeInternal(paramBinaryBitmap);
  }

  public Result decode(BinaryBitmap paramBinaryBitmap, Hashtable paramHashtable)
    throws NotFoundException
  {
    setHints(paramHashtable);
    return decodeInternal(paramBinaryBitmap);
  }

  public Result decodeWithState(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException
  {
    if (this.readers == null)
      setHints(null);
    return decodeInternal(paramBinaryBitmap);
  }

  public void reset()
  {
    int i = this.readers.size();
    for (int j = 0; j < i; j++)
      ((Reader)this.readers.elementAt(j)).reset();
  }

  public void setHints(Hashtable paramHashtable)
  {
    this.hints = paramHashtable;
    int i;
    Vector localVector;
    label27: int j;
    label115: int k;
    if ((paramHashtable != null) && (paramHashtable.containsKey(DecodeHintType.TRY_HARDER)))
    {
      i = 1;
      if (paramHashtable != null)
        break label298;
      localVector = null;
      this.readers = new Vector();
      if (localVector != null)
      {
        if ((!localVector.contains(BarcodeFormat.UPC_A)) && (!localVector.contains(BarcodeFormat.UPC_E)) && (!localVector.contains(BarcodeFormat.EAN_13)) && (!localVector.contains(BarcodeFormat.EAN_8)) && (!localVector.contains(BarcodeFormat.CODE_39)) && (!localVector.contains(BarcodeFormat.CODE_128)) && (!localVector.contains(BarcodeFormat.ITF)))
          break label312;
        j = 1;
        if ((localVector.contains(BarcodeFormat.CODE_39)) || (localVector.contains(BarcodeFormat.CODE_128)) || (localVector.contains(BarcodeFormat.ITF)))
          break label318;
        k = 1;
        label148: if ((j != 0) && (i == 0))
        {
          if (k == 0)
            break label324;
          this.readers.addElement(new MultiFormatUPCEANReader(paramHashtable));
        }
        label177: if (localVector.contains(BarcodeFormat.QR_CODE))
          this.readers.addElement(new QRCodeReader());
        if ((j != 0) && (i != 0))
        {
          if (k == 0)
            break label342;
          this.readers.addElement(new MultiFormatUPCEANReader(paramHashtable));
        }
      }
    }
    while (true)
    {
      if (this.readers.isEmpty())
      {
        if (i == 0)
          this.readers.addElement(new MultiFormatOneDReader(paramHashtable));
        this.readers.addElement(new QRCodeReader());
        if (i != 0)
          this.readers.addElement(new MultiFormatOneDReader(paramHashtable));
      }
      return;
      i = 0;
      break;
      label298: localVector = (Vector)paramHashtable.get(DecodeHintType.POSSIBLE_FORMATS);
      break label27;
      label312: j = 0;
      break label115;
      label318: k = 0;
      break label148;
      label324: this.readers.addElement(new MultiFormatOneDReader(paramHashtable));
      break label177;
      label342: this.readers.addElement(new MultiFormatOneDReader(paramHashtable));
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.MultiFormatReader
 * JD-Core Version:    0.6.2
 */