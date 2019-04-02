public class Main {

    public static void main(String[] args) throws InterruptedException {

        Blockchain blockchain = new Blockchain();
        blockchain.run();
        try {
            blockchain.join();
        } catch (InterruptedException e) {
            System.out.println("Problem joining blockchain");
            e.printStackTrace();
        }

        Thread.sleep(10000);
        blockchain.shutdown();
        System.exit(0);
    }

}
