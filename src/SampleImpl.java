import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class implements the Sample interface with all it's mandated methods
 */
public class SampleImpl implements Sample {
  private final String name;
  private final Integer ID;
  private final List<String> listOfTests;

  /**
   * Constructs a sample with the patient's name and identification number
   * @param name name of the sample (patient's name)
   * @param ID sample's identification number
   */
  public SampleImpl(String name, Integer ID) {
    this.name = name;
    this.ID = ID;
    this.listOfTests = new ArrayList<>();
  }

  /**
   * Returns the name of the sample
   *
   * @return String the name of the sample
   */
  @Override
  public String getSampleName() {
    return this.name;
  }

  /**
   * Returns the ID (a six digit integer) of this sample
   *
   * @return Integer the ID of this sample
   */
  @Override
  public Integer getSampleID() {
    return this.ID;
  }

  /**
   * Adds a test to this sample by adding the test's name to the list of tests of this sample,
   * if and only if the sample does not already contain the test. When the test's name is added
   * to this sample, this sample's name is added to the test's list of samples.
   *
   * @param test the LabTest to add to this sample's list of tests. Specifically, the test's name
   *             will be added to the list
   */
  @Override
  public void addTestToSample(LabTest test) {
    Objects.requireNonNull(test);
    if (this.listOfTests.contains(test.getTestName())) {
      return;
    }
    listOfTests.add(test.getTestName());
    test.addSampleToTest(this);
  }

  /**
   * Deletes a test to this sample by deleting the test's name to the list of tests of this sample.
   * When the test's name is deleted from this sample, this sample's name is deleted from the test's
   * list of samples.
   *
   * @param test the LabTest to delete from this sample's list of tests. Specifically, the test's
   *             name will be deleted from the list
   */
  @Override
  public void deleteTestFromSample(LabTest test) {
    Objects.requireNonNull(test);
    listOfTests.remove(test.getTestName());
    test.deleteSampleFromTest(this);
  }

  /**
   * Compare this sample to another sample by ID
   *
   * @param s the other sample to compare to this one
   * @return a negative integer, zero, or a positive integer if this sample's ID is less than, equal
   * to, or greater than the other sample's ID
   */
  @Override
  public int compareByID(Sample s) {
    Objects.requireNonNull(s);
    return this.getSampleID().compareTo(s.getSampleID());
  }

  /**
   * Returns the list of tests that have been requested for this sample
   *
   * @return List of tests that have been requested for this sample
   */
  @Override
  public List<String> getListOfTests() {
    return this.listOfTests;
  }

  /**
   * Returns a string representing this sample's name
   * @return String representing this sample's name
   */
  public String toString() {
    return this.getSampleName();
  }
}
