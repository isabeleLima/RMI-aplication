import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarServer extends UnicastRemoteObject implements CarService {
    private List<Car> carList;
    private List<Client> clientList;
    protected CarServer() throws RemoteException {
        super();
        this.carList = new ArrayList<>();
        this.clientList = new ArrayList<>();

        addCar("000001","fiat", "economico",2024,5,20000);
        addCar("000002","hb20", "economico",2024,5,20000);
        addCar("000003","toyota", "economico",2024,5,20000);

        clientList.add(new Client("Cliente maria", "user1", "123", "cliente"));
        clientList.add(new Client("Funcionario Jose", "user2", "123", "funcionario"));

    }

    public static void main(String[] args) {
        try {
            CarServer server = new CarServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("CarService", server);
            System.out.println("Server started.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public String getCarInfo(String renavan) throws RemoteException {
        for (Car car : carList) {
            if (car.getRenavan().equals(renavan)) {
                return car.toString();
            }
        }
        return "Carro não encontrado";
    }

    @Override
    public boolean addCar(String renavan, String nome, String categoria, int anoFabricacao, int quantidade, double preco) throws RemoteException {
        Car car = new Car(renavan, nome, categoria, anoFabricacao, quantidade, preco);
        return carList.add(car);
    }

    @Override
    public List<String> listCars() throws RemoteException {
        List<String> carInfoList = new ArrayList<>();
        for (Car car : carList) {
            carInfoList.add(car.toString());
        }
        return carInfoList;
    }

    @Override
    public String removeCar(String renavan) throws RemoteException {
        Iterator<Car> iterator = carList.iterator();
        while (iterator.hasNext()) {
            Car car = iterator.next();
            if (car.getRenavan().equals(renavan)) {
                iterator.remove();
                return "carro removido com sucesso!";
            }
        }
        return "erro ao remover carroc";
    }

    @Override
    public String findCarByRenavan(String renavan) throws RemoteException {
        for (Car car : carList) {
            if (car.getRenavan().equals(renavan)) {
                return car.toString();
            }
        }
        return "Carro não encontrado";
    }

    @Override
    public int getNumberOfCars() throws RemoteException {
        return carList.size();
    }

    public String buyCar(String renavan) throws RemoteException {
        for (Car car : carList) {
            if (car.getRenavan().equals(renavan)) {
                if (car.getQuantidade() > 0) {
                    car.setQuantidade(car.getQuantidade() - 1);
                    return "Carro comprado com sucesso: " + car.toString();
                } else {
                    return "Erro: O carro não está mais disponível em estoque";
                }
            }
        }
        return "Carro não encontrado";
    }

    public String authenticate(String username, String password) throws RemoteException {
        for (Client client : clientList) {
            if (client.getUser().equals(username) && client.getSenha().equals(password)) {
                return client.getTipo();
            }
        }
        return "Cliente não encontrado";
    }
}
