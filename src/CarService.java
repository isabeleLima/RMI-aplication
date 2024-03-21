import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CarService extends Remote {
    String getCarInfo(String renavan) throws RemoteException;

    boolean addCar(String renavan, String nome, String categoria, int anoFabricacao, int quantidade, double preco) throws RemoteException;

    List<String> listCars() throws RemoteException;

    String removeCar(String renavan) throws RemoteException;

    String findCarByRenavan(String renavan) throws RemoteException;

    int getNumberOfCars() throws RemoteException;

    String buyCar(String renavan) throws RemoteException;

    String authenticate(String username, String password) throws RemoteException;
}
