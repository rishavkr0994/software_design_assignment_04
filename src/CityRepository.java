import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

/**
 * This class is the repository for cities and provides functions to add, move and clear cities. Additionally, it also
 * provides functions to load / save the data from / to a file.
 * <p>
 * It extends <code>Observable</code> and notifies its observers whenever there is a change in the repository data. It
 * also provides an iterator for the list of cities contained within it.
 *
 * @author
 * @version 1.0
 * @since 2021-11-12
 */
public class CityRepository extends Observable implements Container {

    private final List<City> cityList = new ArrayList<>();

    private CityRepository() { }

    private static CityRepository _instance = null;

    /**
     * Gets the common instance of <code>CityRepository</code>.
     * <p>
     * NOTE: When this function is first invoked, a new instance is created and the same is returned. In future
     * invocations, the previously created instance is returned.
     *
     * @return instance of <code>CityRepository</code>
     */
    public static synchronized CityRepository getInstance() {
        if (_instance == null)
            _instance = new CityRepository();
        return _instance;
    }

    /**
     * Adds a new city and notifies the observers.
     *
     * @param city new city instance
     */
    public void addNewCity(City city) {
        cityList.add(city);
        setChanged();
        notifyObservers();
    }

    /**
     * Moves an existing city to the new co-ordinates and notifies the observers.
     *
     * @param city existing city instance
     * @param x new x-coordinate of the city
     * @param y new y-coordinate of the city
     */
    public void moveExistingCity(City city, int x, int y) {
        city.move(x, y);
        setChanged();
        notifyObservers();
    }

    /**
     * Clears all the cities and notifies the observers.
     */
    public void clearAllCities() {
        cityList.clear();
        RouteRepository.getInstance().getRouteList().clear();
        setChanged();
        notifyObservers();
    }

    /**
     * Loads a list of cities from a file and notifies the observers.
     *
     * @param file handle to the file containing data
     * @throws IOException if the file does not exist, is a directory rather than a regular file, or for some other
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
     * Saves the list of cities to a file.
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
     * Gets the list of cities.
     * @return list of cities
     */
    public List<City> getCityList() {
        return cityList;
    }

    /**
     * Gets an iterator for the list of cities contained in this repository.
     *
     * @return an iterator for the list of cities contained in this repository.
     */
    @Override
    public Iterator getIterator() {
        return new ObjectIterator<>(cityList);
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
