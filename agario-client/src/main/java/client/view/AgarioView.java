package client.view;

import java.awt.FlowLayout;
import java.awt.event.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 *
 * @author zsiga
 */
public class AgarioView extends JFrame {
    
    private JMenuBar menuBar;
    private JPanel panel;
    private JPanel gameArea;
    
    private Action newGame;
    private Action disconnect;
    
    int currentX, currentY, oldX, oldY;

    
    public AgarioView() {
        setTitle("AgarioClone");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        add(panel);

        menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        gameMenu.add(new JMenuItem(disconnect));
        gameMenu.add(new JMenuItem(newGame));
        menuBar.add(gameMenu);
        setJMenuBar(menuBar);

        initGame();
    }
    
    public void initGame() {
        gameArea = new JPanel();
        gameArea.setBackground(new java.awt.Color(255, 255, 255));
        gameArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        gameArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                gameAreaMousePressed(evt);
            }
        });
        gameArea.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                gameAreaMouseDragged(evt);
            }
        });
        this.setContentPane(gameArea);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    private void gameAreaMouseDragged(MouseEvent evt) {
        currentX = evt.getX();
        currentY = evt.getY();
        oldX = currentX;
        oldY = currentY;
        System.out.println(currentX + " " + currentY);
    }

    private void gameAreaMousePressed(MouseEvent evt) {
        oldX = evt.getX();
        oldY = evt.getY();
        System.out.println(oldX + " " + oldY);
    }
}
    

