package lab.facade;
public class HardDrive {
 public byte[] read(long lba, int size) {
 System.out.println("HardDrive: reading " + size + " bytes from LBA " + lba);
 // return dummy data
 byte[] data = new byte[size];
 for (int i = 0; i < size; i++) data[i] = (byte)(i % 256);
 return data;
 }
}
