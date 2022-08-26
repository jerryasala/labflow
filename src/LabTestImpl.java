import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class implements the LabTest interface with all its mandated methods
 */
public class LabTestImpl implements LabTest {

  private final String name;
  private final Integer duration;
  private final List<String> listOfSamples;

  /**
   * Constructs a lab test with the given name and duration of the test
   * @param name name of the test
   * @param duration duration of the test
   */
  public LabTestImpl(String name, Integer duration) {
    this.name = name;
    this.duration = duration;
    this.listOfSamples = new ArrayList<>();
  }

  /**
   * Return the name of the test
   *
   * @return String the name of the test
   */
  @Override
  public String getTestName() {
    return this.name;
  }

  /**
   * Returns the duration of the test
   *
   * @return int the duration of the test
   */
  @Override
  public Integer getTestDuration() {
    return this.duration;
  }

  /**
   * Adds a sample to this test by adding the sample's name to the list of samples of this test. The
   * sample's name is added to this test because this test was requested to be run on the sample.
   * The call to this method was made when this test was added to the sample.
   *
   * @param sample the Sample to add to this test's list of samples. Specifically, the sample's name
   *               will be added to the list
   */
  @Override
  public void addSampleToTest(Sample sample) {
    Objects.requireNonNull(sample);
    listOfSamples.add(sample.getSampleName());
  }

  /**
   * Deletes a sample from this test by deleting the sample's name from the list of samples of this
   * test. The sample's name is deleted from this test because this test was deleted from the
   * sample. The call to this method was made when this test was deleted from the sample.
   *
   * @param sample the Sample to delete from this test's list of samples. Specifically, the sample's
   *               name will be deleted from the list
   */
  @Override
  public void deleteSampleFromTest(Sample sample) {
    Objects.requireNonNull(sample);
    listOfSamples.remove(sample.getSampleName());
  }

  /**
   * Returns a list of samples that require this test
   *
   * @return List of samples that require this test
   */
  @Override
  public List<String> getListOfSamples() {
    return listOfSamples;
  }

  /**
   * Compares this lab test to the other lab test by the duration of the test
   *
   * @param t the other test to be compared to this test
   * @return a negative integer, zero, or a positive integer if this test's duration is less than,
   * zero, or greater than the other test's duration
   */
  @Override
  public int compareByDuration(LabTest t) {
    Objects.requireNonNull(t);
    return this.getTestDuration().compareTo(t.getTestDuration());
  }

  /**
   * Compares this lab test to the other lab test by the number of samples that require the test
   *
   * @param t the other test to be compared to this test
   * @return a negative integer, zero, or a positive integer if the number of samples of this test
   * is less than, zero, or greater than the number of samples of the other test
   */
  @Override
  public int compareByNumberOfSamples(LabTest t) {
    Objects.requireNonNull(t);
    return Integer.compare(this.listOfSamples.size(), t.getListOfSamples().size());
  }

  /**
   * Returns a string representing this test's name
   * @return String representing this test's name
   */
  public String toString() {
    return this.getTestName();
  }
}
