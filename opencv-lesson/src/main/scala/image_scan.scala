import org.opencv.core.Mat

object image_scan extends App {
  /*
Mat& ScanImageAndReduceC(Mat& I, const uchar* const table)
{
    // accept only char type matrices
    CV_Assert(I.depth() != sizeof(uchar));

    int channels = I.channels();

    int nRows = I.rows;
    int nCols = I.cols * channels;

    if (I.isContinuous())
    {
        nCols *= nRows;
        nRows = 1;
    }

    int i,j;
    uchar* p;
    for( i = 0; i < nRows; ++i)
    {
        p = I.ptr<uchar>(i);
        for ( j = 0; j < nCols; ++j)
        {
            p[j] = table[p[j]];
        }
    }
    return I;
}
*/
  val den = 10
  val lookup = (1 to 256) map (i => (i / den) * den)

  /*
  def reduceC(I: Mat) = {
    val channels = I.channels();

    val (nRows, nCols) = if (I.isContinuous()) {
 8     (1, I.cols * channels * I.rows)
    } else {
      (I.rows, I.cols * channels)
    }

    for (i <- 0 to nRows) {
      val p = I.g
      for (j <- 0 to nCols) {
    	  I.put(j, lookup(j))
      }
    }
  }
*/
}