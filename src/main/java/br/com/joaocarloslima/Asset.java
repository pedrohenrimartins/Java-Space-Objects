package br.com.joaocarloslima;

import javafx.scene.image.ImageView;

public abstract class Asset {

    private int x;
    private int y;
    private ImageView imagem;
    private int velocidade;
    private Direcao direcao;

    public Asset(int x, int y, int velocidade, Direcao direcao) {
        this.x = x;
        this.y = y;
        this.velocidade = velocidade;
        this.direcao = direcao;
    }


    public void mover() {
        switch (direcao) {
            case CIMA:
                y -= velocidade;
                break;
            case BAIXO:
                y += velocidade;
                break;
            case ESQUERDA:
                x -= velocidade;
                break;
            case DIREITA:
                x += velocidade;
                break;
        }
        atualizarImagem();
    }


    public boolean colidiuCom(Asset asset) {
        return x < asset.getX() + asset.getLargura() &&
                x + getLargura() > asset.getX() &&
                y < asset.getY() + asset.getAltura() &&
                y + getAltura() > asset.getY();
    }


    public int getLargura() {
        if (imagem != null && imagem.getBoundsInLocal().getWidth() > 0) {
            return (int) imagem.getBoundsInLocal().getWidth();
        }
        return 50;
    }


    public int getAltura() {
        if (imagem != null && imagem.getBoundsInLocal().getHeight() > 0) {
            return (int) imagem.getBoundsInLocal().getHeight();
        }
        return 50;
    }


    protected void atualizarImagem() {
        if (imagem != null) {
            imagem.setLayoutX(x);
            imagem.setLayoutY(y);
        }
    }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; atualizarImagem(); }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; atualizarImagem(); }

    public ImageView getImagem() { return imagem; }
    public void setImagem(ImageView imagem) {
        this.imagem = imagem;
        atualizarImagem();
    }

    public int getVelocidade() { return velocidade; }
    public void setVelocidade(int velocidade) { this.velocidade = velocidade; }

    public Direcao getDirecao() { return direcao; }
    public void setDirecao(Direcao direcao) { this.direcao = direcao; }
}