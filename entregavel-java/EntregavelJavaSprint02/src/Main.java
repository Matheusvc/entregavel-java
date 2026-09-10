public class Main {

    public static void main(String[] args) {

        Pessoa pessoa1 = new Pessoa("Samyr", 18, "IA sênior");
        Pessoa pessoa2 = new Pessoa("Juan", 23, "Professor de IA");
        Pessoa pessoa3 = new Pessoa("Marcos Barros", 40, "Chefe Master");

        System.out.println("PESSOAS");
        System.out.println("Nome: " + pessoa1.nome + " | Idade: " + pessoa1.idade + " | Profissão: " + pessoa1.profissao);
        System.out.println("Nome: " + pessoa2.nome + " | Idade: " + pessoa2.idade + " | Profissão: " + pessoa2.profissao);
        System.out.println("Nome: " + pessoa3.nome + " | Idade: " + pessoa3.idade + " | Profissão: " + pessoa3.profissao);

        Pessoa pessoaMaisVelha = pessoa1;

        if (pessoa2.idade > pessoaMaisVelha.idade) {
            pessoaMaisVelha = pessoa2;
        }

        if (pessoa3.idade > pessoaMaisVelha.idade) {
            pessoaMaisVelha = pessoa3;
        }

        System.out.println();
        System.out.println("A pessoa mais velha é: " + pessoaMaisVelha.nome);
        System.out.println("Idade: " + pessoaMaisVelha.idade + " anos");


        Carro carro1 = new Carro("Peugeot ", "Peugeot 207 ", 2008);
        Carro carro2 = new Carro("Chevrolet", "Celta", 2000);

        carro1.modelo = "207";


        System.out.println();
        System.out.println("CARROS");

        System.out.println("Carro 1:");
        System.out.println("Marca: " + carro1.marca);
        System.out.println("Modelo: " + carro1.modelo);
        System.out.println("Ano: " + carro1.ano);

        System.out.println();

        System.out.println("Carro 2:");
        System.out.println("Marca: " + carro2.marca);
        System.out.println("Modelo: " + carro2.modelo);
        System.out.println("Ano: " + carro2.ano);

    }
}

