package registrados;

public class Partido {
    private int cargo;
    private int númeroVotável;
    private int qtdVotos;
    
    public Partido(int cargo, int númeroVotável, int qtdVotos) {
        this.cargo = cargo;
        this.númeroVotável = númeroVotável;
        this.qtdVotos = qtdVotos;
    }

    public int getCargo() {
        return cargo;
    }

    public int getNúmeroVotável() {
        return númeroVotável;
    }

    public int getQtdVotos() {
        return qtdVotos;
    }
}
