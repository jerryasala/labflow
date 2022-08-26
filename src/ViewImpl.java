import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This class implements the IView interface with all its mandated methods
 */
public class ViewImpl implements IView {
  private JFrame frame;
  private JTabbedPane tabs;
  private JPanel samplePane, testPane, equipmentPane, resultPane, sButtonPanel,
      tButtonPanel, eButtonPanel, rPanel, sPaneRight, sPaneCenter, sPaneLeft, tPaneRight,
      sPaneTopLeft, sPaneBottomLeft, ePaneLeft, ePaneRight, tPaneCenter, mainPane;
  private JButton sAddButton, tAddButton, eAddButton, sSortButton, tSortButton, eSortButton,
      sClearButton, tClearButton, eClearButton, sDeleteButton, sShowButton, tDeleteButton,
      tShowButton, sAddTestButton, eShowButton, eDeleteButton, autoButton;
  private JTextField sTextField1, sTextField2, tTextField1, tTextField2, eTextField1, eTextField2;
  private JTextArea ePaneTopRight, sPaneTopRight;
  private JScrollPane sScrollPaneTopLeft, tScrollPaneCenter, eScrollPaneLeft, sScrollPaneTopRight,
      sScrollPaneCenter, eScrollPaneTopRight;
  private JLabel sLabel1, sLabel2, tLabel1, tLabel2, eLabel1, eLabel2, rLabel1;
  private final String[] someTests = {"HBV", "HCV", "HTLV I", "HTLV II", "HBCORE", "HIV I",
                                      "HIV II", "DCA", "COVID I"};
  private final String[] someSamples = {"JANE DOE", "LUKE SKYWALKER", "TOM CRUISE", "CHRIS ROCK",
                                        "MARK MILLER", "MEL GIBSON", "LEO MESSI", "ALVARO MONGE"};
  private final String[] someEquipment = {"CENTRIFUGE", "INCUBATOR", "FREEZER", "FUME HOOD",
                                          "PRINTER", "SCALE", "PIPETTE", "MICROSCOPE",
                                          "WATER PURIFIER", "HOTPLATE", "SHAKER", "FURNACE",
                                          "OVEN", "AUTOCLAVE"};
  private final IController controller;

  /**
   * Constructs the view with the given controller
   * @param controller the controller
   */
  public ViewImpl(IController controller) {
    this.controller = controller;
  }

  /**
   * Creates the GUI
   */
  @Override
  public void viewCreateGUI() {
    frame = new JFrame("LabFlow");
    tabs = new JTabbedPane();

    // main panes
    mainPane = new JPanel(new BorderLayout());
    samplePane = new JPanel(new BorderLayout());
    testPane = new JPanel(new BorderLayout());
    equipmentPane = new JPanel(new BorderLayout());
    resultPane = new JPanel(new BorderLayout());

    mainPane.add(testPane, BorderLayout.LINE_START);
    mainPane.add(samplePane, BorderLayout.CENTER);
    mainPane.add(equipmentPane, BorderLayout.LINE_END);


    /* --------------------------------sample pane components-------------------------------- */

    // left, right, center panels
    sPaneRight = new JPanel(new BorderLayout());
    sPaneLeft = new JPanel(new BorderLayout());
    sPaneCenter = new JPanel();
    sPaneCenter.setBackground(Color.WHITE);
    sPaneCenter.setLayout(new BoxLayout(sPaneCenter, BoxLayout.PAGE_AXIS));
    sPaneCenter.setPreferredSize(new Dimension(400, 400));

    // upper left, bottom left, upper right panels/text area
    sPaneTopLeft = new JPanel();
    sPaneTopLeft.setBackground(Color.WHITE);
    sPaneTopLeft.setLayout(new BoxLayout(sPaneTopLeft, BoxLayout.PAGE_AXIS));
    sPaneBottomLeft = new JPanel();
    sPaneBottomLeft.setLayout(new BoxLayout(sPaneBottomLeft, BoxLayout.PAGE_AXIS));

    sPaneTopRight = new JTextArea(10, 10);
    sPaneTopRight.setEditable(false);
    sPaneTopRight.setFont(new Font(Font.DIALOG, Font.PLAIN, 17));
    sPaneTopRight.setBackground(Color.WHITE);

    // button panel
    sButtonPanel = new JPanel();

    // scroll panes for upper left, upper right
    sScrollPaneTopLeft = new JScrollPane(sPaneTopLeft);
    sScrollPaneTopLeft.setAutoscrolls(true);
    sScrollPaneTopRight = new JScrollPane(sPaneTopRight);
    sScrollPaneCenter = new JScrollPane(sPaneCenter);
    sScrollPaneCenter.setAutoscrolls(true);

    // buttons
    sAddButton = new JButton("add sample");
    sAddButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 13));
    sAddButton.setActionCommand("add sample");
    sAddButton.addActionListener(controller);
    sSortButton = new JButton("sort by ID number");
    sSortButton.setActionCommand("sort ID");
    sSortButton.addActionListener(controller);
    sClearButton = new JButton("clear samples");
    sClearButton.setActionCommand("clearSamples");
    sClearButton.addActionListener(controller);
    sDeleteButton = new JButton("delete sample");
    sDeleteButton.setActionCommand("deleteSample");
    sDeleteButton.addActionListener(controller);
    sShowButton = new JButton("show tests");
    sShowButton.setActionCommand("showTests");
    sShowButton.addActionListener(controller);
    sAddTestButton = new JButton("add tests to sample");
    sAddTestButton.setActionCommand("addTestsToSample");
    sAddTestButton.addActionListener(controller);
    tShowButton = new JButton("show samples");
    tShowButton.setActionCommand("showSamples");
    tShowButton.addActionListener(controller);

    // autogenerate button
    autoButton = new JButton("autogenerate");
    autoButton.setActionCommand("autogenerate");
    autoButton.addActionListener(controller);

    // labels
    sLabel1 = new JLabel("sample name:");
    sLabel2 = new JLabel("sample ID:");

    // text fields
    sTextField1 = new JTextField(10);
    sTextField2 = new JTextField(10);

    // add buttons, text fields, labels to button panel
    sButtonPanel.setLayout(new BoxLayout(sButtonPanel, BoxLayout.PAGE_AXIS));
    sButtonPanel.add(sLabel1); sButtonPanel.add(sTextField1); sButtonPanel.add(sLabel2);
    sButtonPanel.add(sTextField2); sButtonPanel.add(sAddButton); sButtonPanel.add(sSortButton);
    sButtonPanel.add(sDeleteButton); sButtonPanel.add(sClearButton); sButtonPanel.add(sShowButton);

    // add buttons, text fields, labels to bottom left (for tests)
    sPaneBottomLeft.add(sAddTestButton); sPaneBottomLeft.add(tShowButton);
    sPaneBottomLeft.add(autoButton);

    // add scrollable panes and panels to left pane, right pane
    sPaneRight.add(sScrollPaneTopRight, BorderLayout.CENTER);
    sPaneRight.add(sButtonPanel, BorderLayout.PAGE_END);
    sPaneLeft.add(sScrollPaneTopLeft, BorderLayout.CENTER);
    sPaneLeft.add(sPaneBottomLeft, BorderLayout.PAGE_END);

    // add left, right, center to sample pane
    samplePane.add(sScrollPaneCenter, BorderLayout.CENTER);
    samplePane.add(sPaneLeft, BorderLayout.LINE_START);
    samplePane.add(sPaneRight, BorderLayout.LINE_END);


    /* --------------------------------test pane components-------------------------------- */

    tPaneCenter = new JPanel();
    tPaneCenter.setBackground(Color.WHITE);
    tPaneCenter.setLayout(new BoxLayout(tPaneCenter, BoxLayout.PAGE_AXIS));
    tPaneRight = new JPanel(new BorderLayout());
    tButtonPanel = new JPanel();

    // scrollable panes for left, center
    tScrollPaneCenter = new JScrollPane(tPaneCenter);
    tScrollPaneCenter.setAutoscrolls(true);

    // buttons
    tAddButton = new JButton("add test");
    tAddButton.setActionCommand("add test");
    tAddButton.addActionListener(controller);
    tSortButton = new JButton("sort by duration");
    tSortButton.setActionCommand("sort duration");
    tSortButton.addActionListener(controller);
    tClearButton = new JButton("clear tests");
    tClearButton.setActionCommand("clearTests");
    tClearButton.addActionListener(controller);
    tDeleteButton = new JButton("delete test");
    tDeleteButton.setActionCommand("deleteTest");
    tDeleteButton.addActionListener(controller);

    // text fields
    tTextField1 = new JTextField(10);
    tTextField2 = new JTextField(10);

    // labels
    tLabel1 = new JLabel("test name:");
    tLabel2 = new JLabel("test duration:");

    // add buttons to button panel
    tButtonPanel.setLayout(new BoxLayout(tButtonPanel, BoxLayout.PAGE_AXIS));
    tButtonPanel.add(tLabel1); tButtonPanel.add(tTextField1); tButtonPanel.add(tLabel2);
    tButtonPanel.add(tTextField2); tButtonPanel.add(tAddButton); tButtonPanel.add(tDeleteButton);
    tButtonPanel.add(tSortButton); tButtonPanel.add(tClearButton);

    // add button panel to right section
    tPaneRight.add(tButtonPanel, BorderLayout.PAGE_START);

    // add left, right, center sections to test pane
    testPane.add(tScrollPaneCenter, BorderLayout.CENTER);
    //testPane.add(tScrollPaneLeft, BorderLayout.LINE_START);
    testPane.add(tPaneRight, BorderLayout.LINE_END);


    /* -------------------------------equipment pane components------------------------------- */

    // left, right sections
    ePaneLeft = new JPanel();
    ePaneLeft.setBackground(Color.WHITE);
    ePaneLeft.setLayout(new BoxLayout(ePaneLeft, BoxLayout.PAGE_AXIS));
    ePaneRight = new JPanel(new BorderLayout());
    ePaneTopRight = new JTextArea(10, 10);
    ePaneTopRight.setEditable(false);

    // button panel
    eButtonPanel = new JPanel();

    // scrollable panes for left and upper right sections
    eScrollPaneLeft = new JScrollPane(ePaneLeft);
    eScrollPaneTopRight = new JScrollPane(ePaneTopRight);

    // buttons
    eAddButton = new JButton("add equipment");
    eAddButton.setActionCommand("add equipment");
    eAddButton.addActionListener(controller);
    eDeleteButton = new JButton("delete equipment");
    eDeleteButton.setActionCommand("deleteEquipment");
    eDeleteButton.addActionListener(controller);
    eSortButton = new JButton("sort by next service due");
    eSortButton.setActionCommand("sort service");
    eSortButton.addActionListener(controller);
    eClearButton = new JButton("clear equipment");
    eClearButton.setActionCommand("clearEquipment");
    eClearButton.addActionListener(controller);
    eShowButton = new JButton("show info");
    eShowButton.setActionCommand("showInfo");
    eShowButton.addActionListener(controller);

    // text fields
    eTextField1 = new JTextField(10);
    eTextField2 = new JTextField(10);

    // labels
    eLabel1 = new JLabel("equipment name:");
    eLabel2 = new JLabel("service due in:");

    // add buttons to button panel
    eButtonPanel.setLayout(new BoxLayout(eButtonPanel, BoxLayout.PAGE_AXIS));
    eButtonPanel.add(eLabel1); eButtonPanel.add(eTextField1); eButtonPanel.add(eLabel2);
    eButtonPanel.add(eTextField2); eButtonPanel.add(eAddButton); eButtonPanel.add(eDeleteButton);
    eButtonPanel.add(eShowButton); eButtonPanel.add(eSortButton); eButtonPanel.add(eClearButton);

    // add components to right section
    ePaneRight.add(eScrollPaneTopRight, BorderLayout.CENTER);
    ePaneRight.add(eButtonPanel, BorderLayout.PAGE_END);

    // add left and right sections to main pane
    equipmentPane.add(eScrollPaneLeft, BorderLayout.CENTER);
    equipmentPane.add(ePaneRight, BorderLayout.LINE_END);


    /* -------------------------------result pane components------------------------------- */

    // main panel
    rPanel = new JPanel();

    // label
    rLabel1 = new JLabel("coming soon!");

    // add label to panel
    rPanel.add(rLabel1);

    // add panel to main pane
    resultPane.add(rPanel, BorderLayout.CENTER);

    // add all panels to tabs
    tabs.add("manage samples", mainPane);
    //tabs.add("view tests", testPane);
    //tabs.add("manage equipment", equipmentPane);
    //tabs.add("send results", resultPane);

    // add tabs to frame, pack, set visible
    frame.add(tabs, BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //frame.setSize(700, 400);
    frame.pack();
    frame.setVisible(true);

    this.viewAutogenerate();
  }

  /**
   * Returns an array with the sample name and sample ID
   * @return array with sample name and sample ID
   */
  @Override
  public String[] getSampleInfo() {
    return new String[]{sTextField1.getText(), sTextField2.getText()};
  }

  /**
   * Returns an array with the test name and test duration
   * @return array with test name and test duration
   */
  @Override
  public String[] getTestInfo() {
    return new String[]{tTextField1.getText(), tTextField2.getText()};
  }

  /**
   * Returns an array with the equipment name and equipment service urgency
   * @return array with equipment name and equipment service urgency
   */
  @Override
  public String[] getEquipmentInfo() {
    return new String[]{eTextField1.getText(), eTextField2.getText()};
  }

  /**
   * Creates a checkbox with the sample's name and adds it to the GUI
   */
  @Override
  public void viewAddSample() {

    // create checkbox, add item listener, display to GUI
    JCheckBox cb = new JCheckBox(sTextField1.getText().toUpperCase());
    cb.setFont(new Font(Font.SERIF, Font.PLAIN, 17));
    cb.addItemListener(controller);

    sPaneCenter.updateUI();
    sPaneCenter.add(cb);
    sTextField1.setText(null);
    sTextField2.setText(null);
  }

  /**
   * Takes a list of names of samples and returns an array of indices of samples
   * from the original list that have been selected on the GUI
   * @param listOfNamesOfSamples list of names of samples on the GUI
   * @return array of indices (integers) of selected samples
   */
  @Override
  public List<Integer> viewDeleteSample(List<String> listOfNamesOfSamples) {
    List<Integer> indices = new ArrayList<>();

    // for each comp selected, get name, loop through list, delete sample
    Component[] components = sPaneCenter.getComponents();
    for (Component component:components) {
      if (((JCheckBox) component).getModel().isSelected()) {

        // if it is selected, get its index in the list of names and append to 'indices'
        for (String sampleName : listOfNamesOfSamples) {
          if (Objects.equals(sampleName, ((JCheckBox) component).getText())) {
            indices.add(listOfNamesOfSamples.indexOf(sampleName));

            // remove from display
            sPaneCenter.remove(component);
            sPaneCenter.revalidate();
            sPaneCenter.repaint();
          }
        }
      }
    }
    return indices;
  }

  /**
   * Iterates through the tests on the GUI and returns a list of names of tests
   * that have been selected
   * @return list of names of tests that have been selected on the GUI
   */
  @Override
  public List<String> getSelectedTests() {
    List<String> tests = new ArrayList<>();

    // get components of the panel
    Component[] components = sPaneTopLeft.getComponents();
    for (Component component:components) {
      if (((JCheckBox) component).getModel().isSelected()) {

        // if it selected, add its text to the list of strings
        tests.add(((JCheckBox) component).getText());
      }
    }

    return tests;
  }

  /**
   * Return list of selected tests and deselect all the selected tests
   * @return list of names of tests that have been selected on the GUI
   */
  @Override
  public List<String> getTestToAddToSample() {

    // get selected checkboxes
    List<String > tests = this.getSelectedTests();

    // uncheck the checkboxes
    Component[] comps = sPaneTopLeft.getComponents();
    for (Component component:comps) {
      ((JCheckBox)component).setSelected(false);
    }
    sPaneTopLeft.revalidate();
    sPaneTopLeft.repaint();

    return tests;
  }

  /**
   * Iterates through the samples on the GUI and returns a list of names of samples
   * that have been selected
   * @return list of names of samples that have been selected on the GUI
   */
  @Override
  public List<String> getSelectedSamples() {
    List<String> samples = new ArrayList<>();

    // get components of the panel
    Component[] components = sPaneCenter.getComponents();
    for (Component component:components) {
      if (((JCheckBox) component).getModel().isSelected()) {

        // if it selected, add its text to the list of strings
        samples.add(((JCheckBox) component).getText());
      }
    }
    return samples;
  }

  /**
   * Takes a list of names of tests and displays the tests
   * @param tests list of tests to display
   */
  @Override
  public void viewShowTestsOfSample(List<String> tests) {
    sPaneTopRight.setText(null);

    // 'tests' is a copy of list of tests
    for (String test:tests) {
      sPaneTopRight.append(test + "\n");
      sPaneTopRight.revalidate();
      sPaneTopRight.repaint();
    }
  }

  /**
   * Creates a checkbox with the test's name and adds it to the GUI
   */
  @Override
  public void viewAddTest() {

    // create checkbox, add item listener, display to GUI
    JCheckBox cb = new JCheckBox(tTextField1.getText().toUpperCase());
    cb.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
    cb.setActionCommand("t");
    cb.addItemListener(controller);

    tPaneCenter.updateUI();
    tPaneCenter.add(cb);
    tTextField1.setText(null);
    tTextField2.setText(null);

    // after displaying to main test pane, display to the test area of the sample pane
    Component[] components = tPaneCenter.getComponents();
    Component comp = components[components.length-1];
    JCheckBox cbCopy = new JCheckBox(((JCheckBox)comp).getText());
    cbCopy.setFont(new Font(Font.SERIF, Font.PLAIN, 17));
    cbCopy.setActionCommand("t");
    cbCopy.addItemListener(controller);
    sPaneTopLeft.updateUI();
    sPaneTopLeft.add(cbCopy);
  }

  /**
   * Takes a list of names of test and returns an array of indices of tests
   * from the original list that have been selected on the GUI
   * @param listOfNamesOfTests list of names of tests on the GUI
   * @return array of indices (integers) of selected tests
   */
  @Override
  public List<Integer> viewDeleteTest(List<String> listOfNamesOfTests) {
    List<Integer> indices = new ArrayList<>();

    // get components of the panel
    Component[] components = tPaneCenter.getComponents();
    for (Component component:components) {

      // if it is selected, get its index in the list of names and append to 'indices'
      if (((JCheckBox) component).getModel().isSelected()) {
        for (String test : listOfNamesOfTests) {
          if (Objects.equals(test, ((JCheckBox) component).getText())) {
            indices.add(listOfNamesOfTests.indexOf(test));

            // remove from display
            tPaneCenter.remove(component);
            tPaneCenter.revalidate();
            tPaneCenter.repaint();
          }
        }
      }
    }

    // reflect changes on test display of sample pane
    sPaneTopLeft.removeAll();
    sPaneTopLeft.revalidate();
    sPaneTopLeft.repaint();

    Component[] comps = tPaneCenter.getComponents();
    for (Component component:comps) {
      JCheckBox cbCopy = new JCheckBox(((JCheckBox)component).getText());
      cbCopy.setFont(new Font(Font.DIALOG, Font.PLAIN, 17));
      cbCopy.setActionCommand("t");
      cbCopy.addItemListener(controller);
      sPaneTopLeft.updateUI();
      sPaneTopLeft.add(cbCopy);
    }

    return indices;
  }

  /**
   * Takes a list of names of samples and displays the samples
   * @param samples list of names of samples
   */
  @Override
  public void viewShowSamplesOfTest(List<String> samples){
    sPaneTopRight.setText(null);

    for (String sample : samples) {
      sPaneTopRight.append(sample + "\n");
      sPaneTopRight.revalidate();
      sPaneTopRight.repaint();
    }
  }

  /**
   * Creates a checkbox with the equipment's name and adds it to the GUI
   */
  @Override
  public void viewAddEquipment() {

    // create checkbox, add item listener, display to GUI
    JCheckBox cb = new JCheckBox(eTextField1.getText().toUpperCase());
    cb.setFont(new Font(Font.SERIF, Font.PLAIN, 17));
    cb.addItemListener(controller);

    ePaneLeft.updateUI();
    ePaneLeft.add(cb);
    eTextField1.setText(null);
    eTextField2.setText(null);
  }

  /**
   * Takes a list of names of equipment and returns an array of indices of equipment
   * from the original list that have been selected on the GUI
   * @param listOfNamesOfEquipment list of names of equipment on the GUI
   * @return array of indices (integers) of selected equipment
   */
  @Override
  public List<Integer> viewDeleteEquipment(List<String> listOfNamesOfEquipment) {
    List<Integer> indices = new ArrayList<>();

    // get components of the panel
    Component[] components = ePaneLeft.getComponents();
    for (Component component:components) {

      // if it is selected, get its index in the list of names and append to 'indices'
      if (((JCheckBox) component).getModel().isSelected()) {
        for (String equipment : listOfNamesOfEquipment) {
          if (Objects.equals(equipment, ((JCheckBox) component).getText())) {
            indices.add(listOfNamesOfEquipment.indexOf(equipment));

            // remove from display
            ePaneLeft.remove(component);
            ePaneLeft.revalidate();
            ePaneLeft.repaint();
          }
        }
      }
    }

    return indices;
  }

  /**
   * Returns a list of names of selected equipment
   * @return list of names of selected equipment
   */
  @Override
  public List<String> getSelectedEquipment() {
    List<String> equipment = new ArrayList<>();

    // get components, if selected, add text to the list
    Component[] components = ePaneLeft.getComponents();
    for (Component component : components) {
      if (((JCheckBox) component).getModel().isSelected()) {
        equipment.add(((JCheckBox) component).getText());
      }
    }

    return equipment;
  }

  /**
   * Clears the equipment info display area
   */
  @Override
  public void cleanUpEquipmentDisplay() {
    ePaneTopRight.setText(null);
  }

  /**
   * Takes an equipment's name and service urgency and displays to the GUI
   * @param equipmentName name of the equipment
   * @param serviceUrgency the number of days until next service is due
   */
  @Override
  public void showEquipmentInfo(String equipmentName, int serviceUrgency) {
    ePaneTopRight.append("Equipment: " + equipmentName + "\n");
    ePaneTopRight.append("Next service due in (days): " + serviceUrgency + "\n\n");
  }

  /**
   * Takes a list of names of current samples in sorted order and sorts
   * the checkboxes on the display in the same order
   * @param listOfNamesOfSamples list of names of sorted samples
   */
  @Override
  public void sortSampleByID(List<String> listOfNamesOfSamples) {

    // get components of the panel
    Component[] components = sPaneCenter.getComponents();

    // clear the display area
    sPaneCenter.removeAll();
    sPaneCenter.revalidate();
    sPaneCenter.repaint();

    // add back components in sorted order
    for (String sample: listOfNamesOfSamples) {
      for (Component component:components) {
        if (Objects.equals(((JCheckBox) component).getText(), sample)) {
          sPaneCenter.add(component);
          sPaneCenter.revalidate();
          sPaneCenter.repaint();
        }
      }
    }
  }

  /**
   * Takes a list of names of current tests in sorted order and sorts
   * the checkboxes on the display in the same order
   * @param listOfNamesOfTests list of names of sorted tests
   */
  @Override
  public void sortTestByDuration(List<String> listOfNamesOfTests) {

    // get components of the panel
    Component[] components = tPaneCenter.getComponents();

    // clear the display area
    tPaneCenter.removeAll();
    tPaneCenter.revalidate();
    tPaneCenter.repaint();

    // add back components in sorted order
    for (String test: listOfNamesOfTests) {
      for (Component component:components) {
        if (Objects.equals(((JCheckBox) component).getText(), test)) {
          tPaneCenter.add(component);
          tPaneCenter.revalidate();
          tPaneCenter.repaint();
        }
      }
    }
  }

  /**
   * Takes a list of names of current equipment in sorted order and sorts
   * the checkboxes on the display in the same order
   * @param listOfNamesOfEquipment list of names of sorted equipment
   */
  @Override
  public void sortEquipmentByService(List<String> listOfNamesOfEquipment) {

    // get components of the panel
    Component[] components = ePaneLeft.getComponents();

    // clear the display area
    ePaneLeft.removeAll();
    ePaneLeft.revalidate();
    ePaneLeft.repaint();

    // add back components in sorted order
    for (String equipment: listOfNamesOfEquipment) {
      for (Component component:components) {
        if (Objects.equals(((JCheckBox) component).getText(), equipment)) {
          ePaneLeft.add(component);
          ePaneLeft.revalidate();
          ePaneLeft.repaint();
        }
      }
    }
  }

  /**
   * Clears the sample display area
   */
  @Override
  public void viewClearSamples() {

    // clear display area
    sPaneCenter.removeAll();
    sPaneCenter.revalidate();
    sPaneCenter.repaint();

    sPaneTopRight.setText(null);
  }

  /**
   * Clears the test display area
   */
  @Override
  public void viewClearTests() {

    // clear both display areas
    tPaneCenter.removeAll();
    tPaneCenter.revalidate();
    tPaneCenter.repaint();

    sPaneTopLeft.removeAll();
    sPaneTopLeft.revalidate();
    sPaneTopLeft.repaint();
  }

  /**
   * Clears the equipment display area
   */
  @Override
  public void viewClearEquipment() {

    // clear display area
    ePaneTopRight.setText(null);
    ePaneLeft.removeAll();
    ePaneLeft.revalidate();
    ePaneLeft.repaint();
  }

  /**
   * Clears sample, test and equipment display areas, auto generates items
   * and displays items to the GUI
   */
  public void viewAutogenerate() {

    // *** view should NOT autogenerate numbers!!!
    // this is done for ease and will be refactored in a future extension

    controller.controllerClearSamples();
    controller.controllerClearTests();
    controller.controllerClearEquipment();

    // samples
    for (String sample : someSamples) {
      int ID = (int) (Math.random() * 100000);
      sTextField1.setText(sample);
      sTextField2.setText(String.valueOf(ID));
      controller.controllerAddSample();
    }

    // tests
    for (String test : someTests) {
      int duration = (int) (((Math.random() * 100) % 10) + 1);
      tTextField1.setText(test);
      tTextField2.setText(String.valueOf(duration));
      controller.controllerAddTest();
    }

    // equipment
    for (String equipment : someEquipment) {
      int service = (int) (((Math.random() * 100000) % 365));
      eTextField1.setText(equipment);
      eTextField2.setText(String.valueOf(service));
      controller.controllerAddEquipment();
    }
  }
}
