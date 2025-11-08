package lab.facade;

public class ComputerFacade {
    private final Cpu cpu;
    private final Memory memory;
    private final IHardDrive hd;

    private static final long BOOT_ADDRESS = 0x1000;
    private static final long BOOT_SECTOR = 0x2000;
    private static final int SECTOR_SIZE = 64;

    // For sleep/wake
    private Cpu.CpuState savedCpuState;
    private Memory.MemoryState savedMemoryState;
    private boolean sleeping = false;

    public ComputerFacade(IHardDrive hd) {
        this.cpu = new Cpu();
        this.memory = new Memory();
        this.hd = hd;
    }

    public ComputerFacade() {
        this(new Hdd()); // default HDD
    }

    public void start() {
        System.out.println("Facade: starting computer using " + hd.getClass().getSimpleName());
        cpu.freeze();
        byte[] bootData = hd.read(BOOT_SECTOR, SECTOR_SIZE);
        memory.load(BOOT_ADDRESS, bootData);
        cpu.jump(BOOT_ADDRESS);
        cpu.execute();
        System.out.println("Facade: computer started\n");
    }

    public void shutdown() {
        System.out.println("Facade: shutting down computer");
        System.out.println("---- METRICS REPORT ----");
        System.out.println("CPU freezes: " + Cpu.getFreezeCount());
        System.out.println("Memory loads: " + Memory.getLoadCount());
        if (hd instanceof Hdd) {
            System.out.println("HDD reads: " + Hdd.getReadCount());
        } else if (hd instanceof Ssd) {
            System.out.println("SSD reads: " + Ssd.getReadCount());
        }
        System.out.println("Facade: power off\n");
    }

    public void sleep() {
        System.out.println("Facade: putting system to sleep...");
        savedCpuState = cpu.saveState();
        savedMemoryState = memory.saveState();
        sleeping = true;
        System.out.println("Facade: power reduced (simulated)\n");
    }

    public void wake() {
        if (!sleeping) {
            System.out.println("Facade: not sleeping, nothing to restore.\n");
            return;
        }
        System.out.println("Facade: waking system...");
        cpu.restoreState(savedCpuState);
        memory.restoreState(savedMemoryState);
        sleeping = false;
        System.out.println("Facade: wake complete.\n");
    }
}
