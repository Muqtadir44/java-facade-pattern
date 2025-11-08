package lab.facade;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Using HDD ---");
        ComputerFacade hddComputer = new ComputerFacade(new Hdd());
        hddComputer.start();
        hddComputer.sleep();
        hddComputer.wake();
        hddComputer.shutdown();

        System.out.println("\n--- Using SSD ---");
        ComputerFacade ssdComputer = new ComputerFacade(new Ssd());
        ssdComputer.start();
        ssdComputer.shutdown();
    }
}
