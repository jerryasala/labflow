import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class implements the IModel interface with all its mandated methods
 */
public class ModelImpl implements IModel {

  private final List<LabTest> tests;
  private final List<Equipment> equipment;
  private final List<Sample> samples;

  /**
   * Constructs the model and instantiates the lists of sample, test, equipment
   */
  public ModelImpl() {
    this.tests = new ArrayList<>();
    this.equipment = new ArrayList<>();
    this.samples = new ArrayList<>();
  }

  /**
   * Adds a sample to the IModel dashboard
   *
   * @param sample the sample to be added to the IModel dashboard
   */
  @Override
  public void addSample(Sample sample) {
    this.samples.add(sample);
  }

  /**
   * Deletes a sample from the IModel dashboard at the given index, and
   * deletes the sample from the tests that contain the sample
   *
   * @param index the index of the sample to be deleted
   * @throws IllegalArgumentException if the index is out of range
   */
  @Override
  public void deleteSample(int index) {
    if (index < 0 || index >= getListOfSamples().size()) {
      throw new IllegalArgumentException("Invalid index");
    }

    // next, delete the sample from the tests that contain the sample
    for (LabTest test : this.tests) {
      if (test.getListOfSamples().contains(this.samples.get(index).getSampleName())) {
        test.deleteSampleFromTest(this.samples.get(index));
      }
    }

    this.samples.remove(index);
  }

  /**
   * Adds a test to the IModel dashboard
   *
   * @param test the test to be added to the IModel dashboard
   */
  @Override
  public void addTest(LabTest test) {
    this.tests.add(test);
  }

  /**
   * Deletes a test from the IModel dashboard at the given index, and
   * deletes the test from the samples that require the test
   *
   * @param index the index of the test to be deleted
   * @throws IllegalArgumentException if the index is out of range
   */
  @Override
  public void deleteTest(int index) throws IllegalArgumentException {
    if (index < 0 || index >= getListOfTests().size()) {
      throw new IllegalArgumentException("Invalid index");
    }

    // next, delete the test from the samples that require the test
    for (Sample sample : this.samples) {
      if (sample.getListOfTests().contains(this.tests.get(index).getTestName())) {
        sample.deleteTestFromSample(this.tests.get(index));
      }
    }

    this.tests.remove(index);   // TODO: better to directly remove object?
  }

  /**
   * Adds equipment to the IModel dashboard
   *
   * @param equipment the equipment to be added to the IModel dashboard
   */
  @Override
  public void addEquipment(Equipment equipment) {
    this.equipment.add(equipment);
  }

  /**
   * Deletes equipment from the IModel dashboard at the given index
   *
   * @param index the index of the equipment to be deleted
   * @throws IllegalArgumentException if the index is out of range
   */
  @Override
  public void deleteEquipment(int index) throws IllegalArgumentException{
    if (index < 0 || index >= getListOfEquipment().size()) {
      throw new IllegalArgumentException("Invalid index");
    }

    this.equipment.remove(index);   // TODO: better to directly remove object?
  }

  /**
   * Returns the current list of samples in the IModel dashboard
   *
   * @return list of samples in the IModel dashboard
   */
  @Override
  public List<Sample> getListOfSamples() {
    return this.samples;
  }

  /**
   * Returns the current list of equipment in the IModel dashboard
   *
   * @return list of equipment in the IModel dashboard
   */
  @Override
  public List<Equipment> getListOfEquipment() {
    return this.equipment;
  }

  /**
   * Returns the current list of tests in the IModel dashboard
   *
   * @return list of tests in the IModel dashboard
   */
  @Override
  public List<LabTest> getListOfTests() {
    return this.tests;
  }

  /**
   * Sorts the list of samples in the IModel dashboard by ID
   */
  @Override
  public void sortSamplesByID() {
    this.samples.sort(Sample::compareByID);
  }

  /**
   * Sorts the list of equipment in the IModel dashboard by remaining number of days
   * until service is due
   */
  @Override
  public void sortEquipmentByServiceUrgency() {
    this.equipment.sort(Equipment::compareByServiceDue);
  }

  /**
   * Sorts the list of tests in the IModel dashboard by one of two enumerated types; DURATION or
   * NUMBER_OF_SAMPLES. Sorting by DURATION will use the duration of each test in the list of tests,
   * while sorting by NUMBER_OF_SAMPLES will use the number of samples in the lust of samples of
   * each test
   *
   * @param priority one of two enumerated types; DURATION or NUMBER_OF_SAMPLES
   */
  @Override
  public void sortTestsByPriority(TestPriority priority) {
    if (priority == TestPriority.DURATION) {
      this.tests.sort(LabTest::compareByDuration);
    } else {
      this.tests.sort(LabTest::compareByNumberOfSamples);
    }
  }

  /**
   * Takes the name of a sample and the name of a test and adds the test to the sample
   *
   * @param sampleName name of the sample to add a test to
   * @param testName   name of test to be added to the sample
   */
  @Override
  public void addTestToSample(String sampleName, String testName) {

    // find the desired sample and test, and add test to sample
    for (Sample sample : this.getListOfSamples()) {
      if (Objects.equals(sample.getSampleName(), sampleName)) {
        for (LabTest test : this.getListOfTests()) {
          if (Objects.equals(test.getTestName(), testName)) {
            sample.addTestToSample(test);
          }
        }
      }
    }
  }

  /**
   * Takes the name of a sample and returns a copy of list of the names of test for the sample
   *
   * @param sampleName the name of the sample of interest
   * @return List of names of tests requested for the sample
   */
  @Override
  public List<String> getTestsForSample(String sampleName) {
    List<String> tests = new ArrayList<>();

    // find the desired sample and add its tests to the new list
    for (Sample sample : this.getListOfSamples()) {
      if (Objects.equals(sample.getSampleName(), sampleName)) {
        tests.addAll(sample.getListOfTests());
      }
    }
    return tests;
  }

  /**
   * Takes the name of a test and returns a copy of list of the names of samples for the test
   *
   * @param testName the name of the sample of interest
   * @return List of names of samples that require this test
   */
  @Override
  public List<String> getSamplesForTest(String testName) {
    List<String> samples = new ArrayList<>();

    // find the desired test and add its samples to the new list
    for (LabTest test : this.getListOfTests()) {
      if (Objects.equals(test.getTestName(), testName)) {
        samples.addAll(test.getListOfSamples());
      }
    }

    return samples;
  }

  /**
   * Takes the name of an equipment and returns its service urgency
   * @param equipmentName names of equipment
   * @return number of days until next service is due
   */
  public int getEquipmentInfo(String equipmentName) {
    int serviceUrgency = 0;
    for (Equipment equipment : this.getListOfEquipment()) {
      if (Objects.equals(equipment.getEquipmentName(), equipmentName)) {
        serviceUrgency = equipment.getServiceUrgency();
      }
    }

    return serviceUrgency;
  }

  // For future refactoring: use method reference/predicate for the following 3 methods
  /**
   * Returns a list of names of the samples in the exact order of the list of samples
   * @return List of names of samples
   */
  public List<String> getListOfNamesOfSamples() {
    List<String> namesOfSamples = new ArrayList<>();
    for (Sample sample : this.getListOfSamples()) {
      namesOfSamples.add(sample.getSampleName());
    }

    return namesOfSamples;
  }

  /**
   * Returns a list of names of the tests in the exact order of the list of tests
   *
   * @return List of names of tests
   */
  @Override
  public List<String> getListOfNamesOfTests() {
    List<String> namesOfTests = new ArrayList<>();
    for (LabTest test : this.getListOfTests()) {
      namesOfTests.add(test.getTestName());
    }

    return namesOfTests;
  }

  /**
   * Returns a list of names of the equipment in the exact order of the list of equipment
   *
   * @return List of names of equipment
   */
  @Override
  public List<String> getListOfNamesOfEquipment() {
    List<String> namesOfEquipment = new ArrayList<>();
    for (Equipment equipment : this.getListOfEquipment()) {
      namesOfEquipment.add(equipment.getEquipmentName());
    }

    return namesOfEquipment;
  }

  // For future refactoring: use method reference/predicate for the following 2 methods
  /**
   * Deletes all samples from the list of samples
   */
  @Override
  public void clearSamples() {
    this.samples.clear();
    for (LabTest test : this.tests) {
      test.getListOfSamples().clear();
    }
  }

  /**
   * Deletes all tests from the list of samples
   */
  @Override
  public void clearTests() {
    this.tests.clear();
    for (Sample sample : this.samples) {
      sample.getListOfTests().clear();
    }
  }

  /**
   * Deletes all equipment from the list of samples
   */
  @Override
  public void clearEquipment() {
    this.equipment.clear();
  }
}
