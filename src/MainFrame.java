import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * This program implements a GUI to display cities and the connections between them using one of the algorithm options
 * for the connections (TSP - Nearest Neighbor, TSP - Pro, Cluster, User Connect). It also provides options to create a
 * city, move a city, and connect two cities manually through mouse movements. The program also supports additional
 * functions to clear the screen and load / save the city data from / to a text file.
 *
 * @author Aru Raghuwanshi, Krishna Sandeep Rupaakula, Rishav Kumar, Sasanka Gali
 * @version 1.0
 * @since 2021-11-12
 */
public class MainFrame extends JFrame implements ActionListener, ItemListener {

    private static final int DEFAULT_WINDOW_HEIGHT = 600;
    private static final int DEFAULT_WINDOW_WIDTH = 800;

    private final WorkSpacePanel drawArea;
    private final ConnectionContext connectionContext;

    private final JRadioButtonMenuItem mItemTSPNearestNeighbor;
    private final JRadioButtonMenuItem mItemTSPPro;
    private final JRadioButtonMenuItem mItemClusters;
    private final JRadioButtonMenuItem mItemUserConnect;

    private final JRadioButtonMenuItem mItemCreate;
    private final JRadioButtonMenuItem mItemMove;
    private final JRadioButtonMenuItem mItemConnect;


    /**
     * Default constructor. Initializes the GUI components and their defines their responses to user actions. It also
     * establishes the connections for the Observer pattern.
     */
    public MainFrame() {
        super("Travelling Salesman Path Plotting Tool");
        setLayout(new BorderLayout());

        connectionContext = new ConnectionContext();
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

        mItemTSPNearestNeighbor = new JRadioButtonMenuItem("TSP - Nearest Neighbor");
        mItemTSPNearestNeighbor.addItemListener(this);
        mItemTSPNearestNeighbor.setSelected(true);
        connectionsButtonGroup.add(mItemTSPNearestNeighbor);
        connectionsMenu.add(mItemTSPNearestNeighbor);

        mItemTSPPro = new JRadioButtonMenuItem("TSP - Pro");
        mItemTSPPro.addItemListener(this);
        connectionsButtonGroup.add(mItemTSPPro);
        connectionsMenu.add(mItemTSPPro);

        mItemClusters = new JRadioButtonMenuItem("Clusters");
        mItemClusters.addItemListener(this);
        connectionsButtonGroup.add(mItemClusters);
        connectionsMenu.add(mItemClusters);

        mItemUserConnect = new JRadioButtonMenuItem("User Connect");
        mItemUserConnect.addItemListener(this);
        connectionsButtonGroup.add(mItemUserConnect);
        connectionsMenu.add(mItemUserConnect);

        menuBar.add(connectionsMenu);

        JMenu actionsMenu = new JMenu("Action");
        ButtonGroup actionsButtonGroup = new ButtonGroup();

        mItemCreate = new JRadioButtonMenuItem("Create");
        mItemCreate.addItemListener(this);
        mItemCreate.setSelected(true);
        actionsButtonGroup.add(mItemCreate);
        actionsMenu.add(mItemCreate);

        mItemMove = new JRadioButtonMenuItem("Move");
        mItemMove.addItemListener(this);
        actionsButtonGroup.add(mItemMove);
        actionsMenu.add(mItemMove);

        mItemConnect = new JRadioButtonMenuItem("Connect");
        mItemConnect.addItemListener(this);
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

    /**
     * Invoked when an action occurs.
     * <p>
     * It defines the action for the File menu items.
     *
     * @param ev action event
     */
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

    /**
     * Invoked when an item has been selected or deselected by the user. The code written for this method performs the
     * operations that need to occur when an item is selected (or deselected).
     * <p>
     *  It defines the actions on item state change event for the Connections and Action menu items
     *
     * @param ev item event
     */
    @Override
    public void itemStateChanged(ItemEvent ev) {
        if (ev.getStateChange() == ItemEvent.SELECTED) {
            if (ev.getSource() == mItemTSPNearestNeighbor) {
                Logger.getInstance().info("[Strategy Pattern] Setting connections strategy to TSP - Nearest Neighbor");
                connectionContext.setStrategy(new ConnectionTSPNearestNeighbour());
            } else if (ev.getSource() == mItemTSPPro) {
                Logger.getInstance().info("[Strategy Pattern] Setting connections strategy to TSP - Pro");
                connectionContext.setStrategy(new ConnectionTSPPro());
            } else if (ev.getSource() == mItemClusters) {
                Logger.getInstance().info("[Strategy Pattern] Setting connections strategy to Clusters");
                connectionContext.setStrategy(new ConnectionClustering());
            } else if (ev.getSource() == mItemUserConnect) {
                Logger.getInstance().info("[Strategy Pattern] Setting connections strategy to User Connect");
                connectionContext.setStrategy(null);
            } else if (ev.getSource() == mItemCreate) {
                Logger.getInstance().info("[Strategy Pattern] Setting mouse action strategy to create city");
                drawArea.setMouseActionStrategy(new ActionCreateOperation(this));
            } else if (ev.getSource() == mItemMove) {
                Logger.getInstance().info("[Strategy Pattern] Setting mouse action strategy to move city");
                drawArea.setMouseActionStrategy(new ActionMoveOperation());
            } else if (ev.getSource() == mItemConnect) {
                Logger.getInstance().info("[Strategy Pattern] Setting mouse action strategy to connect city");
                drawArea.setMouseActionStrategy(new ActionConnectOperation());
            }
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
