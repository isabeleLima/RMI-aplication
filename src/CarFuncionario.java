import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class CarFuncionario {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            CarService carService = (CarService) registry.lookup("CarService");
            Scanner entry = new Scanner (System.in);

            String tipo= null;
            System.out.println("BEM VINDO AO SISTEMA");



            while(true){
                System.out.println("DIGITE SEU USUARIO");
                String user = entry.nextLine();

                System.out.println("DIGITE SUA SENHA");
                String senha = entry.nextLine();

                if(carService.authenticate(user,senha).equals("cliente")){
                    while(true) {
                        System.out.println("DIGITE OQ DESEJA FAZER");
                        System.out.println("1-VER CARROS DISPONIVEIS");
                        System.out.println("2-PESQUISAR UM CARRO");
                        System.out.println("3-COMPRAR UM CARRO");
                        System.out.println("4-SAIR");


                        String option = entry.nextLine();

                        switch (option) {
                            case "1": {
                                List<String> carsList = carService.listCars();
                                System.out.println("Listando carros disponiveis");
                                for (String carInfo : carsList) {
                                    System.out.println(carInfo);
                                }
                                break;
                            }
                            case "2": {
                                System.out.println("Digite o renavam");
                                String renavam = entry.nextLine();

                                System.out.println(carService.findCarByRenavan(renavam));
                                break;
                            }
                            case "3": {
                                System.out.println("Digite o renavam ");
                                String renavam = entry.nextLine();
                                System.out.println(carService.buyCar(renavam));
                                break;
                            }
                            default:
                                break;
                        }
                        if(option.equals("3")){
                            break;
                        }
                    }
                }else if(carService.authenticate(user,senha).equals("funcionario")){
                    while(true) {
                        System.out.println("DIGITE OQ DESEJA FAZER");
                        System.out.println("1-VER CARROS DISPONIVEIS");
                        System.out.println("2-ADICIONAR UM NOVO CARRO");
                        System.out.println("3-EXCLUIR UM CARRO");
                        System.out.println("4-PESQUISAR UM CARRO");
                        System.out.println("5-EXIBIR QUANTIDADE DE CARROS");
                        System.out.println("6-SAIR");


                        String option = entry.nextLine();

                        switch (option) {
                            case "1": {
                                List<String> carsList = carService.listCars();
                                System.out.println("Listando carros disponiveis");
                                for (String carInfo : carsList) {
                                    System.out.println(carInfo);
                                }
                                break;
                            }
                            case "2": {
                                System.out.println("Digite o renavam");
                                String renavam = entry.nextLine();

                                System.out.println("Digite o modelo");
                                String modelo = entry.nextLine();

                                System.out.println("Digite a categoria");
                                String categoria = entry.nextLine();

                                System.out.println("Digite o ano");
                                String ano = entry.nextLine();

                                System.out.println("Digite a quantidade");
                                String qtd = entry.nextLine();

                                System.out.println("Digite o preco");
                                double preco = entry.nextDouble();

                                boolean carAdded = carService.addCar(renavam, modelo, categoria, Integer.parseInt(ano), Integer.parseInt(qtd), preco);
                                if (carAdded) {
                                    System.out.println("Carro adicionado com sucesso");
                                } else {
                                    System.out.println("Falha ao adicionar o carro");
                                }
                                break;
                            }
                            case "3": {
                                System.out.println("Digite o renavam");
                                String renavam = entry.nextLine();

                                System.out.println(carService.removeCar(renavam));
                                break;
                            }
                            case "4": {
                                System.out.println("Digite o renavam");
                                String renavam = entry.nextLine();

                                System.out.println(carService.findCarByRenavan(renavam));
                                break;
                            }
                            case "5": {
                                System.out.println("numero de carros disponiveis no total: ");
                                System.out.println(carService.getNumberOfCars());
                                break;
                            }
                            default:
                                break;
                        }
                        if(option.equals("6")){
                            break;
                        }
                    }
                }else{
                    System.out.println(carService.authenticate(user,senha));
                }


            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
