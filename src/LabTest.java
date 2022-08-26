import java.util.List;

/**
 * This interface represents a laboratory test
 */
public interface LabTest {

  /**
   * Return the name of the test
   * @return String the name of the test
   */
  String getTestName();

  /**
   * Returns the duration of the test
   * @return Integer the duration of the test
   */
  Integer getTestDuration();

  /**
   * Adds a sample to this test by adding the sample's name to the list of samples
   * of this test. The sample's name is added to this test because this test was
   * requested to be run on the sample. The call to this method was made when this
   * test was added to the sample.
   * @param sample the Sample to add to this test's list of samples. Specifically, the
   *               sample's name will be added to the list
   */
  void addSampleToTest(Sample sample);

  /**
   * Deletes a sample from this test by deleting the sample's name from the list of samples
   * of this test. The sample's name is deleted from this test because this test was
   * deleted from the sample. The call to this method was made when this
   * test was deleted from the sample.
   * @param sample the Sample to delete from this test's list of samples. Specifically, the
   *               sample's name will be deleted from the list
   */
  void deleteSampleFromTest(Sample sample);

  /**
   * Returns a list of samples that require this test
   * @return List of samples that require this test
   */
  List<String> getListOfSamples();

  /**
   * Compares this lab test to the other lab test by the duration of the test
   * @param t the other test to be compared to this test
   * @return a negative integer, zero, or a positive integer if this test's
   * duration is less than, zero, or greater than the other test's duration
   */
  int compareByDuration(LabTest t);

  /**
   * Compares this lab test to the other lab test by the number of samples that
   * require the test
   * @param t the other test to be compared to this test
   * @return a negative integer, zero, or a positive integer if the number of samples
   * of this test is less than, zero, or greater than the number of samples of the
   * other test
   */
  int compareByNumberOfSamples(LabTest t);
}
