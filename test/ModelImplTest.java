import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This is a JUnit test for a Laboratory Information Management
 * System model (LIMS Model)
 */
public class ModelImplTest {
  IModel Model1;
  Sample sample1, sample2, sample3;
  Equipment equipment1, equipment2;
  LabTest test1, test2, test3;

  /**
   * Instantiates a model, some samples, tests and equipment
   * @throws Exception is an error occurs
   */
  @Before
  public void setUp() throws Exception {

    // instantiate a IModel
    Model1 = new ModelImpl();

    // instantiate a sample
    sample1 = new SampleImpl("Jane Doe", 3456);
    sample2 = new SampleImpl("John Doe", 1256);
    sample3 = new SampleImpl("Jack Black", 9212);


    // instantiate equipment
    equipment1 = new EquipmentImpl("Grifols Panther", 20);
    equipment2 = new EquipmentImpl("Incubator", 10);

    // instantiate a test
    test1 = new LabTestImpl("HCV Test", 4);
    test2 = new LabTestImpl("HTLV Test", 3);
    test3 = new LabTestImpl("HBV Test", 5);

    // add test to a sample
    sample1.addTestToSample(test1);
    sample1.addTestToSample(test2);
    sample2.addTestToSample(test2);
  }

  /**
   * Tests whether the addSample() method works properly
   */
  @Test
  public void testAddSample() {
    Model1.addSample(sample1);

    assertEquals("[Jane Doe]", Model1.getListOfSamples().toString());
  }

  /**
   * Tests whether the deleteSample() method works properly
   */
  @Test
  public void testDeleteSample() {
    Model1.addSample(sample1);
    Model1.deleteSample(0);

    assertEquals("[]", Model1.getListOfSamples().toString());
  }

  /**
   * Tests deleting a sample at the wrong index. It is expected to
   * throw an exception
   */
  @Test (expected = IllegalArgumentException.class)
  public void testIllegalDeleteSample() {
    Model1.addSample(sample1);
    Model1.deleteSample(1);
  }

  /**
   * Tests whether the addEquipment() method works properly
   */
  @Test
  public void testAddEquipment() {
    Model1.addEquipment(equipment1);

    assertEquals("[Grifols Panther]", Model1.getListOfEquipment().toString());
    // test with size
  }

  /**
   * Tests whether the deleteEquipment() method works properly
   */
  @Test
  public void testDeleteEquipment() {
    Model1.addEquipment(equipment1);
    Model1.deleteEquipment(0);

    assertEquals("[]", Model1.getListOfEquipment().toString());
  }

  /**
   * Tests deleting  equipment at the wrong index. It is expected to
   * throw an exception
   */
  @Test (expected = IllegalArgumentException.class)
  public void testIllegalDeleteEquipment() {
    Model1.addEquipment(equipment1);
    Model1.deleteEquipment(1);
  }

  /**
   * Tests whether the addTest() method works properly
   */
  @Test
  public void testAddTest() {
    Model1.addTest(test1);

    assertEquals("[HCV Test]", Model1.getListOfTests().toString());
  }

  /**
   * Tests whether the deleteTest() method works properly
   */
  @Test
  public void testDeleteTest() {
    Model1.addTest(test1);
    Model1.deleteTest(0);

    assertEquals("[]", Model1.getListOfTests().toString());
  }

  /**
   * Tests deleting a test at the wrong index. It is expected to
   * throw an exception
   */
  @Test (expected = IllegalArgumentException.class)
  public void testIllegalDeleteTest() {
    Model1.addTest(test1);
    Model1.deleteTest(1);
  }

  /**
   * Tests whether the getListOfSamples() method works properly
   */
  @Test
  public void testGetListOfSamples() {
    Model1.addSample(sample1);

    assertEquals("[Jane Doe]", Model1.getListOfSamples().toString());
  }

  /**
   * Tests whether the getListOfEquipment() method works properly
   */
  @Test
  public void testGetListOfEquipment() {
    Model1.addEquipment(equipment1);

    assertEquals("[Grifols Panther]", Model1.getListOfEquipment().toString());
  }

  /**
   * Tests whether the getListOfTests() method works properly
   */
  @Test
  public void testGetListOfTests() {
    Model1.addTest(test1);

    assertEquals("[HCV Test]", Model1.getListOfTests().toString());
  }

  /**
   * Tests whether the sortSamplesByID() method works properly
   */
  @Test
  public void testSortSamplesByID() {
    Model1.addSample(sample1);
    Model1.addSample(sample2);
    Model1.sortSamplesByID();

    assertEquals("[John Doe, Jane Doe]", Model1.getListOfSamples().toString());
  }

  /**
   * Tests whether the sortEquipmentByServiceUrgency() method works properly
   */
  @Test
  public void testSortEquipmentByServiceUrgency() {
    Model1.addEquipment(equipment1);
    Model1.addEquipment(equipment2);
    Model1.sortEquipmentByServiceUrgency();

    assertEquals("[Incubator, Grifols Panther]", Model1.getListOfEquipment().toString());
  }

  /**
   * Tests whether the sortSortTestsByPriority() method works properly
   */
  @Test
  public void testSortTestsByPriority() {
    Model1.addTest(test1);
    Model1.addTest(test2);

    // by duration (HCV 4hrs, HTLV 3hrs)
    Model1.sortTestsByPriority(TestPriority.DURATION);
    assertEquals("[HTLV Test, HCV Test]", Model1.getListOfTests().toString());

    // by number of sample (HTLV Test has two samples, HCV has one)
    Model1.sortTestsByPriority(TestPriority.NUMBER_OF_SAMPLES);
    assertEquals("[HCV Test, HTLV Test]", Model1.getListOfTests().toString());
  }

  /**
   * Tests whether the addTestToSample() method works properly
   */
  @Test
  public void testAddTestToSample() {

    // first add sample3 and test3 to the dashboard
    Model1.addSample(sample3);
    Model1.addTest(test3);
    Model1.addTestToSample(sample3.getSampleName(), test3.getTestName());
    assertEquals("[HBV Test]", sample3.getListOfTests().toString());
  }

  /**
   * Tests whether the getTestsForSample() method works properly
   */
  @Test
  public void testGetTestsForSample() {

    // tests were added to the samples in the setup() method
    // now, add samples and tests to dashboard, then
    // check tests for each sample
    Model1.addSample(sample1);
    Model1.addSample(sample2);
    Model1.addTest(test1);
    Model1.addTest(test2);

    assertEquals("[HCV Test, HTLV Test]",
                  Model1.getTestsForSample(sample1.getSampleName()).toString());
    assertEquals("[HTLV Test]",
                  Model1.getTestsForSample(sample2.getSampleName()).toString());

    // Note:
    // In the above test implementation, tests were added to the samples in the setup() method
    // using the sample's "addTestToSample()" method. To see that the model's "addTestToSample()"
    // method also works, refer to "@test: testAddTestToSample()"
  }

  /**
   * Tests whether the getSamplesForTest() method works properly
   */
  @Test
  public void testGetSamplesForTest() {

    // when tests were added to the samples in the setup() method,
    // names of samples were added to each test
    // so, first add samples and tests to the dashboard,
    // then check name of samples for each test
    Model1.addSample(sample1);
    Model1.addSample(sample2);
    Model1.addTest(test1);
    Model1.addTest(test2);

    assertEquals("[Jane Doe]",
                  Model1.getSamplesForTest(test1.getTestName()).toString());
    assertEquals("[Jane Doe, John Doe]",
                  Model1.getSamplesForTest(test2.getTestName()).toString());
  }

  /**
   * Tests whether the getEquipmentInfo() method works properly
   */
  @Test
  public void testGetEquipmentInfo() {
    Model1.addEquipment(equipment2);  // equipment2 = Incubator
    assertEquals(10, Model1.getEquipmentInfo("Incubator"));
  }

  /**
   * Tests whether the getListOfNamesOfSamples() method works properly
   */
  @Test
  public void testGetListOfNamesOfSamples() {
    Model1.addSample(sample1);
    Model1.addSample(sample3);
    assertEquals("[Jane Doe, Jack Black]", Model1.getListOfNamesOfSamples().toString());
  }

  /**
   * Tests whether the getListOfNamesOfTests() method works properly
   */
  @Test
  public void testGetListOfNamesOfTests() {
    Model1.addTest(test2);
    Model1.addTest(test3);
    assertEquals("[HTLV Test, HBV Test]", Model1.getListOfNamesOfTests().toString());
  }

  /**
   * Tests whether the getListOfNamesOfEquipment() method works properly
   */
  @Test
  public void testGetListOfNamesOfEquipment() {
    Model1.addEquipment(equipment2);
    Model1.addEquipment(equipment1);
    assertEquals("[Incubator, Grifols Panther]",
                  Model1.getListOfNamesOfEquipment().toString());
  }

  /**
   * Tests whether the clearSamples() method works properly
   */
  @Test
  public void testClearSamples() {

    // add samples and check that they are added
    Model1.addSample(sample1);
    Model1.addSample(sample3);
    assertEquals("[Jane Doe, Jack Black]", Model1.getListOfNamesOfSamples().toString());

    // then clear samples and check
    Model1.clearSamples();
    assertEquals("[]", Model1.getListOfNamesOfSamples().toString());
  }

  /**
   * Tests whether the clearTests() method works properly
   */
  @Test
  public void testClearTests() {

    // add tests and check that they are added
    Model1.addTest(test2);
    Model1.addTest(test3);
    assertEquals("[HTLV Test, HBV Test]", Model1.getListOfNamesOfTests().toString());

    // then clear tests and check
    Model1.clearTests();
    assertEquals("[]", Model1.getListOfNamesOfTests().toString());
  }

  /**
   * Tests whether the clearEquipment() method works properly
   */
  @Test
  public void testClearEquipment() {

    // add equipment and check that they are added
    Model1.addEquipment(equipment2);
    Model1.addEquipment(equipment1);
    assertEquals("[Incubator, Grifols Panther]",
                  Model1.getListOfNamesOfEquipment().toString());

    // then clear equipment and check
    Model1.clearEquipment();
    assertEquals("[]", Model1.getListOfNamesOfEquipment().toString());
  }
}
