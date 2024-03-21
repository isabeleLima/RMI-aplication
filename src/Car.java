import java.io.Serializable;

public class Car implements Serializable {
    private String renavan;
    private String nome;
    private String categoria;
    private int anoFabricacao;
    private int quantidade;
    private double preco;

    public Car(String renavan, String nome, String categoria, int anoFabricacao, int quantidade, double preco) {
        this.renavan = renavan;
        this.nome = nome;
        this.categoria = categoria;
        this.anoFabricacao = anoFabricacao;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public String getRenavan() {
        return renavan;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Car{" +
                "renavan='" + renavan + '\'' +
                ", nome='" + nome + '\'' +
                ", categoria='" + categoria + '\'' +
                ", anoFabricacao=" + anoFabricacao +
                ", quantidade=" + quantidade +
                ", preco=" + preco +
                '}';
    }
}
