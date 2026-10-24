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

    /** Soma a velocidade na direção em que o objeto está apontando. (0,0) = canto superior esquerdo. */
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

    /** Verifica se este asset ocupa o mesmo espaço que outro (usa o tamanho real da imagem). */
    public boolean colidiuCom(Asset asset) {
        return x < asset.getX() + asset.getLargura() &&
                x + getLargura() > asset.getX() &&
                y < asset.getY() + asset.getAltura() &&
                y + getAltura() > asset.getY();
    }

    /** Largura da imagem (50 se ainda não houver imagem). */
    public int getLargura() {
        if (imagem != null && imagem.getBoundsInLocal().getWidth() > 0) {
            return (int) imagem.getBoundsInLocal().getWidth();
        }
        return 50;
    }

    /** Altura da imagem (50 se ainda não houver imagem). */
    public int getAltura() {
        if (imagem != null && imagem.getBoundsInLocal().getHeight() > 0) {
            return (int) imagem.getBoundsInLocal().getHeight();
        }
        return 50;
    }

    /** Mantém o ImageView sincronizado com a posição lógica. */
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