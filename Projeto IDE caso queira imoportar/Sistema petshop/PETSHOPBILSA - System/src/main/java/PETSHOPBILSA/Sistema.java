package PETSHOPBILSA;



import java.sql.*;
import java.util.Scanner;

class Conexao {
    public static Connection conectar() {
        try {
            String url = "jdbc:postgresql://localhost:5432/clinica_veterinaria";
            String user = "postgres";
            String password = "Maniko12!";
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Erro conexão: " + e.getMessage());
            return null;
        }
    }
}

public class Sistema {
    static Scanner sc = new Scanner(System.in);
    static Connection conn = Conexao.conectar();  

    public static void main(String[] args) {
        
        if (conn == null) {
            System.out.println("ERRO: Não foi possível conectar ao banco de dados!");
            System.out.println("Verifique se o PostgreSQL está rodando.");
            return;
        }
        
        login();
        menu();
    }

    
    static void login() {
        System.out.println("=== LOGIN ===");
        System.out.print("Usuário: ");
        String user = sc.nextLine();
        System.out.print("Senha: ");
        String pass = sc.nextLine();

        if (user.equals("admin") && pass.equals("123")) {
            System.out.println("Login realizado!\n");
        } else {
            System.out.println("Login inválido!");
            System.exit(0);
        }
    }

    static void menu() {
        int op;
        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Cadastrar Pet");
            System.out.println("3 - Cadastrar Consulta");
            System.out.println("4 - Cadastrar Veterinario");
            System.out.println("5 - Listar Dados");            
            System.out.println("6 - Atualizar Dados");
            System.out.println("7 - Deletar Dados");
            System.out.println("8 - Consulta com JOIN");
            System.out.println("0 - Sair");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> inserirCliente();
                case 2 -> inserirPet();
                case 3 -> inserirConsulta();
                case 4 -> inserirVeterinario();  
                case 5 -> menuListar();
                case 6 -> atualizarDados();
                case 7 -> menuDelete();
                case 8 -> menuJoin();
            }
        } while (op != 0);
    }

   static void inserirCliente() {
    try {
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Idade: ");
        int idade = Integer.parseInt(sc.nextLine());

        System.out.print("Data de nascimento (YYYY-MM-DD): ");
        String data = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        String sql = "INSERT INTO cliente " +
                     "(nome_cliente, cpf, idade_cliente, data_nasc_cliente, telefone) " +
                     "VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, nome);
        ps.setString(2, cpf);
        ps.setInt(3, idade);
        ps.setDate(4, java.sql.Date.valueOf(data));
        ps.setString(5, telefone);

        ps.executeUpdate();

        System.out.println("Cliente inserido com sucesso!");

    } catch (Exception e) {
        System.out.println("Erro ao inserir cliente: " + e.getMessage());
    }
}
    
    static void inserirPet() {
    try {
        System.out.print("Nome do Pet: ");
        String nomePet = sc.nextLine();

        System.out.print("Idade do Pet: ");
        int idadePet = Integer.parseInt(sc.nextLine());

        System.out.print("ID do Cliente: ");
        int idCliente = Integer.parseInt(sc.nextLine());

        System.out.print("Tipo do Pet: ");
        String tipoPet = sc.nextLine();

        System.out.print("Raça do Pet: ");
        String racaPet = sc.nextLine();

        String sql = "INSERT INTO pet " +
                     "(nome_pet, idade_pet, id_cliente, tipo_pet, raca_pet) " +
                     "VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, nomePet);
        ps.setInt(2, idadePet);
        ps.setInt(3, idCliente);
        ps.setString(4, tipoPet);
        ps.setString(5, racaPet);

        ps.executeUpdate();

        System.out.println("Pet inserido com sucesso!");

    } catch (Exception e) {
        System.out.println("Erro ao inserir pet: " + e.getMessage());
    }
}

static void menuListar() {
    int op;
    do {
        System.out.println("\n=== LISTAR ===");
        System.out.println("1 - Clientes");
        System.out.println("2 - Pets");
        System.out.println("3 - Veterinários");
        System.out.println("4 - Consultas");
        System.out.println("0 - Voltar");

        op = sc.nextInt();
        sc.nextLine();

        switch (op) {
            case 1 -> listarClientes();
            case 2 -> listarPets();
            case 3 -> listarVeterinarios();
            case 4 -> listarConsultas();
            
        }

    } while (op != 0);
}

static void listarClientes() {
    try {
        String sql = "SELECT * FROM cliente";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            System.out.println(
                rs.getInt("id_cliente") + " - " +
                rs.getString("nome_cliente") + " - " +
                rs.getString("cpf") + " - " +
                rs.getString("telefone")
            );
        }
    } catch (Exception e) {
        System.out.println("Erro ao listar clientes: " + e.getMessage());
    }
}

static void listarPets() {
    try {
        String sql = "SELECT * FROM pet";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            System.out.println(
                rs.getInt("id_pet") + " - Nome do pet: " + 
                rs.getString("nome_pet") + " - Idade do pet: " +
                rs.getInt("idade_pet") + " - Tipo de pet: " +
                rs.getString("tipo_pet") + " - Raca do pet: " +
                rs.getString("raca_pet") + " - Cliente ID: " +
                rs.getInt("id_cliente")
            );
        }
    } catch (Exception e) {
        System.out.println("Erro ao listar pets: " + e.getMessage());
    }
}

static void listarVeterinarios() {
    try {
        String sql = "SELECT * FROM veterinario";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            System.out.println(
                rs.getInt("id_veterinario") + " - " +
                rs.getString("nome_veterinario") + " - " +
                rs.getString("crmv") + " - " +
                rs.getString("cpf") + " - " +
                rs.getInt("idade_veterinario")
            );
        }
    } catch (Exception e) {
        System.out.println("Erro ao listar veterinários: " + e.getMessage());
    }
}

static void listarConsultas() {
    try {
        String sql = "SELECT * FROM consulta";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            System.out.println(
                rs.getInt("id_consulta") + " - Pet ID: " +
                rs.getInt("id_pet") + " - Vet ID: " +
                rs.getInt("id_veterinario") + " - Data: " +
                rs.getDate("dia_consulta")
            );
        }
    } catch (Exception e) {
        System.out.println("Erro ao listar consultas: " + e.getMessage());
    }
}

    static void inserirConsulta() {
    try {
        System.out.print("ID do Pet: ");
        int idPet = Integer.parseInt(sc.nextLine());

        System.out.print("Data da consulta (YYYY-MM-DD): ");
        String data = sc.nextLine();

        System.out.print("ID do Veterinário: ");
        int idVet = Integer.parseInt(sc.nextLine());

        String sql = "INSERT INTO consulta (id_pet, dia_consulta, id_veterinario) VALUES (?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idPet);
        ps.setDate(2, Date.valueOf(data));
        ps.setInt(3, idVet);

        ps.executeUpdate();

        System.out.println("Consulta cadastrada com sucesso!");
    } catch (Exception e) {
        System.out.println("Erro ao inserir consulta: " + e.getMessage());
    }
}
    
    static void inserirVeterinario() {
    try {
        System.out.print("Nome do Veterinario: ");
        String nome = sc.nextLine();

        System.out.print("CRMV: ");
        String crmv = sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Idade: ");
        int idade = Integer.parseInt(sc.nextLine());

        String sql = "INSERT INTO veterinario (crmv, cpf, nome_veterinario, idade_veterinario) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, crmv);
        ps.setString(2, cpf);
        ps.setString(3, nome);
        ps.setInt(4, idade);

        ps.executeUpdate();

        System.out.println("Veterinario inserido com sucesso!");
    } catch (Exception e) {
        System.out.println("Erro ao inserir veterinario: " + e.getMessage());
    }
}
    
static void atualizarDados() {
    int op;
    do {
        System.out.println("\n=== ATUALIZAR ===");
        System.out.println("1 - Cliente");
        System.out.println("2 - Pet");
        System.out.println("3 - Veterinário");
        System.out.println("4 - Consulta");
        System.out.println("0 - Voltar");

        op = sc.nextInt();
        sc.nextLine();

        switch (op) {
            case 1 -> atualizarCliente();
            case 2 -> atualizarPet();
            case 3 -> atualizarVeterinario();
            case 4 -> atualizarConsulta();
        }

    } while (op != 0);
}

static void atualizarCliente() {
    try {
        System.out.print("ID do Cliente: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Novo nome: ");
        String nome = sc.nextLine();

        System.out.print("Novo CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Nova idade: ");
        int idade = Integer.parseInt(sc.nextLine());

        System.out.print("Nova data (YYYY-MM-DD): ");
        String data = sc.nextLine();

        System.out.print("Novo telefone: ");
        String tel = sc.nextLine();

        String sql = "UPDATE cliente SET nome_cliente=?, cpf=?, idade_cliente=?, data_nasc_cliente=?, telefone=? WHERE id_cliente=?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nome);
        ps.setString(2, cpf);
        ps.setInt(3, idade);
        ps.setDate(4, java.sql.Date.valueOf(data));
        ps.setString(5, tel);
        ps.setInt(6, id);

        ps.executeUpdate();

        System.out.println("Cliente atualizado!");
    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
    }
}


   static void menuDelete() {
    int op;
    do {
        System.out.println("\n=== DELETAR ===");
        System.out.println("1 - Cliente");
        System.out.println("2 - Pet");
        System.out.println("3 - Veterinário");
        System.out.println("4 - Consulta");
        System.out.println("0 - Voltar");

        op = sc.nextInt();
        sc.nextLine();

        switch (op) {
            case 1 -> deletarCliente();
            case 2 -> deletarPet();
            case 3 -> deletarVeterinario();
            case 4 -> deletarConsulta();
        }

    } while (op != 0);
}
   
   static void atualizarPet() {
    try {
        System.out.print("ID do Pet: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Novo nome: ");
        String nome = sc.nextLine();

        System.out.print("Nova idade: ");
        int idade = Integer.parseInt(sc.nextLine());

        System.out.print("Novo tipo: ");
        String tipo = sc.nextLine();

        System.out.print("Nova raça: ");
        String raca = sc.nextLine();

        String sql = "UPDATE pet SET nome_pet=?, idade_pet=?, tipo_pet=?, raca_pet=? WHERE id_pet=?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nome);
        ps.setInt(2, idade);
        ps.setString(3, tipo);
        ps.setString(4, raca);
        ps.setInt(5, id);

        ps.executeUpdate();

        System.out.println("Pet atualizado!");
    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
    }
}
   
   static void atualizarVeterinario() {
    try {
        System.out.print("ID do Veterinário: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Novo nome: ");
        String nome = sc.nextLine();

        System.out.print("Novo CRMV: ");
        String crmv = sc.nextLine();

        System.out.print("Novo CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Nova idade: ");
        int idade = Integer.parseInt(sc.nextLine());

        String sql = "UPDATE veterinario SET nome_veterinario=?, crmv=?, cpf=?, idade_veterinario=? WHERE id_veterinario=?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nome);
        ps.setString(2, crmv);
        ps.setString(3, cpf);
        ps.setInt(4, idade);
        ps.setInt(5, id);

        ps.executeUpdate();

        System.out.println("Veterinário atualizado!");
    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
    }
}
 
   static void atualizarConsulta() {
    try {
        System.out.print("ID da Consulta: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Novo ID do Pet: ");
        int idPet = Integer.parseInt(sc.nextLine());

        System.out.print("Nova data (YYYY-MM-DD): ");
        String data = sc.nextLine();

        System.out.print("Novo ID do Veterinário: ");
        int idVet = Integer.parseInt(sc.nextLine());

        String sql = "UPDATE consulta SET id_pet=?, dia_consulta=?, id_veterinario=? WHERE id_consulta=?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idPet);
        ps.setDate(2, java.sql.Date.valueOf(data));
        ps.setInt(3, idVet);
        ps.setInt(4, id);

        ps.executeUpdate();

        System.out.println("Consulta atualizada!");
    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
    }
}

   static void deletarCliente() {
    try {
        System.out.print("ID do Cliente: ");
        int id = sc.nextInt();
        sc.nextLine();

        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

        System.out.println("Cliente deletado!");
    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
    }
}
   
   static void deletarPet() {
    try {
        System.out.print("ID do Pet: ");
        int id = sc.nextInt();
        sc.nextLine();

        String sql = "DELETE FROM pet WHERE id_pet = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

        System.out.println("Pet deletado!");
    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
    }
}
   
   static void deletarVeterinario() {
    try {
        System.out.print("ID do Veterinário: ");
        int id = sc.nextInt();
        sc.nextLine();

        String sql = "DELETE FROM veterinario WHERE id_veterinario = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

        System.out.println("Veterinário deletado!");
    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
    }
}
   
   static void deletarConsulta() {
    try {
        System.out.print("ID da Consulta: ");
        int id = sc.nextInt();
        sc.nextLine();

        String sql = "DELETE FROM consulta WHERE id_consulta = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

        System.out.println("Consulta deletada!");
    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
    }
}
   
static void menuJoin() {
    int op;
    do {
        System.out.println("\n=== CONSULTAS (JOIN) ===");
        System.out.println("1 - Clientes + Pets");
        System.out.println("2 - Pets + Consultas");
        System.out.println("3 - Pets + Consultas (LEFT JOIN)");
        System.out.println("0 - Voltar");

        op = sc.nextInt();
        sc.nextLine();

        switch (op) {
            case 1 -> joinClientePet();
            case 2 -> joinPetConsulta();
            case 3 -> leftJoinPetConsulta();
        }

    } while (op != 0);
}


static void joinClientePet() {
    try {
        String sql = """
            SELECT c.nome_cliente, p.nome_pet, p.tipo_pet, p.raca_pet
            FROM cliente c
            INNER JOIN pet p ON c.id_cliente = p.id_cliente
        """;

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            System.out.println(
                "Cliente: " + rs.getString("nome_cliente") +
                " | Pet: " + rs.getString("nome_pet") +
                " | Tipo: " + rs.getString("tipo_pet") +
                " | Raça: " + rs.getString("raca_pet")
            );
        }

    } catch (Exception e) {
        System.out.println("Erro no JOIN Cliente-Pet: " + e.getMessage());
    }
}


static void joinPetConsulta() {
    try {
        String sql = """
            SELECT p.nome_pet, c.dia_consulta, c.id_veterinario
            FROM pet p
            INNER JOIN consulta c ON p.id_pet = c.id_pet
        """;

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            System.out.println(
                "Pet: " + rs.getString("nome_pet") +
                " | Data: " + rs.getDate("dia_consulta") +
                " | Vet ID: " + rs.getInt("id_veterinario")
            );
        }

    } catch (Exception e) {
        System.out.println("Erro no JOIN Pet-Consulta: " + e.getMessage());
    }
}

static void leftJoinPetConsulta() {
    try {
        String sql = """
            SELECT p.nome_pet, c.dia_consulta
            FROM pet p
            LEFT JOIN consulta c ON p.id_pet = c.id_pet
        """;

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            String nome = rs.getString("nome_pet");
            Date data = rs.getDate("dia_consulta");

            if (data == null) {
                System.out.println("Pet: " + nome + " | Consulta: NÃO REALIZADA");
            } else {
                System.out.println("Pet: " + nome + " | Data: " + data);
            }
        }

    } catch (Exception e) {
        System.out.println("Erro no LEFT JOIN: " + e.getMessage());
    }
}

}

