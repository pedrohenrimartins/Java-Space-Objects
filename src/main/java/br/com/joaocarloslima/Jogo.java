package br.com.joaocarloslima;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jogo {

    private static final int NIVEL_MAXIMO = 4;
    private static final int PONTOS_POR_NIVEL = 10;
    private static final int LARGURA_TELA = 640;

    protected List<Asset> assets = new ArrayList<>();
    protected Nave nave = new Nave(280, 400, 10, Direcao.DIREITA);
    private int pontos;
    private int nivel = 1;

    private final Random random = new Random();

    public Jogo() {
        assets.add(nave);
    }


    public Tiro atirar() {
        Tiro tiro = nave.atirar(nivel);
        assets.add(tiro);
        return tiro;
    }


    public Meteoro criarMeteoro() {
        int x = random.nextInt(LARGURA_TELA - 50 + 1); // 0 a 590
        int velocidade = random.nextInt(3) + 1;        // 1 a 3
        Meteoro meteoro = new Meteoro(x, 0, velocidade, Direcao.BAIXO);
        assets.add(meteoro);
        return meteoro;
    }


    public void pontuar() {
        pontos++;
        if (pontos % PONTOS_POR_NIVEL == 0 && nivel < NIVEL_MAXIMO) {
            nivel++;
        }
    }

    public List<Asset> getAssets() { return assets; }
    public Nave getNave() { return nave; }
    public int getPontos() { return pontos; }
    public int getNivel() { return nivel; }
}