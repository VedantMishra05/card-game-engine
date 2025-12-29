package engine.util;


public final class Sleep {
    private Sleep() {

    }

    public static void sleep(int milli) {
        try {
            Thread.sleep(milli);
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
