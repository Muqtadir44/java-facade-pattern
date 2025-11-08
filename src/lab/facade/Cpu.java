package lab.facade;

public class Cpu {
    private long programCounter;
    private boolean running;
    private static int freezeCount = 0;

    public void freeze() {
        freezeCount++;
        running = false;
        System.out.println("CPU: freeze");
    }

    public void jump(long position) {
        programCounter = position;
        System.out.println("CPU: jump to " + position);
    }

    public void execute() {
        running = true;
        System.out.println("CPU: execute");
    }

    // for sleep/wake
    public CpuState saveState() {
        System.out.println("CPU: saving state");
        return new CpuState(programCounter, running);
    }

    public void restoreState(CpuState state) {
        this.programCounter = state.programCounter;
        this.running = state.running;
        System.out.println("CPU: state restored (PC=" + programCounter + ")");
    }

    public static int getFreezeCount() {
        return freezeCount;
    }

    public static class CpuState {
        long programCounter;
        boolean running;
        public CpuState(long programCounter, boolean running) {
            this.programCounter = programCounter;
            this.running = running;
        }
    }
}
