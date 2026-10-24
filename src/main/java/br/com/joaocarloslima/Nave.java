package br.com.joaocarloslima;

import javafx.scene.image.ImageView;

public class Nave extends Asset {

    private static final int X_MIN = 0;
    private static final int X_MAX = 640 - 50;

    public Nave(int x, int y, int velocidade, Direcao direcao) {
        super(x, y, velocidade, direcao);

        String path = "images/ships/playerShip1_blue.png";
        ImageView imagem = new ImageView(App.class.getResource(path).toString());
        imagem.setFitWidth(50);
        imagem.setPreserveRatio(true);
        setImagem(imagem);
    }

    /** Cria e retorna um novo tiro saindo da nave, para cima, velocidade 10. */
    public Tiro atirar(int poder) {
        return new Tiro(getX(), getY(), 10, Direcao.CIMA, poder);
    }

    /** Move normalmente, mas impede a nave de sair da tela. */
    @Override
    public void mover() {
        super.mover();

        if (getX() < X_MIN) {
            setX(X_MIN);
        } else if (getX() > X_MAX) {
            setX(X_MAX);
        }
    }
}