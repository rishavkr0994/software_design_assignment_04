import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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

        TSP tsp = new TSP();
        WorkSpace.getInstance().addObserver(tsp);

        drawArea = new WorkSpacePanel();
        tsp.addObserver(drawArea);
        add(drawArea, BorderLayout.CENTER);

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
        mItemTSPNearestNeighbor.setActionCommand("Connections_TSPNearestNeighbor");
        mItemTSPNearestNeighbor.setSelected(true);
        mItemTSPNearestNeighbor.addActionListener(this);
        connectionsButtonGroup.add(mItemTSPNearestNeighbor);
        connectionsMenu.add(mItemTSPNearestNeighbor);

        JRadioButtonMenuItem mItemTSPPro = new JRadioButtonMenuItem("TSP - Pro");
        mItemTSPPro.setActionCommand("Connections_TSPPro");
        mItemTSPPro.addActionListener(this);
        connectionsButtonGroup.add(mItemTSPPro);
        connectionsMenu.add(mItemTSPPro);

        JRadioButtonMenuItem mItemClusters = new JRadioButtonMenuItem("Clusters");
        mItemClusters.setActionCommand("Connections_Clusters");
        mItemClusters.addActionListener(this);
        connectionsButtonGroup.add(mItemClusters);
        connectionsMenu.add(mItemClusters);

        JRadioButtonMenuItem mItemUserConnect = new JRadioButtonMenuItem("User Connect");
        mItemUserConnect.setActionCommand("Connections_UserConnect");
        mItemUserConnect.addActionListener(this);
        connectionsButtonGroup.add(mItemUserConnect);
        connectionsMenu.add(mItemUserConnect);

        menuBar.add(connectionsMenu);

        JMenu actionsMenu = new JMenu("Action");
        ButtonGroup actionsButtonGroup = new ButtonGroup();

        JRadioButtonMenuItem mItemCreate = new JRadioButtonMenuItem("Create");
        mItemCreate.addChangeListener(e -> drawArea.setMouseActionStrategy(new ActionCreateOperation(this)));
        mItemCreate.setSelected(true);
        actionsButtonGroup.add(mItemCreate);
        actionsMenu.add(mItemCreate);

        JRadioButtonMenuItem mItemMove = new JRadioButtonMenuItem("Move");
        mItemMove.addChangeListener(e -> drawArea.setMouseActionStrategy(new ActionMoveOperation()));
        actionsButtonGroup.add(mItemMove);
        actionsMenu.add(mItemMove);

        JRadioButtonMenuItem mItemConnect = new JRadioButtonMenuItem("Connect");
        mItemConnect.addChangeListener(e -> drawArea.setMouseActionStrategy(new ActionConnectOperation()));
        actionsButtonGroup.add(mItemConnect);
        actionsMenu.add(mItemConnect);

        menuBar.add(actionsMenu);

        setJMenuBar(menuBar);

        JTextArea loggingArea = new JTextArea(10, 50);
        loggingArea.setEditable(false);
        loggingArea.setBackground(Color.decode("#F5DEB3"));
        loggingArea.setForeground(Color.decode("#523A28"));
        JScrollPane loggingScrollPane = new JScrollPane(loggingArea);
        add(loggingScrollPane, BorderLayout.SOUTH);
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
            case "Connections_TSPNearestNeighbor":
                onClickConnectionsTSPNearestNNeighbor();
                break;
            case "Connections_TSPPro":
                onClickConnectionsTSPPro();
                break;
            case "Connections_Clusters":
                onClickConnectionsClusters();
                break;
            case "Connections_UserConnect":
                onClickConnectionsUserConnect();
                break;
        }
    }

    private void onClickFileNew() {
        WorkSpace.getInstance().clearAllCities();
        drawArea.repaint();
    }

    private void onClickFileLoad() {
        File selectedFile = displayFileSelectionDialog();
        if (selectedFile != null) {
            try {
                WorkSpace.getInstance().load(selectedFile);
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
                WorkSpace.getInstance().save(selectedFile);
            } catch (IOException e) {
                String msg = String.format("Failed To Save Data To File\nException: %s", e);
                JOptionPane.showMessageDialog(this, msg);
            }
        }
    }

    private void onClickConnectionsTSPNearestNNeighbor() {
        JOptionPane.showMessageDialog(this, "Not Implemented !");
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
