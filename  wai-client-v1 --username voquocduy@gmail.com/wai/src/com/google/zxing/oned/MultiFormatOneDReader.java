package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;
import java.util.Vector;

public final class MultiFormatOneDReader extends OneDReader
{
  private final Vector readers;

  public MultiFormatOneDReader(Hashtable paramHashtable)
  {
    Vector localVector;
    if (paramHashtable == null)
    {
      localVector = null;
      if ((paramHashtable == null) || (paramHashtable.get(DecodeHintType.ASSUME_CODE_39_CHECK_DIGIT) == null))
        break label251;
    }
    label251: for (boolean bool = true; ; bool = false)
    {
      this.readers = new Vector();
      if (localVector != null)
      {
        if ((localVector.contains(BarcodeFormat.EAN_13)) || (localVector.contains(BarcodeFormat.UPC_A)) || (localVector.contains(BarcodeFormat.EAN_8)) || (localVector.contains(BarcodeFormat.UPC_E)))
          this.readers.addElement(new MultiFormatUPCEANReader(paramHashtable));
        if (localVector.contains(BarcodeFormat.CODE_39))
          this.readers.addElement(new Code39Reader(bool));
        if (localVector.contains(BarcodeFormat.CODE_128))
          this.readers.addElement(new Code128Reader());
        if (localVector.contains(BarcodeFormat.ITF))
          this.readers.addElement(new ITFReader());
      }
      if (this.readers.isEmpty())
      {
        this.readers.addElement(new MultiFormatUPCEANReader(paramHashtable));
        this.readers.addElement(new Code39Reader());
        this.readers.addElement(new Code128Reader());
        this.readers.addElement(new ITFReader());
      }
      return;
      localVector = (Vector)paramHashtable.get(DecodeHintType.POSSIBLE_FORMATS);
      break;
    }
  }

  public Result decodeRow(int paramInt, BitArray paramBitArray, Hashtable paramHashtable)
    throws NotFoundException
  {
    int i = this.readers.size();
    int j = 0;
    while (j < i)
    {
      OneDReader localOneDReader = (OneDReader)this.readers.elementAt(j);
      try
      {
        Result localResult = localOneDReader.decodeRow(paramInt, paramBitArray, paramHashtable);
        if (localResult != null)
          return localResult;
      }
      catch (ReaderException localReaderException)
      {
        j++;
      }
    }
    throw NotFoundException.getNotFoundInstance();
  }

  public void reset()
  {
    int i = this.readers.size();
    for (int j = 0; j < i; j++)
      ((Reader)this.readers.elementAt(j)).reset();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.MultiFormatOneDReader
 * JD-Core Version:    0.6.2
 */