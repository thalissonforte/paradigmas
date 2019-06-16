package sample;

import java.io.IOException;

public class RandomPickerCmd {
    public static void main(String[] args) throws IOException {

        // VERIFICA EXISTENCIA DO ARQUIVO
        if(args.length < 1) {
            System.out.println("Arquivo não foi passado por parâmetro.");
            System.exit(1);
        }

        // CRIAR OBJETO CONTROLADOR DO ARQUIVO
        FileController controller = new FileController(args[0]);

        // SETANDO MODO (OFFLINE ~True OU ONLINE ~False) DEPENDENDO DA CONEXAO
        if(controller.verificaConexao()) {
            System.out.println("Conexão estabelecida. Operando em modo ONLINE:\n");
            controller.setOffline(false);
        }
        else {
            System.out.println("Operando em modo OFFLINE:\n");
            controller.setOffline(true);
        }

        controller.runShuffle(); // "EMBARALHAR" LISTA

        // EXIBIR DADOS
        controller.exibirEnter();
    }
}
