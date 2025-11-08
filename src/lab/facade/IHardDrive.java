package lab.facade;

public interface IHardDrive {
    byte[] read(long lba, int size);
}
