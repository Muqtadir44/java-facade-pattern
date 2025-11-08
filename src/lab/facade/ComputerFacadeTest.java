package lab.facade;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ComputerFacadeTest {

    static class MockDrive implements IHardDrive {
        @Override
        public byte[] read(long lba, int size) {
            byte[] data = new byte[size];
            for (int i = 0; i < size; i++) data[i] = (byte) 42; // predictable data
            return data;
        }
    }

    @Test
    public void testComputerStartLoadsBootData() {
        ComputerFacade facade = new ComputerFacade(new MockDrive());
        assertDoesNotThrow(facade::start);
    }
}
