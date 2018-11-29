package main;

public class Log
{
    public static void error(String message, Throwable throwable)
    {
        System.out.println(message);
    }

    public static void info(String message)
    {
        System.out.println(message);
    }
}
