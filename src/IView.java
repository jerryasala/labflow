import java.util.List;

/**
 * This interface represents a view of the LIMS
 */
public interface IView {

  /**
   * Creates the GUI
   */
  void viewCreateGUI();

  /**
   * Returns an array with the sample name and sample ID
   *
   * @return array with sample name and sample ID
   */
  String[] getSampleInfo();

  /**
   * Returns an array with the test name and test duration
   *
   * @return array with test name and test duration
   */
  String[] getTestInfo();

  /**
   * Returns an array with the equipment name and equipment service urgency
   *
   * @return array with equipment name and equipment service urgency
   */
  String[] getEquipmentInfo();

  /**
   * Creates a checkbox with the sample's name and adds it to the GUI
   */
  void viewAddSample();

  /**
   * Takes a list of names of samples and returns an array of indices of samples from the original
   * list that have been selected on the GUI
   *
   * @param listOfNamesOfSamples list of names of samples on the GUI
   * @return array of indices (integers) of selected samples
   */
  List<Integer> viewDeleteSample(List<String> listOfNamesOfSamples);

  /**
   * Iterates through the tests on the GUI and returns a list of names of tests that have been
   * selected
   *
   * @return list of names of tests that have been selected on the GUI
   */
  List<String> getSelectedTests();

  /**
   * Return list of selected tests and deselect all the selected tests
   *
   * @return list of names of tests that have been selected on the GUI
   */
  List<String> getTestToAddToSample();

  /**
   * Iterates through the samples on the GUI and returns a list of names of samples that have been
   * selected
   *
   * @return list of names of samples that have been selected on the GUI
   */
  List<String> getSelectedSamples();

  /**
   * Takes a list of names of tests and displays the tests
   *
   * @param tests list of tests to display
   */
  void viewShowTestsOfSample(List<String> tests);

  /**
   * Creates a checkbox with the test's name and adds it to the GUI
   */
  void viewAddTest();

  /**
   * Takes a list of names of test and returns an array of indices of tests from the original list
   * that have been selected on the GUI
   *
   * @param listOfNamesOfTests list of names of tests on the GUI
   * @return array of indices (integers) of selected tests
   */
  List<Integer> viewDeleteTest(List<String> listOfNamesOfTests);

  /**
   * Takes a list of names of samples and displays the samples
   *
   * @param samples list of names of samples
   */
  void viewShowSamplesOfTest(List<String> samples);

  /**
   * Creates a checkbox with the equipment's name and adds it to the GUI
   */
  void viewAddEquipment();

  /**
   * Takes a list of names of equipment and returns an array of indices of equipment from the
   * original list that have been selected on the GUI
   *
   * @param listOfNamesOfEquipment list of names of equipment on the GUI
   * @return array of indices (integers) of selected equipment
   */
  List<Integer> viewDeleteEquipment(List<String> listOfNamesOfEquipment);

  /**
   * Returns a list of names of selected equipment
   *
   * @return list of names of selected equipment
   */
  List<String> getSelectedEquipment();

  /**
   * Clears the equipment info display area
   */
  void cleanUpEquipmentDisplay();

  /**
   * Takes an equipment's name and service urgency and displays to the GUI
   *
   * @param equipmentName  name of the equipment
   * @param serviceUrgency the number of days until next service is due
   */
  void showEquipmentInfo(String equipmentName, int serviceUrgency);

  /**
   * Takes a list of names of current samples in sorted order and sorts the checkboxes on the
   * display in the same order
   *
   * @param listOfNamesOfSamples list of names of sorted samples
   */
  void sortSampleByID(List<String> listOfNamesOfSamples);

  /**
   * Takes a list of names of current tests in sorted order and sorts the checkboxes on the display
   * in the same order
   *
   * @param listOfNamesOfTests list of names of sorted tests
   */
  void sortTestByDuration(List<String> listOfNamesOfTests);

  /**
   * Takes a list of names of current equipment in sorted order and sorts the checkboxes on the
   * display in the same order
   *
   * @param listOfNamesOfEquipment list of names of sorted equipment
   */
  void sortEquipmentByService(List<String> listOfNamesOfEquipment);

  /**
   * Clears the sample display area
   */
  void viewClearSamples();

  /**
   * Clears the test display area
   */
  void viewClearTests();

  /**
   * Clears the equipment display area
   */
  void viewClearEquipment();
}
