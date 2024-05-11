public class BakerySimulation {

    static class Baker extends Thread {
        private String item;
        private int quantity;
        private long delay;

        public Baker(String item, int quantity, long delay) {
            this.item = item;
            this.quantity = quantity;
            this.delay = delay;
        }

        @Override
        public void run() {
            for (int i = 1; i <= quantity; i++) {
                try {
                    System.out.println("Starting to bake " + item + " " + i);
                    Thread.sleep(delay); // Simulating baking time
                    System.out.println(item + " " + i + " baked.");
                } catch (InterruptedException e) {
                    System.out.println("Baking " + item + " interrupted.");
                }
            }
            System.out.println("Finished baking " + quantity + " " + item + ".");
        }
    }

    public static void main(String[] args) {
        Baker cakeBaker = new Baker("cake", 5, 1000); // 5 cakes with 1 second delay each
        Baker cookieBaker = new Baker("cookie", 10, 500); // 10 cookies with 0.5 second delay each

        cakeBaker.start(); // Start baking cakes
        cookieBaker.start(); // Start baking cookies

        try {
            cakeBaker.join(); // Wait for cake baker to finish
            cookieBaker.join(); // Wait for cookie baker to finish
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("All baking tasks completed.");
    }
}
