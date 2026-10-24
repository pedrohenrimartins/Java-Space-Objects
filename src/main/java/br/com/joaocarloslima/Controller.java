package br.com.joaocarloslima;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Controller implements Initializable {

    @FXML
    ImageView imgNave;
    @FXML
    AnchorPane pane;
    @FXML
    Label level;
    @FXML
    Label pontos;

    private int frame = 0;
    private Jogo jogo = new Jogo();
    private Timeline relogio;

    public void keyHandler(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                jogo.nave.setDirecao(Direcao.ESQUERDA);
                jogo.nave.mover();
                break;
            case RIGHT:
                jogo.nave.setDirecao(Direcao.DIREITA);
                jogo.nave.mover();
                break;
            case SPACE:
                atirar();
                break;
            default:
                break;
        }
    }

    private void atirar() {
        Tiro tiro = jogo.atirar();
        pane.getChildren().add(tiro.getImagem());
    }

    private void remover(Asset asset) {
        pane.getChildren().remove(asset.getImagem());
        jogo.assets.remove(asset);
    }

    private void atualizar() {
        frame++;

        if (frame % 100 == 0) {
            Meteoro meteoro = jogo.criarMeteoro();
            pane.getChildren().add(meteoro.getImagem());
        }

        // itera sobre uma cópia para poder remover da lista original
        for (Asset asset : new ArrayList<>(jogo.assets)) {
            if (asset instanceof Nave) continue;               // a nave só move pelo teclado
            if (!jogo.assets.contains(asset)) continue;        // já removido neste frame

            asset.mover();

            if (asset.getY() > 480 || asset.getY() < 0 || asset.getX() > 640 || asset.getX() < 0) {
                remover(asset);
                continue;
            }

            if (asset instanceof Tiro) {
                Tiro tiro = (Tiro) asset;
                for (Asset outro : new ArrayList<>(jogo.assets)) {
                    if (outro instanceof Meteoro && tiro.colidiuCom(outro)) {
                        Meteoro meteoro = (Meteoro) outro;
                        meteoro.tomarTiro(tiro);
                        if (meteoro.getPoder() <= 0) {
                            remover(meteoro);
                            jogo.pontuar();
                        }
                        remover(tiro);
                        break;
                    }
                }
            }
        }

        pontos.setText(String.valueOf(jogo.getPontos()));
        level.setText(String.valueOf(jogo.getNivel()));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // troca a imagem fixa do FXML pela imagem da nave do jogo
        if (imgNave != null) {
            pane.getChildren().remove(imgNave);
        }
        pane.getChildren().add(jogo.nave.getImagem());

        relogio = new Timeline(new KeyFrame(Duration.millis(20), e -> atualizar()));
        relogio.setCycleCount(Animation.INDEFINITE);
        relogio.play();
    }
}