package manage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BankAccountService extends Remote {
    int getAccountBalance(String sdt, String matKhau) throws RemoteException;
}
