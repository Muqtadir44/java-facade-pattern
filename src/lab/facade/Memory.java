package lab.facade;

public class Memory {
    private static int loadCount = 0;
    private byte[] lastData;

    public void load(long position, byte[] data) {
        loadCount++;
        lastData = data.clone();
        System.out.println("Memory: loading " + data.length + " bytes at " + position);
    }

    public MemoryState saveState() {
        System.out.println("Memory: saving snapshot");
        return new MemoryState(lastData);
    }

    public void restoreState(MemoryState state) {
        if (state != null && state.data != null)
            lastData = state.data.clone();
        System.out.println("Memory: state restored");
    }

    public static int getLoadCount() {
        return loadCount;
    }

    public static class MemoryState {
        byte[] data;
        public MemoryState(byte[] data) {
            this.data = data;
        }
    }
}
