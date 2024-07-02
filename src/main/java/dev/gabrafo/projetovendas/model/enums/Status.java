package dev.gabrafo.projetovendas.model.enums;

public enum Status {
    PENDENTE(1),
    PAGO(2);

    private int codigo;

    private Status(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static Status valueOf(int codigo) {
        for (Status status : Status.values()) {
            if (status.getCodigo() == codigo) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código de Status inválido!");
    }
}
