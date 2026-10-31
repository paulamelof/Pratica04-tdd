// excecao para quando passam 1 nota ou nenhuma no checarSituacao
// deixei como RuntimeException pq no diagrama o checarSituacao nao tem throws
public class NotasInsuficientesException extends RuntimeException {

    public NotasInsuficientesException(String mensagem) {
        super(mensagem);
    }
}
