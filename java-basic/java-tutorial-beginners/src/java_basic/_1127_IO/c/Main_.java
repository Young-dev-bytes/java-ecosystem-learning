package java_basic._1127_IO.c;

public class Main_ {
    public static void main(String[] args) {


        FileReader_ fileReader = new FileReader_();
        StringReader_ stringReader_ = new StringReader_();
        BufferedReader_ bufferedReaderFile = new BufferedReader_(fileReader);
        BufferedReader_ bufferedReaderString = new BufferedReader_(stringReader_);

        // bufferedReaderFile.readFile();
        bufferedReaderFile.readFiles(10);
        bufferedReaderFile.read();

        // bufferedReaderString.readString();
        // bufferedReaderString.readStrings(5);
        bufferedReaderString.read();


    }
}
