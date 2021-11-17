import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * This program implements a GUI to display the optimal travel route between a group of cities. The cities can be marked
 * on screen with a mouse click. Also, the marked cities can be moved to a new location by clicking and dragging it. The
 * program also supports additional functions to clear the screen and load/save the city data from/to a text file.
 *
 * @author Zhuoran Li, Rishav Kumar
 * @version 1.0
 * @since 2021-10-02
 */
public class MainFrame extends JFrame implements ActionListener {
    private static final int DEFAULT_WINDOW_HEIGHT = 600;
    private static final int DEFAULT_WINDOW_WIDTH = 800;

    private final WorkSpacePanel drawArea;

    /**
     * Default constructor. Initializes the GUI components and their defines their responses to user actions. It sets
     * the TSP class as an <tt>Observer</tt> of the WorkSpace class (the <tt>Observable</tt>), so that it can respond to
     * addition/movement of a city by re-evaluating the optimal route. Also, the WorkSpace class is set as an
     * <tt>Observer</tt> of the TSP class (the <tt>Observable</tt>) so that it can respond to the generation of a new
     * route by re-drawing the new route on the panel.
     */
    public MainFrame() {
        super("Travelling Salesman Path Plotting Tool");
        setLayout(new BorderLayout());

        ConnectionContext connectionContext = new ConnectionContext();
        CityRepository.getInstance().addObserver(connectionContext);

        drawArea = new WorkSpacePanel();
        CityRepository.getInstance().addObserver(drawArea);
        RouteRepository.getInstance().addObserver(drawArea);
        add(drawArea, BorderLayout.CENTER);

        JTextArea loggingArea = new JTextArea(10, 50);
        loggingArea.setEditable(false);
        loggingArea.setBackground(Color.decode("#F5DEB3"));
        loggingArea.setForeground(Color.decode("#523A28"));
        loggingArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        JScrollPane loggingScrollPane = new JScrollPane(loggingArea);
        add(loggingScrollPane, BorderLayout.SOUTH);

        PrintStream loggingAreaPrintStream = new PrintStream(new TextAreaOutputStream(loggingArea));
        Logger.getInstance().setOutputStream(loggingAreaPrintStream);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem mItemNew = new JMenuItem("New");
        mItemNew.setActionCommand("File_New");
        mItemNew.addActionListener(this);
        fileMenu.add(mItemNew);

        fileMenu.add(new JSeparator());

        JMenuItem mItemLoad = new JMenuItem("Load");
        mItemLoad.setActionCommand("File_Load");
        mItemLoad.addActionListener(this);
        fileMenu.add(mItemLoad);

        JMenuItem mItemSave = new JMenuItem("Save");
        mItemSave.setActionCommand("File_Save");
        mItemSave.addActionListener(this);
        fileMenu.add(mItemSave);

        menuBar.add(fileMenu);

        JMenu connectionsMenu = new JMenu("Connections");
        ButtonGroup connectionsButtonGroup = new ButtonGroup();

        JRadioButtonMenuItem mItemTSPNearestNeighbor = new JRadioButtonMenuItem("TSP - Nearest Neighbor");
        mItemTSPNearestNeighbor.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Logger.getInstance().info("[Strategy Pattern] Setting connections strategy to TSP - Nearest Neighbor");
                connectionContext.setStrategy(new ConnectionTSPNearestNeighbour());
            }
        });
        mItemTSPNearestNeighbor.setSelected(true);
        connectionsButtonGroup.add(mItemTSPNearestNeighbor);
        connectionsMenu.add(mItemTSPNearestNeighbor);

        JRadioButtonMenuItem mItemTSPPro = new JRadioButtonMenuItem("TSP - Pro");
        mItemTSPPro.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Logger.getInstance().info("[Strategy Pattern] Setting connections strategy to TSP - Pro");
                connectionContext.setStrategy(new ConnectionTSPPro());
            }
        });
        connectionsButtonGroup.add(mItemTSPPro);
        connectionsMenu.add(mItemTSPPro);

        JRadioButtonMenuItem mItemClusters = new JRadioButtonMenuItem("Clusters");
        mItemClusters.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Logger.getInstance().info("[Strategy Pattern] Setting connections strategy to Clusters");
                connectionContext.setStrategy(new ConnectionClustering());
            }
        });
        connectionsButtonGroup.add(mItemClusters);
        connectionsMenu.add(mItemClusters);

        JRadioButtonMenuItem mItemUserConnect = new JRadioButtonMenuItem("User Connect");
        mItemUserConnect.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Logger.getInstance().info("[Strategy Pattern] Setting connections strategy to User Connect");
                connectionContext.setStrategy(null);
            }
        });
        connectionsButtonGroup.add(mItemUserConnect);
        connectionsMenu.add(mItemUserConnect);

        menuBar.add(connectionsMenu);

        JMenu actionsMenu = new JMenu("Action");
        ButtonGroup actionsButtonGroup = new ButtonGroup();

        JRadioButtonMenuItem mItemCreate = new JRadioButtonMenuItem("Create");
        mItemCreate.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Logger.getInstance().info("[Strategy Pattern] Setting mouse action strategy to create city");
                drawArea.setMouseActionStrategy(new ActionCreateOperation(this));
            }
        });
        mItemCreate.setSelected(true);
        actionsButtonGroup.add(mItemCreate);
        actionsMenu.add(mItemCreate);

        JRadioButtonMenuItem mItemMove = new JRadioButtonMenuItem("Move");
        mItemMove.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Logger.getInstance().info("[Strategy Pattern] Setting mouse action strategy to move city");
                drawArea.setMouseActionStrategy(new ActionMoveOperation());
            }
        });
        actionsButtonGroup.add(mItemMove);
        actionsMenu.add(mItemMove);

        JRadioButtonMenuItem mItemConnect = new JRadioButtonMenuItem("Connect");
        mItemConnect.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Logger.getInstance().info("[Strategy Pattern] Setting mouse action strategy to connect city");
                drawArea.setMouseActionStrategy(new ActionConnectOperation());
            }
        });
        actionsButtonGroup.add(mItemConnect);
        actionsMenu.add(mItemConnect);

        menuBar.add(actionsMenu);

        setJMenuBar(menuBar);
    }

    /**
     * Entry point for the program. It creates an instance of the GUI, configures its physical attributes and displays
     * it on screen.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        switch (ev.getActionCommand()) {
            case "File_New":
                onClickFileNew();
                break;
            case "File_Load":
                onClickFileLoad();
                break;
            case "File_Save":
                onClickFileSave();
                break;
        }
    }

    private void onClickFileNew() {
        CityRepository.getInstance().clearAllCities();
        drawArea.repaint();
    }

    private void onClickFileLoad() {
        File selectedFile = displayFileSelectionDialog();
        if (selectedFile != null) {
            try {
                CityRepository.getInstance().load(selectedFile);
            } catch (IOException e) {
                String msg = String.format("Failed To Load Data From File\nException: %s", e);
                JOptionPane.showMessageDialog(this, msg);
            }
            drawArea.repaint();
        }
    }

    private void onClickFileSave() {
        File selectedFile = displayFileSaveDialog();
        if (selectedFile != null) {
            try {
                CityRepository.getInstance().save(selectedFile);
            } catch (IOException e) {
                String msg = String.format("Failed To Save Data To File\nException: %s", e);
                JOptionPane.showMessageDialog(this, msg);
            }
        }
    }

    private void onClickConnectionsTSPNearestNNeighbor() {
    }

    private void onClickConnectionsTSPPro() {
    }

    private void onClickConnectionsClusters() {
    }

    private void onClickConnectionsUserConnect() {
    }

    private File displayFileSelectionDialog() {
        JFileChooser jFileChooser = new JFileChooser();
        if (jFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            return jFileChooser.getSelectedFile();
        else return null;
    }

    private File displayFileSaveDialog() {
        JFileChooser jFileChooser = new JFileChooser();
        if (jFileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
            return jFileChooser.getSelectedFile();
        else return null;
    }
}
