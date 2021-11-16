import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

/**
 * A repository which contains the city list and route information. It extends the </tt>Observable</tt> class, so that
 * it can notify its <tt>Observer</tt> for updates i.e. when a new city is added or an existing city is moved.
 *
 * @author Zhuoran Li, Rishav Kumar
 * @version 1.0
 * @since 2021-10-02
 */
public class WorkSpace extends Observable {
    private final List<City> cityList = new ArrayList<>();
    private List<List<Route>> routeList = new ArrayList<>();

    private WorkSpace() { }

    public static WorkSpace _instance = null;
    public static synchronized WorkSpace getInstance() {
        if (_instance == null)
            _instance = new WorkSpace();
        return _instance;
    }

    /**
     * Add a new city and notify the observers.
     */
    public void addNewCity(City city) {
        cityList.add(city);
        setChanged();
        notifyObservers();
    }

    /**
     * Move an existing city to the new co-ordinates and notify the observers.
     */
    public void moveExistingCity(City city, int x, int y) {
        city.move(x, y);
        setChanged();
        notifyObservers();
    }

    /**
     * Clear all the cities and notify the observers.
     */
    public void clearAllCities() {
        cityList.clear();
        setChanged();
        notifyObservers();
    }

    public void addNewRoute(Route route) {
        routeList.get(0).add(route);
        setChanged();
        notifyObservers();
    }

    /**
     * Load a list of cities from a file and notify the observers.
     *
     * @param file handle to file containing data
     * @throws IOException  if the file does not exist, is a directory rather than a regular file, or for some other
     * reason cannot be opened for reading.
     */
    public void load(File file) throws IOException {
        cityList.clear();

        String fileText = readTextFile(file);
        String[] lineList = fileText.split("\n");

        for (String line : lineList) {
            String[] tokens = line.split(" ");

            String cityLabel = tokens[0];
            int cityPosX = Integer.parseInt(tokens[1]);
            int cityPosY = Integer.parseInt(tokens[2]);
            City city = CityFactory.getInstance().getCity(cityLabel, cityPosX, cityPosY);

            int citySize = City.DEFAULT_SIZE;
            if (tokens.length > 3)
                citySize = Integer.parseInt(tokens[3]);
            city.setDimension(new Dimension(citySize, citySize));

            Color cityColor = City.DEFAULT_COLOR;
            if (tokens.length > 4)
                cityColor = new Color(Integer.parseInt(tokens[4]));
            city.setColor(cityColor);

            if (tokens.length > 5) {
                String[] decorationTokenList = tokens[5].substring(1, tokens[5].length() - 2).split(",");
                if (decorationTokenList.length == 5) {
                    boolean[] options = new boolean[5];
                    for (int i = 0; i < decorationTokenList.length; i++)
                        options[i] = Boolean.parseBoolean(decorationTokenList[i]);
                    city.setOptions(options);
                }
            }
            cityList.add(city);
        }

        setChanged();
        notifyObservers();
    }

    /**
     * Save the list of cities to a file.
     *
     * @param file handle to the file where the data is to be saved
     * @throws IOException  if the named file exists but is a directory rather than a regular file, does not exist but
     * cannot be created, or cannot be opened for any other reason
     */
    public void save(File file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
        for (City city : cityList)
            writer.write(String.format("%s %d %d %d %d %s\n", city.getLabel(), city.getX(), city.getY(),
                    city.getDimension().height, city.getColor().getRGB(),
                    Arrays.toString(city.getOptions()).replaceAll("\\s+", "")));
        writer.close();
    }

    /**
     * Get the list of cities.
     * @return list of cities
     */
    public List<City> getCityList() {
        return cityList;
    }

    /**
     * Get the route information.
     * @return route information
     */
    public List<List<Route>> getRouteList() {
        return routeList;
    }

    /**
     * Set the route information.
     * @param routeList route information
     */
    public void setRouteList(List<List<Route>> routeList) {
        this.routeList = routeList;
    }

    private String readTextFile(File file) throws IOException {
        String lineText;
        StringBuilder fileTextStringBuilder = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((lineText = br.readLine()) != null) {
            fileTextStringBuilder.append(lineText).append("\n");
        }
        return fileTextStringBuilder.toString();
    }
}
