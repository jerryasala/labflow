import java.util.List;

/**
 * This interface represents the model of a Laboratory Information Management
 * System (LIMS)
 */
public interface IModel {

  /**
   * Adds a sample to the IModel dashboard
   *
   * @param sample the sample to be added to the IModel dashboard
   */
  void addSample(Sample sample);

  /**
   * Deletes a sample from the IModel dashboard at the given index, and
   * deletes the sample from the tests that contain the sample
   *
   * @param index the index of the sample to be deleted
   * @throws IllegalArgumentException if the index is out of range
   */
  void deleteSample(int index);

  /**
   * Adds equipment to the IModel dashboard
   *
   * @param equipment the equipment to be added to the IModel dashboard
   */
  void addEquipment(Equipment equipment);

  /**
   * Deletes equipment from the IModel dashboard at the given index
   *
   * @param index the index of the equipment to be deleted
   * @throws IllegalArgumentException if the index is out of range
   */
  void deleteEquipment(int index);

  /**
   * Adds a test to the IModel dashboard
   * @param test the test to be added to the IModel dashboard
   */
  void addTest(LabTest test);

  /**
   * Deletes a test from the IModel dashboard at the given index, and
   * deletes the test from the samples that require the test
   *
   * @param index the index of the test to be deleted
   * @throws IllegalArgumentException if the index is out of range
   */
  void deleteTest(int index) throws IllegalArgumentException;

  /**
   * Returns the current list of samples in the IModel dashboard
   * @return list of samples in the IModel dashboard
   */
  List<Sample> getListOfSamples();

  /**
   * Returns the current list of equipment in the IModel dashboard
   * @return list of equipment in the IModel dashboard
   */
  List<Equipment> getListOfEquipment();

  /**
   * Returns the current list of tests in the IModel dashboard
   * @return list of tests in the IModel dashboard
   */
  List<LabTest> getListOfTests();

  /**
   * Sorts the list of samples in the IModel dashboard by ID
   */
  void sortSamplesByID();

  /**
   * Sorts the list of equipment in the IModel dashboard by remaining number
   * of days until service is due
   */
  void sortEquipmentByServiceUrgency();

  /**
   * Sorts the list of tests in the IModel dashboard by one of two enumerated
   * types; DURATION or NUMBER_OF_SAMPLES. Sorting by DURATION will use the
   * duration of each test in the list of tests, while sorting by NUMBER_OF_SAMPLES
   * will use the number of samples in the lust of samples of each test
   */
  void sortTestsByPriority(TestPriority priority);

  /**
   * Takes the name of a sample and the name of a test and adds the test to the sample
   * @param sampleName name of the sample to add a test to
   * @param testName name of test to be added to the sample
   */
  void addTestToSample(String sampleName, String testName);

  /**
   * Takes the name of a sample and returns a list of the names of test for the sample
   * @param sampleName the name of the sample of interest
   * @return List of names of tests requested for the sample
   */
  List<String> getTestsForSample(String sampleName);

  /**
   * Takes the name of a test and returns a list of the names of samples for the test
   * @param testName the name of the sample of interest
   * @return List of names of samples that require this test
   */
  List<String> getSamplesForTest(String testName);

  /**
   * Takes the name of an equipment and returns its service urgency
   * @param equipmentName names of equipment
   * @return number of days until next service is due
   */
  int getEquipmentInfo(String equipmentName);

  // For future refactoring: use method reference/predicate for the following 3 methods
  /**
   * Returns a list of names of the samples in the exact order of the list of samples
   * @return List of names of samples
   */
  List<String> getListOfNamesOfSamples();

  /**
   * Returns a list of names of the tests in the exact order of the list of tests
   * @return List of names of tests
   */
  List<String> getListOfNamesOfTests();

  /**
   * Returns a list of names of the equipment in the exact order of the list of equipment
   * @return List of names of equipment
   */
  List<String> getListOfNamesOfEquipment();

  // For future refactoring: use method reference/predicate for the following 2 methods
  /**
   * Deletes all samples from the list of samples
   */
  void clearSamples();

  /**
   * Deletes all tests from the list of samples
   */
  void clearTests();

  /**
   * Deletes all equipment from the list of samples
   */
  void clearEquipment();
}
