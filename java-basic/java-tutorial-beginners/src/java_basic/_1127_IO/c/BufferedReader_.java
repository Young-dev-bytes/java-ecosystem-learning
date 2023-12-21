package java_basic._1127_IO.c;

public class BufferedReader_ extends Reader_ {

    private Reader_ reader;

    public BufferedReader_(Reader_ reader) {
        this.reader = reader;
    }

    @Override
    public void read() {
        reader.read();
    }

    /*@Override
    public void readFile() {
        reader.readFile();
        reader.readString();
    }

    @Override
    public void readString() {
        super.readString();
    }*/

    public void readFile() {
        reader.readFile();
    }

    public void readFiles(int num) {
        for (int i = 0; i < num; i++) {
            read();
        }
    }

    public void readString() {
        reader.readString();
    }

    public void readStrings(int num) {
        for (int i = 0; i < num; i++) {
            reader.readString();
        }
    }
}
