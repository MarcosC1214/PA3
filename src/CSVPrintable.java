import java.io.*;

interface CSVPrintable { // The CSVPrintable interface defines the methods that classes implementing it must provide, which include getting the name, ID, and printing CSV data.
    String getName();
    int getID();
    void csvPrintln(PrintWriter out);
}
