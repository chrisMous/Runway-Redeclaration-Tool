package uk.ac.soton.comp2211.group37.runwayTool.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class XMLParser {

    /**
     * File object of the XML file containing obstacle information
     */
    private final File obstacleFile;

    /**
     * File object of the XSD file which specifies the schema for the obstacles XML file
     */
    private final File obstacleSchemaFile;

    /**
     * File object of the XML file containing airport and runway information
     */
    private final File airportFile;

    /**
     * File object of the XSD file which specifies the schema for the airports XML file
     */
    private final File airportSchemaFile;


    public XMLParser(String obstacleFilePath, String obstacleSchemaFilePath, String airportFilePath,
                     String airportSchemaFile) {
        this.obstacleFile = new File(obstacleFilePath);
        this.obstacleSchemaFile = new File(obstacleSchemaFilePath);
        this.airportFile = new File(airportFilePath);
        this.airportSchemaFile = new File(airportSchemaFile);
    }

    /**
     * Validates that a given XML file conforms to a given schema
     * @param xml File object of an open XML file
     * @param xsd File object of an open XSD file
     * @return is valid
     */
    private boolean validateXML(File xml, File xsd) {
        SchemaFactory sf = SchemaFactory.newDefaultInstance();
        try {
            Schema schema = sf.newSchema(xsd);

            // If validation does not fail, the schema is valid
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xml));
            return true;

        } catch (SAXException | IOException e) {
            return false;
        }
    }

    /**
     * Parses the XML file containing obstacles into a list of Obstacles
     * @return ArrayList of Obstacle objects
     */
    private ArrayList<Obstacle> parseObstacles() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        Document obstacleDocument = null;
        try {
            // Read file into DOM parser
            DocumentBuilder db = dbf.newDocumentBuilder();
            obstacleDocument = db.parse(this.obstacleFile);

        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.err.println("Unable to read file with DOM parser");
            return null;
        }

        if (!validateXML(this.obstacleFile, this.obstacleSchemaFile)) {
            System.err.println("The Schema of the obstacle xml file is invalid");
            return null;
        }

        // This ArrayList will be populated with the parsed Obstacles
        ArrayList<Obstacle> obstacles = new ArrayList<>();

        Element root = obstacleDocument.getDocumentElement();
        NodeList obstructionCategories = root.getChildNodes();

        // for each category tag e.g. <Aircraft>
        for (int categoryIndex = 0; categoryIndex < obstructionCategories.getLength(); categoryIndex++) {

            Node categoryNode = obstructionCategories.item(categoryIndex);

            // Only parse children which are element nodes
            if (categoryNode.getNodeType() == Node.ELEMENT_NODE) {

                Element categoryElement = (Element) categoryNode;

                // Determine ObstacleType from node name
                Obstacle.ObstacleType obstacleType;
                switch (categoryNode.getNodeName()) {
                    case "Aircraft":
                        obstacleType = Obstacle.ObstacleType.AIRCRAFT;
                        break;
                    case "Vehicle":
                        obstacleType = Obstacle.ObstacleType.VEHICLE;
                        break;
                    case "Debris":
                        obstacleType = Obstacle.ObstacleType.DEBRIS;
                        break;
                    case "DebrisScatter":
                        obstacleType = Obstacle.ObstacleType.DEBRIS_SCATTER;
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + categoryNode.getNodeName());
                }


                // for each <obstruction> tag
                NodeList obstructionList = categoryElement.getElementsByTagName("Obstruction");
                for (int obstructionIndex = 0; obstructionIndex < obstructionList.getLength(); obstructionIndex++) {

                    Node obstructionNode = obstructionList.item(obstructionIndex);

                    if (obstructionNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element obstructionTags = (Element) obstructionNode;

                        // Extract the text content of each field
                        String obstructionName = obstructionTags.getElementsByTagName("name").item(0).getTextContent();
                        String obstructionLength = obstructionTags.getElementsByTagName("length").item(0).getTextContent();
                        String obstructionWidth = obstructionTags.getElementsByTagName("width").item(0).getTextContent();
                        String obstructionHeight = obstructionTags.getElementsByTagName("height").item(0).getTextContent();

                        // Instantiate new Obstacle with the numerical values parsed to double
                        Obstacle obstacle = new Obstacle(Double.parseDouble(obstructionLength),
                                Double.parseDouble(obstructionWidth),
                                Double.parseDouble(obstructionHeight),
                                obstructionName, obstacleType);

                        obstacles.add(obstacle);
                    }
                }
            }
        }
        return obstacles;
    }

    /**
     * Parses the XML file containing airport and runway info into a list of Airports
     * @return ArrayList of Airport objects
     */
    public ArrayList<Airport> parseAiports() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        Document airportDocument = null;
        try {
            // Read file into DOM parser
            DocumentBuilder db = dbf.newDocumentBuilder();
            airportDocument = db.parse(this.airportFile);

        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.err.println("Unable to read airport file with DOM parser");
            return null;
        }

        if (!validateXML(this.airportFile, this.airportSchemaFile)) {
            System.err.println("The Schema of the airport xml file is invalid");
            return null;
        }

        // This ArrayList will be populated with the parsed Obstacles
        ArrayList<Airport> airports = new ArrayList<>();

        Element root = airportDocument.getDocumentElement();

        NodeList airportTags = root.getElementsByTagName("Airport");

        // for each category tag e.g. <Airport>
        for (int airportIndex = 0; airportIndex < airportTags.getLength(); airportIndex++) {

            Node airportNode = airportTags.item(airportIndex);

            // Only parse children which are element nodes
            if (airportNode.getNodeType() == Node.ELEMENT_NODE) {

                Element airportElement = (Element) airportNode;

                // Extract the text content of each field
                String icao = airportElement.getElementsByTagName("ICAO").item(0).getTextContent();
                String airportName = airportElement.getElementsByTagName("name").item(0).getTextContent();

                Airport airport = new Airport(airportName, icao);
                airports.add(airport);

                NodeList runwayTags = airportElement.getElementsByTagName("Runway");

                // This map keeps track of which logical runways are on the same physical runway
                HashMap<String, ArrayList<LogicalRunway>> logicalRunways = new HashMap<>();

                // for each category tag e.g. <Runway>
                for (int runwayIndex = 0; runwayIndex < runwayTags.getLength(); runwayIndex++) {

                    Node runwayNode = runwayTags.item(runwayIndex);

                    // Only parse children which are element nodes
                    if (runwayNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element runwayElement = (Element) runwayNode;
                        String physicalRwyId = runwayElement.getAttribute("physicalRunway");

                        // Extract the text content of each field
                        String tora = runwayElement.getElementsByTagName("TORA").item(0).getTextContent();
                        String toda = runwayElement.getElementsByTagName("TODA").item(0).getTextContent();
                        String asda = runwayElement.getElementsByTagName("ASDA").item(0).getTextContent();
                        String lda = runwayElement.getElementsByTagName("LDA").item(0).getTextContent();
                        String displacement = runwayElement.getElementsByTagName("displaced").item(0).getTextContent();

                        // Parse designator for heading and position
                        String designator = runwayElement.getElementsByTagName("designator").item(0).getTextContent();
                        Pattern p = Pattern.compile("(\\d\\d)([LCR])");
                        Matcher matcher = p.matcher(designator);

                        int heading;
                        String position;
                        LogicalRunway.RunwayPosition runwayPosition;

                        if (matcher.matches()) {
                            heading = Integer.parseInt(matcher.group(1));
                            position = matcher.group(2);

                            // Assign position of runway from designator
                            switch (position) {
                                case "R":
                                    runwayPosition = LogicalRunway.RunwayPosition.RIGHT;
                                    break;
                                case "C":
                                    runwayPosition = LogicalRunway.RunwayPosition.CENTER;
                                    break;
                                case "L":
                                    runwayPosition = LogicalRunway.RunwayPosition.LEFT;
                                    break;
                                default:
                                    throw new IllegalStateException("Unexpected value: " + position);
                            }

                        } else {
                            throw new IllegalStateException("Unexpected value: " + designator);
                        }


                        LogicalRunway rwy = new LogicalRunway(Double.parseDouble(tora), Double.parseDouble(toda),
                                Double.parseDouble(asda), Double.parseDouble(lda), Double.parseDouble(displacement),
                                heading, runwayPosition);

                        // Add the LogicalRunway to the designated runway ArrayList, or create a new ArrayList
                        ArrayList<LogicalRunway> val = logicalRunways.get(physicalRwyId);
                        if (val == null) {
                            logicalRunways.put(physicalRwyId, new ArrayList<>(List.of(rwy)));
                        } else {
                            val.add(rwy);
                        }
                    }
                }
                // For each physical runway, instantiate PhysicalRunway and add to the Airport ArrayList
                for (String key : logicalRunways.keySet()) {
                    ArrayList<LogicalRunway> logicalRwysOnPhysicalRwy = logicalRunways.get(key);
                    PhysicalRunway prwy = new PhysicalRunway(logicalRwysOnPhysicalRwy.get(0), logicalRwysOnPhysicalRwy.get(1));
                    airport.addRunway(prwy);
                }
            }

        }

        return airports;
    }

    /**
     * Parses both obstacle and airport XML files to populate a ModelRecord which holds all information needed by the
     * runway redeclaration tool at startup
     * @return ModelRecord object which acts as a record for Obstacles and Airports
     */
    public ModelRecord parseXML() {
        ArrayList<Obstacle> obstacles = parseObstacles();
        ArrayList<Airport> airports = parseAiports();
        return new ModelRecord(airports, obstacles);
    }


    public static void main(String[] args) {
        XMLParser xmlp = new XMLParser("src/main/resources/obstacles.xml",
                                "src/main/resources/obstacles.xsd",
                                        "src/main/resources/airports.xml",
                                    "src/main/resources/airports.xsd");
        ModelRecord mr = xmlp.parseXML();
        System.out.println("Number of parsed airports: " + mr.getAirports().size());
        System.out.println("Number of parsed obstacles: " + mr.getObstacles().size());
    }
}
