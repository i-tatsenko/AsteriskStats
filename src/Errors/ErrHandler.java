package Errors;

import org.jdom2.JDOMException;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;


public class ErrHandler {
    public static void fileMissing(String file){
        System.err.println("There is no settings file: " + file);
        System.err.println("Program shutdown.");
        System.err.println("Operation system: " + System.getProperties().getProperty("os.name"));
        System.exit(0);
    }

    public static void SQLError(SQLException e){

    }

    public static void ErrorOutput(JDOMException e, PrintStream out){
        out.println(e);
    }

    public static void ErrorOutput(IOException e, PrintStream out){
        out.println(e);
    }

    public static void ErrorOutput(SQLException e, PrintStream out){
        out.println(e);
    }
}
