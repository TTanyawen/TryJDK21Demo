package demo;

public class PatternDemo {
    static String handle(Object obj) {
        return switch (obj) {
            case Integer i -> "int " + i;
            case String s  -> "str " + s;
            case null      -> "null";
            default        -> "other";
        };
    }

    public static void main(String[] args) {
        System.out.println(handle(String.valueOf(1)));
    }
}
