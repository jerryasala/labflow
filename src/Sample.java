import java.util.List;

/**
 * This interface represents a laboratory sample
 */
public interface Sample {

  /**
   * Returns the name of the sample
   * @return String the name of the sample
   */
  String getSampleName();

  /**
   * Returns the ID (a six digit integer) of this sample
   * @return Integer the ID of this sample
   */
  Integer getSampleID();

  /**
   * Returns the list of tests that have been requested for this sample
   * @return List of tests that have been requested for this sample
   */
  List<String> getListOfTests();

  /**
   * Adds a test to this sample by adding the test's name to the list of tests of this sample,
   * if and only if the sample does not already contain the test. When the test's name is added
   * to this sample, this sample's name is added to the test's list of samples.
   *
   * @param test the LabTest to add to this sample's list of tests. Specifically, the test's name
   *             will be added to the list
   */
  void addTestToSample(LabTest test);

  /**
   * Deletes a test to this sample by deleting the test's name to the list of tests
   * of this sample. When the test's name is deleted from this sample, this sample's name
   * is deleted from the test's list of samples.
   * @param test the LabTest to delete from this sample's list of tests. Specifically, the
   *             test's name will be deleted from the list
   */
  void deleteTestFromSample(LabTest test);

  /**
   * Compares this sample to another sample by ID
   * @param s the other sample to compare to this one
   * @return a negative integer, zero, or a positive integer if this sample's ID is
   * less than, equal to, or greater than the other sample's ID
   */
  int compareByID(Sample s);
}
